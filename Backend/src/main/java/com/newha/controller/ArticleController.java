package com.newha.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("UserController V1")
@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ArticleController {
	
	//WebDriver 설정
	private WebDriver driver;
	private WebElement element;
	private String url;
    
	//Properties 설정
	public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static String WEB_DRIVER_PATH = System.getProperty("user.dir") + "\\chromedriver.exe";

	@ApiOperation(value = "기사 파싱", notes = "url을 받아 기사를 파싱", response = Map.class)
	@PostMapping(value = "/article")
	public void getArticle(@RequestBody String url) throws IOException {
		Connection.Response response = Jsoup.connect(url)
                .method(Connection.Method.GET)
                .execute();
		Document document = response.parse();
		
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        
        //Driver SetUp
        driver = new ChromeDriver();
        
        try {
            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
            driver.get(url);
            driver.findElement(By.linkText("요약봇")).click();
            Thread.sleep(1000);
            System.out.println(driver.findElement(By.className("_contents_body")).getText());
    
        } catch (Exception e) {
            
            e.printStackTrace();
        
        } finally {
 
            driver.close();
        }
		
		
//		String html = document.text();
		Elements temp1 = document.select("#articleBodyContents");//본문
		Elements temp2 = document.select("#articleTitle");//제목
		Elements temp3 = document.select(".media_end_summary");//부제
		Elements temp4 = document.select(".t11");//날짜
		Elements temp5 = document.select(".end_photo_org");//이미지
		Elements temp6 = document.select(".press_logo > a").select("img");//언론사
		String text = temp1.text();
		String title = temp2.text();
		String subTitle = temp3.text();
		String date = temp4.text();
//		String token[] = temp1.html().split("<br>");
//		for (int i = 0; i < token.length; i++) {
//			System.out.println(token[i]);
//		}
//		System.out.println(title);
//		System.out.println(subTitle);
//		System.out.println(date);
		
//		ArrayList<String> img = new ArrayList<String>();
//		for (Element el5 : temp5) {
//			Elements eImg = el5.select("img");
//			System.out.println(eImg.attr("abs:src"));
//			img.add(eImg.attr("abs:src"));
//		}
		
//		System.out.println(temp6.attr("title")); //언론사
	}
	
}
