<script setup lang="ts">
const { placeholder = '입력해주세요' } = defineProps<{
  placeholder?: string;
}>();
</script>

<template>
  <input
    type="text"
    :placeholder="placeholder"
  >
</template>

<style scoped></style>
