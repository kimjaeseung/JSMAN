package com.newha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newha.dao.NewsDAO;
import com.newha.vo.News;
import com.newha.vo.NewsImage;
import com.newha.vo.PostTag;
import com.newha.vo.UserScrapNews;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	NewsDAO dao;
	
	@Override
	public int insertNews(News news) {
		return dao.insertNews(news);
	}
	
	@Override
	public int insertImages(ArrayList<NewsImage> images) {
		return dao.insertImages(images);
	}
	
	@Override
	public int selectByUrl(String url) {
		return dao.selectByUrl(url);
	}
	
	@Override
	public int insertPost(String name) {
		return dao.insertPost(name);
	}
	
	@Override
	public int selectPostByName(String name) {
		return dao.selectPostByName(name);
	}
	
	@Override
	public int selectUserById(String id) {
		return dao.selectUserById(id);
	}
	
	@Override
	public int insertUserScrapNews(UserScrapNews post) {
		return dao.insertUserScrapNews(post);
	}
	
	@Override
	public int insertHashTag(ArrayList<String> tags) {
		return dao.insertHashTag(tags);
	}
	
	@Override
	public List<String> selectUserScrapNewsByPostNo(String postNo) {
		return dao.selectUserScrapNewsByPostNo(postNo);
	}
	
	@Override
	public int selectHashTagByName(String name) {
		return dao.selectHashTagByName(name);
	}
	
	@Override
	public int insertPostTag(PostTag tag) {
		return dao.insertPostTag(tag);
	}
}
