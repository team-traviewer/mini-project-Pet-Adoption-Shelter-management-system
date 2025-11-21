export interface MyProfileResponse {
  name: string;
  email: string;
  phone: string;
  createdAt: string;
}

export interface UpdateMyProfileRequest {
  name: string;
  email: string;
  phone: string;
}