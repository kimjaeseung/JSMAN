<template>
  <v-card>
    <v-card-title>
      <v-list two-line>
        <template>
          <v-list-item-avatar>
            <v-img :src="member.thumbnail_path" :alt="member.name"></v-img>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title
              ><a style="color: black;text-decoration:none">{{ member.id }}</a>
              <a style="color: black;text-decoration:none"
                >업로드 : {{ time }}</a
              ></v-list-item-title
            >
          </v-list-item-content>
        </template>
      </v-list>
    </v-card-title>
    <v-card-text>
      <Tiptap :board="board" @saveData="updateBoard"></Tiptap>
    </v-card-text>
  </v-card>
</template>

<script>
import Tiptap from '@/components/TiptapBoard.vue';
import { boardUpdate } from '@/api/board.js';

export default {
  components: {
    Tiptap,
  },
  props: {
    board: Object,
    member: Object,
  },
  data() {
    return {
      time: '',
    };
  },
  methods: {
    updateBoard(content, title) {
      let modifiedBoard = {
        boardPostNo: this.board.boardPostNo,
        title: title,
        date: this.board.date,
        visit_cnt: this.board.visit_cnt,
        userNo: this.board.userNo,
        content: content,
        is_notice: this.board.is_notice,
      };
      console.log(modifiedBoard);
      boardUpdate(
        modifiedBoard,
        (response) => {
          if (response.data.message === 'success') {
            alert('게시판 수정에 성공했습니다.');
            let id = localStorage['id'];
            this.$router.push(`/board/${id}`);
          } else {
            alert('게시판 수정에 실패했습니다.');
          }
        },
        (error) => {
          console.error(error);
          alert('게시판 수정 중 에러가 발생했습니다..');
        }
      );
    },
  },
  created() {
    this.time = this.$moment(this.board.date).fromNow();
  },
};
</script>

<style></style>
