package com.example.CursoGDG.services


import org.springframework.stereotype.Service
import com.example.CursoGDG.domain.*
import org.springframework.beans.factory.annotation.Autowired

import org.apache.commons.validator.routines.EmailValidator


@Service
class UserService {

	@Autowired
    UserRepository userRepository
    EmailValidator validator = EmailValidator.getInstance();
    
	User createUser(String name, String email, String password){

        User user = new User()
        user.setName(name)
        user.setEmail(email)
        def encrypt = this.checksum(password)
        while(this.existsByPassword(encrypt)) {
            encrypt = this.checksum(password)
        }
        user.setPassword(encrypt)
        userRepository.save(user)
        user

	}

	def emailValid(String email){
        validator.isValid(email)
    }

    def getAllUsers() {
        userRepository.findAll()
    }
	def deleteUser(Integer id) {
		User user = userRepository.findById(id)
        userRepository.delete(user)
    }
	User editUser(Integer id,String name, String email, String password) {
		User user = userRepository.findById(id)
		user.setName(name)
        user.setEmail(email)
        def encrypt = this.checksum(password)
        while(this.existsByPassword(encrypt)) {
            encrypt = this.checksum(password)
        }
        user.setPassword(encrypt)
        userRepository.save(user)
        user
    }
    def findById(Integer id) {
        userRepository.findById(id)
    }
    def findByName(String name) {
        userRepository.findByName(name)
    }

    def findByEmail(String email) {
        userRepository.findByEmail(email)
    }


    def existsById(Integer id){
        userRepository.existsById(id)
    }
    def existsByName(String name){
        userRepository.existsByName(name)
    }

    def existsByEmail(String email){
        userRepository.existsByEmail(email)
    }
    def existsByPassword(String password){
        userRepository.existsByPassword(password)
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
	*/
    String checksum( String input ) {
        def digest = java.security.MessageDigest.getInstance("SHA-256")
        digest.update( input.bytes )
        new BigInteger(1,digest.digest()).toString(16).padLeft(32, '0')
    }
    


    

}