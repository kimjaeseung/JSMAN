package com.newha.mapper;

import java.util.ArrayList;
import java.util.List;

import com.newha.vo.User;

public interface UserMapper {
	public ArrayList<User> selectAll();
	public int insert(User u);
	public void delete(String id);
	public void update(User u);
	public User selectOne(User u);
	public User selectOneById(String id);
	public List<User> selectAllByKeyword(String keyword);
	public int selectid(String id);
	public int userNo(String id);

}
