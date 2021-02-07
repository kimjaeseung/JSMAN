<template>
  <v-container>
    <Tiptap @saveData="addBoard"></Tiptap>
  </v-container>
</template>

<script>
import Tiptap from '@/components/TiptapBoard.vue';

export default {
  components: {
    Tiptap,
  },
  methods: {
    addBoard(content, images) {},
  },
};
</script>

<style></style>
