package com.openclassrooms.PayMyBuddy.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.PayMyBuddy.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	User findByEmail(String email);

//	@Override
//	@SuppressWarnings("unchecked")
//	User save(User user);

	User getUser(String email);

	User getUserById(int userId);

}