<template>
  <div>
    <v-container>
        <v-row>
            <v-col cols="2">
                <v-responsive :aspect-ratio="1/1">
                    <v-avatar width=100% height=100%>
                        <v-img v-if="mode=='curator'" src="http://images.khan.co.kr/article/2021/02/08/l_2021020802000444700074261.jpg"></v-img>
                        <v-icon v-else size="min(10vw,100px)" color="grey lighten-1">mdi-pound</v-icon>
                    </v-avatar>
                </v-responsive>
            </v-col>
            <v-col class="d-flex align-center justify-start" style="padding:0px">
                <div class="ml-0" style="font-size:min(4vw, 40px);">{{search_word}}</div>
            </v-col>
            <v-col class="d-flex align-center justify-end"><v-btn v-if="mode == 'curator'">구독</v-btn></v-col>
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
                <v-col style="width:0">
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
    data() {
        return {
            mode: '',
            search_word: '',
            scrap_news: [],
        }
    },
    methods: {
        numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    },
    created() {
        this.mode = this.$route.params.mode;
        this.search_word = this.$route.params.search_word;
        console.log(this.$route.params.mode);
        console.log(this.$route.params.search_word);
        if(this.$route.params.mode == 'curator') {
          console.log('curator');
        } else {
          let tag = this.$route.params.search_word;
          axios.get('http://localhost:8080/article/newsByTag', 
            { params: { tagName: tag } },
            ).then((response) => { 
                this.scrap_news = response.data;
                console.log(this.scrap_news);
            })
        }
        
    },
}
</script>

<style>

</style>