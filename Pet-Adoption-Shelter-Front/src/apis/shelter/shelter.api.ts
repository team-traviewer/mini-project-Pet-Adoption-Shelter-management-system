import type { ApiResponse } from "@/types/common/ApiResponse";
import { SHELTER_PATH } from "./shelter.path";
import type { CreateShelterReq, ShelterDetailResponse, ShelterListResponse, ShelterUpdateReq } from "@/types/shelter/shelter.dto";
import { privateApi, publicApi } from "../common/axiosInstance";
import { FILE_PATH } from "../file/file.path";

export const ShelterApi = {
  createShelterInfo: async (req: CreateShelterReq) : Promise<ApiResponse<void>> => {
    const res = await privateApi.post<ApiResponse<void>>(
      SHELTER_PATH.ROOT, req, {headers: {"Content-Type": "multipart/form-data"}}
    );
    return res.data;
  },


  getShelterList: async () => {
    const res = await publicApi.get<ShelterListResponse> (
      SHELTER_PATH.ROOT
    );
    return res.data;
  },


  getShelterDetail: async (shelterId: number) => {
    const res = await publicApi.get<ShelterDetailResponse> (
      SHELTER_PATH.BY_ID(shelterId)
    );
    return res.data;
  },


  updateShelterInfo: async (shelterId: number, req: ShelterUpdateReq): Promise<ApiResponse<void>> => {
    const res = await privateApi.put<ApiResponse<void>>(
      SHELTER_PATH.BY_ID(shelterId), req,
        {headers : { "Content-Type" : "multipart/form-data"}}
    );
    return res.data;
  },


  deleteShelter: async (shelterId: number): Promise<void> => {
    await privateApi.delete(SHELTER_PATH.BY_ID(shelterId));
  }
}