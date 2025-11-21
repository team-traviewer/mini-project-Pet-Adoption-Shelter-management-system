//! application.dto.ts

import type { ApplicationStatus } from "./application.type";

// 입양 신청
export interface ApplicationCreateReq {
  message?: string;
}

// 입양 신청 취소
export interface ApplicationCancelReq {
  reason?: string;
}

// 입양 신청 거절
export interface ApplicationRejectReq {
  reason?: string;
}

// 입양 신청 목록 조회
export interface ApplicationList {
  id: number;
  species: Species;
  status: ApplicationStatus;
  message?: string;
  applicantName: string;
  createdAt: string;
}

export type ApplicationListRes = ApplicationList[];

// 입양 신청 상세 조회
export interface ApplicationDetailRes {
  id: number;
  species: Species;
  status: ApplicationStatus;
  message?: string;
  applicantName: string;
  interviewAt?: string;
  homeCheck: boolean;
  reason?: string;
  createdAt: string;
  updatedAt: string;
}

// 입양 신청 심사상태 수정
export interface ApplicationUpdateReq {
  interviewAt?: string;
  homeCheck: boolean;
}
