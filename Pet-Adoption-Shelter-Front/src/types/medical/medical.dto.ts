export interface MedicalCreateReq {
  animalId: number;
  recordDate: string;        
  type: 'VACCINE' | 'NEUTER' | 'TREATMENT' | 'EXAM';
  description: string;
  cost?: number;
}

export interface MedicalListDto {
  medicalId: number;
  animalId: number;
  recordDate: string;
  type: 'VACCINE' | 'NEUTER' | 'TREATMENT' | 'EXAM';
}

export type MedicalListResponse = MedicalListDto[];

export interface MedicalDetailResponse {
  medicalId: number;
  animalId: number;
  recordDate: string;
  type: 'VACCINE' | 'NEUTER' | 'TREATMENT' | 'EXAM';
  description: string;
  cost?: number;
  createdAt: string;
}

export interface MedicalUpdateReq {
  recordDate?: string;
  type?: 'VACCINE' | 'NEUTER' | 'TREATMENT' | 'EXAM';
  description?: string;
  cost?: number;
}