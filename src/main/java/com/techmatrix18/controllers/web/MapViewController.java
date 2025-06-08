package com.techmatrix18.controllers.web;

import com.techmatrix18.model.*;
import com.techmatrix18.services.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
    private UserService userService;
    private BlogService blogService;

    public MapViewController(BaseService baseService, BaseLevelService baseLevelService, MapService mapService,
                             SpaceService spaceService, UserService userService, BlogService blogService) {
        this.baseService = baseService;
        this.baseLevelService = baseLevelService;
        this.mapService = mapService;
        this.spaceService = spaceService;
        this.userService = userService;
        this.blogService = blogService;
    }

    //
    @GetMapping("/map")
    public String getDiv(Model model, HttpSession session) {

        // get session
        Long userId = (Long) session.getAttribute("userId");
        log.info("---------- user ID--------------> " + userId);
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);

            log.info("---------- user --------------> " + user.toString());

        } else {
            return "redirect:/req/login";
        }

        // space
        Optional<Space> spaceOptional = spaceService.getSpaceByUserId(userId);
        if (spaceOptional.isPresent()) {
            Space space = spaceOptional.get();

            // count time - seconds
            Instant dateTimeNow = Instant.now();
            Instant lastTime = space.getUpdatedAt(); // Предположим, это тоже Instant
            long diffSeconds = Duration.between(lastTime, dateTimeNow).getSeconds();

            // update - no less 15 minutes
            if (diffSeconds >= 60 * 15) {
                // update res per 1 hour
                double aguaPerHours = space.getDoResAgua() * (diffSeconds / 3600.0);
                double plasticPerHours = space.getDoResPlastic() * (diffSeconds / 3600.0);
                double foodPerHours = space.getDoResFood() * (diffSeconds / 3600.0);
                double ironPerHours = space.getDoResIron() * (diffSeconds / 3600.0);

                space.setResAgua(space.getResAgua() + (int) aguaPerHours);
                space.setResPlastic(space.getResPlastic() + (int) plasticPerHours);
                space.setResFood(space.getResFood() + (int) foodPerHours);
                space.setResIron(space.getResIron() + (int) ironPerHours);
                space.setUpdatedAt(Instant.now());
                spaceService.updateSpace(space);
            }
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
    public String getMapSectorBases(Model model,
                                    @PathVariable Integer sector,
                                    HttpServletRequest request,
                                    HttpSession session) {
        //
        List<Base> bases = baseService.getAll();
        model.addAttribute("bases", bases);
        model.addAttribute("sector", sector);

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            return "redirect:/req/login";
        }

        // space
        Optional<Space> spaceOptional = spaceService.getSpaceByUserId(userId);
        if (spaceOptional.isPresent()) {
            Space space = spaceOptional.get();
            model.addAttribute("space", space);
        }

        return "map/map-bases";
    }

    /**
     * URL for pages with design for type: Banco
     *
     * @param model
     * @param sector
     * @param baseId
     * @return
     */
    @GetMapping("/map/{sector}/bases/{baseId}/banco")
    public String getMapSectorBaseBanco(Model model,
                                        @PathVariable Integer sector,
                                        @PathVariable Long baseId,
                                        HttpSession session) {
        // get base
        Base base = baseService.getById(baseId);
        model.addAttribute("base", base);
        model.addAttribute("sector", sector);

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            return "redirect:/req/login";
        }

        // space
        Optional<Space> spaceOptional = spaceService.getSpaceByUserId(userId);
        if (spaceOptional.isPresent()) {
            Space space = spaceOptional.get();
            model.addAttribute("space", space);

            // get map
            Optional<Map> map = mapService.getSector(space.getId(), sector);
            model.addAttribute("map", map.orElse(null));

            // get current
            BaseLevel baseLevelCurrent = null;
            if (map.isPresent() && map.get().getBaseLevel() != null) {
                baseLevelCurrent = baseLevelService.getById(map.get().getBaseLevel().getId());
            }
            model.addAttribute("baseLevelCurrent", baseLevelCurrent);

            // get next
            BaseLevel nextLevel = null;
            if (baseLevelCurrent != null) {
                nextLevel = baseLevelService.getNext(baseId, baseLevelCurrent.getLevel());
            } else {
                nextLevel = baseLevelService.getNext(baseId, 0);
            }

            model.addAttribute("nextLevel", nextLevel);

            // by default - check res
            model.addAttribute("is_has_agua", true);
            model.addAttribute("is_has_plastic", true);
            model.addAttribute("is_has_food", true);
            model.addAttribute("is_has_iron", true);

            // probamos res para nivel siguiente  - Martines
            if (nextLevel != null) {
                if (space.getResAgua() < nextLevel.getResAgua() || space.getResPlastic() < nextLevel.getResPlastic()
                        || space.getResFood() < nextLevel.getResFood() || space.getResIron() < nextLevel.getResIron()) {

                    // check res
                    if (space.getResAgua() < nextLevel.getResAgua()) {
                        model.addAttribute("is_has_agua", false);
                    }
                    if (space.getResPlastic() < nextLevel.getResPlastic()) {
                        model.addAttribute("is_has_plastic", false);
                    }
                    if (space.getResFood() < nextLevel.getResFood()) {
                        model.addAttribute("is_has_food", false);
                    }
                    if (space.getResIron() < nextLevel.getResIron()) {
                        model.addAttribute("is_has_iron", false);
                    }
                }
            } else {
                model.addAttribute("is_has_agua", false);
                model.addAttribute("is_has_plastic", false);
                model.addAttribute("is_has_food", false);
                model.addAttribute("is_has_iron", false);
            }
        }

        return "map/map-bases/banco/index";
    }

    /**
     * URL for pages with design for type: Base-1
     *
     * @param model
     * @param sector
     * @param baseId
     * @return
     */
    @GetMapping("/map/{sector}/bases/{baseId}/base-1")
    public String getMapSectorBaseBase1(Model model,
                                         @PathVariable Integer sector,
                                         @PathVariable Long baseId,
                                         HttpSession session) {
        // get base
        Base base = baseService.getById(baseId);
        model.addAttribute("base", base);
        model.addAttribute("sector", sector);

        // get baseLevels
        List<BaseLevel> baseLevels = baseLevelService.getAllByBaseIdOrderByLevelAsc(baseId);
        model.addAttribute("baseLevels", baseLevels);

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            return "redirect:/req/login";
        }

        // space
        Optional<Space> spaceOptional = spaceService.getSpaceByUserId(userId);
        if (spaceOptional.isPresent()) {
            Space space = spaceOptional.get();
            model.addAttribute("space", space);

            // get map
            Optional<Map> map = mapService.getSector(space.getId(), sector);
            model.addAttribute("map", map.orElse(null));

            // get current
            BaseLevel baseLevelCurrent = null;
            if (map.isPresent() && map.get().getBaseLevel() != null) {
                baseLevelCurrent = baseLevelService.getById(map.get().getBaseLevel().getId());
            }
            model.addAttribute("baseLevelCurrent", baseLevelCurrent);

            // get next
            BaseLevel nextLevel = null;
            if (baseLevelCurrent != null) {
                nextLevel = baseLevelService.getNext(baseId, baseLevelCurrent.getLevel());
            } else {
                nextLevel = baseLevelService.getNext(baseId, 0);
            }

            model.addAttribute("nextLevel", nextLevel);

            // by default - check res
            model.addAttribute("is_has_agua", true);
            model.addAttribute("is_has_plastic", true);
            model.addAttribute("is_has_food", true);
            model.addAttribute("is_has_iron", true);

            // probamos res para nivel siguiente  - Martines
            if (nextLevel != null) {
                if (space.getResAgua() < nextLevel.getResAgua() || space.getResPlastic() < nextLevel.getResPlastic()
                        || space.getResFood() < nextLevel.getResFood() || space.getResIron() < nextLevel.getResIron()) {

                    // check res
                    if (space.getResAgua() < nextLevel.getResAgua()) {
                        model.addAttribute("is_has_agua", false);
                    }
                    if (space.getResPlastic() < nextLevel.getResPlastic()) {
                        model.addAttribute("is_has_plastic", false);
                    }
                    if (space.getResFood() < nextLevel.getResFood()) {
                        model.addAttribute("is_has_food", false);
                    }
                    if (space.getResIron() < nextLevel.getResIron()) {
                        model.addAttribute("is_has_iron", false);
                    }
                }
            } else {
                model.addAttribute("is_has_agua", false);
                model.addAttribute("is_has_plastic", false);
                model.addAttribute("is_has_food", false);
                model.addAttribute("is_has_iron", false);
            }
        }

        return "map/map-bases/base-1/index";
    }

    /**
     * URL for pages with design for type: Hierro
     *
     * @param model
     * @param sector
     * @param baseId
     * @return
     */
    @GetMapping("/map/{sector}/bases/{baseId}/hierro")
    public String getMapSectorBaseHierro(Model model,
                                         @PathVariable Integer sector,
                                         @PathVariable Long baseId,
                                         HttpSession session) {
        // get base
        Base base = baseService.getById(baseId);
        model.addAttribute("base", base);
        model.addAttribute("sector", sector);

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            return "redirect:/req/login";
        }

        // space
        Optional<Space> spaceOptional = spaceService.getSpaceByUserId(userId);
        if (spaceOptional.isPresent()) {
            Space space = spaceOptional.get();
            model.addAttribute("space", space);

            // get map
            Optional<Map> map = mapService.getSector(space.getId(), sector);
            model.addAttribute("map", map.orElse(null));

            // get current
            BaseLevel baseLevelCurrent = null;
            if (map.isPresent() && map.get().getBaseLevel() != null) {
                baseLevelCurrent = baseLevelService.getById(map.get().getBaseLevel().getId());
            }
            model.addAttribute("baseLevelCurrent", baseLevelCurrent);

            // get next
            BaseLevel nextLevel = null;
            if (baseLevelCurrent != null) {
                nextLevel = baseLevelService.getNext(baseId, baseLevelCurrent.getLevel());
            } else {
                nextLevel = baseLevelService.getNext(baseId, 0);
            }

            model.addAttribute("nextLevel", nextLevel);

            // by default - check res
            model.addAttribute("is_has_agua", true);
            model.addAttribute("is_has_plastic", true);
            model.addAttribute("is_has_food", true);
            model.addAttribute("is_has_iron", true);

            // probamos res para nivel siguiente  - Martines
            if (nextLevel != null) {
                if (space.getResAgua() < nextLevel.getResAgua() || space.getResPlastic() < nextLevel.getResPlastic()
                        || space.getResFood() < nextLevel.getResFood() || space.getResIron() < nextLevel.getResIron()) {

                    // check res
                    if (space.getResAgua() < nextLevel.getResAgua()) {
                        model.addAttribute("is_has_agua", false);
                    }
                    if (space.getResPlastic() < nextLevel.getResPlastic()) {
                        model.addAttribute("is_has_plastic", false);
                    }
                    if (space.getResFood() < nextLevel.getResFood()) {
                        model.addAttribute("is_has_food", false);
                    }
                    if (space.getResIron() < nextLevel.getResIron()) {
                        model.addAttribute("is_has_iron", false);
                    }
                }
            } else {
                model.addAttribute("is_has_agua", false);
                model.addAttribute("is_has_plastic", false);
                model.addAttribute("is_has_food", false);
                model.addAttribute("is_has_iron", false);
            }
        }

        return "map/map-bases/hierro/index";
    }

    /**
     * URL for pages with design for type: TI CENTRO
     *
     * @param model
     * @param sector
     * @param baseId
     * @return
     */
    @GetMapping("/map/{sector}/bases/{baseId}/ti-centro")
    public String getMapSectorBaseTICentro(Model model,
                                         @PathVariable Integer sector,
                                         @PathVariable Long baseId,
                                         HttpSession session) {
        // get base
        Base base = baseService.getById(baseId);
        model.addAttribute("base", base);
        model.addAttribute("sector", sector);

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            return "redirect:/req/login";
        }

        // space
        Optional<Space> spaceOptional = spaceService.getSpaceByUserId(userId);
        if (spaceOptional.isPresent()) {
            Space space = spaceOptional.get();
            model.addAttribute("space", space);

            // get map
            Optional<Map> map = mapService.getSector(space.getId(), sector);
            model.addAttribute("map", map.orElse(null));

            // get current
            BaseLevel baseLevelCurrent = null;
            if (map.isPresent() && map.get().getBaseLevel() != null) {
                baseLevelCurrent = baseLevelService.getById(map.get().getBaseLevel().getId());
            }
            model.addAttribute("baseLevelCurrent", baseLevelCurrent);

            // get next
            BaseLevel nextLevel = null;
            if (baseLevelCurrent != null) {
                nextLevel = baseLevelService.getNext(baseId, baseLevelCurrent.getLevel());
            } else {
                nextLevel = baseLevelService.getNext(baseId, 0);
            }

            model.addAttribute("nextLevel", nextLevel);

            // by default - check res
            model.addAttribute("is_has_agua", true);
            model.addAttribute("is_has_plastic", true);
            model.addAttribute("is_has_food", true);
            model.addAttribute("is_has_iron", true);

            // probamos res para nivel siguiente  - Martines
            if (nextLevel != null) {
                if (space.getResAgua() < nextLevel.getResAgua() || space.getResPlastic() < nextLevel.getResPlastic()
                        || space.getResFood() < nextLevel.getResFood() || space.getResIron() < nextLevel.getResIron()) {

                    // check res
                    if (space.getResAgua() < nextLevel.getResAgua()) {
                        model.addAttribute("is_has_agua", false);
                    }
                    if (space.getResPlastic() < nextLevel.getResPlastic()) {
                        model.addAttribute("is_has_plastic", false);
                    }
                    if (space.getResFood() < nextLevel.getResFood()) {
                        model.addAttribute("is_has_food", false);
                    }
                    if (space.getResIron() < nextLevel.getResIron()) {
                        model.addAttribute("is_has_iron", false);
                    }
                }
            } else {
                model.addAttribute("is_has_agua", false);
                model.addAttribute("is_has_plastic", false);
                model.addAttribute("is_has_food", false);
                model.addAttribute("is_has_iron", false);
            }

            List<Blog> posts = blogService.getAllPosts(space.getId());
            model.addAttribute("posts", posts);
        }

        return "map/map-bases/ti-centro/index";
    }

    /**
     * URL for pages with design for type: TI CENTRO
     *
     * @param model
     * @param sector
     * @param baseId
     * @return
     */
    @GetMapping("/map/{sector}/bases/{baseId}/electriciti")
    public String getMapSectorBaseElectriciti(Model model,
                                           @PathVariable Integer sector,
                                           @PathVariable Long baseId,
                                           HttpSession session) {
        // get base
        Base base = baseService.getById(baseId);
        model.addAttribute("base", base);
        model.addAttribute("sector", sector);

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            return "redirect:/req/login";
        }

        // space
        Optional<Space> spaceOptional = spaceService.getSpaceByUserId(userId);
        if (spaceOptional.isPresent()) {
            Space space = spaceOptional.get();
            model.addAttribute("space", space);

            // get map
            Optional<Map> map = mapService.getSector(space.getId(), sector);
            model.addAttribute("map", map.orElse(null));

            // get current
            BaseLevel baseLevelCurrent = null;
            if (map.isPresent() && map.get().getBaseLevel() != null) {
                baseLevelCurrent = baseLevelService.getById(map.get().getBaseLevel().getId());
            }
            model.addAttribute("baseLevelCurrent", baseLevelCurrent);

            // get next
            BaseLevel nextLevel = null;
            if (baseLevelCurrent != null) {
                nextLevel = baseLevelService.getNext(baseId, baseLevelCurrent.getLevel());
            } else {
                nextLevel = baseLevelService.getNext(baseId, 0);
            }

            model.addAttribute("nextLevel", nextLevel);

            // by default - check res
            model.addAttribute("is_has_agua", true);
            model.addAttribute("is_has_plastic", true);
            model.addAttribute("is_has_food", true);
            model.addAttribute("is_has_iron", true);

            // probamos res para nivel siguiente  - Martines
            if (nextLevel != null) {
                if (space.getResAgua() < nextLevel.getResAgua() || space.getResPlastic() < nextLevel.getResPlastic()
                        || space.getResFood() < nextLevel.getResFood() || space.getResIron() < nextLevel.getResIron()) {

                    // check res
                    if (space.getResAgua() < nextLevel.getResAgua()) {
                        model.addAttribute("is_has_agua", false);
                    }
                    if (space.getResPlastic() < nextLevel.getResPlastic()) {
                        model.addAttribute("is_has_plastic", false);
                    }
                    if (space.getResFood() < nextLevel.getResFood()) {
                        model.addAttribute("is_has_food", false);
                    }
                    if (space.getResIron() < nextLevel.getResIron()) {
                        model.addAttribute("is_has_iron", false);
                    }
                }
            } else {
                model.addAttribute("is_has_agua", false);
                model.addAttribute("is_has_plastic", false);
                model.addAttribute("is_has_food", false);
                model.addAttribute("is_has_iron", false);
            }
        }

        return "map/map-bases/electriciti/index";
    }


    //
    @GetMapping("/map/{sector}/bases/{baseId}")
    public String getMapSectorBase(Model model,
                                   @PathVariable Integer sector,
                                   @PathVariable Long baseId,
                                   HttpSession session) {
        // get base
        Base base = baseService.getById(baseId);
        model.addAttribute("base", base);
        model.addAttribute("sector", sector);

        // get baseLevels
        List<BaseLevel> baseLevels = baseLevelService.getAllByBaseIdOrderByLevelAsc(baseId);
        model.addAttribute("baseLevels", baseLevels);

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            return "redirect:/req/login";
        }

        // space
        Optional<Space> spaceOptional = spaceService.getSpaceByUserId(userId);
        if (spaceOptional.isPresent()) {
            Space space = spaceOptional.get();
            model.addAttribute("space", space);

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

            // by default - check res
            model.addAttribute("is_has_agua", true);
            model.addAttribute("is_has_plastic", true);
            model.addAttribute("is_has_food", true);
            model.addAttribute("is_has_iron", true);

            // probamos res para nivel siguiente  - Martines
            if (nextLevel != null) {
                if (space.getResAgua() < nextLevel.getResAgua() || space.getResPlastic() < nextLevel.getResPlastic()
                        || space.getResFood() < nextLevel.getResFood() || space.getResIron() < nextLevel.getResIron()) {

                    // check res
                    if (space.getResAgua() < nextLevel.getResAgua()) {
                        model.addAttribute("is_has_agua", false);
                    }
                    if (space.getResPlastic() < nextLevel.getResPlastic()) {
                        model.addAttribute("is_has_plastic", false);
                    }
                    if (space.getResFood() < nextLevel.getResFood()) {
                        model.addAttribute("is_has_food", false);
                    }
                    if (space.getResIron() < nextLevel.getResIron()) {
                        model.addAttribute("is_has_iron", false);
                    }
                }
            } else {
                model.addAttribute("is_has_agua", false);
                model.addAttribute("is_has_plastic", false);
                model.addAttribute("is_has_food", false);
                model.addAttribute("is_has_iron", false);
            }
        }

        return "map/map-base";
    }

    //
    @PostMapping("/map/{sector}/bases/{baseId}")
    public String getMapSectorBasePost(Model model,
                                       @PathVariable Integer sector,
                                       @PathVariable Long baseId,
                                       RedirectAttributes redirectAttributes,
                                       HttpSession session) {
        //
        Base base = baseService.getById(baseId);
        model.addAttribute("base", base);
        model.addAttribute("sector", sector);

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            return "redirect:/req/login";
        }

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

            // TODO: probar (!)
            // by default - check res
            model.addAttribute("is_has_agua", true);
            model.addAttribute("is_has_plastic", true);
            model.addAttribute("is_has_food", true);
            model.addAttribute("is_has_iron", true);

            // probamos res para nivel siguiente  - Martines
            if (nextLevel != null) {
                if (space.getResAgua() < nextLevel.getResAgua() || space.getResPlastic() < nextLevel.getResPlastic()
                        || space.getResFood() < nextLevel.getResFood() || space.getResIron() < nextLevel.getResIron() ) {

                    // TODO: probar (!)
                    // check res
                    if (space.getResAgua() < nextLevel.getResAgua()) {
                        model.addAttribute("is_has_agua", false);
                        log.info("is_has_agua: fasle");
                    }
                    if (space.getResPlastic() < nextLevel.getResPlastic()) {
                        model.addAttribute("is_has_plastic", false);
                        log.info("is_has_plastic: fasle");
                    }
                    if (space.getResFood() < nextLevel.getResFood()) {
                        model.addAttribute("is_has_food", false);
                        log.info("is_has_food: fasle");
                    }
                    if (space.getResIron() < nextLevel.getResIron()) {
                        model.addAttribute("is_has_iron", false);
                        log.info("is_has_iron: fasle");
                    }

                    //redirectAttributes.addFlashAttribute("error", "No tienes res para mejorar tu base ahora!");
                    //return "redirect:/map/" + sector + "/bases/" + baseId;

                } else {

                    // minus - res para esta mejorar'ia
                    space.setResAgua( space.getResAgua() - nextLevel.getResAgua() );
                    space.setResPlastic( space.getResPlastic() - nextLevel.getResPlastic() );
                    space.setResFood( space.getResFood() - nextLevel.getResFood() );
                    space.setResIron( space.getResIron() - nextLevel.getResIron() );
                    // plus - plus res desde este base
                    space.setResAgua( space.getResAgua() + nextLevel.getPlusResAgua() );
                    space.setResPlastic( space.getResPlastic() + nextLevel.getPlusResPlastic() );
                    space.setResFood( space.getResFood() + nextLevel.getPlusResFood() );
                    space.setResIron( space.getResIron() + nextLevel.getPlusResIron() );
                    // plus - do res desde este base
                    space.setDoResAgua( space.getDoResAgua() + nextLevel.getDoResAgua() );
                    space.setDoResPlastic( space.getDoResPlastic() + nextLevel.getDoResPlastic() );
                    space.setDoResFood( space.getDoResFood() + nextLevel.getDoResFood() );
                    space.setDoResIron( space.getDoResIron() + nextLevel.getDoResIron() );
                    spaceService.updateSpace(space);
                }
            } else {
                model.addAttribute("is_has_agua", false);
                model.addAttribute("is_has_plastic", false);
                model.addAttribute("is_has_food", false);
                model.addAttribute("is_has_iron", false);
            }

            // add new map or update
            if (map.isPresent()) { // update
                Map newMap = map.get();
                newMap.setBaseLevel(nextLevel);
                newMap.setBuildStartedAt(Instant.now()); // date

                mapService.addMap(newMap);

                // add post to Blog (Diario de a bardo)
                Blog blog = new Blog();
                blog.setAction("Construcción");
                blog.setDay(space.getDias().intValue());
                blog.setSpaceId(space.getId());
                blog.setSector(sector.longValue());
                blog.setTitle(nextLevel.getTitle());
                blog.setPost("Construcción " + nextLevel.getTitle() + ".");
                blog.setAuthor("Capitan");
                blogService.addPost(blog);

            } else {
                if (nextLevel != null && nextLevel.getId() != null) { // add
                    Map newMap = new Map();
                    newMap.setSpaceId(space.getId());
                    //newMap.setBaseLevel(nextLevel.getId());
                    newMap.setBaseLevel(nextLevel);
                    newMap.setSector((long) sector);
                    newMap.setBuildStartedAt(Instant.now()); // date

                    mapService.addMap(newMap);

                    // add post to Blog (Diario de a bardo)
                    Blog blog = new Blog();
                    blog.setAction("Construcción");
                    blog.setDay(space.getDias().intValue());
                    blog.setSpaceId(space.getId());
                    blog.setSector(sector.longValue());
                    blog.setTitle(nextLevel.getTitle());
                    blog.setPost("Construcción " + nextLevel.getTitle() + ".");
                    blog.setAuthor("Capitan");
                    blogService.addPost(blog);
                }
            }
        }

        return "redirect:/map";
    }

    //
    @GetMapping("/la-historia")
    public String pageLaHistoria(Model model, HttpSession session) {

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        }

        return "la-historia";
    }

    //
    @GetMapping("/sobre-nosotros")
    public String pageLaEmpresa(Model model, HttpSession session) {

        // get session
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            User user = userService.getById(userId);
            model.addAttribute("user", user);
        } else {
            return "redirect:/req/login";
        }

        return "la-empresa";
    }
}

