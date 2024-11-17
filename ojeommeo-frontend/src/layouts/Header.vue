<script setup lang="ts"></script>

<template>
  <header>
    <div class="container py-2 flex items-center justify-between">
      <div>
        <router-link
          :to="{ name: 'Home' }"
          class="flex items-center justify-between gap-2"
        >
          <img
            src="@/assets/images/logo.png"
            alt="로고"
            class="h-8"
          >
          <span class="font-semibold">ㅇㅈㅁ</span>
        </router-link>
      </div>
      <nav>
        <div class="flex items-center gap-1">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            viewBox="0 0 24 24"
            fill="#cfdde8"
            class="size-10"
          >
            <path
              fill-rule="evenodd"
              d="M18.685 19.097A9.723 9.723 0 0 0 21.75 12c0-5.385-4.365-9.75-9.75-9.75S2.25 6.615 2.25 12a9.723 9.723 0 0 0 3.065 7.097A9.716 9.716 0 0 0 12 21.75a9.716 9.716 0 0 0 6.685-2.653Zm-12.54-1.285A7.486 7.486 0 0 1 12 15a7.486 7.486 0 0 1 5.855 2.812A8.224 8.224 0 0 1 12 20.25a8.224 8.224 0 0 1-5.855-2.438ZM15.75 9a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0Z"
              clip-rule="evenodd"
            />
          </svg>
          <div>유저123</div>
        </div>
      </nav>
    </div>
  </header>
</template>

<style scoped></style>
