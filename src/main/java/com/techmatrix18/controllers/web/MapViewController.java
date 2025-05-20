package com.techmatrix18.controllers.web;

import com.techmatrix18.model.Base;
import com.techmatrix18.model.BaseLevel;
import com.techmatrix18.model.Map;
import com.techmatrix18.model.Space;
import com.techmatrix18.services.BaseLevelService;
import com.techmatrix18.services.BaseService;
import com.techmatrix18.services.MapService;
import com.techmatrix18.services.SpaceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.Instant;
import java.util.ArrayList;
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
    private SpaceService spaceService;

    public MapViewController(BaseService baseService, BaseLevelService baseLevelService, MapService mapService, SpaceService spaceService) {
        this.baseService = baseService;
        this.baseLevelService = baseLevelService;
        this.mapService = mapService;
        this.spaceService = spaceService;
    }

    //
    @GetMapping("/map")
    public String getDiv(Model model) {
        // user
        Long userId = 1L;

        // space
        Optional<Space> spaceOptional = spaceService.getSpaceByUserId(userId);
        if (spaceOptional.isPresent()) {
            Space space = spaceOptional.get();
            model.addAttribute("space", space);

            // map:sectors
            List<Map> sectors = mapService.getAllSectors(space.getId());
            model.addAttribute("sectors", sectors);

            ArrayList<Map> arrSectors = new ArrayList<>();
            for (int i = 0; i <= 28; i++) {
                //if (sectors.filter()) // exist i in sectors - in_array(i, sectors);
                arrSectors.add(i, null);
            }

            //Если тебе нужна структура с «пропущенными» индексами, вместо List лучше использовать:
            //Map<Integer, Object> map = new HashMap<>();
            //map.put(8, someValue);  // Работает независимо от порядка

            for (int j = 0; j < sectors.size(); j++) {
                Map m = sectors.get(j);
                arrSectors.set((m.getSector().intValue() - 1), m);
            }
            model.addAttribute("arrSectors", arrSectors);
        }

        return "map/map";
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
        // get base
        Base base = baseService.getById(baseId);
        model.addAttribute("base", base);
        model.addAttribute("sector", sector);

        // get baseLevels
        List<BaseLevel> baseLevels = baseLevelService.getAllByBaseIdOrderByLevelAsc(baseId);
        model.addAttribute("baseLevels", baseLevels);

        // user
        Long userId = 1L;

        // space
        Optional<Space> spaceOptional = spaceService.getSpaceByUserId(userId);
        if (spaceOptional.isPresent()) {
            Space space = spaceOptional.get();

            // get map
            Optional<Map> map = mapService.getSector(space.getId(), sector);
            model.addAttribute("map", map.orElse(null));

            // get current
            BaseLevel baseLevelCurrent = null;
            if (map.isPresent() && map.get().getBaseLevel() != null) {
                baseLevelCurrent = baseLevelService.getById(map.get().getBaseLevel().getId());
            }

            // get next
            BaseLevel nextLevel = null;
            if (baseLevelCurrent != null) {
                nextLevel = baseLevelService.getNext(baseId, baseLevelCurrent.getLevel());
            } else {
                nextLevel = baseLevelService.getNext(baseId, 0);
            }

            model.addAttribute("nextLevel", nextLevel);
        }

        return "map/map-base";
    }

    //
    @PostMapping("/map/{sector}/bases/{baseId}")
    public String getMapSectorBasePost(Model model,
                                       @PathVariable Integer sector,
                                       @PathVariable Long baseId,
                                       RedirectAttributes redirectAttributes) {
        //
        Base base = baseService.getById(baseId);
        model.addAttribute("base", base);
        model.addAttribute("sector", sector);

        // user
        Long userId = 1L;

        // space
        Optional<Space> spaceOptional = spaceService.getSpaceByUserId(userId);
        if (spaceOptional.isPresent()) {
            Space space = spaceOptional.get();

            // get map
            Optional<Map> map = mapService.getSector(space.getId(), sector);
            //model.addAttribute("map", map.orElse(null));

            // get current
            BaseLevel baseLevelCurrent = null;
            if (map.isPresent() && map.get().getBaseLevel() != null) {
                baseLevelCurrent = baseLevelService.getById(map.get().getBaseLevel().getId());
            }

            // get next
            BaseLevel nextLevel = null;
            if (baseLevelCurrent != null) {
                nextLevel = baseLevelService.getNext(baseId, baseLevelCurrent.getLevel());
            } else {
                nextLevel = baseLevelService.getNext(baseId, 0);
            }

            // probamos res para nivel siguiente  - Martines
            if (nextLevel != null) {
                if (space.getResAgua() < nextLevel.getResAgua() || space.getResPlastic() < nextLevel.getResPlastic()
                        || space.getResFood() < nextLevel.getResFood() || space.getResIron() < nextLevel.getResIron() ) {

                    redirectAttributes.addFlashAttribute("error", "No tienes res para mejorar tu base ahora!");
                    return "redirect:'/map/' + sector + '/bases/' + baseId";
                } else {
                    // TODO: añadir baseLevels.res_do_agua etc para space.res_do_agua
                    // minus res para esta mejorar'ia
                    space.setResAgua( space.getResAgua() - nextLevel.getResAgua() );
                    space.setResPlastic( space.getResPlastic() - nextLevel.getResPlastic() );
                    space.setResFood( space.getResFood() - nextLevel.getResFood() );
                    space.setResIron( space.getResIron() - nextLevel.getResIron() );
                    // plus do res desde este base
                    space.setResAgua( space.getResAgua() + nextLevel.getPlusResAgua() );
                    space.setResPlastic( space.getResPlastic() + nextLevel.getPlusResPlastic() );
                    space.setResFood( space.getResFood() + nextLevel.getPlusResFood() );
                    space.setResIron( space.getResIron() + nextLevel.getPlusResIron() );
                    //
                    spaceService.updateSpace(space);
                }
            }

            // add new map or update
            if (map.isPresent()) { // update
                Map newMap = map.get();
                newMap.setBaseLevel(nextLevel);
                newMap.setBuildStartedAt(Instant.now()); // date

                mapService.addMap(newMap);
            } else {
                if (nextLevel != null && nextLevel.getId() != null) { // add
                    Map newMap = new Map();
                    newMap.setSpaceId(space.getId());
                    //newMap.setBaseLevel(nextLevel.getId());
                    newMap.setBaseLevel(nextLevel);
                    newMap.setSector((long) sector);
                    newMap.setBuildStartedAt(Instant.now()); // date

                    mapService.addMap(newMap);
                }
            }
        }

        return "redirect:/map";
    }

    //
    @GetMapping("/la-historia")
    public String pageLaHistoria(Model model) {
        return "la-historia";
    }

    //
    @GetMapping("/sobre-nosotros")
    public String pageLaEmpresa(Model model) {
        return "la-empresa";
    }
}

