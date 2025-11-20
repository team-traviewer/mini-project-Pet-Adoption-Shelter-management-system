export interface SignUpReq {
  loginId: string;
  password: string;
  confirmPassword: string;
  name: string;
  email: string;
  phone?: string;
}

export interface SignUpRes {
  message: string;
}

export interface LoginReq {
  loginId: string;
  password: string;
}

export interface LoginRes {
  accessToken: string;
  expireTime: number;
}

export interface LogoutRes {
  message: string;
}

export interface RefreshReq {
  refreshToken: string;
}

export interface PasswordResetReq {
  loginId: string;
  newPassword: string;
  confirmNewPassword: string;
}

export interface PasswordResetRes {
  refreshToken: string;
}


export interface VerifyReq {
  token: string;
}


export interface EmailSendReq {
  email: string;
}

