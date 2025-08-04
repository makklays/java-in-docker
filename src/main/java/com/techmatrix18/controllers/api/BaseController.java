package com.techmatrix18.controllers.api;

import com.techmatrix18.model.Base;
import com.techmatrix18.services.BaseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@Tag(name = "Bases", description = "Base management API")
@RequestMapping("/api/v1/bases")
public class BaseController {

    Logger log = Logger.getLogger(UserController.class.getName());

    private final BaseService baseService;

    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

    /**
     * Get all bases
     *
     * @return json response all bases
     */
    @PostMapping(value = "/all")
    public List<Base> getAllBases() {

        List<Base> bases = baseService.getAll();

        log.info(bases.toString());

        return bases;
    }

    /**
     * Get page of bases
     *
     * @return
     */
    @GetMapping
    public List<Base> getBases(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sortBy) {
        //
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return baseService.getAllBases(pageable);
    }
}

