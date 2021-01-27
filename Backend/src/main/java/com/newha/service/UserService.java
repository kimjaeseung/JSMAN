package com.newha.service;

import java.util.ArrayList;
import java.util.Map;

import com.newha.vo.User;


public interface UserService {
	
	public ArrayList<User> selectAll();
	public int insert(User u);
	public void delete(String id);
	public void update(User u);
	public User login(User u);
	public User userInfo(String id);
	public int selectid(String id);
	public void inserttag(int userNo, String name);
	public int userNo(String id);
	
}
