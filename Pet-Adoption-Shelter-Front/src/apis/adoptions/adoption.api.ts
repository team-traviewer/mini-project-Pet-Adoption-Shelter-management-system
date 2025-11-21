import type {
  CreateAdoptionReq,
  UpdateAdoptionReq,
  AdoptionRes,
} from "@/types/adoptions/adoption.dto";

import type { ApiResponse } from "@/types/common/ApiResponse";
import { privateApi } from "../common/axiosInstance";
import { ADOPTION_PATH } from "./adoption.path";

export const adoptionsApi = {
  // 입양 계약 생성
  create: async (req: CreateAdoptionReq): Promise<AdoptionRes> => {
    const res = await privateApi.post<ApiResponse<AdoptionRes>>(
      ADOPTION_PATH.ROOT,
      req
    );
    return res.data.data;
  },

  // 입양 계약 상세 조회
  getById: async (adoptionId: number): Promise<AdoptionRes> => {
    const res = await privateApi.get<ApiResponse<AdoptionRes>>(
      ADOPTION_PATH.BY_ID(adoptionId)
    );
    return res.data.data;
  },

  // 입양 계약 수정
  update: async (
    adoptionId: number,
    req: UpdateAdoptionReq
  ): Promise<AdoptionRes> => {
    const res = await privateApi.put<ApiResponse<AdoptionRes>>(
      ADOPTION_PATH.BY_ID(adoptionId),
      req
    );
    return res.data.data;
  },
};