package com.example.CursoGDG.domain

import org.springframework.data.repository.CrudRepository


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {

	User findById(Integer id)
	User findByEmail(String email)
	User findByName(String name)
	boolean existsById(Integer id)
	boolean existsByName(String name)
	boolean existsByEmail(String email)
	boolean existsByPassword(String password)
	def delete(User user)
	//def editUser(String id,User user)

}