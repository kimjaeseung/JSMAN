package com.newha.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newha.dao.UserDAO;
import com.newha.vo.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;

	@Override
	public ArrayList<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User u) {
		// TODO Auto-generated method stub

	}

	@Override
	public User login(User u) {
		return dao.selectOne(u);
	}
	
	@Override
	public User userInfo(String id) {
		return dao.selectOneById(id);
	}
}
