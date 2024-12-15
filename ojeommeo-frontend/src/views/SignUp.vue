<script setup lang="ts">
import { Input } from '@/components/ui/input'
import { Button } from '@/components/ui/button'
import type { SignUpRequest } from '@/types/auth'
import { reactive, ref } from 'vue'
import { api } from '@/api/common'
import { isApiError, isUnknownError } from '@/api/error'
import router from '@/router'
import { useToast } from '@/components/ui/toast/use-toast'
import { useForm } from 'vee-validate'
import { toTypedSchema } from '@vee-validate/zod'
import { h } from 'vue'
import * as z from 'zod'
import {
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from '@/components/ui/form'
import { configure } from 'vee-validate'

const { toast } = useToast()

configure({
  validateOnBlur: false, // blur 시 validation 비활성화
  validateOnChange: false, // input 변경 시 validation 비활성화
  validateOnInput: false, // 입력 시 validation 비활성화
  validateOnModelUpdate: false, // v-model 업데이트 시 validation 비활성화
})
const formSchema = toTypedSchema(
  z
    .object({
      username: z
        .string()
        // .nonempty('아이디를 입력해 주세요')
        .max(12, '1-12자 사이의 아이디를 입력해 주세요'),
      // .regex(/^[a-zA-Z0-9]+$/, '아이디는 영문, 숫자만 입력할 수 있습니다'),
      nickname: z
        .string()
        .nonempty('닉네임을 입력해 주세요')
        .max(10, '1-10자 사이의 닉네임을 입력해 주세요'),
      password: z
        .string()
        .nonempty('비밀번호를 입력해 주세요')
        .max(18, '1-18자 사이의 비밀번호를 입력해 주세요'),
      'confirm-password': z.string().nonempty('비밀번호를 다시 입력해 주세요'),
    })
    .refine((data) => data.password === data['confirm-password'], {
      message: '비밀번호가 일치하지 않습니다',
      path: ['confirm-password'],
    }),
)

/**
 * 초기값을 설정하지 않으면 nonempty()가 의도대로 동작하지 않음
 *  1. 아무 값도 입력하지 않고 폼을 제출하면 기본 validation message인 "required"가 표시됨
 *  2. 값을 입력하고 지운 뒤 폼을 제출하면 정상적으로 nonempty()로 커스텀한 메시지가 표시됨
 * 따라서 아래와 같이 초기값을 설정함
 * useForm<SignUpRequest>와 같이 type을 지정할 수 있긴 한데,
 * confirm-password가 필드로 있지 않아서 아래와 같이 세팅함
 */
const { handleSubmit, setErrors, setFieldError } = useForm({
  validationSchema: formSchema,
  initialValues: {
    username: '',
    nickname: '',
    password: '',
    'confirm-password': '',
  },
})

const onSubmit = handleSubmit(async (values) => {
  try {
    await api.signUp({
      username: values.username,
      nickname: values.nickname,
      password: values.password,
    })
    await router.push({ name: 'Login' })
  } catch (e) {
    if (isApiError(e)) {
      const { code, errors, message } = e
      switch (code) {
        case 'REQ_001':
          const fieldErrors = errors.reduce(
            (acc, error) => {
              acc[error.field] = error.message
              return acc
            },
            {} as { [key: string]: string },
          )
          setErrors(fieldErrors)
          break
        case 'USER_002':
          setFieldError('username', message)
      }
    } else if (isUnknownError(e)) {
      toast({
        title: '오류!',
        description: '회원 가입 중 오류가 발생하였습니다. 잠시 후 다시 시도해주세요.',
        variant: 'destructive',
      })
    } else {
      console.log('알 수 없는 오류')
    }
  }
})
</script>

<template>
  <main>
    <div class="container min-h-screen flex justify-center items-center">
      <div>
        <div class="flex items-center justify-center">
          <!-- 로고 -->
          <div class="flex items-center gap-2">
            <img src="@/assets/images/logo_full.png" alt="로고" class="h-20" />
          </div>
        </div>

        <!-- form -->
        <form @submit="onSubmit" class="mt-10">
          <FormField v-slot="{ componentField }" name="username">
            <FormItem>
              <FormControl>
                <Input v-bind="componentField" type="text" placeholder="아이디" name="username" />
              </FormControl>
              <FormMessage />
            </FormItem>
          </FormField>
          <FormField v-slot="{ componentField }" name="nickname">
            <FormItem class="mt-2">
              <FormControl>
                <Input v-bind="componentField" type="text" placeholder="닉네임" name="nickname" />
              </FormControl>
              <FormMessage />
            </FormItem>
          </FormField>
          <FormField v-slot="{ componentField }" name="password">
            <FormItem class="mt-2">
              <FormControl>
                <Input
                  v-bind="componentField"
                  type="password"
                  placeholder="비밀번호"
                  name="password"
                />
              </FormControl>
              <FormMessage />
            </FormItem>
          </FormField>
          <FormField v-slot="{ componentField }" name="confirm-password">
            <FormItem class="mt-2">
              <FormControl>
                <Input
                  v-bind="componentField"
                  type="password"
                  placeholder="비밀번호 확인"
                  name="confirm-password"
                />
              </FormControl>
              <FormMessage />
            </FormItem>
          </FormField>
          <Button type="submit" class="w-full mt-8"> 회원 가입</Button>
          <Button as-child variant="ghost" class="w-full mt-4">
            <router-link :to="{ name: 'Login' }"> 이미 계정이 있어요</router-link>
          </Button>
        </form>
      </div>
    </div>
  </main>
</template>

<style scoped>
.error-message {
  @apply mt-1.5 ml-2 text-destructive;
}
</style>
