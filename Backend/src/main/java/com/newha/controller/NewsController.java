package com.newha.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newha.service.NewsService;
import com.newha.vo.News;
import com.newha.vo.NewsImage;
import com.newha.vo.PostTag;
import com.newha.vo.UserScrapNews;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("UserController V1")
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class NewsController {
	
	@Autowired
	NewsService service;
	
	//WebDriver 설정
	private WebDriver driver;
    
	//Properties 설정
	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static String WEB_DRIVER_PATH = System.getProperty("user.dir") + "\\chromedriver.exe";

	@ApiOperation(value = "기사 파싱", notes = "url을 받아 기사를 파싱", response = Map.class)
	@PostMapping(value = "/article")
	public void getArticle(@ApiParam(value = "List", required = true)@RequestBody List<Map<String, String>> map) throws IOException {
		for (int i = 0; i < map.size(); i++) {
			String url = map.get(i).get("url");
			if(service.selectByUrl(url) > 0)//이미 존재하는 기사 생략
				continue;
			
			News news = new News();
			//JSOUP
			Connection.Response response = Jsoup.connect(url)
	                .method(Connection.Method.GET)
	                .execute();
			Document document = response.parse();
			
	        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
	        
	        //Selenium Driver SetUp
	        driver = new ChromeDriver();
	        try {
	            //get page
	            driver.get(url);
	            driver.findElement(By.linkText("요약봇")).click();
	            Thread.sleep(500);
	            String summary = driver.findElement(By.className("_contents_body")).getText();//요약봇
	            news.setArticle_bot_summary(summary);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            driver.close();
	        }
	        Elements temp = document.select(".end_photo_org");//이미지
	        ArrayList<String> img = new ArrayList<String>();//이미지 리스트
	        for (Element el5 : temp) {
	        	Elements eImg = el5.select("img");
	        	img.add(eImg.attr("abs:src"));
	        }
	        
	        Elements temp2 = document.select(".img_desc");//이미지 캡션
	        ArrayList<String> caption = new ArrayList<String>();//이미지 캡션 리스트
	        for (Element el5 : temp2) {
	        	caption.add(el5.text());
	        }
	        
	        String content = document.select("#articleBodyContents").text();//본문
	        String title = document.select("#articleTitle").text();//제목
	        String subtitle = document.select(".media_end_summary").text();//부제
	        String date = document.select(".t11").get(0).text();//날짜
	        String company = document.select(".press_logo > a").select("img").attr("title");//언론사
	        news.setContent(content);
	        news.setTitle(title);
	        if(subtitle.length() > 0)//서브타이틀 존재하면
	        	news.setSubtitle(subtitle);
	        news.setArticle_date(date);
	        news.setCompany(company);
	        news.setUrl(url);
			if(img.size() > 0)//이미지가 존재하면
				news.setImage_path(img.get(0));
			if(caption.size() > 0)//이미지 존재하고 이미지 캡션이 존재할경우
				news.setArticle_image_caption(caption.get(0));
			
			service.insertNews(news);
			
			if(img.size() > 0) {//이미지가 2개이상 존재할 경우 news_image에 넣어준다
				int newsNo = service.selectByUrl(url);
				ArrayList<NewsImage> images = new ArrayList<NewsImage>();
				for (String s : img) {
					images.add(new NewsImage(Integer.toString(newsNo), s));
				}
				int cnt = 0;
				for (String s : caption) {//캡션이 존재할경우 같이 넣어줌
					images.get(cnt++).setImage_caption(s);
				}
				service.insertImages(images);
			}
		}
		
		String name = map.get(0).get("name");
		String id = map.get(0).get("id");
		System.out.println(name);
		service.insertPost(name);
		
		int postNo = service.selectPostByName(name);
		int userNo = service.selectUserById(id);
		
		for (int i = 0; i < map.size(); i++) {
			String url = map.get(i).get("url");
			String summary = map.get(i).get("summary");
			int newsNo = service.selectByUrl(url);
			UserScrapNews post = new UserScrapNews();
			post.setCurator_summary(summary);
			post.setNewsNo(Integer.toString(newsNo));
			post.setPostNo(Integer.toString(postNo));
			post.setUserNo(Integer.toString(userNo));
			service.insertUserScrapNews(post);
		}
		
		List<String> list = service.selectUserScrapNewsByPostNo(Integer.toString(postNo));
		for (int i = 0; i < map.size(); i++) {
			String tag[] = map.get(i).get("tags").split(",");
			ArrayList<String> tags = new ArrayList<String>();
			for (int j = 0; j < tag.length; j++) {
				tags.add(tag[i]);
			}
			service.insertHashTag(tags);
			
			for (int j = 0; j < tag.length; j++) {
				int tagNo = service.selectHashTagByName(tag[j]);
				service.insertPostTag(new PostTag(list.get(i), Integer.toString(tagNo)));
			}
		}
			
	}
	
}
