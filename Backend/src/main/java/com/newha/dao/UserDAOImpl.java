package com.newha.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newha.mapper.UserMapper;
import com.newha.vo.User;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	UserMapper mapper;

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
	public User selectOne(User u) {
		return mapper.selectOne(u);
	}
	
	@Override
	public User selectOneById(String id) {
		return mapper.selectOneById(id);
	}
}
