<template>
  <v-expansion-panels v-model="panel" accordion>
    <v-expansion-panel>
      <v-expansion-panel-header>
        <v-row>
          <v-col sm="10">{{ news.url }}</v-col>
          <v-col sm="1"
            ><v-btn color="red darken-4" dark icon @click="remove"
              ><v-icon>mdi-close</v-icon></v-btn
            ></v-col
          >
        </v-row>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <v-textarea
          outlined
          v-model="summary"
          label="Outlined textarea"
          placeholder="직접 요약하고 싶다면 작성해주세요."
        ></v-textarea>
        <span><h4>관심 목록</h4></span>
        <v-row justify="space-around">
          <v-col cols="10" sm="10" md="8">
            <v-sheet outlined width="330">
              <v-chip-group
                v-model="tags"
                multiple
                active-class="yellow --text"
              >
                <v-chip border large v-for="tag in tagName" :key="tag">
                  {{ tag }}
                </v-chip>
              </v-chip-group>
            </v-sheet>
          </v-col>
        </v-row>
        <v-row class="d-flex flex-row-reverse">
          <v-btn v-if="!isSaveOnce" @click="save">저장</v-btn>
          <v-btn v-else @click="save">수정</v-btn>
        </v-row>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>

<script>
export default {
  name: 'NewsForm',
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
    save() {
      this.$emit('save', this.summary, this.tags, this.num);
      this.isSaveOnce = true;
      this.panel = [];
    },
  },
};
</script>

<style></style>
