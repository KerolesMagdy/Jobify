package com.keroles.jobify.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployerController {

    @PostMapping(value = "/employer/login")
    private void employerLogin(){}
}
