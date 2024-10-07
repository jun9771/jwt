package com.wnsud9771.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}
