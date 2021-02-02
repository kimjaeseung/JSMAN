<template>
  <div>
    <v-container>
      <v-row no-gutters>
        <v-col class="d-flex justify-center align-center" cols=3>
          <v-avatar size="100px">
            <v-img :src="member.thumbnail_path"></v-img>
          </v-avatar>
        </v-col>
        <v-col cols="9">
          <v-container fill-height class="d-flex align-center ml-1">
            <v-row >
              <v-col>
                <div><h2>{{member.name}}</h2></div>
                <div>구독자 없음</div>
              </v-col>
            </v-row>
          </v-container>
        </v-col>
        <v-col cols=4></v-col>
      </v-row> 
      <v-row> 
        <v-col class="d-flex justify-center">
          <v-container>
            <v-row v-if="mypage == true">
              <v-col><v-btn block>스크랩 만들기</v-btn></v-col>
              <v-col><v-btn block>커뮤니티</v-btn></v-col>
            </v-row>
            <v-row v-else>
              <v-col><v-btn block>구독하기</v-btn></v-col>
              <v-col><v-btn block>커뮤니티</v-btn></v-col>
            </v-row>
          </v-container>
        </v-col>
      </v-row>
      <v-row no-gutters>현재 스크랩 수: {{scrabs.length}}</v-row>
      <v-row no-gutters>
        <v-col>
          <v-card height="600px" style="overflow-y: scroll">
            <router-view></router-view>
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
          member: {},
          now_url: window.location.href,
          mypage: false,
        }
    },
    methods: {
      showScrab(scrab_no) {
        console.log("scrab_no>>>>" + scrab_no);
      }
    },
  created() {
      console.log("아이디>>>>" + this.$route.params.id);
      //유저 정보 받아오는 axios
      this.member = {
          name: '김재성',
          id: 'kimjea23@naver.com',
          thumbnail_path: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
      };
      // 내 정보랑 같은지 체크해서
      this.mypage = true;
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

<style scoped>
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