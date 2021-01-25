<template>
  <v-app>
      <v-app-bar flat app style="border-bottom:1px solid">
        <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>
        <v-spacer/>
        <v-img contain src="@/assets/logo.png" style="height: 50%"></v-img>
        <v-spacer/>
        <v-icon>mdi-magnify</v-icon>
      </v-app-bar>
      <v-navigation-drawer v-model="drawer" absolute temporary>
        <v-list>
            <v-list-item-group>
                <v-list-item>
                    <v-list-item-avatar>
                        <v-img src='https://cdn.vuetifyjs.com/images/lists/1.jpg'></v-img>
                    </v-list-item-avatar>
                    <v-list-item-content>
                        <v-list-item-title>김재성</v-list-item-title>
                        <v-list-item-subtitle>온라인</v-list-item-subtitle>
                    </v-list-item-content>
                </v-list-item> <v-divider/>
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-newspaper-variant-multiple-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                    내가 댓글 남긴 기사
                   </v-list-item-title>
                </v-list-item>
                
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-newspaper-plus</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                    언론사 선택하기
                   </v-list-item-title>
                </v-list-item>
                
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-brightness-6</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                    다크모드
                   </v-list-item-title>
                </v-list-item>
                
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-email-open-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                    피드백 보내기
                   </v-list-item-title>
                </v-list-item>
                
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-comment-processing-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                    댓글 운영 정책
                   </v-list-item-title>
                </v-list-item>
                
                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-home</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                    홈페이지 바로가기
                   </v-list-item-title>
                </v-list-item>

                <v-list-item>
                    <v-list-item-icon>
                        <v-icon>mdi-information-outline</v-icon>
                    </v-list-item-icon>
                    <v-list-item-title>
                    버전 정보
                   </v-list-item-title>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </v-navigation-drawer>
  </v-app>
</template>

<script>
export default {
    data() {
        return {
            drawer: false,
        }
    },
}
</script>

<style>

</style>