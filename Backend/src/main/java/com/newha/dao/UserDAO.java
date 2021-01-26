package com.newha.dao;

import java.util.ArrayList;

import com.newha.vo.User;

public interface UserDAO {
	public ArrayList<User> selectAll();
	public void insert(User u);
	public void delete(User u);
	public void update(User u);
	
}
 