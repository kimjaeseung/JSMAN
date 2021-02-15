<template>
  <v-container>
      <!-- <v-row no-gutters>현재 스크랩 수: {{scraps.length}}</v-row> -->
    <v-row justify="center">
        <v-col v-for="(scrap, index) in scraps" :key="index" cols="auto">
        <v-hover v-slot="{ hover }">
            <v-card width='150' height='150'>
            <v-img :src="scrap.scrap_thumbnail" aspect-ratio="1">
                <div fill-height style="width:100%; height:100%;" class="d-flex justify-center align-center" else>
                  <v-container >
                    <v-row>
                      <v-col class="d-flex justify-center align-center">
                         <v-btn :disabled="hover == false" @click="showNews(scrap.postNo)">보러가기</v-btn>
                      </v-col>
                    </v-row>
                    <v-row v-if="isMyPage==true">
                      <v-col class="d-flex justify-center align-center">
                        <v-dialog width="500" v-model="dialog">
                          <template v-slot:activator="{ on, attrs }">
                            <v-btn v-bind="attrs" v-on="on" @click="modifyBtn(scrap.name)">
                             수정하기
                            </v-btn>
                          </template>
                          <v-card>
                            <input type="text" v-model="modify_title" style="font-size:30px; width:100%; text-align:center">
                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn color="primary" @click="modify()">
                                수정
                              </v-btn>
                              <v-btn color="primary" @click="refreshPage">
                                취소
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                         <!-- <v-btn :disabled="hover == false">수정하기</v-btn> -->
                      </v-col>
                    </v-row>
                  </v-container>
                </div>
            </v-img>
            <div class="ellipsis">{{scrap.name}}</div>
            </v-card>
        </v-hover>
        </v-col>
    </v-row>
    </v-container>
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
      if(this.member.id == this.$route.params.id){
        this.isMyPage = true;
        console.log("isMyPage");
      }
    }
  },
    props: {
      propsIsMyPage: {
        type: Boolean
      }
    },
    data() {
        return {
          isMyPage: Boolean,
          scraps: [],
          dialog: false,
          flag: false,
          member: {},
          modify_title: '',
        }
    },
    methods: {
      showNews(scrap_no) {
        this.$router.push('./' + this.member.id + '/' + scrap_no);
      },
      modifyBtn(name) {
        this.modify_title = name;
      },
      modify() {
        console.log(this.modify_title);
        this.refreshPage();
      },
      refreshPage() {
        this.dialog=false;
      },
      getPostList() {
        axios.get('http://localhost:8080/article/post', 
        { params: { id: this.$route.params.id } },
      ).then((response) => { 
        this.scraps = response.data;
        this.scraps.forEach((scrap) => {
          axios.get('http://localhost:8080/article/scraplist', 
            { params: { postNo: scrap.postNo } },
          ).then((response) => {
            scrap.scrap_thumbnail = response.data[0].new_image[0];
          });
        })
        console.log(this.scraps);
        })
      }
    },
    created() {
      this.member = this.$store.getters.userProfile;
        //스크랩 받아오는 axios
      //큐레이터 정보 받아오기..
      
      this.getPostList();
        // this.forceUpdate()
      // this.scraps = [
      //   {
      //     title: '테스트1',
      //     scrap_thumbnail: 'https://imgnews.pstatic.net/image/030/2021/02/01/0002926385_001_20210201141045283.jpg?type=w647',
      //     scrap_no: 1,
      //   },
      //   {
      //     title: '긴문자자자자자자자자자자자자',
      //     scrap_thumbnail: 'https://imgnews.pstatic.net/image/005/2021/02/01/611711110015488625_1_20210201135740135.jpg?type=w647',
      //     scrap_no: 2,
      //   },
      //   {
      //     title: '짧',
      //     scrap_thumbnail: 'https://imgnews.pstatic.net/image/421/2021/02/01/0005141266_001_20210201142223475.jpg?type=w647',
      //     scrap_no: 3,
      //   },
      //   {
      //     title: '테스트4',
      //     scrap_thumbnail: 'https://imgnews.pstatic.net/image/005/2021/02/01/611511110015488651_1_20210201143210985.jpg?type=w647',
      //     scrap_no: 4,
      //   },
      //   {
      //     title: '테스트5',
      //     scrap_thumbnail: 'https://imgnews.pstatic.net/image/008/2021/02/01/0004537180_001_20210201140726181.jpg?type=w647',
      //     scrap_no: 5,
      //   },
      // ];
    },
}
</script>

<style>
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