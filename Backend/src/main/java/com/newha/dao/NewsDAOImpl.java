package com.newha.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newha.mapper.NewsMapper;
import com.newha.vo.News;
import com.newha.vo.NewsImage;
import com.newha.vo.PostTag;
import com.newha.vo.UserScrapNews;

@Repository
public class NewsDAOImpl implements NewsDAO {
	
	@Autowired
	NewsMapper mapper; 
	
	@Override
	public int insertNews(News news) {
		return mapper.insertNews(news);
	}
	
	@Override
	public int insertImages(ArrayList<NewsImage> images) {
		return mapper.insertImages(images);
	}
	
	@Override
	public int selectByUrl(String url) {
		return mapper.selectByUrl(url);
	}
	
	@Override
	public int insertPost(String name) {
		return mapper.insertPost(name);
	}
	
	@Override
	public int selectPostByName(String name) {
		return mapper.selectPostByName(name);
	}
	
	@Override
	public int selectUserById(String id) {
		return mapper.selectUserById(id);
	}
	
	@Override
	public int insertUserScrapNews(UserScrapNews post) {
		return mapper.insertUserScrapNews(post);
	}
	
	@Override
	public int insertHashTag(ArrayList<String> tags) {
		return mapper.insertHashTag(tags);
	}
	
	@Override
	public List<String> selectUserScrapNewsByPostNo(String postNo) {
		return mapper.selectUserScrapNewsByPostNo(postNo);
	}
	
	@Override
	public int selectHashTagByName(String name) {
		return mapper.selectHashTagByName(name);
	}
	
	@Override
	public int insertPostTag(PostTag tag) {
		return mapper.insertPostTag(tag);
	}
}
