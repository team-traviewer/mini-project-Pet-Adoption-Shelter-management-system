export interface CreateAdoptionReq {
  animalId: number;
  applicationId: number;
  adopterId: number;
  adoptionDate: string;
  fee?: number;
  contractPDF: string;
}

export interface UpdateAdoptionReq {
  adoptionDate?: string;
  fee?: number;
  contractPDF: string;
}


export interface AdoptionRes {
  adoptionId: number;
  animalId: number;
  applicationId: number;
  adopterId: number;
  adoptionDate: string;
  fee?: number;
  contractPDF: string;
  createdAt?: string;
  updatedAt?: string;
  message: string;
}