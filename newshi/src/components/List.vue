<template>
  <v-list>
    <template v-for="(newsInfo, i) in newsItem">
      <v-list-item
        :key="newsInfo+i"
        @click="move(newsInfo)"
      >
      {{ newsInfo.title }}
        <v-list-item-avatar rounded >
          <v-img :src="newsInfo.image_path[0]" centered></v-img>
        </v-list-item-avatar>
        <v-list-item-content>
          <v-list-item-title v-html="newsInfo.title[0]"></v-list-item-title>
          <v-list-item-subtitle v-html="newsInfo.company[0]"></v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
    </template>
  </v-list>
</template>

<script>

export default {
  name: 'List',
  props: [
    'basicNews'
  ],
  data() {
    return {
      newsItem: this.basicNews
    }
  },
  created: function () {
    console.log(this.basicNews)
  }
}
</script>

<style>

</style>