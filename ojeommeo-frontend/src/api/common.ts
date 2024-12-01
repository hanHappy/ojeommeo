import { instance } from '@/api/axios';
import type { AxiosRequestConfig } from 'axios';
import type { SignUpRequest } from '@/types/auth';

export const api = {
  signUp: (data: SignUpRequest) => {
    return request<void>({
      url: '/auth/users',
      method: 'POST',
      data,
    });
  },
  get: {
    users: () => {},
  },
};

export const request = async <T = unknown>({
  url,
  method = 'GET',
  ...config
}: AxiosRequestConfig): Promise<T> => {
  return instance.request<any, T>({
    url,
    method,
    ...config,
  });
};
