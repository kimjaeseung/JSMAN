# newshi / [FE] Article 0125 task

## 1. App.vue
- google font 추가 (noto-serif)
- html, body, #app의 기본 font-family noto serif로 변경
```css
<style>
@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&family=Noto+Serif+KR:wght@700&display=swap');

html, body {
  font-family: Spectral, "Noto Serif KR", serif;
  font-weight: 900;
}

#app {
  font-family: Spectral, "Noto Serif KR", serif;
}

</style>
```

## 2. Views/Article.vue 
- header / section1(news image)/ section2(news content)로 레이아웃 나눔
```html
<v-container>
    <!-- news header -->
    <header>
      <div class="news-title text-center">
        {{ title }}
      </div>
      <div class="news-header text-center">
        {{ article_date }}
      </div>
      <div class="news-info d-flex justify-content-start">
        <div>신문사</div>
        <div>기자명</div>
        <div>
          <a :href="url">원본보기</a>
        </div>
        <v-spacer></v-spacer>
        <v-btn icon>
          <v-icon medium>mdi-bookmark</v-icon>
        </v-btn>
      </div>
    </header>
    <!-- main image -->
    <section>
      <div class="news-image">
        <v-img
          :src="image_path"
          max-width:="700"
          max-height="480"
        >
        </v-img>
        <div class="news-image-caption text-center">
          {{ news_image_caption }}
        </div>
      </div>
    </section>
    <!-- news body -->
    <section class="my-2">
      <div class="news-body-text">
        <div class="article-bot-summary">
          <p>핵심 요약</p>
          <p>{{ article_bot_summary }}</p>
        </div>
        <div>
          <span class="my-auto">본문 보기</span>
            <v-btn
              icon
              @click="show = !show"
            >
              <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
            </v-btn>
        </div>
        <v-expand-transition>
          <div v-show="show">
            <div class="article-content">
              <p>{{ content }}</p>
            </div>
          </div>
        </v-expand-transition>
      </div>
    </section>
  </v-container>
```
- CSS style 적용 
 - container 크기 최대 800으로 고정
 - 기타 레이아웃 조정
```css
.container {
  max-width: 800px;
}
.news-header {
  font-size: 12px;
  color: #999;
}
.news-title {
  margin-top: 30px;
  font-size: 35px;
  font-weight: 900;
  color: #222;
}
.news-info {
  margin-top: 30px;
}
.news-info > div {
  margin-right: .5rem;
  font-size: 15px;
}
.news-image-caption {
  color: #999;
  font-size: 12px;
  margin-bottom: 20px;
}
```

## 3. public/index.html
- material icon CDN 추가
```html
<!-- material icon CDN -->
<link href="https://cdn.jsdelivr.net/npm/@mdi/font@5.x/css/materialdesignicons.min.css" rel="stylesheet">
```