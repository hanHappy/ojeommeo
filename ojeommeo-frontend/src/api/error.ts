import type { ApiErrorType } from '@/api/types';

export class ApiError extends Error implements ApiErrorType {
  readonly code: string;
  readonly status: number;
  readonly errors: string[];

  constructor (code: string, status: number, message: string, errors: string[] = []) {
    super(message);
    this.name = 'ApiError';
    this.code = code;
    this.status = status;
    this.errors = errors;

    // Error 상속 시 prototype 체인 수정을 위해 필요
    // https://developer.mozilla.org/ko/docs/Web/JavaScript/Inheritance_and_the_prototype_chain
    Object.setPrototypeOf(this, ApiError.prototype);
  }
}

export class UnknownError extends Error {
  readonly status: number;

  constructor (status: number, message: string) {
    super(message);
    this.name = 'UnknownError';
    this.status = status;

    Object.setPrototypeOf(this, UnknownError.prototype);
  }
}

export function isApiError (error: unknown): error is ApiError {
  return error instanceof ApiError;
}

export function isUnknownError (error: unknown): error is UnknownError {
  return error instanceof UnknownError;
}
