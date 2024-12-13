<script setup lang="ts">
import { Input } from '@/components/ui/input';
import { Button } from '@/components/ui/button';
import type { SignUpRequest } from '@/types/auth';
import { reactive, ref } from 'vue';
import { api } from '@/api/common';
import { isApiError, isUnknownError } from '@/api/error';
import router from '@/router';
import { useToast } from '@/components/ui/toast/use-toast';

const { toast } = useToast();

const signUpRequest = ref<SignUpRequest>({
  username: '',
  nickname: '',
  password: '',
});

const signUp = async () => {
  clearErrorMessages();
  try {
    await api.signUp(signUpRequest.value);
    await router.push({ name: 'Login' });
  } catch (e) {
    if (isApiError(e)) {
      const { errors } = e;
      errors.forEach(error => {
        if (!errorMessages[error.field]) {
          errorMessages[error.field] = error.message;
        }
      });
    } else if (isUnknownError(e)) {
      toast({
        title: '오류!',
        description: '회원 가입 중 오류가 발생하였습니다. 잠시 후 다시 시도해주세요.',
        variant: 'destructive',
      });
    } else {
      console.log('알 수 없는 오류');
    }
  }
};

// 에러 메시지
const errorMessages = reactive({
  username: '',
  nickname: '',
  password: '',
  confirmPassword: '',
}) as { [key: string]: string };

// 에러 메시지 초기화
function clearErrorMessages () {
  Object.keys(errorMessages).forEach(key => {
    errorMessages[key] = '';
  });
}
</script>

<template>
  <main>
    <div class="container min-h-screen flex justify-center items-center">
      <div>
        <div class="flex items-center justify-center">
          <!-- 로고 -->
          <div class="flex items-center gap-2">
            <img
              src="@/assets/images/logo_full.png"
              alt="로고"
              class="h-20"
            >
          </div>
        </div>

        <!-- form -->
        <div class="mt-10">
          <!-- Login ID -->
          <Input
            v-model="signUpRequest.username"
            type="text"
            placeholder="아이디"
          />
          <div class="error-message">
            {{ errorMessages.username }}
          </div>
          <!-- Nickname -->
          <Input
            v-model="signUpRequest.nickname"
            type="text"
            placeholder="닉네임"
            class="mt-3"
          />
          <div class="error-message">
            {{ errorMessages.nickname }}
          </div>
          <!-- password -->
          <Input
            v-model="signUpRequest.password"
            type="password"
            placeholder="비밀번호"
            class="mt-3"
          />
          <div class="error-message">
            {{ errorMessages.password }}
          </div>
          <!-- confirm password -->
          <Input
            type="password"
            placeholder="비밀번호 확인"
            class="mt-3"
          />
          <div class="error-message">
            {{ errorMessages.confirmPassword }}
          </div>

          <Button
            class="w-full mt-8"
            @click="signUp"
          >
            회원 가입
          </Button>

          <Button
            as-child
            variant="ghost"
            class="w-full mt-4"
          >
            <router-link :to="{ name: 'Login' }">
              이미 계정이 있어요
            </router-link>
          </Button>
        </div>
      </div>
    </div>
  </main>
</template>

<style scoped>
.error-message {
  @apply mt-1.5 ml-2 text-destructive;
}
</style>
