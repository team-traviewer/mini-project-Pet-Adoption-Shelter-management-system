export interface CreateShelterReq {
  name: string;
  address: string;
  latitude?: number;
  longitude?:number;
  phone: string;
}

export interface ShelterListDto {
  name: string;
  address: string;
  phone: string;
}

export type ShelterListResponse = ShelterListDto[];

export interface ShelterDetailResponse {
  shelterId: number;
  address: string;
  latitude?: number;
  longitude?:number;
  phone: string;
}

export interface ShelterUpdateReq {
  name: string;
  address: string;
  latitude?: number;
  longitude?:number;
  phone: string;
}

export interface ShelterFileUpdateReq {
  files?: File[];
}