package com.techmatrix18.controllers.web;

import com.techmatrix18.model.Base;
import com.techmatrix18.model.BaseLevel;
import com.techmatrix18.services.BaseLevelService;
import com.techmatrix18.services.BaseService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Logger;

/**
 * Base controller with endpoints for base pages
 *
 * @author Alexander Kuziv
 * @since 18.02.2025
 * @version 0.0.1
 */

@Controller
//@RequestMapping("/bases")
public class BaseViewController {

    Logger logger = Logger.getLogger(BaseViewController.class.getName());

    private final BaseService baseService;
    private final BaseLevelService baseLevelService;

    public BaseViewController(BaseService baseService, BaseLevelService baseLevelService) {
        this.baseService = baseService;
        this.baseLevelService = baseLevelService;
    }

    @GetMapping("/all")
    public String getAllBases(Model model) {
        model.addAttribute("title", "V-V-V");

        return "bases/index";
    }

    @GetMapping("/available")
    public String getAvailableBases(Model model) {
        model.addAttribute("title", "V-V-V");

        return "bases/available";
    }

    @GetMapping("/view/{id}")
    public String getBuild(@PathVariable Long id, Model model) {
        model.addAttribute("title", "V-V-V");
        model.addAttribute("id", id);

        return "bases/view";
    }

    @GetMapping("/admin/bases/")
    public String getAdminBases(Model model) {
        List<Base> bases = baseService.getAll();
        model.addAttribute("bases", bases);

        return "admin/bases/index";
    }

    @GetMapping("/admin/bases/add")
    public String getAdminAddBase(Model model) {
        Base base = new Base();
        model.addAttribute("base", base);

        return "admin/bases/add";
    }

    @PostMapping("/admin/bases/add")
    public String addAdminAddBase(Model model, @Valid Base base, BindingResult bindingResult, HttpServletRequest request) throws Exception {
        if (bindingResult.hasErrors()) {
            return "bases/add";
        }

        //-----------------------------
        // upload file
        Part part = request.getPart("img");
        if (part != null && part.getSize() > 0) {
            logger.info("--------------> Entr'e en part for 'img' ---------->");
            //get the InputStream to store the file somewhere
            InputStream fileInputStream = part.getInputStream();
            String fileName = part.getSubmittedFileName();
            File fileToSave = new File("/home/alexander/IdeaProjects/JavaInDocker/src/main/resources/mystatic/uploads/bases/" + fileName);
            Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);

            base.setImg(fileName);
        }
        //-----------------------------

        baseService.addBase(base);

        return "redirect:/admin/bases/";
    }

    @GetMapping("/admin/bases/view/{baseId}")
    public String getAdminBase(Model model, @PathVariable Long baseId) throws IOException {
        Base base = baseService.getById(baseId);
        if (base.getId() != null) {
            model.addAttribute("base", base);

            List<BaseLevel> baseLevels = baseLevelService.getAllByBaseIdOrderByLevelAsc(baseId);
            model.addAttribute("baseLevels", baseLevels);

            logger.info("Base found..");
        } else {
            model.addAttribute("base", null);
            logger.info("Error! Base not found..");
        }

        return "admin/bases/view";
    }

    @GetMapping("/admin/bases/edit/{baseId}")
    public String editAdminBase(Model model, @PathVariable Long baseId) throws IOException {
        Base base = baseService.getById(baseId);
        if (base.getId() != null) {
            model.addAttribute("base", base);
            logger.info("Base found..");
        } else {
            model.addAttribute("city", null);
            logger.info("Error! Base not found..");
        }

        return "admin/bases/edit";
    }

    @PostMapping("/admin/bases/edit/{baseId}")
    public String editPostAdminBase(@PathVariable("baseId") Long baseId,
                                    @Valid @ModelAttribute("base") Base base,
                                    BindingResult bindingResult,
                                    Model model,
                                    @RequestParam("img") MultipartFile img) throws IOException, ServletException {
        // validacion el archivo
        if (img.isEmpty()) {
            bindingResult.rejectValue("img", "NotEmpty.base.img", "Es necesario cargar el archivo.");
            return "admin/bases/edit";
        }

        // Validacion los campos
        /*if (bindingResult.hasErrors()) {
            base.setId(baseId);
            model.addAttribute("base", base);

            return "admin/bases/edit";
        }*/

        // sin borrar baseLevels (relación OneToMany)
        Base baseUpdate = baseService.getById(base.getId());

        //-----------------------------
        // upload file
        if (!img.isEmpty()) {
            // Ejemplo: gurdar el archivo, recibir un nombre
            String fileName = img.getOriginalFilename();
            // Guarda cuando necesite
            File fileToSave = new File("/home/alexander/IdeaProjects/JavaInDocker/src/main/resources/mystatic/uploads/bases/", fileName);
            img.transferTo(fileToSave);

            baseUpdate.setImg(fileName);
        }
        //-----------------------------

        logger.info("------- Base ---------> " + baseUpdate.toString());

        baseService.updateBase(baseUpdate);

        return "redirect:/admin/bases/";
    }

    @GetMapping("/admin/bases/delete/{baseId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long baseId) throws IOException {
        Base base = baseService.getById(baseId);
        if (base.getId() != null) {
            baseService.deleteBase(baseId);
        }

        response.sendRedirect("/admin/bases/");
    }
}

