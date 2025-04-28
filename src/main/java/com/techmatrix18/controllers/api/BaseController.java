package com.techmatrix18.controllers.api;

import com.techmatrix18.config.RabbitMQConfig;
import com.techmatrix18.model.Base;
import com.techmatrix18.model.User;
import com.techmatrix18.services.BaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/bases")
public class BaseController {

    Logger log = Logger.getLogger(RegistrationController.class.getName());

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
}

