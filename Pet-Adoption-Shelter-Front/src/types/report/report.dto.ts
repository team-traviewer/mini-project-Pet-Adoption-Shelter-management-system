// 1) 대시보드
export interface DashboardDto {
  newIntakes: number;
  availableAnimals: number;
  protectionOrTreatment: number;
  reference: string;
  message: string;
}

export interface DashboardResponse {
  newIntakes: number;
  availableAnimals: number;
  protectionOrTreatment: number;
  reference: string;
  message: string;
}

// 2) 동물 상태 리포트
export interface AnimalStatusReportDto {
  reportId: number;
  totalAnimals: number;
  message: string;
}

export interface AnimalStatusReportResponse {
  reportId: number;
  totalAnimals: number;
  message: string;
}

// 3) 입양 신청 리포트
export interface ApplicationReportDto {
  reportId: number;
  totalApplications: number;
  message: string;
}

export interface ApplicationReportResponse {
  reportId: number;
  totalApplications: number;
  message: string;
}

// 4) 입양 완료 / 수수료 리포트
export interface AdoptionReportDto {
  reportId: number;
  totalAdoptions: number;
  totalFee: number;
  message: string;
}

export interface AdoptionReportResponse {
  reportId: number;
  totalAdoptions: number;
  totalFee: number;
  message: string;
}