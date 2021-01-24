<template>
  <div class="home">
    <v-app-bar 
    color="white"
    dense
    >
      <!-- drawer -->
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

      <v-row
        align="center"
        justify="center">
        <v-toolbar-title>BOOK JOURNALISM</v-toolbar-title>
      </v-row>

      <!-- <v-spacer></v-spacer> -->

      <v-btn icon>
        <v-icon>far fa-newspaper</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn>

      <v-btn icon>
        <v-icon>mdi-account</v-icon>
      </v-btn>

    </v-app-bar>
    <v-navigation-drawer
      v-model="drawer"
      absolute
      temporary
    >
      <v-list
        nav
        dense
      >
        <v-list-item-group
          v-model="group"
          active-class="deep-purple--text text--accent-4"
        >
          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Home</v-list-item-title>
          </v-list-item>

          <v-list-item>
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title>Account</v-list-item-title>
          </v-list-item>

        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>

    <!-- Sizes your content based upon application components -->
    <v-main>
      <!-- Provides the application the proper gutter -->
      <v-container fluid>
        <v-row
        align="center"
        justify="center"
        class="d-flex flex-column"
        >
          <v-card
              class="flex mx-auto my-2"
              max-width="720"
              v-for="(img, idx) in imgs" :key="idx"
            >
            <div
              class="news-title"
            >
              Top western road trips
            </div>
            <v-img
              :src="img"
              height="480px"
            ></v-img>

            <v-card-title>
              Top western road trips
            </v-card-title>

            <v-card-subtitle>
              1,000 miles of wonder
            </v-card-subtitle>

            <v-card-actions>
              <v-btn
                color="orange lighten-2"
                text
                v-for="(tag, idx) in tags"
                :key="idx"
              >
                {{ tag }}
              </v-btn>

              <v-spacer></v-spacer>

              <v-btn
                icon
                @click="show = !show"
              >
                <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
              </v-btn>
            </v-card-actions>

            <v-expand-transition>
              <div v-show="show">
                <v-divider></v-divider>

                <v-card-text>
                  I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
                  Lorem ipsum dolor sit amet consectetur adipisicing elit. Asperiores magni doloribus illum repudiandae earum amet dolore maiores, provident quos nulla quis similique voluptatibus nesciunt labore ipsa dolorem cumque architecto vitae?
                </v-card-text>
              </div>
            </v-expand-transition>
          </v-card>
        </v-row>
        <!-- If using vue-router -->
      </v-container>
    </v-main>

    <v-footer app>
      <!-- -->
      NO COPYRIGHT!
    </v-footer>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'Home',
  components: {
  },
  inject: {
    theme: {
      default: { isDark: false },
    },
  },

  data: () => ({
    //
    drawer: false,
    show: false,
    imgs: [
      'https://cdn.vuetifyjs.com/images/cards/sunshine.jpg',
      'https://cdn.pixabay.com/photo/2021/01/08/07/52/trees-5899195_960_720.jpg',
      'https://cdn.pixabay.com/photo/2020/12/17/08/21/waterfall-5838582_960_720.jpg',
    ],
    tags: ['#정치', '경제', '사회', '문화']
  }),
}
</script>
<style>
.news-title{
  margin-top: 15px;
  margin-bottom: 15px;
  text-align: center;
  font-size: 35px;
  font-weight: 900;
  line-height: 1.4;
  color: #222;
}
</style>