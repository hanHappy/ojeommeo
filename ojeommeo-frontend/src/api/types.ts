export interface ApiErrorType {
  code: string;
  status: number;
  message: string;
  errors?: FieldError[];
}

export interface FieldError {
  field: string;
  value: string;
  message: string;
}
