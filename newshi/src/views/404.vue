<template>
  <v-container>
    <h2 class="text-center">404 Error</h2>
    <h3 class="text-center">잘못된 페이지 경로입니다.</h3>
    <a href="/">
      <div class="text-center center-icon">
        <h1>👻</h1>
      </div>
    </a>
    <div class="text-center">
      <router-link to='/'>
        <v-btn text>
          메인으로 돌아가기
        </v-btn>
      </router-link>
    </div>
  
  </v-container>
</template>

<script>

export default {
  name: 'notFound',
  data() {
    return {
    }
  }
}
</script>

<style>
.center-icon {
  max-width: 100%;
  margin: 1rem;
}

</style>