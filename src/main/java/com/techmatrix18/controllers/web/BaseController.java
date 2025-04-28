package com.techmatrix18.controllers.web;

import com.techmatrix18.services.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.logging.Logger;

/**
 * Base controller with endpoints for base pages
 *
 * @author Alexander Kuziv
 * @since 18.02.2025
 * @version 0.0.1
 */

@Controller
@RequestMapping("/bases")
public class BaseController {

    Logger log = Logger.getLogger(BaseController.class.getName());

    private final BaseService baseService;

    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

    @GetMapping("/all")
    public String getAllBases(Model model) {
        model.addAttribute("title", "V-V-V");

        return "index";
    }

    @GetMapping("/available")
    public String getAvailableBases(Model model) {
        model.addAttribute("title", "V-V-V");

        return "available";
    }

    @GetMapping("/view/{id}")
    public String getBuild(@PathVariable Long id, Model model) {
        model.addAttribute("title", "V-V-V");
        model.addAttribute("id", id);

        return "view";
    }
}

