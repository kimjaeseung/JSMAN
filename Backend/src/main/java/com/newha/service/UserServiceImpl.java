package com.newha.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newha.dao.UserDAO;
import com.newha.vo.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO dao;

	@Override
	public ArrayList<User> selectAll() {
		return dao.selectAll();
	}

	@Override
	public void insert(User u) {
		dao.insert(u);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public void update(User u) {
		dao.update(u);
	}

}
