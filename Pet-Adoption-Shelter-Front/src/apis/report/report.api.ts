import { privateApi } from "../common/axiosInstance";
import type { ApiResponse } from "@/types/common/ApiResponse";

import type {
  DashboardDto,
  AnimalStatusReportDto,
  ApplicationReportDto,
  AdoptionReportDto,
} from "@/types/report/report.dto";

import { REPORT_PATH } from "./report.path";

export const ReportApi = {

  // 보호소 대시보드
  getDashboard: async (shelterId: string): Promise<DashboardDto> => {
    const res = await privateApi.get<ApiResponse<DashboardDto>>(
      REPORT_PATH.DASHBOARD(shelterId)
    );
    return res.data.data;
  },

  // 동물 상태 리포트
  getAnimalStatus: async (): Promise<AnimalStatusReportDto> => {
    const res = await privateApi.get<ApiResponse<AnimalStatusReportDto>>(
      REPORT_PATH.ANIMAL_STATUS
    );
    return res.data.data;
  },

  // 입양 신청 리포트
  getApplications: async (): Promise<ApplicationReportDto> => {
    const res = await privateApi.get<ApiResponse<ApplicationReportDto>>(
      REPORT_PATH.APPLICATIONS
    );
    return res.data.data;
  },

  // 입양 완료 / 수수료 리포트
  getAdoptions: async (): Promise<AdoptionReportDto> => {
    const res = await privateApi.get<ApiResponse<AdoptionReportDto>>(
      REPORT_PATH.ADOPTIONS
    );
    return res.data.data;
  },
};