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
                <v-col class="caption">
                  {{news.date}}
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
                    <v-img :src="news.image_path"></v-img>
                  </v-avatar>
                </v-col>
                <v-col style="width:0">
                  <v-card-title>
                    <div class="ellipsis">{{news.title}}</div>
                  </v-card-title>
                  <v-card-subtitle>
                    <div class="ellipsis">{{news.curator_summary}}</div>
                  </v-card-subtitle>
                  <v-card-actions class="d-flex justify-end">
                    <v-btn dark small :color="news.islike ? 'red':'grey'"> 
                      <v-icon dark>mdi-heart</v-icon>
                      <div>{{news.like_cnt}}</div>
                    </v-btn>
                    <v-btn dark small :color="news.isdislike ? 'blue':'grey'"> 
                      <v-icon dark>mdi-heart-broken</v-icon>
                      <div>{{news.dislike_cnt}}</div>
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
export default {
  data() {
    return {
      scrap_no: 0,
      id: String,
      scrap_news: []
    }
  },
  methods: {
    numberWithCommas(x) {
      return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
    backPage() {
      this.$emit("callScrap");
    }
  },
  created() {
    this.scrap_no = this.$route.params.scrap_no;
    this.id = this.$route.params.id;

    // 스크랩 기사들 axios
    this.scrap_news = [
      {
        title: '증여세는 시가 기준…증여 직후 거래가격 뛰면 세금 더 낼수도',
        curator_summary: '기사요약기사요약기사요약기사요약기사요약기사요약기사요약',
        like_cnt: this.numberWithCommas(12345),
        dislike_cnt: this.numberWithCommas(6789),
        date: '2021-02-01 23:43',
        image_path: 'https://imgnews.pstatic.net/image/001/2021/02/02/PYH2021011914410001300_P4_20210202120232595.jpg?type=w647',
        islike: true,
        isdislike: false,
      },
      {
        title: "회삿돈 빼돌려 증여자금 마련…부동산 '아빠찬스' 딱 걸렸네",
        curator_summary: '기사요약기사요약기사요약기사요약기사요약기사요약기사요약',
        like_cnt: this.numberWithCommas(23345),
        dislike_cnt: this.numberWithCommas(67349),
        date: '2020-12-01 23:43',
        image_path: 'https://imgnews.pstatic.net/image/025/2021/02/02/0003074770_001_20210202150647925.jpg?type=w647',
        islike: false,
        isdislike: true,
      },
    ]
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