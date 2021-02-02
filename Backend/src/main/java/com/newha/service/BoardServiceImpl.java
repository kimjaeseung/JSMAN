package com.newha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newha.dao.BoardDAO;
import com.newha.vo.Board;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDAO dao;
	
	@Override
	public void boardInsert(Board b) {
		dao.boardInsert(b);
	}

	@Override
	public void boardCreate(String id) {
		dao.boardCreate(id);
	}

	@Override
	public List<Integer> boardListNo(int id) {
		return dao.boardListNo(id);
	}

	@Override
	public Board selectBoard(int boardPostNo) {
		return dao.selectBoard(boardPostNo);
	}

	@Override
	public void boardUpdate(Board b) {
		dao.boardUpdate(b);
	}

	@Override
	public void boardDelete(String boardPostNo) {
		dao.boardDelete(boardPostNo);
	}

}
