package com.techmatrix18.controllers.web;

import com.techmatrix18.model.Base;
import com.techmatrix18.model.BaseLevel;
import com.techmatrix18.services.BaseLevelService;
import com.techmatrix18.services.BaseService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
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

    @PostMapping("/admin/bases/add-post")
    public String addAdminAddBase(Model model, @Valid Base base, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "bases/add";
        }
        baseService.addBase(base);

        return "redirect:/admin/bases/";
    }

    @GetMapping("/admin/bases/view/{baseId}")
    public String getAdminBase(Model model, @PathVariable Long baseId) throws IOException {
        Base base = baseService.getById(baseId);
        if (base.getId() != null) {
            model.addAttribute("base", base);

            List<BaseLevel> baseLevels = baseLevelService.getAllByBaseId(baseId);
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

    @PostMapping("/admin/bases/edit-post/{baseId}")
    public String editPostAdminBase(@PathVariable("baseId") Long baseId, @Valid Base base, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            base.setId(baseId);
            model.addAttribute("base", base);

            return "admin/bases/edit";
        }

        logger.info("Base: " + base.toString());

        baseService.updateBase(base);

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

