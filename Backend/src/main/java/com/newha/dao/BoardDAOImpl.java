package com.newha.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newha.mapper.BoardMapper;
import com.newha.vo.Board;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	BoardMapper mapper;
	
	@Override
	public void boardInsert(Board b) {
		mapper.boardInsert(b);
	}

	@Override
	public void boardCreate(String id) {
		mapper.boardCreate(id);
	}

	@Override
	public List<Integer> boardListNo(int id) {
		return mapper.boardListNo(id);
	}

	@Override
	public Board selectBoard(int boardPostNo) {
		return mapper.selectBoard(boardPostNo);
	}

	@Override
	public void boardUpdate(Board b) {
		mapper.boardUpdate(b);
	}

	@Override
	public void boardDelete(String boardPostNo) {
		mapper.boardDelete(boardPostNo);
	}
	
}
