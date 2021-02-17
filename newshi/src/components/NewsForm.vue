<template>
  <v-container class="container">
    <v-expansion-panels v-model="panel">
      <v-expansion-panel class="panels">
        <v-tooltip bottom>
          <template v-slot:activator="{ on, attrs }">
            <v-expansion-panel-header
              hide-actions
              class="panels"
              v-bind="attrs"
              v-on="on"
            >
              <v-row>
                <v-col class="url-text ellipsis">{{ title }}</v-col>
                <v-col class="no-margin">
                  <v-btn
                    class="close-btn"
                    color="red darken-4 "
                    dark
                    icon
                    @click="remove"
                    ><v-icon large>mdi-close</v-icon></v-btn
                  ></v-col
                >
              </v-row>
            </v-expansion-panel-header>
          </template>
          <span>{{ news.url }}</span>
        </v-tooltip>
        <v-expansion-panel-content>
          <Tiptap @saveData="saveOther"></Tiptap>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-container>
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
      title: '',
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
  created() {
    if (this.news.url.indexOf('naver') != -1) {
      this.title = '네이버뉴스 ' + (this.num + 1);
    } else {
      this.title = '다른 뉴스 ' + this.num;
    }
  },
};
</script>

<style>
.panels {
  border: 1px solid gray;
  background-color: lightgray;
  padding: 0px;
}
.left-border {
  border-left: 2px solid gray;
}
.no-margin {
  margin: 0 0 0 0;
  padding: 0 0 0 0;
  float: right;
}
/* .ellipsis {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: center;
} */
.url-text {
  padding: 0 50% 1px 0;
}
.close-btn {
  position: absolute;
  right: 1%;
  top: 13%;
}
.container {
  margin: 0;
  padding: 0 24px 0 24px;
}
</style>
