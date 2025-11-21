// report.path.ts

import { BASE } from "../apis/common/base.path";

export const REPORT_PREFIX = `${BASE}/reports`;
export const SHELTER_PREFIX = `${BASE}/shelters`;

export const REPORT_PATH = {
  // 보호소 대시보드
  DASHBOARD: (shelterId: string) => `${SHELTER_PREFIX}/${shelterId}/dashboard`,

  // 동물 상태 리포트
  ANIMAL_STATUS: `${REPORT_PREFIX}/animals`,

  // 입양 신청 리포트
  APPLICATIONS: `${REPORT_PREFIX}/applications`,

  // 입양 완료 / 수수료 리포트
  ADOPTIONS: `${REPORT_PREFIX}/adoptions`,
};