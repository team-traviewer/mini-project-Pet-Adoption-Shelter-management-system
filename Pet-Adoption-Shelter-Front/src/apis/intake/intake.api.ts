import type { ApiResponse } from "@/types/common/ApiResponse";
import type { IntakeCreateReq, IntakeDetailResponse, IntakeListResponse, IntakeupdateReq } from "@/types/intake/intake.dto";
import { INTAKE_PATH } from "@/apis/intake/intake.path";
import { privateApi, publicApi } from "../common/axiosInstance";

export const IntakeApi = {
  // 입소기록 등록
  createIntakeInfo: async (animalId:number,req: IntakeCreateReq): Promise<void> => {
    const res = await privateApi.post<ApiResponse<void>>(INTAKE_PATH.INTAKES_ANIMAL(animalId), req);
    return res.data.data;
  },

  // 입소기록 조회
  getIntakeList : async (animalId: number) => {
    const res = await publicApi.get<IntakeListResponse>(INTAKE_PATH.INTAKES_ANIMAL(animalId));
    return res.data;
  },

  // 입소기록 상세 조회
  getIntakeDetail : async (intakeId: number) => {
    const res = await publicApi.get<IntakeDetailResponse>(INTAKE_PATH.BY_ID(intakeId));
    return res.data;
  },

  // 입소기록 정보 수정
  updateIntakeInfo: async (intakeId: number, req: IntakeupdateReq) => {
    const res = await privateApi.put<void>(
      INTAKE_PATH.BY_ID(intakeId), req
    );
    return res.data;
  }
}