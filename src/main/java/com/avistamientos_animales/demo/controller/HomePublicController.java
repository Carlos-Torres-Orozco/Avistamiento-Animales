package com.avistamientos_animales.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePublicController {

    @GetMapping("/")
    public String homePublic() {
        return "home-public";  // archivo home-public.html
    }
}