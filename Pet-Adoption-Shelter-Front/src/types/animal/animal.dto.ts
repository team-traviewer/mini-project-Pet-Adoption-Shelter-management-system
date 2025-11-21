export interface AnimalCreateReq {
  shelterId: number;
  name: string;
  species: string;
  breed?: string;
  sex: string;
  ageYears?: number;
  weightKg?: number;
  temperament?: string;
  status: string;
  files?: File [];
}

export interface AnimalListDto {
  animalId: number;
  shelterId: number;
  name: string;
  species: string;
}

// export interface AnimalFileListDto {
//   filedId: number;
//   originalName: string;
//   storedName: string;
//   contentType: string;
//   fileSize: number;
//   downloadUrl: string;
// }

export type AnimalListResponse = AnimalListDto[];

export interface AnimalDetailDto {
  animalId: number;
  shelterId: number;
  name: string;
  species: string;
  breed: string;
  sex: string;
  ageYears: number;
  weightKg?: number;
  temperament?: string;
  status: string;
  files?: File[];
}

export interface AnimalUpdateReq {
  shelterId: number;
  name: string;
  species: string;
  breed?: string;
  sex: string;
  ageYears?: number;
  weightKg?: number;
  temperament?: string;
  status: string;

  keepFileIds?: number [];
  files?: File[];  
}