import axios from 'axios';
import type { AxiosInstance, AxiosError } from 'axios';
import { ApiError, UnknownError } from './error';
import type { ApiErrorType } from '@/api/types';

// axios 인스턴스 생성
export const instance: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  timeout: 15000,
  headers: {
    'Content-Type': 'application/json',
  },
});

// 요청 인터셉터
instance.interceptors.request.use(
  config => {
    const token = sessionStorage.getItem('JWT');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  },
);

// 응답 인터셉터
instance.interceptors.response.use(
  response => response.data,
  (error: AxiosError<ApiErrorType>) => {
    if (error.response) {
      const { data, status } = error.response;
      if (data?.code && data?.message) {
        throw new ApiError(data.code, status, data.message, data.errors);
      } else {
        throw new UnknownError(status, '서버 오류가 발생하였습니다');
      }
    }
    throw new UnknownError(500, '서버와 통신할 수 없습니다');
  },
);
