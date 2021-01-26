<template>
  <v-container>
    <v-toolbar
      color="white"
      flat
      dense
    >
      <v-tabs
        v-model="tab"
        center-active
        show-arrows
        align-with-title
      >
        <v-tabs-slider color="grey lighten-4"></v-tabs-slider>
        <v-tab
          v-for="curator in curators"
          :key="curator"
          style="height: 48px; width: 48px;"
        >
          <v-list-item-avatar>
            <v-img :src="curator"></v-img>
          </v-list-item-avatar>
        </v-tab>
      </v-tabs>
    </v-toolbar>

    <v-tabs-items v-model="tab">
      <v-tab-item
        v-for="item in items"
        :key="item"
      >
        <v-list>
          <template v-for="(item, index) in items">
            <v-subheader
              v-if="item.header"
              :key="item.header"
              v-text="item.header"
            ></v-subheader>

            <v-divider
              v-else-if="item.divider"
              :key="index"
              :inset="item.inset"
            ></v-divider>

            <v-list-item
              v-else
              :key="item.title"
            >
              <v-list-item-avatar
                tile
              >
                <v-img :src="item.avatar"></v-img>
              </v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title v-html="item.title"></v-list-item-title>
                <v-list-item-title v-html="item.subtitle"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </template>
        </v-list>
      </v-tab-item>
    </v-tabs-items>
  </v-container>
</template>

<script>
export default {
  name: "Main",
  data: function () {
    return {
        tab: null,
        curators: [
          'https://cdn.vuetifyjs.com/images/lists/1.jpg', 
          'https://cdn.vuetifyjs.com/images/lists/2.jpg', 
          'https://cdn.vuetifyjs.com/images/lists/3.jpg', 
          'https://cdn.vuetifyjs.com/images/lists/4.jpg', 
          'https://cdn.vuetifyjs.com/images/lists/5.jpg'
        ],
        items: [
          // { header: 'Today' },
          {
            avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
            title: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Cupiditate rem vel impedit, animi corporis iure possimus quos dolore reiciendis? Iure animi eos optio, dolor tempore nihil id in odit sunt?',
            subtitle: 'OO일보'
          },
          { divider: true, inset: true },
          {
            avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
            title: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Cupiditate rem vel impedit, animi corporis iure possimus quos dolore reiciendis? Iure animi eos optio, dolor tempore nihil id in odit sunt?',
            subtitle: 'OO일보'
          },
          { divider: true, inset: true },
          {
            avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
            title: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Cupiditate rem vel impedit, animi corporis iure possimus quos dolore reiciendis? Iure animi eos optio, dolor tempore nihil id in odit sunt?',
            subtitle: 'OO일보'
          },
          { divider: true, inset: true },
          {
            avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
            title: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Cupiditate rem vel impedit, animi corporis iure possimus quos dolore reiciendis? Iure animi eos optio, dolor tempore nihil id in odit sunt?',
            subtitle: 'OO일보'
          },
          { divider: true, inset: true },
          {
            avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
            title: 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Cupiditate rem vel impedit, animi corporis iure possimus quos dolore reiciendis? Iure animi eos optio, dolor tempore nihil id in odit sunt?',
            subtitle: 'OO일보'
          },
        ],
      }
  }
}
</script>

<style>
.container {
  max-width: 800px;
}
</style>