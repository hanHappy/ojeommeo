<script setup>

import Header from '@/layouts/TheHeader.vue'
import Main from '@/layouts/TheMain.vue'
</script>

<template>
  <Header></Header>
  <Main>
    <RouterView></RouterView>
  </Main>
</template>

<style scoped>

</style>
