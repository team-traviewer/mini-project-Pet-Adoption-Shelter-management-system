import type { ApiResponse } from "@/types/common/ApiResponse";
import { SHELTER_PATH } from "./shelter.path";
import type { CreateShelterReq, ShelterDetailResponse, ShelterListResponse, ShelterUpdateReq } from "@/types/shelter/shelter.dto";
import { privateApi, publicApi } from "../common/axiosInstance";
import { FILE_PATH } from "../file/file.path";

export const ShelterApi = {
// 등록 -> 이미지 등록
  createShelterInfo: async (req: CreateShelterReq) : Promise<void> => {
    const res = await privateApi.post<ApiResponse<void>>(
      SHELTER_PATH.ROOT, req
    );
    return res.data.data;
  },

  uploadShelterImg: async (shelterId: number, formData: FormData) => {
    const res = await privateApi.post<void>(
      FILE_PATH.FILES_BY_SHELTER(shelterId), formData,
      {headers: {"Content-Type": "multipart/form-data"}}
    );
    return res.data;
  },

  // 조회
  getShelterList: async () => {
    const res = await publicApi.get<ShelterListResponse> (
      SHELTER_PATH.ROOT
    );
    return res.data;
  },

  // 상세 조회
  getShelterDetail: async (shelterId: number) => {
    const res = await publicApi.get<ShelterDetailResponse> (
      SHELTER_PATH.BY_ID(shelterId)
    );
    return res.data;
  },

  // 수정
  updateShelterInfo: async (shelterId: number, req: ShelterUpdateReq) => {
    const res = await privateApi.put<void>(
      SHELTER_PATH.BY_ID(shelterId), req
    );
    return res.data;
  },

  updateShelterImg: async (shelterId: number, formData: FormData) => {
    const res = await privateApi.put<void>(
      FILE_PATH.FILES_BY_SHELTER(shelterId), formData,
      {headers: {"Content-Type": 'multipart/form-data'}}
    );
    return res.data;
  },


  // 삭제 - 보류
  deleteShelter: async (shelterId: number): Promise<void> => {
    await privateApi.delete(SHELTER_PATH.BY_ID(shelterId));
  }
}