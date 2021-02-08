package com.newha.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newha.service.NewsService;
import com.newha.vo.News;
import com.newha.vo.NewsImage;
import com.newha.vo.Post;
import com.newha.vo.PostTag;
import com.newha.vo.UserGoodNews;
import com.newha.vo.UserScrapNews;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("UserController V1")
@RestController
@CrossOrigin(origins = "http://localhost:8080")
@EnableTransactionManagement
public class NewsController {
	
	@Autowired
	NewsService service;
	
	//WebDriver 설정
	private WebDriver driver;
    
	//Properties 설정
	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
//  window chromedriver
//	public static String WEB_DRIVER_PATH = System.getProperty("user.dir") + "\\chromedriver_window.exe";
//  aws 서버용 chromedriver
	public static String WEB_DRIVER_PATH = System.getProperty("user.dir") + "\\chromedriver_linux.exe";
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Transactional
	@ApiOperation(value = "기사 파싱", notes = "url을 받아 기사를 파싱", response = Map.class)
	@PostMapping(value = "/article")
	public ResponseEntity<Map<String, String>> getArticle(@ApiParam(value = "List", required = true)@RequestBody List<Map<String, String>> m) throws IOException {
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			for (int i = 0; i < m.size(); i++) {
				String url = m.get(i).get("url");
				System.out.println(url);
				if(service.selectByUrl(url).size() != 0)//이미 존재하는 기사 생략
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
					String newsNo = service.selectByUrl(url).get(0).getNewsNo();
					ArrayList<NewsImage> images = new ArrayList<NewsImage>();
					for (String s : img) {
						images.add(new NewsImage(newsNo, s));
					}
					int cnt = 0;
					for (String s : caption) {//캡션이 존재할경우 같이 넣어줌
						images.get(cnt++).setImage_caption(s);
					}
					service.insertImages(images);
				}
			}
			
			String name = m.get(0).get("name");
			String id = m.get(0).get("id");
			service.insertPost(new Post(name, id));
			
			List<Post> posts = service.selectPostByName(name);
			
			String postNo = posts.get(posts.size() - 1).getPostNo();
			String userNo = service.selectUserById(id).getUserNo();
			
			//UserScrapNew에 게시물을 넣어줌
			for (int i = 0; i < m.size(); i++) {
				String url = m.get(i).get("url");
				String summary = m.get(i).get("summary");
				String newsNo = service.selectByUrl(url).get(0).getNewsNo();
				UserScrapNews post = new UserScrapNews();
				post.setCurator_summary(summary);
				post.setNewsNo(newsNo);
				post.setPostNo(postNo);
				post.setUserNo(userNo);
				service.insertUserScrapNews(post);
			}
			
			//Hash태그에 태그를 넣어주고(db에서 중복방지) 해당 태그들과 게시물을 태그 연결(post_tag 테이블)
			List<UserScrapNews> temp = service.selectUserScrapNewsByPostNo(postNo);
			ArrayList<UserScrapNews> list =new ArrayList<UserScrapNews>(temp);
			for (int i = 0; i < m.size(); i++) {
				String tag[] = m.get(i).get("tags").split("#");
				for (int j = 1; j < tag.length; j++) {
					service.insertHashTag(tag[j]);
				}
				
				for (int j = 0; j < tag.length; j++) {
					String tagNo = service.selectHashTagByName(tag[j]).get(0).getTagNo();
					service.insertPostTag(new PostTag(list.get(i).getScrapNo(), tagNo));
				}
			}
			status = HttpStatus.ACCEPTED;
		} catch (IOException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "포스트 삭제", notes = "삭제 결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@DeleteMapping(value = "/article/post/{postNo}")
	public ResponseEntity<Map<String, String>> deletePost(@ApiParam(value = "String", required = true)@PathVariable String postNo){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			if(service.deletePost(postNo) > 0){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "스크랩 삭제", notes = "삭제 결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@DeleteMapping(value = "/article/scrap/{scrapNo}")
	public ResponseEntity<Map<String, String>> deleteScrap(@ApiParam(value = "String", required = true)@PathVariable String scrapNo){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			if(service.deleteScrap(scrapNo) > 0){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "포스트 수정(이름)", notes = "수정 결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@PutMapping(value = "/article/post")
	public ResponseEntity<Map<String, String>> updatePost(@ApiParam(value = "post", required = true)@RequestBody Post post){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			if(service.updatePost(post) > 0){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "스크랩 수정", notes = "수정 결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@PutMapping(value = "/article/scrap")
	public ResponseEntity<Map<String, String>> updateScrap(@ApiParam(value = "post", required = true)@RequestBody UserScrapNews scrap){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			if(service.updateScrap(scrap) > 0){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "포스트 목록", notes = "해당 id가 작성한 포스트 목록을 반환", response = List.class)
	@GetMapping(value = "/article/post/{id}")
	public ResponseEntity<List<Post>> getPost(@ApiParam(value = "String", required = true)@PathVariable String id){
		HttpStatus status = null;
		List<Post> list = new ArrayList<Post>();
		try {
			list = service.selectPostById(id);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<Post>>(list, status);
	}
	
	@ApiOperation(value = "스크랩 목록", notes = "해당 포스트에 포함된 스크랩 목록을 반환", response = List.class)
	@GetMapping(value = "/article/scrap/{postNo}")
	public ResponseEntity<List<UserScrapNews>> getScrap(@ApiParam(value = "String", required = true)@PathVariable String postNo){
		HttpStatus status = null;
		List<UserScrapNews> list = new ArrayList<UserScrapNews>();
		try {
			list = service.selectUserScrapNewsByPostNo(postNo);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<List<UserScrapNews>>(list, status);
	}
	
	@ApiOperation(value = "좋아요 증가", notes = "결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@GetMapping(value = "/article/increalike/{scrapNo}")
	public ResponseEntity<Map<String, String>> increaseLike(@ApiParam(value = "String", required = true)@PathVariable String scrapNo){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			if(service.increaseLike(scrapNo) > 0){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "좋아요 감소", notes = "결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@GetMapping(value = "/article/decrealike/{scrapNo}")
	public ResponseEntity<Map<String, String>> decreaseLike(@ApiParam(value = "String", required = true)@PathVariable String scrapNo){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			if(service.decreaseLike(scrapNo) > 0){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "싫어요 증가", notes = "결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@GetMapping(value = "/article/increadislike/{scrapNo}")
	public ResponseEntity<Map<String, String>> increaseDisLike(@ApiParam(value = "String", required = true)@PathVariable String scrapNo){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			if(service.increaseDisLike(scrapNo) > 0){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "싫어요 감소", notes = "결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@GetMapping(value = "/article/decreadislike/{scrapNo}")
	public ResponseEntity<Map<String, String>> decreaseDisLike(@ApiParam(value = "String", required = true)@PathVariable String scrapNo){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			if(service.decreaseDisLike(scrapNo) > 0){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "유저가 좋아요 누른 목록 등록/해제", notes = "결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@GetMapping(value = "/article/like/{scrapNo}/{id}")
	public ResponseEntity<Map<String, String>> updateLike(@ApiParam(value = "String", required = true)@PathVariable String scrapNo, @ApiParam(value = "String", required = true)@PathVariable String id){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			String userNo = service.selectUserById(id).getUserNo();
			UserGoodNews userGoodNews = new UserGoodNews();
			userGoodNews.setScrapNo(scrapNo);
			userGoodNews.setUserNo(userNo);
			service.insertUserGoodNews(userGoodNews);
			if(service.updateLike(userGoodNews) > 0){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "유저가 싫어요 누른 목록 등록/해제", notes = "결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@GetMapping(value = "/article/dislike/{scrapNo}/{id}")
	public ResponseEntity<Map<String, String>> updateDisLike(@ApiParam(value = "String", required = true)@PathVariable String scrapNo, @ApiParam(value = "String", required = true)@PathVariable String id){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			String userNo = service.selectUserById(id).getUserNo();
			UserGoodNews userGoodNews = new UserGoodNews();
			userGoodNews.setScrapNo(scrapNo);
			userGoodNews.setUserNo(userNo);
			service.insertUserGoodNews(userGoodNews);
			if(service.updateDisLike(userGoodNews) > 0){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "유저가 저장 누른 목록 등록/해제", notes = "결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@GetMapping(value = "/article/save/{scrapNo}/{id}")
	public ResponseEntity<Map<String, String>> updateSave(@ApiParam(value = "String", required = true)@PathVariable String scrapNo, @ApiParam(value = "String", required = true)@PathVariable String id){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			String userNo = service.selectUserById(id).getUserNo();
			UserGoodNews userGoodNews = new UserGoodNews();
			userGoodNews.setScrapNo(scrapNo);
			userGoodNews.setUserNo(userNo);
			service.insertUserGoodNews(userGoodNews);
			if(service.updateSave(userGoodNews) > 0){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "유저가 좋아요 눌럿는지 여부 파악", notes = "결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@GetMapping(value = "/article/islike/{scrapNo}/{id}")
	public ResponseEntity<Map<String, String>> isLike(@ApiParam(value = "String", required = true)@PathVariable String scrapNo, @ApiParam(value = "String", required = true)@PathVariable String id){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			String userNo = service.selectUserById(id).getUserNo();
			UserGoodNews userGoodNews = new UserGoodNews();
			userGoodNews.setScrapNo(scrapNo);
			userGoodNews.setUserNo(userNo);
			service.insertUserGoodNews(userGoodNews);
			userGoodNews = service.selectLikeNews(userGoodNews);
			if(userGoodNews.getIs_like().equals("1")){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
	@ApiOperation(value = "유저가 싫어요 눌럿는지 여부 파악", notes = "결과 'success' 또는 'fail' 문자열을 리턴", response = Map.class)
	@GetMapping(value = "/article/isdislike/{scrapNo}/{id}")
	public ResponseEntity<Map<String, String>> isDisLike(@ApiParam(value = "String", required = true)@PathVariable String scrapNo, @ApiParam(value = "String", required = true)@PathVariable String id){
		Map<String, String> map = new HashMap<>();
		HttpStatus status = null;
		try {
			String userNo = service.selectUserById(id).getUserNo();
			UserGoodNews userGoodNews = new UserGoodNews();
			userGoodNews.setScrapNo(scrapNo);
			userGoodNews.setUserNo(userNo);
			service.insertUserGoodNews(userGoodNews);
			userGoodNews = service.selectLikeNews(userGoodNews);
			if(userGoodNews.getIs_dislike().equals("1")){
				map.put("message", SUCCESS);
			}else {
				map.put("message", FAIL);
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			e.printStackTrace();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, String>>(map, status);
	}
	
}
