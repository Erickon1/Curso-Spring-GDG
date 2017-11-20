package com.example.CursoGDG.services


import org.springframework.stereotype.Service
import com.example.CursoGDG.domain.*
import org.springframework.beans.factory.annotation.Autowired



@Service
class UserService {

	@Autowired
    UserRepository userRepository

	User createUser(String name, String email, String password){

        User user = new User()
        user.setName(name)
        user.setEmail(email)
        user.setPassword(password)
        userRepository.save(user)
        user

	}


    

}