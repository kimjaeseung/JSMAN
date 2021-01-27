package com.newha.dao;

import java.util.ArrayList;

import com.newha.vo.User;

public interface UserDAO {
	public ArrayList<User> selectAll();
	public void insert(User u);
	public void delete(String id);
	public void update(User u);
	
}
 