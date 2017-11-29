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
@RequestMapping("/api")
class UserController extends WebMvcConfigurerAdapter {

    @Autowired
    UserService userService
		
    @RequestMapping("/")
    String index() {
        return "Greetings from Spring Boot!";
    }


    @PostMapping(path="/add") 
    @ResponseBody def addNewUser (@RequestBody Map json) {
        //"name: ${json.name}\n email: ${json.email}\n password: ${json.password}"
        userService.createUser(json.name,json.email,json.password)

    }
    @GetMapping(path="/all") 
    @ResponseBody def getAllUsers () {
        userService.getAllUsers()

    }
    @DeleteMapping(path="/delete/{id}") 
    @ResponseBody def deleteUser (@PathVariable("id") Integer id) {
        if(userService.existsById(id)){
          userService.deleteUser(id)
        }else{
            Map request = [
            "timestamp":1424834682223,
            "error":"Bad Request",
            "status":400,
            "path" : "/delete",
            "message":"El id del usuario no existe."
            ]
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request)
            
        }


    }
    @PutMapping(path="/edit/{id}") 
    @ResponseBody def editUser (@RequestBody Map json, @PathVariable("id") Integer id) {
        if(userService.existsById(id)){
            userService.editUser(id,json.name,json.email,json.password)
        }else{
            Map request = [
            "timestamp":1424834682223,
            "error":"Bad Request",
            "status":400,
            "path" : "/edit",
            "message":"El id del usuario no existe."
            ]
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request)
            
        }

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


    @PostMapping(path="/add/{token}")  
    ResponseEntity addNewUserToken (@RequestBody String email,@PathVariable("token") String token ) {
    
    @PostMapping(path="/add")
    def addNewUser (@RequestBody String email) {
        if (userService.emailValid(email)) {
            if(!userService.existsByEmail(email)){
                userService.createUser(email)
            }else{
                Map request = [
                "timestamp":1424834682223,
                "error":"Bad Request",
                "status":400,
                "path" : "/add",
                "message":"El correo ya ha sido registrado."                ]
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request)
                
            }   
        }else{
            Map request = [
            "timestamp":1424834682223,
            "error":"Bad Request",
            "status":400,
            "path" : "/add",
            "message":"El correo ya ha sido registrado."
            ]
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request)
            
        }
                  
    }
    */

    
	@Override
    void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index")
        registry.addViewController("/home").setViewName("home")
       
    }

}
