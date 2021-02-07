<template>
  <v-container>
    <!-- news header -->      
    <header>
      <div class="news-title text-center">
        {{ news.title }}
      </div>
      <div class="news-header text-center">
        {{ news.article_date }}
      </div>
      <div class="news-info d-flex justify-content-start">
        <div>신문사</div>
        <div>기자명</div>
        <div>
          <a :href="news.url">원본보기</a>
        </div>
        <v-spacer></v-spacer>
        <v-btn 
          @click="save()"
          icon
        >
          <v-icon medium v-if="this.saved === false">mdi-bookmark</v-icon>
          <v-icon medium v-else color="#ff9800">mdi-bookmark</v-icon>
        </v-btn>
      </div>
    </header>
    <!-- main image -->
    <template>
      <div
        v-touch="{
          left: () => swipe('Left'),
          right: () => swipe('Right'),
        }"
      >
    <section>
      <div class="news-image">
        <v-img
          :src="news.image_path"
          max-width:="700"
          max-height="480"
        >
        </v-img>
        <div class="news-image-caption text-center">
          {{ news.news_image_caption }}
        </div>
      </div>
    </section> 
    <!-- news body -->
    <section class="my-2" v-if="swipeDirection === 'Right'">
      <div class="news-body-text">
        <div class="article-bot-summary">
          <h3 class="text-center">핵심 요약</h3>
          <p>{{ news.article_bot_summary }}</p>
        </div>
        <div class="text-center my-2">
          <span class="my-auto">기사 본문</span>
            <v-btn
              icon
              @click="news.show = !news.show"
            >
              <v-icon>{{ news.show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
            </v-btn>
        </div>
        <v-expand-transition>
        <div v-show="news.show">
          <div class="article-content">
            <p>{{ news.content }}</p>
          </div>
        </div>
        </v-expand-transition>
        <div class="news-body-text hidden">
          <div class="article-bot-summary">
            <h3 class="text-center">큐레이터의 오피니언</h3>
            <p>{{ news.article_bot_summary }}</p>
          </div>
          <div class="d-flex my-2">
            <div class="mx-auto">
              <v-btn 
                icon 
                class="mx-2"
                @click="like()"
              >
                <v-icon large v-if="this.liked === false">mdi-thumb-up-outline</v-icon>
                <v-icon large v-else color="#ff9800">mdi-thumb-up</v-icon>
              </v-btn>
              <v-btn 
                icon 
                class="mx-2"
                @click="dislike()"
              >
                <v-icon large v-if="this.disliked === false">mdi-thumb-down-outline</v-icon>
                <v-icon large v-else color="#ff9800">mdi-thumb-down</v-icon>
              </v-btn>
            </div>
          </div>
          <p class="text-center">큐레이터의 오피니언이 괜찮았나요?</p>
        </div>
      </div>
    </section>
    <section class="my-2" v-if="swipeDirection === 'Left'">
      <div class="news-body-text">
        <div class="article-bot-summary">
          <p class="text-center">큐레이터의 오피니언</p>
          <p>{{ news.article_bot_summary }}</p>
        </div>
        <div class="d-flex">
          <div class="mx-auto">
            <v-btn 
              icon 
              class="mx-2"
              @click="like()"
            >
              <v-icon large v-if="this.liked === false">mdi-thumb-up-outline</v-icon>
              <v-icon large v-else color="#ff9800">mdi-thumb-up</v-icon>
            </v-btn>
            <v-btn 
              icon 
              class="mx-2"
              @click="dislike()"
            >
              <v-icon large v-if="this.disliked === false">mdi-thumb-down-outline</v-icon>
              <v-icon large v-else color="#ff9800">mdi-thumb-down</v-icon>
            </v-btn>
          </div>
        </div>
        <p class="text-center">큐레이터의 오피니언이 괜찮았나요?</p>
      </div>
    </section>
    </div>
    </template>
  </v-container>
</template>

<script>

export default {
  name: "ArticleDetail",
  props: [
    'newsInfo',
  ],
  methods: {
      swipe (direction) {
        this.swipeDirection = direction
      },
      save () {
      this.saved = !this.saved;
    },
      like () {
      this.liked = !this.liked;
    },
      dislike () {
      this.disliked = !this.disliked;
    },
  },
  data: function () {
    return {
      news: this.newsInfo,
      swipeDirection: 'Right',
      saved: false,
      liked: false,
      disliked: false,
    }
  },
}
</script>

<style>
@media(max-width: 560px){
  .hidden {
    display: none;
  }
}
</style>