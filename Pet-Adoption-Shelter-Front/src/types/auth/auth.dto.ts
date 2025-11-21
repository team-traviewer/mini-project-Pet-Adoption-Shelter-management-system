export interface SignUpRequest {
  loginId: string;
  password: string;
  confirmPassword: string;
  name: string;
  email: string;
  phone: string;
}

export interface SignUpResponse {
  loginId: string;
  password: string;
  confirmPassword: string;
  name: string;
  email: string;
  phone: string;
}

export interface LoginRequest {
  loginId: string;
  password: string;
}

export interface LoginResponse {
  accessToken: string;
  refreshToken: string;
  expireTime: number;
}

export interface LogoutRequest {
  refreshToken: string;
}

export interface RefreshRequest {
  refreshToken: string;
}

export interface PasswordResetRequest {
  token: string;
  newPassword: string;
  confirmNewPassword: string;
}

export interface PasswordVerifyResponse {
  valid: boolean;
  email: string;
}