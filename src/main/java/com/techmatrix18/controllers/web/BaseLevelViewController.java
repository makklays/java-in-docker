package com.techmatrix18.controllers.web;

import com.techmatrix18.dto.BaseLevelDto;
import com.techmatrix18.dto.BaseLevelEditDto;
import com.techmatrix18.model.Base;
import com.techmatrix18.model.BaseLevel;
import com.techmatrix18.services.BaseLevelService;
import com.techmatrix18.services.BaseService;
import jakarta.persistence.EntityNotFoundException;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        // Validación el archivo
        if (baseLevelDto.getImg() == null || baseLevelDto.getImg().isEmpty()) {
            result.rejectValue("img", "NotEmpty.baseLevelEditDto.img", "Es necesario cargar el archivo.");
        }

        // Validación el baseLevelDTO
        Base base = null;
        if (result.hasErrors()) {
            base = baseService.getById(baseId);
            model.addAttribute("base", base);
            model.addAttribute("baseId", baseId);
            model.addAttribute("niveles", this.niveles);
            model.addAttribute("baseLevelDto", baseLevelDto);

            return "admin/base-levels/add";
        }

        BaseLevel baseLevel = new BaseLevel();
        baseLevel.setBase(base);
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
        if (part != null && part.getSize() > 0) {
            logger.info("--------------> Entr'e en part for 'img' ---------->");
            //get the InputStream to store the file somewhere
            InputStream fileInputStream = part.getInputStream();
            String fileName = part.getSubmittedFileName();
            File fileToSave = new File("/home/alexander/IdeaProjects/JavaInDocker/src/main/resources/mystatic/uploads/base-levels/" + fileName);
            Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

            baseLevel.setImg(fileName);
        }
        //-----------------------------

        baseLevelService.addBaseLevel(baseLevel);

        return "redirect:/admin/bases/view/" + baseId;
    }

    @GetMapping("/admin/base-levels/edit/{baseId}/{baseLevelId}")
    public String editAdminBaseLevel(Model model,
                                     @PathVariable Long baseId,
                                     @PathVariable Long baseLevelId) throws IOException, ServletException {
        //
        BaseLevel baseLevel = baseLevelService.getById(baseLevelId);
        if (baseLevel.getId() != null) {
            Base base = baseService.getById(baseId);
            model.addAttribute("base", base);
            model.addAttribute("baseId", baseId);
            model.addAttribute("baseLevelId", baseLevelId);
            model.addAttribute("niveles", this.niveles);

            BaseLevelEditDto baseLevelEditDto = new BaseLevelEditDto();
            baseLevelEditDto.setId(baseLevel.getId());
            baseLevelEditDto.setTitle(baseLevel.getTitle());
            baseLevelEditDto.setDescription(baseLevel.getDescription());
            baseLevelEditDto.setLevel(baseLevel.getLevel());

            baseLevelEditDto.setResAgua((long) baseLevel.getResAgua());
            baseLevelEditDto.setResPlastic((long) baseLevel.getResPlastic());
            baseLevelEditDto.setResFood((long) baseLevel.getResFood());
            baseLevelEditDto.setResIron((long) baseLevel.getResIron());

            baseLevelEditDto.setPlusResAgua((long) baseLevel.getPlusResAgua());
            baseLevelEditDto.setPlusResPlastic((long) baseLevel.getPlusResPlastic());
            baseLevelEditDto.setPlusResFood((long) baseLevel.getPlusResFood());
            baseLevelEditDto.setPlusResIron((long) baseLevel.getPlusResIron());

            baseLevelEditDto.setBuildSeconds((long) baseLevel.getBuildSeconds());

            model.addAttribute("baseLevelEditDto", baseLevelEditDto);
            model.addAttribute("img", baseLevel.getImg());

            logger.info("=-=-=> " + baseLevelEditDto.toString());

            return "admin/base-levels/edit";

        } else {
            return "redirect:/admin/bases/view/" + baseId;
        }
    }

    @PostMapping("/admin/base-levels/edit/{baseId}/{baseLevelId}")
    public String editAdminBaseLevelPost(@Valid @ModelAttribute("baseLevelEditDto") BaseLevelEditDto baseLevelEditDto,
                                     BindingResult result,
                                     Model model,
                                     HttpServletRequest request,
                                     @PathVariable Long baseId,
                                     @PathVariable Long baseLevelId) throws IOException, ServletException {
        // Validación el archivo
        /*if (baseLevelEditDto.getImg() == null || baseLevelEditDto.getImg().isEmpty()) {
            result.rejectValue("img", "NotEmpty.baseLevelEditDto.img", "Es necesario cargar el archivo.");
        }*/

        // Validación otros campos
        Base base = null;
        if (result.hasErrors()) {
            base = baseService.getById(baseId);
            model.addAttribute("base", base);
            model.addAttribute("baseId", baseId);
            model.addAttribute("baseLevelId", baseLevelId);

            model.addAttribute("niveles", this.niveles);
            model.addAttribute("baseLevelEditDto", baseLevelEditDto);

            return "admin/base-levels/edit";
        }

        // Competamos el modelo BaseLevel
        BaseLevel baseLevel = baseLevelService.getById(baseLevelId);
        if (baseLevel.getId() != null) {
            baseLevel.setBase(base);
            baseLevel.setTitle(baseLevelEditDto.getTitle());
            baseLevel.setDescription(baseLevelEditDto.getDescription());
            baseLevel.setLevel(baseLevelEditDto.getLevel());

            baseLevel.setResAgua(baseLevelEditDto.getResAgua().intValue());
            baseLevel.setResPlastic(baseLevelEditDto.getResPlastic().intValue());
            baseLevel.setResFood(baseLevelEditDto.getResFood().intValue());
            baseLevel.setResIron(baseLevelEditDto.getResIron().intValue());

            baseLevel.setPlusResAgua(baseLevelEditDto.getPlusResAgua().intValue());
            baseLevel.setPlusResPlastic(baseLevelEditDto.getPlusResPlastic().intValue());
            baseLevel.setPlusResFood(baseLevelEditDto.getPlusResFood().intValue());
            baseLevel.setPlusResIron(baseLevelEditDto.getPlusResIron().intValue());

            baseLevel.setBuildSeconds(baseLevelEditDto.getBuildSeconds().intValue());

            //-----------------------------
            // upload file
            Part part = request.getPart("img");
            if (part != null && part.getSize() > 0) {
                logger.info("--------------> Entr'e en part for 'img' ---------->");
                //get the InputStream to store the file somewhere
                InputStream fileInputStream = part.getInputStream();
                String fileName = part.getSubmittedFileName();
                File fileToSave = new File("/home/alexander/IdeaProjects/JavaInDocker/src/main/resources/mystatic/uploads/base-levels/" + fileName);
                Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

                baseLevel.setImg(fileName);
            }
            //-----------------------------

            baseLevelService.updateBaseLevel(baseLevel);

            return "redirect:/admin/bases/view/" + baseId;

        } else {
            model.addAttribute("Base", null);
            model.addAttribute("BaseLevel", null);

            logger.info("Error! Base Level not found..");
        }

        return "admin/base-levels/edit";
    }

    @GetMapping("/admin/base-levels/delete/{baseId}/{baseLevelId}")
    public String deleteBaseLevel(@PathVariable Long baseId,
                                  @PathVariable Long baseLevelId,
                                  RedirectAttributes redirectAttributes) {
        // base
        try {
            Base base = baseService.getById(baseId);
        } catch (EntityNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", "Este base no existe!");
            return "redirect:/admin/bases/";
        }

        // baseLevel
        try {
            baseLevelService.deleteBaseLevel(baseLevelId);
            redirectAttributes.addFlashAttribute("message", "BaseLevel eliminado exitosamente");
        } catch (EntityNotFoundException ex) {
            redirectAttributes.addFlashAttribute("error", "BaseLevel con ID " + baseLevelId + " extraviado.");
        }

        return "redirect:/admin/bases/view/" + baseId;
    }

    @GetMapping("/admin/base-levels/view/{baseId}/{baseLevelId}")
    public String viewAdminBaseLevel(Model model,
                                     @PathVariable Long baseId,
                                     @PathVariable Long baseLevelId) throws IOException, ServletException {
        //
        BaseLevel baseLevel = baseLevelService.getById(baseLevelId);
        if (baseLevel.getId() != null) {

            Base base = baseService.getById(baseId);
            model.addAttribute("base", base);
            model.addAttribute("baseId", baseId);
            model.addAttribute("baseLevelId", baseLevelId);
            model.addAttribute("niveles", this.niveles);
            model.addAttribute("baseLevel", baseLevel);
            model.addAttribute("img", baseLevel.getImg());

            return "admin/base-levels/view";
        } else {
            return "redirect:/admin/bases/view/" + baseId;
        }
    }
}

