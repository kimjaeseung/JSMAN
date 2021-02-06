package com.newha.service;

import java.util.ArrayList;
import java.util.List;

import com.newha.vo.HashTag;
import com.newha.vo.News;
import com.newha.vo.NewsImage;
import com.newha.vo.Post;
import com.newha.vo.PostTag;
import com.newha.vo.User;
import com.newha.vo.UserGoodNews;
import com.newha.vo.UserScrapNews;

public interface NewsService {
	public int insertNews(News news);
	public int insertImages(ArrayList<NewsImage> images);
	public int insertPost(Post post);
	public int insertUserScrapNews(UserScrapNews post);
	public int insertHashTag(String tag);
	public int insertPostTag(PostTag tag);
	public int insertUserGoodNews(UserGoodNews userGoodNews);
	public List<News> selectByUrl(String url);
	public List<Post> selectPostByName(String name);
	public List<Post> selectPostById(String id);
	public User selectUserById(String id);
	public List<UserScrapNews> selectUserScrapNewsByPostNo(String postNo);
	public List<HashTag> selectHashTagByName(String name);
	public UserGoodNews selectLikeNews(UserGoodNews userGoodNews);
	public UserGoodNews selectDisLikeNews(UserGoodNews userGoodNews);
	public int deletePost(String postNo);
	public int deleteScrap(String scarpNo);
	public int updatePost(Post post);
	public int updateScrap(UserScrapNews scrap);
	public List<String> selectUserScrapNews(String userNo);
	public News selectNews(String newsNo);
	public int increaseLike(String scrapNo);
	public int increaseDisLike(String scrapNo);
	public int decreaseLike(String scrapNo);
	public int decreaseDisLike(String scrapNo);
	public int updateLike(UserGoodNews userGoodNews);
	public int updateDisLike(UserGoodNews userGoodNews);
	public int updateSave(UserGoodNews userGoodNews);
}
