package com.wnsud9771.service;

import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.wnsud9771.model.Board;
import com.wnsud9771.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	
	public Board getArticle(int articleNo) throws DataAccessException{
		Optional<Board> optionalBoard = boardRepository.findById(articleNo);
		Board board = null;
		if(optionalBoard.isPresent()) {
			board = optionalBoard.get();
		}
		
		return board;
		
	}
}
