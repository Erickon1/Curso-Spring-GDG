package com.example.CursoGDG.services


import org.springframework.stereotype.Service
import com.example.CursoGDG.domain.*
import org.springframework.beans.factory.annotation.Autowired

//import org.apache.commons.validator.routines.EmailValidator


@Service
class UserService {

	@Autowired
    UserRepository userRepository
    EmailValidator validator = EmailValidator.getInstance();
    
	User createUser(String name, String email, String password){

        User user = new User()
        user.setName(name)
        user.setEmail(email)
        user.setPassword(password)
        userRepository.save(user)
        user

	}

    /*

    User createUser(String email){
        User user = new User()
        def token =  UUID.randomUUID()
        def encrypt = this.checksum(token.toString())
        while(this.existsByToken(token.toString())) {
            encrypt = this.checksum(token.toString())
        }
        user.setToken(encrypt)
        user.setEmail(email)
        userRepository.save(user)
        user

    }

    String checksum( String input ) {
        def digest = java.security.MessageDigest.getInstance("SHA-256")
        digest.update( input.bytes )
        new BigInteger(1,digest.digest()).toString(16).padLeft(32, '0')
    }
    def emailValid(String email){
        validator.isValid(email)
    }

    def getAllUsers() {
        userRepository.findAll()
    }

    def findById(int id) {
        userRepository.findById(id)
    }
    def findByToken(String token) {
        userRepository.findByToken(token)
    }

    def findByEmail(String email) {
        userRepository.findByEmail(email)
    }

    def existsByToken(String token){
        userRepository.existsByToken(token)
    }

    def existsByEmail(String email){
        userRepository.existsByEmail(email)
    }

    */

    

}