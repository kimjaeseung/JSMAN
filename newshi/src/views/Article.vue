<template>
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
</template>

<script>
export default {
  name: "Article",
  data: function () {
    return {
      title: "재택근무서도 드러난 코로나 양극화",
      subtitle: "중소기업 직장인 53.2% “재택근무 경험 없다",
      content: "코로나19로 인한 양극화가 대기업과 중소기업의 재택근무에서도 드러났다. 잡코리아가 지난 13일부터 22일 동안 직장인 839명을 대상으로 벌인 온라인 설문조사에서 중소기업에 다닌다고 응답한 477명 중 254명(53.2%)이 재택근무를 한 경험이 없다고 답했다. 공기업·공공기관은 61명 중 49명(80.3%), 대기업은 110명 중 84명(76.4%), 중견기업은 191명 중 135명(70.7%)이 재택 근무 경험이 있었다. 대기업, 중견기업, 중소기업 공기업·공공기관 재직자 군 중 재택근무 경험이 없는 사람이 재택근무 경험이 있는 사람보다 더 많은 곳은 중소기업 뿐이었다. ‘재직중인 회사가 거리두기 단계예 따라 적극적으로 재택근무를 실시했냐’는 질문에도 중소기업만 유일하게 ‘그렇다’는 응답보다 ‘아니다’라는 응답이 많았다. 중소기업 재직자 269명(56.4%)이 해당 질문에 ‘아니다’ 라고 대답했다. 대기업 재직자 82명(74.5%)이 ‘그렇다’고 대답한 수치와 크게 상반된다. 공기업·공공기관 재직자 48명(78.7%), 중견기업 재직자 126명(66%)도 ‘그렇다’고 답했다. 또 재택근무 경험이 있다고 응답한 직장인을 대상으로 ‘평균 재택근무 실시 기간’을 묻자 중소기업 재직자의 평균 재택근무 일수는 48일로 나타났다. 이는 대기업(61일), 중견기업(51일)보다 적지만 공기업·공공기관(40일)보다는 많았다. 한편, ‘2021년에도 재택근무가 필요할까요?’라는 질문에는 ‘필요하다’는 응답이 기업 분류에 관계없이 794명(94.9%)으로 압도적으로 높았다. 거리두기가 완화될 때까지 재택근무를 해야 한다는 응답은 482명(57.4%), 코로나 종식까지 재택근무를 해야 한다는 응답은 312명(37.2%)이었다.",
      news_image_caption: '잡코리아 제공',
      image_path: "https://imgnews.pstatic.net/image/081/2021/01/25/0003158464_001_20210125125623755.jpg?type=w647",
      url: "https://www.seoul.co.kr/news/newsView.php?id=20210125500075&wlog_tag3=naver",
      article_date: '2021/01/25',
      article_last_date: '2021.01.25',
      article_bot_summary: "코로나19로 인한 양극화가 대기업과 중소기업의 재택근무에서도 드러났다. 공기업·공공기관은 61명 중 49명, 대기업은 110명 중 84명, 중견기업은 191명 중 135명이 재택 근무 경험이 있었다. 대기업, 중견기업, 중소기업 공기업·공공기관 재직자 군 중 재택근무 경험이 없는 사람이 재택근무 경험이 있는 사람보다 더 많은 곳은 중소기업 뿐이었다.",
      show: false,
    }
  },
};
</script>

<style>
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
</style>