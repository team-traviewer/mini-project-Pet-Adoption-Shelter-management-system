//! application.dto.ts

// 입양 신청
export interface ApplicationCreateReq {
  animalId: number;
  applicantId: number;
  message?: string;
}

// 입양 신청 상세 조회
export interface ApplicationDetailRes {
  id: number;
  animalId: number;
  applicantId: number;
  status: string;
  message?: string;
  interviewAt?: Date;
  homeCheck: boolean;
  createdAt: Date;
  updatedAt: Date;
}

// 입양 신청 목록 조회
export interface ApplicationList {
  id: number;
  animalId: number;
  status: string;
  message?: string;
  createdAt: Date;
}

export type ApplicationListRes = ApplicationList[];
