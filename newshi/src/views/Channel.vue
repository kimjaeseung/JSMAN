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
            <v-row v-if="isMyPage == true">
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
      <v-row no-gutters>
        <v-col>
          <v-card height="600px" style="overflow-y: scroll">
            <router-view @callScrap="showScrap"></router-view>
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
          isMyPage: false,
        }
    },
    methods: {
      showScrap() {
        this.$router.push({name:"Scrap", params: {propsIsMyPage: this.isMyPage}});
      },
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
      this.isMyPage = true;
      // 하위 컴포넌트에 전달
      // this.showScrap();
    },
}
</script>

<style scoped>
  .scrap_dark{
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