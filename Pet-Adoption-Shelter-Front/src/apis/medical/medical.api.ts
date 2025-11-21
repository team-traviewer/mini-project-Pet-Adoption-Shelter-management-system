import type { MedicalCreateReq, MedicalUpdateReq, MedicalListResponse, MedicalDetailResponse } from "@/types/medical/medical.dto";
import { privateApi, publicApi } from "../common/axiosInstance";
import type { ApiResponse } from "@/types/common/ApiResponse";
import { MEDICAL_PATH } from "./medical.path";


export const MedicalApi = {
  // 의료 정보 등록
  createMedicalInfo: async (animalId: number,req: MedicalCreateReq): Promise<void> => {
    const res = await privateApi.post<ApiResponse<void>>(MEDICAL_PATH.MEDICAL_ANIMAL(animalId), req);
    return res.data.data;
  },

  // 의료 정보 조회
  getMedicalList : async (animalId: number) => {
    const res = await publicApi.get<MedicalListResponse>(MEDICAL_PATH.MEDICAL_ANIMAL(animalId));
    return res.data;
  },

  // 의료 정보 상세 조회
  getMedicalDetail : async (medicalId: number) => {
    const res = await publicApi.get<MedicalDetailResponse>(MEDICAL_PATH.BY_ID(medicalId));
    return res.data;
  },

  // 의료 정보 수정
  updateMedicalInfo: async (medicalId: number, req: MedicalUpdateReq) => {
    const res = await privateApi.put<void>(
      MEDICAL_PATH.BY_ID(medicalId), req
    );
    return res.data;
  }
}