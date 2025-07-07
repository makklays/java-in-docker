package com.techmatrix18.controllers.web;

import com.techmatrix18.dto.BaseDto;
import com.techmatrix18.model.Autobattle;
import com.techmatrix18.model.Base;
import com.techmatrix18.model.BaseLevel;
import com.techmatrix18.services.AutobattleService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class AutobattleViewController {

    Logger logger = Logger.getLogger(BaseViewController.class.getName());

    private final AutobattleService autobattleService;

    private final List<String> niveles = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11");

    public AutobattleViewController(AutobattleService autobattleService) {
        this.autobattleService = autobattleService;
    }

    @GetMapping("/admin/autobattles/")
    public String getAllAutoBattles(Model model) {
        List<Autobattle> autobattles = autobattleService.getAll();
        model.addAttribute("autobattles", autobattles);

        return "admin/autobattles/index";
    }

    @GetMapping("/admin/autobattles/add")
    public String addAdminAddAutobattle(Model model){
        Autobattle autobattle = new Autobattle();
        model.addAttribute("autobattle", autobattle);

        return "admin/autobattles/add";
    }

    @PostMapping("/admin/autobattles/add")
    public String addAdminAddAutobattlePost(@Valid @ModelAttribute Autobattle autobattle, BindingResult bindingResult, Model model, HttpServletRequest request) throws Exception {

        // Validación
        if (bindingResult.hasErrors()) {
            return "admin/autobattles/add";
        }
        autobattleService.addAutobattle(autobattle);

        return "redirect:/admin/autobattles/";
    }

    @GetMapping("/admin/autobattles/edit/{autobattleId}")
    public String editAdminEditAutobattle(Model model, @PathVariable Long autobattleId) throws IOException {
        Autobattle autobattle = autobattleService.getById(autobattleId);
        if (autobattle.getId() != null) {
            model.addAttribute("autobattle", autobattle);
            logger.info("Autobattle found..");
        } else {
            model.addAttribute("autobattle", null);
            logger.info("Error! Autobattle not found..");
        }

        return "admin/autobattles/edit";
    }

    @PostMapping("/admin/autobattles/edit/{autobattleId}")
    public String editAdminEditAutobattlePost(@PathVariable("autobattleId") Long autobattleId,
                                    @Valid @ModelAttribute("autobattle") Autobattle autobattle,
                                    BindingResult result,
                                    Model model) throws IOException, ServletException {
        // Validacion los campos
        if (result.hasErrors()) {
            //Autobattle autobattle_obj = autobattleService.getById(autobattleId);
            model.addAttribute("autobattle", autobattle);
            return "admin/autobattles/edit";
        }
        autobattleService.updateAutobattle(autobattle);

        return "redirect:/admin/autobattles/";
    }

    @GetMapping("/admin/autobattles/view/{autobattleId}")
    public String getAdminAutobattleView(Model model, @PathVariable Long autobattleId) throws IOException {
        Autobattle autobattle = autobattleService.getById(autobattleId);
        if (autobattle.getId() != null) {
            model.addAttribute("autobattle", autobattle);

            logger.info("Autobattle found..");
        } else {
            model.addAttribute("autobattle", null);
            logger.info("Error! Autobattle not found..");
        }

        return "admin/autobattles/view";
    }

    @GetMapping("/admin/autobattles/delete/{autobattleId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long autobattleId) throws IOException {
        Autobattle autobattle = autobattleService.getById(autobattleId);
        if (autobattle.getId() != null) {
            autobattleService.deleteAutobattle(autobattleId);
        }

        response.sendRedirect("/admin/autobattles/");
    }
}

