package com.techmatrix18.controllers.web;

import com.techmatrix18.model.Base;
import com.techmatrix18.model.BaseLevel;
import com.techmatrix18.model.Map;
import com.techmatrix18.services.BaseLevelService;
import com.techmatrix18.services.BaseService;
import com.techmatrix18.services.MapService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * This is MapController
 *
 * @company for TechMatrix18
 * @author alexander kuziv
 * @since 13-05-2025
 * @version 0.0.1
 */

@Controller
public class MapViewController {

    Logger log = Logger.getLogger(UserViewController.class.getName());

    private BaseService baseService;
    private BaseLevelService baseLevelService;
    private MapService mapService;

    public MapViewController(BaseService baseService, BaseLevelService baseLevelService, MapService mapService) {
        this.baseService = baseService;
        this.baseLevelService = baseLevelService;
        this.mapService = mapService;
    }

    //
    @GetMapping("/map/{sector}/bases/")
    public String getMapSectorBases(Model model, @PathVariable Integer sector) {
        List<Base> bases = baseService.getAll();
        model.addAttribute("bases", bases);
        model.addAttribute("sector", sector);

        return "map/map-bases";
    }

    //
    @GetMapping("/map/{sector}/bases/{baseId}")
    public String getMapSectorBase(Model model, @PathVariable Integer sector, @PathVariable Long baseId) {
        // get Base
        Base base = baseService.getById(baseId);
        model.addAttribute("base", base);
        model.addAttribute("sector", sector);

        // get all
        List<BaseLevel> baseLevels = baseLevelService.getAllByBaseIdOrderByLevelAsc(baseId);
        model.addAttribute("baseLevels", baseLevels);

        Long spaceId = 1L;

        // get map
        Optional<Map> map = mapService.getSector(spaceId, sector);
        model.addAttribute("map", map.orElse(null));

        // get current
        BaseLevel baseLevelCurrent = null;
        if (map.isPresent() && map.get().getBaseLevelId() != null) {
            baseLevelCurrent = baseLevelService.getById( map.get().getBaseLevelId() );
        }

        //log.info("===== baseLevelCurrent ====> " + baseLevelCurrent.toString());

        // get next
        BaseLevel nextLevel = null;
        if (baseLevelCurrent != null) {
            nextLevel = baseLevelService.getNext(baseId, baseLevelCurrent.getLevel());
        } else {
            nextLevel = baseLevelService.getNext(baseId, 0);
        }

        log.info("===== nextLevel ====> " + Objects.toString(nextLevel, "No next level"));

        model.addAttribute("nextLevel", nextLevel);

        return "map/map-base";
    }

    //
    @PostMapping("/map/{sector}/bases/{baseId}")
    public String getMapSectorBasePost(Model model, @PathVariable Integer sector, @PathVariable Long baseId) {
        Base base = baseService.getById(baseId);
        model.addAttribute("base", base);
        model.addAttribute("sector", sector);

        // BaseLevel baseLevel = baseLevelService.getOneByBaseId(baseId);
        Long baseLevelId = 1L;

        // TODO: Session
        Long spaceId = 1L;

        // add new map or update
        Map map = new Map();
        map.setSpaceId(spaceId);
        map.setBaseLevelId(baseLevelId);
        map.setSector((long) sector);

        log.info(map.toString());

        // mapService.addMap(map);

        List<Map> sectors = mapService.getAllSectors(spaceId);
        model.addAttribute("sectors", sectors);

        return "redirect:/map";
    }
}

