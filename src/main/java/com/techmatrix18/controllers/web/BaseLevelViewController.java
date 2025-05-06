package com.techmatrix18.controllers.web;

import com.techmatrix18.dto.BaseLevelDto;
import com.techmatrix18.model.Base;
import com.techmatrix18.model.BaseLevel;
import com.techmatrix18.services.BaseLevelService;
import com.techmatrix18.services.BaseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class BaseLevelViewController {

    Logger logger = Logger.getLogger(BaseViewController.class.getName());

    private final BaseService baseService;
    private final BaseLevelService baseLevelService;

    private final List<String> niveles = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");

    public BaseLevelViewController(BaseService baseService, BaseLevelService baseLevelService) {
        this.baseService = baseService;
        this.baseLevelService = baseLevelService;
    }

    @GetMapping("/admin/base-levels/")
    public String getAllBaseLevels(Model model) {
        model.addAttribute("title", "V-V-V");

        return "admin/base-levels/index";
    }

    @GetMapping("/admin/base-levels/add/{baseId}")
    public String getAdminAddBase(Model model, @PathVariable Long baseId) throws IOException {
        Base base = baseService.getById(baseId);
        if (base.getId() != null) {
            model.addAttribute("base", base);
            model.addAttribute("baseId", baseId);

            model.addAttribute("baseLevelDto", new BaseLevelDto());

            model.addAttribute("niveles", this.niveles);

            logger.info("BaseLevel found..");
        } else {
            model.addAttribute("baseLevelDto", new BaseLevelDto());
            model.addAttribute("niveles", this.niveles);
            logger.info("Error! BaseLevel not found..");
        }

        return "admin/base-levels/add";
    }

    @PostMapping("/admin/base-levels/add/{baseId}")
    public String addAdminBaseLevelPost(@Valid @ModelAttribute("baseLevelDto") BaseLevelDto baseLevelDto,
                                        BindingResult result,
                                        Model model,
                                        HttpServletRequest request,
                                        @PathVariable Long baseId) throws ServletException, IOException {
        if (result.hasErrors()) {
            Base base = baseService.getById(baseId);
            model.addAttribute("base", base);
            model.addAttribute("baseId", baseId);

            model.addAttribute("niveles", this.niveles);

            model.addAttribute("baseLevelDto", baseLevelDto);

            return "admin/base-levels/add";
        }

        BaseLevel baseLevel = new BaseLevel();
        baseLevel.setBaseId(baseId);
        baseLevel.setTitle(baseLevelDto.getTitle());
        baseLevel.setDescription(baseLevelDto.getDescription());
        baseLevel.setLevel(baseLevelDto.getLevel());

        baseLevel.setResAgua(baseLevelDto.getResAgua().intValue());
        baseLevel.setResPlastic(baseLevelDto.getResPlastic().intValue());
        baseLevel.setResFood(baseLevelDto.getResFood().intValue());
        baseLevel.setResIron(baseLevelDto.getResIron().intValue());

        baseLevel.setPlusResAgua(baseLevelDto.getPlusResAgua().intValue());
        baseLevel.setPlusResPlastic(baseLevelDto.getPlusResPlastic().intValue());
        baseLevel.setPlusResFood(baseLevelDto.getPlusResFood().intValue());
        baseLevel.setPlusResIron(baseLevelDto.getPlusResIron().intValue());

        //-----------------------------
        // upload file
        Part part = request.getPart("img");
        if (part != null) {
            logger.info("--------------> Entr'e en part for 'img' ---------->");
            //get the InputStream to store the file somewhere
            InputStream fileInputStream = part.getInputStream();
            String fileName = part.getSubmittedFileName();
            File fileToSave = new File("/home/alexander/IdeaProjects/JavaInDocker/src/main/resources/static/uploads/base-levels/" + fileName);
            Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

            baseLevel.setImg(fileName);
        }
        //-----------------------------

        baseLevelService.addBaseLevel(baseLevel);

        return "redirect:/admin/bases/view/" + baseId;
    }

    @GetMapping("/admin/base-levels/edit/{baseId}/{baseLevelId}")
    public String editAdminBaseLevel(Model model, @PathVariable Long baseId, @PathVariable Long baseLevelId) throws IOException {
        BaseLevel baseLevel = baseLevelService.getById(baseLevelId);
        if (baseLevel.getId() != null) {
            model.addAttribute("baseLevel", baseLevel);

            Base base = baseService.getById(baseId);
            model.addAttribute("base", base);

            logger.info("Base found..");
        } else {
            model.addAttribute("Base", null);
            model.addAttribute("BaseLevel", null);

            logger.info("Error! Base not found..");
        }

        return "admin/base-levels/edit";
    }
}

