package com.example.demo.domain

import org.springframework.data.repository.CrudRepository

public interface UserRepository extends CrudRepository<User, Long> {
	void deleteById(Integer id)
	User findById(Integer id)
	void delete(User user)
	boolean existsByEmail(String email)
	User findByEmail(String email)
}