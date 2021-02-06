package com.newha.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newha.dao.NewsDAO;
import com.newha.vo.HashTag;
import com.newha.vo.News;
import com.newha.vo.NewsImage;
import com.newha.vo.Post;
import com.newha.vo.PostTag;
import com.newha.vo.User;
import com.newha.vo.UserGoodNews;
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
	public List<News> selectByUrl(String url) {
		return dao.selectByUrl(url);
	}
	
	@Override
	public int insertPost(Post post) {
		return dao.insertPost(post);
	}
	
	@Override
	public List<Post> selectPostByName(String name) {
		return dao.selectPostByName(name);
	}
	
	@Override
	public User selectUserById(String id) {
		return dao.selectUserById(id);
	}
	
	@Override
	public int insertUserScrapNews(UserScrapNews post) {
		return dao.insertUserScrapNews(post);
	}
	
	@Override
	public int insertHashTag(String tag) {
		return dao.insertHashTag(tag);
	}
	
	@Override
	public List<UserScrapNews> selectUserScrapNewsByPostNo(String postNo) {
		return dao.selectUserScrapNewsByPostNo(postNo);
	}
	
	@Override
	public List<HashTag> selectHashTagByName(String name) {
		return dao.selectHashTagByName(name);
	}
	
	@Override
	public int insertPostTag(PostTag tag) {
		return dao.insertPostTag(tag);
	}
	
	@Override
	public int deletePost(String postNo) {
		return dao.deletePost(postNo);
	}
	
	@Override
	public int deleteScrap(String scarpNo) {
		return dao.deleteScrap(scarpNo);
	}
	
	@Override
	public int updatePost(Post post) {
		return dao.updatePost(post);
	}
	
	@Override
	public int updateScrap(UserScrapNews scrap) {
		return dao.updateScrap(scrap);
	}
	
	@Override
	public List<Post> selectPostById(String id) {
		return dao.selectPostById(id);
	}

	@Override
	public List<String> selectUserScrapNews(String userNo) {
		return dao.selectUserScrapNews(userNo);
	}

	@Override
	public News selectNews(String newsNo) {
		return dao.selectNews(newsNo);
	}
	
	@Override
	public int increaseLike(String scrapNo) {
		return dao.increaseLike(scrapNo);
	}
	
	@Override
	public int increaseDisLike(String scrapNo) {
		return dao.increaseDisLike(scrapNo);
	}
	
	@Override
	public int decreaseLike(String scrapNo) {
		return dao.decreaseLike(scrapNo);
	}
	
	@Override
	public int decreaseDisLike(String scrapNo) {
		return dao.decreaseDisLike(scrapNo);
	}
	
	@Override
	public int updateLike(UserGoodNews userGoodNews) {
		return dao.updateLike(userGoodNews);
	}
	
	@Override
	public int insertUserGoodNews(UserGoodNews userGoodNews) {
		return dao.insertUserGoodNews(userGoodNews);
	}
	
	@Override
	public int updateDisLike(UserGoodNews userGoodNews) {
		return dao.updateDisLike(userGoodNews);
	}
	
	@Override
	public int updateSave(UserGoodNews userGoodNews) {
		return dao.updateSave(userGoodNews);
	}
	
	@Override
	public UserGoodNews selectLikeNews(UserGoodNews userGoodNews) {
		return dao.selectLikeNews(userGoodNews);
	}
	
	@Override
	public UserGoodNews selectDisLikeNews(UserGoodNews userGoodNews) {
		return dao.selectDisLikeNews(userGoodNews);
	}
}
