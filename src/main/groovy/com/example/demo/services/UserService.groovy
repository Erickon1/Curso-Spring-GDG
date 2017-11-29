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
    def getUsers(){
            userRepository.findAll()
    }
    void deleteUser(Integer id){
         userRepository.deleteById(id)  
    }

    void deleteUser2(Integer id){
        User user = userRepository.findById(id)
         userRepository.delete(user)  
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