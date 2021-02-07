package com.newha.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.newha.vo.User;

public interface UserDAO {
	public ArrayList<User> selectAll();
	public ArrayList<Integer> follow(int userNo);
	
	public void insert(User u);
	public void delete(String id);
	public void update(User u);
	
	public User selectUser(int userNo);
	public User selectOne(User u);
	public User selectOneById(String id);
	public List<User> selectAllByKeyword(String keyword);
	public int selectId(String id);
	public int userNo(String id);

	public void thumbnailPath(String userNo, String thumbnail_path);
	public void insertTag(String id, String tag);
	
	public void subscribe(String id, String id2);
	public void subscdelete(String id, String id2);
	public int selectName(String name);

}