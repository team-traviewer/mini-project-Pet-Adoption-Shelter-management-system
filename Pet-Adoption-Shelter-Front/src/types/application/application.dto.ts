//! application.dto.ts

import type { ApplicationStatus } from "./application.type";

// 입양 신청
export interface ApplicationCreateReq {
  // animalId: number;     @PathVariable
  // applicantId: number;  @AuthenticalPrincipal
  message?: string;
}

// 입양 신청 목록 조회
export interface ApplicationList {
  id: number;
  animalId: number;
  status: ApplicationStatus;
  message?: string;
  createdAt: string;
}

export type ApplicationListRes = ApplicationList[];

// 입양 신청 상세 조회
export interface ApplicationDetailRes {
  id: number;
  animalId: number;
  applicantId: number;
  status: ApplicationStatus;
  message?: string;
  interviewAt?: string;
  homeCheck: boolean;
  createdAt: string;
  updatedAt: string;
}

// 입양 신청 심사상태 수정
export interface ApplicationUpdateReq {
  interviewAt?: string;
  homeCheck: boolean;
}