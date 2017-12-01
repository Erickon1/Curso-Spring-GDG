package com.example.demo.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import com.example.demo.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

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
        def x=userService.getUsers()
        if(x!=[]){
            Map request = [
            "status":200,
            "path" : "/all",
            "users": x,
            "message":"Ok."                ]
            ResponseEntity.status(HttpStatus.OK).body(request)

        }else{
            Map request = [
            "error":"Bad Request",
            "status":400,
            "path" : "/all",
            "message":"No hay usuarios registrados."                ]
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request)
            
        }
    }

    @DeleteMapping(path="/delete/{id}")
    @ResponseBody def delete(@PathVariable("id") Integer id){
        if(userService.deleteUser(id)){
            Map request = [
            "status":202,
            "path" : "/add",
            "message":"Accepted."                ]
            ResponseEntity.status(HttpStatus.ACCEPTED).body(request)

        }else{
            Map request = [
            "error":"Bad Request",
            "status":400,
            "path" : "/add",
            "message":"El usuario no se pudo borrar."                ]
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request)
            
        }
    }

    @DeleteMapping(path="/delete2/{id}")
    @ResponseBody def delete2(@PathVariable("id") Integer id){
        if(userService.deleteUser2(id)){
            Map request = [
            "status":202,
            "path" : "/add",
            "message":"Accepted."                ]
            ResponseEntity.status(HttpStatus.ACCEPTED).body(request)
            
        }else{
            Map request = [
            "error":"Bad Request",
            "status":400,
            "path" : "/add",
            "message":"El usuario no se pudo borrar."
            ]
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request)
            
        }
    }

    @PutMapping(path="/put/{id}")
    @ResponseBody def put(@RequestBody Map user, @PathVariable("id") Integer id) {
    	def xx=userService.putUser(user.name, user.email, user.password, id)
        if(xx!= null){
            def x= userService.findByEmail(user.email)
            Map userr = [
            "id":x.id,
            "name":x.name,
            "email":x.email
            ]
            Map request = [
            "status":202,
            "path" : "/add",
            "user": userr,
            "message":"Accepted."                ]
            ResponseEntity.status(HttpStatus.ACCEPTED).body(request)
        }else{
            Map request = [
            "error":"Bad Request",
            "status":400,
            "path" : "/add",
            "message":"El usuario no se pudo editar."                ]
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(request)
            
        }
    }

    @PostMapping(path="/login")
    @ResponseBody def login(@RequestBody Map user) {
       Map x=userService.login(user.email, user.password)
       if(x.status==202){
            ResponseEntity.status(HttpStatus.ACCEPTED).body(x)
        }else{
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(x)

        }

    }


}
