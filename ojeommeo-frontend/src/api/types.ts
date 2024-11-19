export interface ApiErrorType {
  code: string;
  status: number;
  message: string;
  errors?: string[];
}
