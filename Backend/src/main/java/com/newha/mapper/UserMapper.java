package com.newha.mapper;

import java.util.ArrayList;

import com.newha.vo.User;

public interface UserMapper {
public ArrayList<User> seletAll(); //모든 회원 조회
	
	public void insert(User u); //회원가입
	
	public void delete(User u); //회원삭제
	
	public void update(User u); //회원정보수정
}
