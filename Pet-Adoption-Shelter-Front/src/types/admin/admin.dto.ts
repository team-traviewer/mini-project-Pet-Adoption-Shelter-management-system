export interface UserListDto {
  loginId: string;
  name: string;
  createdAt: string;
}

export type UserListResponse = UserListDto[];

export interface UserProfileResponse {
  loginId: string;
  name: string;
  email: string;
  phone: string;
  createdAt: string;
  updatedAt: string;
}