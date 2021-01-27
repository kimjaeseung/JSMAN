package com.newha.service;

import java.util.ArrayList;
import java.util.Map;

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
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public void update(User u) {
		dao.update(u);
	}

	@Override
	public User login(User u) {
		return dao.selectOne(u);
	}

	@Override
	public User userInfo(String id) {
		return dao.selectOneById(id);
	}

	@Override
	public int selectid(String id) {
		return dao.selectid(id);
	}

	@Override
	public int insert(User u) {
		return dao.insert(u);
	}

	@Override
	public void inserttag(int userNo, String name) {
		dao.inserttag(userNo, name);
		
	}

	@Override
	public int userNo(String id) {
		return dao.userNo(id);
	}


}
