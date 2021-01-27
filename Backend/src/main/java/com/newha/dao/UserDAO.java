package com.newha.dao;

import java.util.ArrayList;
import java.util.List;

import com.newha.vo.User;

public interface UserDAO {
	public ArrayList<User> selectAll();
	public int insert(User u);
	public void delete(String id);
	public void update(User u);
	public User selectOne(User u);
	public User selectOneById(String id);
	public List<User> selectAllByKeyword(String keyword);
	
}