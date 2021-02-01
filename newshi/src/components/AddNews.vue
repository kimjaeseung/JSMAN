<template>
  <v-row>
    <v-dialog v-model="addDialog" fullscreen>
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="orange lighten-4"
          fixed
          bottom
          right
          fab
          v-bind="attrs"
          v-on="on"
        >
          <v-icon>mdi-plus</v-icon>
        </v-btn>
      </template>
      <v-card max-height="300px">
        <v-toolbar color="orange lighten-4">
          <v-btn icon @click="addDialog = !addDialog">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-spacer />
          <v-toolbar-title>기사 등록</v-toolbar-title>
          <v-spacer />
        </v-toolbar>

        <v-form class="pa-6" @submit.prevent="registURL">
          <ValidationProvider
            name="id"
            rules="required|url"
            v-slot="{ errors }"
          >
            <v-text-field
              v-model="url"
              ref="regist"
              :error-messages="errors"
              required
              placeholder="URL을 등록해주세요"
              autocapitalize="off"
            ></v-text-field>
          </ValidationProvider>
          <v-btn color="orange lighten-4" @click="registURL">
            저장
          </v-btn>
        </v-form>
        <NewsForm
          v-for="(news, index) in post"
          :key="index"
          :num="index"
          :news="news"
          @remove="removeURL"
          @save="saveOther"
        ></NewsForm>
        <v-form class="pa-6" @submit.prevent="registPost">
          <v-text-field
            v-model="postName"
            required
            placeholder="포스트 이름을 작성해주세요"
          ></v-text-field>
          <v-btn color="orange lighten-4" @click="registPost"
            >등록<v-icon right>mdi-cloud-upload</v-icon>
          </v-btn>
        </v-form>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import { ValidationProvider } from 'vee-validate';
import { extend } from 'vee-validate';
import * as rules from 'vee-validate/dist/rules';
import NewsForm from '@/components/NewsForm.vue';
Object.keys(rules).forEach((rule) => {
  extend(rule, rules[rule]);
});
extend('url', {
  message: 'URL 형식이 아닙니다. 다시 한번 확인해주세요.',
  validate: (value) => {
    return /(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!]))?/i.test(
      value
    );
  },
});
export default {
  components: {
    ValidationProvider,
    NewsForm,
  },
  data() {
    return {
      addDialog: null,
      url: '',
      post: [],
      postName: '',
    };
  },
  methods: {
    registURL() {
      const news = {
        url: this.url,
        summary: '',
        tags: [],
      };
      this.post.push(news);
      this.url = '';
    },
    registPost() {},
    removeURL(index) {
      this.post.splice(index, 1);
      console.log(this.post);
      console.log(index);
    },
    saveOther(summary, tags, index) {
      let news = {
        url: this.post[index].url,
        summary: summary,
        tags: tags,
      };
      this.post.splice(index, 1, news);
      console.log(this.post[index]);
    },
  },
};
</script>

<style></style>
