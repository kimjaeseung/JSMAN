package com.newha.service;

import java.util.ArrayList;

import com.newha.vo.User;

public interface UserService {
	
	public ArrayList<User> selectAll();
	public void insert(User u);
	public void delete(User u);
	public void update(User u);
	
}
