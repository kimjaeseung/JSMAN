<template>
  <div>
    <v-container>
        <v-row>
            <v-col cols="2">
                <v-responsive :aspect-ratio="1/1" @click="toChannel(curator.id)" style="cursor: pointer">
                    <v-avatar width=100% height=100%>
                        <v-img v-if="mode=='curator'" :src="curator.thumbnail_path"></v-img>
                        <v-icon v-else size="min(10vw,100px)" color="grey lighten-1">mdi-pound</v-icon>
                    </v-avatar>
                </v-responsive>
            </v-col>
            <v-col class="d-flex align-center justify-start" style="padding:0px;">
                <div class="ml-0" style="font-size:min(4vw, 40px); cursor: pointer" @click="toChannel(curator.id)">{{search_word}}</div>
            </v-col>
            <v-col v-if="mode == 'curator' && member.id != curator.id" class="d-flex align-center justify-end">
              <v-btn v-if="isSubs == false" @click="follow">구독</v-btn>
              <v-btn v-else @click="unfollow" class="error">구독취소</v-btn>
            </v-col>
            <v-col v-else class="d-flex align-center justify-end">
              <v-btn disabled>구독</v-btn>
            </v-col>
        </v-row>
        <v-row v-for="(news, index) in scrap_news" :key="index">
        <v-col>
          <v-card>
            <v-container>
              <v-row>
                <v-col class="caption">
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
                <v-col style="width:0; cursor: pointer" @click="move(news)">
                  <v-card-title>
                    <div class="ellipsis">{{news.title[0]}}</div>
                  </v-card-title>
                  <v-card-subtitle>
                    <div class="ellipsis d-flex justify-left" >{{news.article_bot_summary[0]}}</div>
                  </v-card-subtitle>
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
      this.subsCheck();
      console.log(this.isSubs);
    },
  },
    data() {
        return {
            mode: '',
            search_word: '',
            scrap_news: [],
            curator: {},
            member: {},
            isSubs: false,
        }
    },
    methods: {
      move(newsInfo){
        this.$router.push({name: 'Article', params: {newsNo: newsInfo.newsNo, newsInfo2: newsInfo} })
      },
      subsCheck() {
        axios
          .get('http://localhost:8080/subscribe', {
            params: { id: this.member.id },
          })
          .then((response) => {
            // 응답 처리
            var followers = response.data;
            followers.forEach((follower) => {
              if (follower.id == this.curator.id) {
                this.isSubs = true;
              }
            });
          })
          .catch((error) => {
            console.log(error);
          });
      },
      follow() {
        if (localStorage['access-token'] && localStorage['access-token'] !== '') {
          var frm = new FormData();
          frm.append('id', this.member.id);
          frm.append('id2', this.curator.id);
          axios
            .post('http://localhost:8080/subsc', frm, {
              headers: { 'Content-Type': 'multipart/form-data' },
            })
            .then(() => {
              this.$router.go(this.$router.currentRoute);
            });
        }
      },
      unfollow() {
        if (localStorage['access-token'] && localStorage['access-token'] !== '') {
        var frm = new FormData();
        frm.append('id', this.member.id);
        frm.append('id2', this.curator.id);
        axios
          .post('http://localhost:8080/subscdelete', frm, {
            headers: { 'Content-Type': 'multipart/form-data' },
          })
          .then(() => {
            this.$router.go(this.$router.currentRoute);
          });
        }
      },
      numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
      },
      toChannel(id) {
        console.log(id);
        this.$router.push('/channel/' + id);
      },
    },
    
    created() {
        this.mode = this.$route.params.mode;
        this.search_word = this.$route.params.search_word;

        if(this.$store.getters.userProfile.id != undefined) {
          this.member = this.$store.getters.userProfile;
        }

        console.log(this.$route.params.mode);
        console.log(this.$route.params.search_word);
        if(this.$route.params.mode == 'curator') {
          let name = this.$route.params.search_word;
          //이름으로 아이디랑 썸네일 받아오기
          axios.get('http://localhost:8080/search/people', 
            { params: { keyword: name } },
            ).then((response) => { 
                let resData = response.data;
                let arr = [];
                resData.forEach(e => {
                  if(e['name'] == name){
                    console.log(e);
                    this.curator = e;
                  }
                });
                this.autocomp_value = arr;
            })
          //이름으로 기사들 받아오기
          axios.get('http://localhost:8080/article/newsByName', 
            { params: { name: name } },
            ).then((response) => { 
                this.scrap_news = response.data;
            })
        } else {
          let tag = this.$route.params.search_word;
          axios.get('http://localhost:8080/article/newsByTag', 
            { params: { tagName: tag } },
            ).then((response) => { 
                this.scrap_news = response.data;
            })
        }
    },
}
</script>

<style>

</style>