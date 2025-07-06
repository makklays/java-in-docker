package com.techmatrix18.controllers.web;

import com.techmatrix18.model.Autobattle;
import com.techmatrix18.model.Base;
import com.techmatrix18.services.AutobattleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/admin/auto-battles/")
    public String getAllAutoBattles(Model model) {
        List<Autobattle> autobattles = autobattleService.getAll();
        model.addAttribute("autobattles", autobattles);

        return "admin/auto-battles/index";
    }
}

