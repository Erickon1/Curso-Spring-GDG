package com.example.demo.services
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired
import com.example.demo.domain.*

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
    Map login( String email, String password){
        Map request=[:]
        if (userRepository.existsByEmail(email)) {
            User user = userRepository.findByEmail(email)
            if (user.password==password) {
                Map userr = [
                "id":user.id,
                "name":user.name,
                "email":user.email
                ]
                request = [
                "status":202,
                "path" : "/add",
                "user": userr,
                "message":"Accepted."                ]
                
            }else{
                
                request = [
                "error":"Bad Request",
                "status":400,
                "path" : "/add",
                "message":"Correo o contraseña incorrectos."                
                ]
            }
        }else{

            request = [
            "error":"Bad Request",
            "status":400,
            "path" : "/add",
            "message":"El usuario no existe."     
            ]
        }
        request
    }
    
    def getUsers(){
            userRepository.findAll()
    }
    boolean deleteUser(Integer id){
        try{
            userRepository.deleteById(id)  
            true
        }
        catch(Exception e) {
            false
        }
    }

    boolean deleteUser2(Integer id){
        try {
                
            User user = userRepository.findById(id)
            userRepository.delete(user)
            true      
        }
        catch(Exception e) {
            false
        }
        
    }

    User putUser(String name, String email, String password, Integer id){
        User user = userRepository.findById(id)
        user.setName(name)
        user.setEmail(email)
        user.setPassword(password)
        userRepository.save(user)
        user
    }

    boolean existsByEmail(String email){
        userRepository.existsByEmail(email)
    }    
    User findByEmail(String email){
        userRepository.findByEmail(email)
    }
}