package com.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springmvc.binding.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.isDeleted =false")
	List<User> findAllActiveUsers();  // exc

}
