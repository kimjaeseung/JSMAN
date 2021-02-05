package com.newha.service;

import java.util.ArrayList;
import java.util.List;

import com.newha.vo.HashTag;
import com.newha.vo.News;
import com.newha.vo.NewsImage;
import com.newha.vo.Post;
import com.newha.vo.PostTag;
import com.newha.vo.User;
import com.newha.vo.UserScrapNews;

public interface NewsService {
	public int insertNews(News news);
	public int insertImages(ArrayList<NewsImage> images);
	public int insertPost(Post post);
	public int insertUserScrapNews(UserScrapNews post);
	public int insertHashTag(String tag);
	public int insertPostTag(PostTag tag);
	public List<News> selectByUrl(String url);
	public List<Post> selectPostByName(String name);
	public List<Post> selectPostById(String id);
	public User selectUserById(String id);
	public List<UserScrapNews> selectUserScrapNewsByPostNo(String postNo);
	public List<HashTag> selectHashTagByName(String name);
	public int deletePost(String postNo);
	public int deleteScrap(String scarpNo);
	public int updatePost(Post post);
	public int updateScrap(UserScrapNews scrap);
}
