<template>
  <v-container>
    <Tiptap @saveData="addBoard"></Tiptap>
  </v-container>
</template>

<script>
import Tiptap from '@/components/TiptapBoard.vue';
import { boardInsert } from '@/api/board.js';

export default {
  components: {
    Tiptap,
  },
  methods: {
    addBoard(content, images, title) {
      var frm = new FormData();
      frm.append('title', title);
      frm.append('content', content);
      boardInsert(
        frm,
        images,
        (response) => {
          if (response.data.message === 'success') {
            alert('게시판 작성에 성공했습니다.');
          } else {
            alert('게시판 작성에 실패했습니다.');
          }
        },
        (error) => {
          console.error(error);
          alert('게시판 작성 중 에러가 발생했습니다..');
        }
      );
    },
  },
};
</script>

<style></style>
