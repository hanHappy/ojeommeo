<script setup lang="ts">
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
</script>

<template>
  <main>
    <div class="container min-h-screen flex justify-center items-center">
      <div>
        <div class="flex items-center justify-center">
          <!-- 로고 -->
          <div class="flex items-center gap-2">
            <img
              src="@/assets/images/logo.png"
              alt="로고"
              class="h-20"
            >
            <span class="text-5xl mt-6">ㅇㅈㅁ</span>
          </div>
        </div>

        <!-- form -->
        <div class="mt-10">
          <!-- ID -->
          <Input
            type="email"
            placeholder="ID"
          />
          <!-- password -->
          <Input
            type="password"
            placeholder="Password"
            class="mt-3"
          />

          <div class="flex justify-end mt-2">
            <Button
              variant="link-weak"
              class="float-right"
            >
              비밀번호 찾기
            </Button>
          </div>

          <Button class="w-full mt-4">
            로그인
          </Button>

          <Button
            as-child
            variant="outline"
            class="w-full mt-3"
          >
            <router-link :to="{ name: 'SignUp' }">
              회원 가입
            </router-link>
          </Button>
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped></style>
