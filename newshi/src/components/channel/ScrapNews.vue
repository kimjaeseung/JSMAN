<template>
  <div>
    <v-btn x-large text @click="backPage">
      <v-icon>mdi-arrow-left</v-icon>
    </v-btn>
    <v-container>
      <v-row v-for="(news, index) in scrap_news" :key="index">
        <v-col>
          <v-card>
            <v-container>
              <v-row>
                <v-col class="caption" cols=8>
                  {{news.article_date[0]}}
                </v-col>
                <v-col class="d-flex justify-end">
                  <v-btn fab x-small dark color="grey">
                    <v-icon>mdi-plus</v-icon>
                  </v-btn>
                </v-col>
              </v-row>
              <v-row no-gutters>
                <v-col class="ma-auto" cols=auto>
                  <v-avatar size="120" tile>
                    <v-img :src="news.image_path[0]"></v-img>
                  </v-avatar>
                </v-col>
                <v-col style="width:0">
                  <v-card-title>
                    <div class="ellipsis">{{news.title[0]}}</div>
                  </v-card-title>
                  <v-card-subtitle>
                    <div class="ellipsis" v-html="news.curator_summary[0]"></div>
                  </v-card-subtitle>
                  <v-card-actions class="d-flex justify-end">
                    <v-btn dark small :color="news.islike ? 'red':'grey'"> 
                      <v-icon dark>mdi-heart</v-icon>
                      <div>{{news.like_cnt[0]}}</div>
                    </v-btn>
                    <v-btn dark small :color="news.isdislike ? 'blue':'grey'"> 
                      <v-icon dark>mdi-heart-broken</v-icon>
                      <div>{{news.dislike_cnt[0]}}</div>
                    </v-btn>
                  </v-card-actions>
                </v-col>
              </v-row>
            </v-container>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      post_no: 0,
      id: String,
      scrap_news: []
    }
  },
  methods: {
    numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    backPage() {
      this.$router.go(-1);
    }
  },
  created() {
    this.post_no = this.$route.params.scrap_no;
    this.id = this.$route.params.id;

    // 스크랩 기사들 axios
    axios.get('http://localhost:8080/article/scraplist', 
            { params: { postNo: this.post_no } },
          ).then((response) => {
            this.scrap_news = response.data;
            // 사진없는 기사 이미지 추가할것.
          });
  },
}
</script>

<style>
  .ellipsis{
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        text-align: center;
    } 
</style>