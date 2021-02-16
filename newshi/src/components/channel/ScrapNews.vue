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
                  <div @click="move(news)" style="cursor: pointer">
                  <v-card-title>
                    <div class="ellipsis">{{news.title[0]}}</div>
                  </v-card-title>
                  <v-card-subtitle>
                    <div class="ellipsis" v-html="news.curator_summary[0]"></div>
                  </v-card-subtitle>
                  </div>
                  <v-card-actions class="d-flex justify-end">
                    <v-btn dark small :color="news.islike != undefined && news.islike[0] ? 'red':'grey'" @click="likeBtn(news.scrapNo[0], news.islike[0])"> 
                      <v-icon dark>mdi-heart</v-icon>
                      <div>{{news.like_cnt[0]}}</div>
                    </v-btn>
                    <v-btn dark small :color="news.isdislike != undefined && news.isdislike[0] ? 'blue':'grey'" @click="dislikeBtn(news.scrapNo[0], news.isdislike[0])"> 
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
  computed: {
    getMember() {
      return this.$store.state.userProfile;
    },
  },
  watch: {
    getMember: function(val) {
      this.member = val;
      if(this.member.id == this.$route.params.id){
        this.isMyPage = true;
      }
    },
    member: function() {
      // 스크랩 기사들 axios
      this.getScrap();
    }
  },
  data() {
    return {
      post_no: 0,
      id: String,
      scrap_news: [],
      member: {},
    }
  },
  methods: {
    move(newsInfo){
        this.$router.push({name: 'Article', params: {newsNo: newsInfo.newsNo, newsInfo2: newsInfo} })
      },

    getScrap() {
      axios.get('http://localhost:8080/article/scraplist', 
            { params: { postNo: this.post_no } },
          ).then((response) => {
            let scrap_news = response.data;
            // 사진없는 기사 이미지 추가할것.
            // 좋아요 싫어요 axios 추가하자
            
            if (localStorage['access-token'] && localStorage['access-token'] !== '') {
              for(let i = 0; i < scrap_news.length; i++) {
                axios.get('http://localhost:8080/article/islike',
                {params: { id: this.member.id, scrapNo: scrap_news[i].scrapNo[0]}})
                .then((response) => {
                  if(response.data['message'] == 'success') {
                    scrap_news[i]['islike'] = new Array(true);
                  } else { scrap_news[i]['islike'] = new Array(false); }
                }).finally(() => {
                  
                });
                  axios.get('http://localhost:8080/article/isdislike',
                {params: { id: this.member.id, scrapNo: scrap_news[i].scrapNo[0]}})
                .then((response) => {
                  if(response.data['message'] == 'success') {
                    scrap_news[i]['isdislike'] = new Array(true);
                  } else { scrap_news[i]['isdislike'] = new Array(false); }
                }).finally(() => { 
                  this.scrap_news.push(scrap_news[i]); 
                  console.log(scrap_news[i]);
                });
              }
            }
          }).finally(() => {
            setTimeout(() => {
              this.scrap_news.sort(function(a, b) {
                return a.scrapNo - b.scrapNo;
              });
            }, 5);
          });
          
    },
    likeBtn(scrapNo, check) {
      if (localStorage['access-token'] && localStorage['access-token'] !== '') {
        // console.log('좋아요 axios');
        console.log(scrapNo);
        console.log(check);
        axios.get('http://localhost:8080/article/like',
                {params: { id: this.member.id, scrapNo: scrapNo}})
                .then(() => {
                });
        
        if(check == true) {
          axios.get('http://localhost:8080/article/decrealike',
                {params: {scrapNo: scrapNo}})
                .then(() => {
                });
        } else {
          axios.get('http://localhost:8080/article/increalike',
                {params: {scrapNo: scrapNo}})
                .then(() => {
                });
        }
        this.scrap_news = [];
        this.getScrap();
      } else {
        alert('로그인을 해주세요.');
      }
    },
    dislikeBtn(scrapNo, check) {
      if (localStorage['access-token'] && localStorage['access-token'] !== '') {
        // console.log("싫어요 axios");
        console.log(scrapNo);
        console.log(check);
        axios.get('http://localhost:8080/article/dislike',
                {params: { id: this.member.id, scrapNo: scrapNo}})
                .then(() => {
                });
        if(check == true) {
          axios.get('http://localhost:8080/article/decreadislike',
                {params: {scrapNo: scrapNo}})
                .then(() => {
                });
        } else {
          axios.get('http://localhost:8080/article/increadislike',
                {params: {scrapNo: scrapNo}})
                .then(() => {
                });
        }
        this.scrap_news = [];
        this.getScrap();
      } else {
        alert('로그인을 해주세요.');
      }
    },
    numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    backPage() {
      this.$router.go(-1);
    }
  },
  created() {
    if(this.$store.getters.userProfile.id != undefined) {
      this.member = this.$store.getters.userProfile;
    }
    console.log("member>>>>>");
    console.log(this.member.id);
    this.post_no = this.$route.params.scrap_no;
    this.id = this.$route.params.id;
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