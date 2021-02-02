<template>
  <v-container>
      <!-- <v-row no-gutters>현재 스크랩 수: {{scrabs.length}}</v-row> -->
    <v-row justify="center">
        <v-col v-for="(scrab, index) in scrabs" :key="index" cols="auto">
        <v-hover v-slot="{ hover }">
            <v-card width='150' height='150'>
            <v-img :src="scrab.scrab_thumbnail" aspect-ratio="1">
                <div v-if="hover == false" class="scrab_dark"></div>
                <div fill-height style="width:100%; height:100%;" class="d-flex justify-center align-center" else>
                <v-btn :disabled="hover == false" @click="showNews(scrab.scrab_no)">버튼</v-btn>
                </div>
            </v-img>
            <div class="ellipsis">{{scrab.title}}</div>
            </v-card>
        </v-hover>
        </v-col>
    </v-row>
    </v-container>
</template>

<script>
export default {
    data() {
        return {
          scrabs: [],
        }
    },
    methods: {
      showNews(scrab_no) {
        this.$router.push('./' + this.$route.params.id + '/' + scrab_no);
      }
    },
    created() {
        //스크랩 받아오는 axios
      this.scrabs = [
        {
          title: '테스트1',
          scrab_thumbnail: 'https://imgnews.pstatic.net/image/030/2021/02/01/0002926385_001_20210201141045283.jpg?type=w647',
          scrab_no: 1,
        },
        {
          title: '긴문자자자자자자자자자자자자',
          scrab_thumbnail: 'https://imgnews.pstatic.net/image/005/2021/02/01/611711110015488625_1_20210201135740135.jpg?type=w647',
          scrab_no: 2,
        },
        {
          title: '짧',
          scrab_thumbnail: 'https://imgnews.pstatic.net/image/421/2021/02/01/0005141266_001_20210201142223475.jpg?type=w647',
          scrab_no: 3,
        },
        {
          title: '테스트4',
          scrab_thumbnail: 'https://imgnews.pstatic.net/image/005/2021/02/01/611511110015488651_1_20210201143210985.jpg?type=w647',
          scrab_no: 4,
        },
        {
          title: '테스트5',
          scrab_thumbnail: 'https://imgnews.pstatic.net/image/008/2021/02/01/0004537180_001_20210201140726181.jpg?type=w647',
          scrab_no: 5,
        },
      ];
    },
}
</script>

<style>
    .scrab_dark{
    background-color:black;
    opacity: 0.4;
    width: 100%;
    height: 100%;
 }
 .ellipsis{
        width:100%;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        text-align: center;
    } 
</style>