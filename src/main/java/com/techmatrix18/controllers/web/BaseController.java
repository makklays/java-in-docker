package com.techmatrix18.controllers.web;

import com.techmatrix18.services.BaseService;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

/**
 * Base controller with endpoints for base pages
 *
 * @author Alexander Kuziv
 * @since 18.02.2025
 * @version 0.0.1
 */

@Controller
public class BaseController {

    Logger log = Logger.getLogger(BaseController.class.getName());

    private final BaseService baseService;

    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }


}

