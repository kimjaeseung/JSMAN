<template>
  <v-container>
    <v-row no-gutters>
      <v-col class="d-flex justify-center align-center" cols="3">
        <v-avatar size="100px">
          <v-img :src="member.thumbnail_path"></v-img>
        </v-avatar>
      </v-col>
      <v-col cols="9">
        <v-container fill-height class="d-flex align-center ml-1">
          <v-row>
            <v-col>
              <div>
                <h2>{{ member.name }}</h2>
              </div>
              <div>구독자 없음</div>
            </v-col>
          </v-row>
        </v-container>
      </v-col>
      <v-col cols="4"></v-col>
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
    <v-row v-if="isMyPage"><v-btn @click="addBoard">게시글 등록</v-btn></v-row>
    <v-row no-gutters v-if="hasList">
      <v-col>
        <BoardInfo
          v-for="(board, index) in list"
          :key="index"
          :board="board"
          :num="index"
          :member="member"
          :isMain="true"
          @delBoard="removeBoard"
        ></BoardInfo>
      </v-col>
    </v-row>
    <v-row no-gutters v-else>
      <h2>게시글이 없습니다.</h2>
    </v-row>
  </v-container>
</template>

<script>
import BoardInfo from '@/components/BoardInfo.vue';
import { sidebarUser } from '@/api/user.js';
import { boardList, boardDelete } from '@/api/board.js';

export default {
  components: {
    BoardInfo,
  },
  data() {
    return {
      member: {},
      isMyPage: false,
      list: [],
      hasList: false,
      curator: '',
    };
  },
  methods: {
    addBoard() {
      this.$router.push('/addBoard');
    },
    removeBoard(index) {
      let board = this.list.splice(index, 1);
      boardDelete(
        board.boardPostNo,
        (response) => {
          if (response.data.message === 'success') {
            alert('게시물 삭제에 성공했습니다.');
          } else {
            alert('게시물 삭제에 실패했습니다.');
          }
        },
        (error) => {
          console.error(error);
          alert('게시물 삭제 중 에러가 발생했습니다.');
        }
      );
      console.log(this.list);
      console.log(index);
    },
  },
  created() {
    this.curator = this.$route.params.id;
    //유저 정보 받아오는 axios
    sidebarUser(
      this.curator,
      (response) => {
        if (response.status >= 200 && response.status < 300) {
          this.member = {
            id: this.curator,
            name: response.data['name'],
            userNo: response.data['userNo'],
            thumbnail_path: response.data['thumbnail_path'],
          };

          if (this.member.id === localStorage['id']) {
            console.log(this.member.id);
            console.log(localStorage['id']);
            this.isMyPage = true;
          } else {
            this.isMyPage = false;
          }
        } else {
          alert('큐레이터의 데이터를 받아오는데 실패했습니다.');
          this.$router.push(`/channel/${this.curator}`);
        }
      },
      (error) => {
        console.error(error);
        alert('큐레이터의 데이터를 받아오는 중 에러가 발생했습니다.');
      }
    );

    boardList(
      this.curator,
      (response) => {
        if (response.status >= 200 && response.status < 300) {
          if (response.data[0].message === '게시글이 없습니다.') {
            this.hasList = false;
            console.log(this.hasList);
          } else {
            this.hasList = true;
            console.log(response.data);
            this.list = response.data;
          }
        }
      },
      (error) => {
        console.error(error);
        alert('게시판 목록을 받아오는 중 에러가 발생했습니다.');
      }
    );
  },
};
</script>

<style></style>
