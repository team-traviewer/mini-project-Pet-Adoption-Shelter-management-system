export interface CreateShelterReq {
  name: string;
  address: string;
  latitude?: number;
  longitude?:number;
  phone: string;
  files?: File[];
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
  files?: File[];
}

export interface ShelterUpdateReq {
  name: string;
  address: string;
  latitude?: number;
  longitude?:number;
  phone: string;
  files?: File[];
}

export interface ShelterFileUpdateReq {
  files?: File[];
}