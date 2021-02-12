<template>
  <v-expansion-panels v-model="panel" accordion>
    <v-expansion-panel>
      <v-expansion-panel-header
        hide-actions
        class="panels rounded-xl no-margin"
      >
        <v-row class="no-margin">
          <v-col class="no-margin"
            ><div class="ellipsis">{{ news.url }}</div></v-col
          >
          <v-col class="no-margin">
            <v-btn color="red darken-4 " dark icon @click="remove"
              ><v-icon large>mdi-close</v-icon></v-btn
            ></v-col
          >
        </v-row>
      </v-expansion-panel-header>
      <v-expansion-panel-content class="pl-6">
        <Tiptap @saveData="saveOther"></Tiptap>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
import Tiptap from '@/components/Tiptap.vue';

export default {
  name: 'NewsForm',
  components: {
    Tiptap,
  },
  props: {
    news: Object,
    num: Number,
  },
  data() {
    return {
      panel: [],
      summary: '',
      tags: [],
      tagName: [
        '속보',
        '정치',
        '경제',
        '사회',
        '생활/문화',
        '세계/국제',
        'IT/과학',
        '오피니언',
      ],
      isSaveOnce: false,
    };
  },
  methods: {
    remove() {
      this.$emit('remove', this.num);
      this.panel = [];
    },
    saveOther(summary, fullTag) {
      this.$emit('save', summary, fullTag, this.num);
      this.panel = [];
    },
  },
};
</script>

<style>
.panels {
  border: 1px solid gray;
  background-color: lightgray;
  width: 80%;
  height: 10%;
}
.left-border {
  border-left: 2px solid gray;
}
.no-margin {
  margin: 0 0 0 0;
  padding: 0 0 0 0;
  float: right;
}
.ellipsis {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: center;
}
</style>
