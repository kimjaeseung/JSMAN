package com.newha.service;

import java.util.ArrayList;
import java.util.List;

import com.newha.vo.News;
import com.newha.vo.NewsImage;
import com.newha.vo.PostTag;
import com.newha.vo.UserScrapNews;

public interface NewsService {
	public int insertNews(News news);
	public int insertImages(ArrayList<NewsImage> images);
	public int insertPost(String name);
	public int insertUserScrapNews(UserScrapNews post);
	public int insertHashTag(ArrayList<String> tags);
	public int insertPostTag(PostTag tag);
	public int selectByUrl(String url);
	public int selectPostByName(String name);
	public int selectUserById(String id);
	public List<String> selectUserScrapNewsByPostNo(String postNo);
	public int selectHashTagByName(String name);
}
