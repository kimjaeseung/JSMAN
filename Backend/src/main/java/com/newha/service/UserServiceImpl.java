package com.newha.service;

import java.util.ArrayList;
import java.util.List;

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
	public int insert(User u) {
		return dao.insert(u);
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
	public List<User> searchUser(String keyword) {
		return dao.selectAllByKeyword(keyword);
	}

	@Override
	public int selectId(String id) {
		return dao.selectId(id);
	}

	@Override
	public int userNo(String id) {
		return dao.userNo(id);
	}

	@Override
	public User selectUser(int userNo) {
		return dao.selectUser(userNo);
	}

	@Override
	public ArrayList<Integer> follow(int userNo) {
		return dao.follow(userNo);
	}
	
}
