export interface VeiwAllUsersDto {
  loginId: string;
  name: string;
  createdAt: string;
}

export type VeiwAllUsersResponse = VeiwAllUsersDto[];

export interface ViewUserProfile {
  loginId: string;
  name: string;
  email: string;
  phone: string;
  createdAt: string;
  updatedAt: string;
}