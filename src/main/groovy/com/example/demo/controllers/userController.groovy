package com.example.demo.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import com.example.demo.services.UserService
import org.springframework.*

@RestController
//@Controller
@RequestMapping("/api")
class UserController {

	@Autowired
    UserService userService
		
    
    @RequestMapping("/")
    String index() {
        return "Greetings from Spring Boot!"
    }

    //@RequestMapping("/add")
    @PostMapping(path="/add") 
    @ResponseBody def add(@RequestBody Map json) {
        if(!userService.existsByEmail(json.email)){
        	userService.createUser(json.name,json.email,json.password)	
        /*
        def x= userService.findByEmail(json.email)
        Map user = [
        "id":x.id,
        "name":x.name,
        "email":x.email
        ]
        Map request = [
        "status":201,
        "path" : "/add",
        "user": user,
        "message":"Created."                ]
        ResponseEntity.status(HttpStatus.CREATED).body(request)
        */
        }else{
        Map request = [
        "error":"Bad Request",
        "status":400,
        "path" : "/add",
        "message":"El correo ya ha sido registrado."                ]
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request)

        }
        
    }

    @GetMapping(path="/all") 
    @ResponseBody def all() {
        userService.getUsers()
    }

    @DeleteMapping(path="/delete/{id}")
    @ResponseBody def delete(@PathVariable("id") Integer id){
    	userService.deleteUser(id)
    }

    @DeleteMapping(path="/delete2/{id}")
    @ResponseBody def delete2(@PathVariable("id") Integer id){
    	userService.deleteUser2(id)

    }

    @PutMapping(path="/Put/{id}")
    @ResponseBody def put(@RequestBody Map user, @PathVariable("id") Integer id) {
    	userService.putUser(user.name, user.email, user.password, id)
    } 
}
