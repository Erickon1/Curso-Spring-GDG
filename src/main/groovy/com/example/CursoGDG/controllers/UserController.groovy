package com.example.CursoGDG.controllers

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.*

import org.springframework.stereotype.Controller

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.beans.factory.annotation.Autowired

import com.example.CursoGDG.services.UserService



@RestController
//@Controller
@Configuration
class UserController extends WebMvcConfigurerAdapter {

    @Autowired
    UserService userService
		
    @RequestMapping("/")
    String index() {
        return "Greetings from Spring Boot!";
    }


    @PostMapping(path="/add") 
    @ResponseBody String addNewUser (@RequestBody Map json) {
        //"name: ${json.name}\n email: ${json.email}\n password: ${json.password}"
        userService.createUser(json.name,json.email,json.password)

    }
    
    /*
    @PostMapping(path="/add") 
    @ResponseBody String addNewUser (
        @RequestParam String name, 
        @RequestParam String email,
        @RequestParam String password
        ) {

        userService.createUser(name,email,password)

    }
    */

    
	@Override
    void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index")
        registry.addViewController("/home").setViewName("home")
       
    }

}
