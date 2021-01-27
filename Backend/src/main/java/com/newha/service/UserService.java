package com.newha.service;

import java.util.ArrayList;
import java.util.List;

import com.newha.vo.User;


public interface UserService {
	
	public ArrayList<User> selectAll();
	public void insert(User u);
	public void delete(String id);
	public void update(User u);
	public User login(User u);
	public User userInfo(String id);
	public List<User> searchUser(String keyword);
}
