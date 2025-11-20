export interface AnimalCreateReq {
  shelterId: number;
  species: string;
  breed?: string;
  sex: string;
  ageYears?: number;
  weightKg?: number;
  temperament?: string;
  status: string;
}

export interface AnimalListDto {
  animalId: number;
  shelterId: number;
  species: string;
}

export interface AnimalFileListDto {
  filedId: number;
  originalName: string;
  storedName: string;
  contentType: string;
  fileSize: number;
  downloadUrl: string;
}

export type AnimalListResponse = AnimalListDto[];

export interface AnimalDetailDto {
  animalId: number;
  shelterId: number;
  species: string;
  breed: string;
  sex: string;
  ageYears: number;
  weightKg?: number;
  temperament?: string;
  status: string;

}

export interface AnimalUpdateReq {
  shelterId: number;
  species: string;
  breed?: string;
  sex: string;
  ageYears?: number;
  weightKg?: number;
  temperament?: string;
  status: string;
}

export interface AnimalFileUpdateReq {
  keepFileIds?: number [];
  files?: File[];  
}