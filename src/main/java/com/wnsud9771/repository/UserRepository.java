package com.wnsud9771.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User getByUid(String uid);
}
