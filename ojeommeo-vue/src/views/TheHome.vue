<script setup>
import { useDarkMode } from '@/composables/useDarkMode.js'
import Button from '@/components/TheButton.vue'

const { isDark } = useDarkMode()

</script>

<template>
  <div class="flex pt-8">
    <section class="flex-1">
      <h1>랭킹</h1>
      <div class="mt-3">
        <ul class="flex flex-col gap-2">
          <li class="flex items-center gap-4">
            <div class="w-6 text-center">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="#FEC447" class="size-6">
                <path fill-rule="evenodd" d="M5.166 2.621v.858c-1.035.148-2.059.33-3.071.543a.75.75 0 0 0-.584.859 6.753 6.753 0 0 0 6.138 5.6 6.73 6.73 0 0 0 2.743 1.346A6.707 6.707 0 0 1 9.279 15H8.54c-1.036 0-1.875.84-1.875 1.875V19.5h-.75a2.25 2.25 0 0 0-2.25 2.25c0 .414.336.75.75.75h15a.75.75 0 0 0 .75-.75 2.25 2.25 0 0 0-2.25-2.25h-.75v-2.625c0-1.036-.84-1.875-1.875-1.875h-.739a6.706 6.706 0 0 1-1.112-3.173 6.73 6.73 0 0 0 2.743-1.347 6.753 6.753 0 0 0 6.139-5.6.75.75 0 0 0-.585-.858 47.077 47.077 0 0 0-3.07-.543V2.62a.75.75 0 0 0-.658-.744 49.22 49.22 0 0 0-6.093-.377c-2.063 0-4.096.128-6.093.377a.75.75 0 0 0-.657.744Zm0 2.629c0 1.196.312 2.32.857 3.294A5.266 5.266 0 0 1 3.16 5.337a45.6 45.6 0 0 1 2.006-.343v.256Zm13.5 0v-.256c.674.1 1.343.214 2.006.343a5.265 5.265 0 0 1-2.863 3.207 6.72 6.72 0 0 0 .857-3.294Z" clip-rule="evenodd" />
              </svg>
            </div>
            <div class="border-base rounded py-2 px-3 w-full flex items-center justify-between">
              <div>진라이</div>
              <div>317표</div>
            </div>
          </li>
          <li class="flex items-center gap-4">
            <div class="w-6 text-center">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="#C3C3C3" class="size-6">
                <path fill-rule="evenodd" d="M5.166 2.621v.858c-1.035.148-2.059.33-3.071.543a.75.75 0 0 0-.584.859 6.753 6.753 0 0 0 6.138 5.6 6.73 6.73 0 0 0 2.743 1.346A6.707 6.707 0 0 1 9.279 15H8.54c-1.036 0-1.875.84-1.875 1.875V19.5h-.75a2.25 2.25 0 0 0-2.25 2.25c0 .414.336.75.75.75h15a.75.75 0 0 0 .75-.75 2.25 2.25 0 0 0-2.25-2.25h-.75v-2.625c0-1.036-.84-1.875-1.875-1.875h-.739a6.706 6.706 0 0 1-1.112-3.173 6.73 6.73 0 0 0 2.743-1.347 6.753 6.753 0 0 0 6.139-5.6.75.75 0 0 0-.585-.858 47.077 47.077 0 0 0-3.07-.543V2.62a.75.75 0 0 0-.658-.744 49.22 49.22 0 0 0-6.093-.377c-2.063 0-4.096.128-6.093.377a.75.75 0 0 0-.657.744Zm0 2.629c0 1.196.312 2.32.857 3.294A5.266 5.266 0 0 1 3.16 5.337a45.6 45.6 0 0 1 2.006-.343v.256Zm13.5 0v-.256c.674.1 1.343.214 2.006.343a5.265 5.265 0 0 1-2.863 3.207 6.72 6.72 0 0 0 .857-3.294Z" clip-rule="evenodd" />
              </svg>
            </div>
            <div class="border-base rounded py-2 px-3 w-full flex items-center justify-between">
              <div>소반백</div>
              <div>317표</div>
            </div>
          </li>
          <li class="flex items-center gap-4">
            <div class="w-6 text-center">
              <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="#C78367" class="size-6">
                <path fill-rule="evenodd" d="M5.166 2.621v.858c-1.035.148-2.059.33-3.071.543a.75.75 0 0 0-.584.859 6.753 6.753 0 0 0 6.138 5.6 6.73 6.73 0 0 0 2.743 1.346A6.707 6.707 0 0 1 9.279 15H8.54c-1.036 0-1.875.84-1.875 1.875V19.5h-.75a2.25 2.25 0 0 0-2.25 2.25c0 .414.336.75.75.75h15a.75.75 0 0 0 .75-.75 2.25 2.25 0 0 0-2.25-2.25h-.75v-2.625c0-1.036-.84-1.875-1.875-1.875h-.739a6.706 6.706 0 0 1-1.112-3.173 6.73 6.73 0 0 0 2.743-1.347 6.753 6.753 0 0 0 6.139-5.6.75.75 0 0 0-.585-.858 47.077 47.077 0 0 0-3.07-.543V2.62a.75.75 0 0 0-.658-.744 49.22 49.22 0 0 0-6.093-.377c-2.063 0-4.096.128-6.093.377a.75.75 0 0 0-.657.744Zm0 2.629c0 1.196.312 2.32.857 3.294A5.266 5.266 0 0 1 3.16 5.337a45.6 45.6 0 0 1 2.006-.343v.256Zm13.5 0v-.256c.674.1 1.343.214 2.006.343a5.265 5.265 0 0 1-2.863 3.207 6.72 6.72 0 0 0 .857-3.294Z" clip-rule="evenodd" />
              </svg>
            </div>
            <div class="border-base rounded py-2 px-3 w-full flex items-center justify-between">
              <div>고봉김밥</div>
              <div>317표</div>
            </div>
          </li>
          <li class="flex items-center gap-4">
            <div class="w-6 text-center">1</div>
            <div class="border-base rounded py-2 px-3 w-full flex items-center justify-between">
              <div>시골밥상</div>
              <div>317표</div>
            </div>
          </li>
          <li class="flex items-center gap-4">
            <div class="w-6 text-center">1</div>
            <div class="border-base rounded py-2 px-3 w-full flex items-center justify-between">
              <div>시골밥상</div>
              <div>317표</div>
            </div>
          </li>
          <li class="flex items-center gap-4">
            <div class="w-6 text-center">1</div>
            <div class="border-base rounded py-2 px-3 w-full flex items-center justify-between">
              <div>시골밥상</div>
              <div>317표</div>
            </div>
          </li>
          <li class="flex items-center gap-4">
            <div class="w-6 text-center">1</div>
            <div class="border-base rounded py-2 px-3 w-full flex items-center justify-between">
              <div>시골밥상</div>
              <div>317표</div>
            </div>
          </li>
          <li class="flex items-center gap-4">
            <div class="w-6 text-center">1</div>
            <div class="border-base rounded py-2 px-3 w-full flex items-center justify-between">
              <div>시골밥상</div>
              <div>317표</div>
            </div>
          </li>
        </ul>
      </div>
    </section>
    <section class="pl-3 max-w-[300px] w-full">
      <h1 v-text="isDark ? '오저투' : '오점투'"></h1>
      <div class="bg-2 rounded p-3 mt-3">
        <ul class="flex flex-col gap-2">
          <li class="bg-base dark:bg-2 rounded text-default text-sm relative py-2 px-3 overflow-hidden">
            <div class="absolute top-0 left-0 h-full w-10 bg-primary dark:bg-base-dark"></div>
            <div class="relative flex justify-between">
              <div>삼보부대찌개</div>
              <div class="flex gap-1">
                2표
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" :fill="isDark ? '#ffffff39' : '#00000014'" class="size-5">
                  <path fill-rule="evenodd" d="M2.25 12c0-5.385 4.365-9.75 9.75-9.75s9.75 4.365 9.75 9.75-4.365 9.75-9.75 9.75S2.25 17.385 2.25 12Zm13.36-1.814a.75.75 0 1 0-1.22-.872l-3.236 4.53L9.53 12.22a.75.75 0 0 0-1.06 1.06l2.25 2.25a.75.75 0 0 0 1.14-.094l3.75-5.25Z" clip-rule="evenodd" />
                </svg>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="#43C234" class="size-5">
                  <path fill-rule="evenodd" d="M2.25 12c0-5.385 4.365-9.75 9.75-9.75s9.75 4.365 9.75 9.75-4.365 9.75-9.75 9.75S2.25 17.385 2.25 12Zm13.36-1.814a.75.75 0 1 0-1.22-.872l-3.236 4.53L9.53 12.22a.75.75 0 0 0-1.06 1.06l2.25 2.25a.75.75 0 0 0 1.14-.094l3.75-5.25Z" clip-rule="evenodd" />
                </svg>
              </div>
            </div>
          </li>
          <li class="py-2 px-3 bg-base dark:bg-2 rounded text-default text-sm">소반백</li>
          <li class="py-2 px-3 bg-base dark:bg-2 rounded text-default text-sm">고봉김밥</li>
          <li class="py-2 px-3 bg-base dark:bg-2 rounded text-default text-sm">시골밥상</li>
          <li class="py-2 px-3 bg-base dark:bg-2 rounded text-default text-sm">시골밥상</li>
          <li class="py-2 px-3 bg-base dark:bg-2 rounded text-default text-sm">시골밥상</li>
          <li class="py-2 px-3 bg-base dark:bg-2 rounded text-default text-sm">시골밥상</li>
          <li class="py-2 px-3 bg-base dark:bg-2 rounded text-default text-sm">시골밥상</li>
          <li class="py-2 px-3 bg-base dark:bg-2 rounded text-default text-sm">시골밥상</li>
          <li class="py-2 px-3 bg-base dark:bg-2 rounded text-default text-sm">시골밥상</li>
          <li class="py-2 px-3 bg-base dark:bg-2 rounded text-default text-sm">시골밥상</li>
        </ul>
        <div class="text-xs text-text-mute pt-3 font-light flex items-center justify-between">
          <div>13표 · 투표 종료됨</div>
          <Button size="sm">투표</Button>
        </div>
      </div>
    </section>
  </div>

</template>

<style scoped>

</style>
