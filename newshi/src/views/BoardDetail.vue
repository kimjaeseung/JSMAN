<template>
  <v-container>
    <v-row no-gutters>
      <v-col>
        <BoardInfo
          :member="member"
          :board="board"
          :num="num"
          @delBoard="removeBoard"
        ></BoardInfo>
      </v-col>
    </v-row>
    <v-divider></v-divider>
    <v-row
      ><h3>댓글 {{ board.boardCommentCount }}</h3></v-row
    >
    <v-row no-gutters>
      <BoardComment :boardPostNo="board.boardPostNo"></BoardComment>
    </v-row>
  </v-container>
</template>

<script>
import BoardInfo from '@/components/BoardInfo.vue';
import BoardComment from '@/components/BoardComment.vue';
export default {
  components: {
    BoardInfo,
    BoardComment,
  },
  props: {
    member: Object,
    board: Object,
    num: Number,
  },
  methods: {
    removeBoard(index) {
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
};
</script>

<style></style>
