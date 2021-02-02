package com.newha.service;

import java.util.List;

import com.newha.vo.Board;

public interface BoardService {

	public void boardInsert(Board b);

	public void boardCreate(String id);

	public List<Integer> boardListNo(int userNo);

	public Board selectBoard(int boardPostNo);

	public void boardUpdate(Board b);

	public void boardDelete(String boardPostNo);

}
