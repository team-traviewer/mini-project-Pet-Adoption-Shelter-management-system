import type { ApiResponse } from "@/types/common/ApiResponse";
import { privateApi, publicApi } from "../common/axiosInstance"
import { ANIMAL_PATH } from "./animal.path"
import type { AnimalListResponse, AnimalCreateReq, AnimalDetailDto,  AnimalUpdateReq } from "@/types/animal/animal.dto";
import { FILE_PATH } from "../file/file.path";

export const AnimalApi = {
  // 등록 -> 이미지 등록
  createAnimalInfo: async (req: AnimalCreateReq) : Promise<void> => {
    const res = await privateApi.post<ApiResponse<void>>(
      ANIMAL_PATH.ROOT, req
    );
    return res.data.data;
  },

  uploadAnimalImg: async (animalId: number, formData: FormData) => {
    const res = await privateApi.post<ApiResponse<void>>(
      FILE_PATH.FILES_BY_ANIMAL(animalId), formData,
      {headers: {"Content-Type": "multipart/form-data"}}
    );
    return res.data;
  },

  // 조회
  getAnimalList: async () => {
    const res = await publicApi.get<ApiResponse<AnimalListResponse>> (
      ANIMAL_PATH.ROOT
    );
    return res.data;
  },

  // 상세 조회
  getAnimalDetail: async (animalId: number) => {
    const res = await publicApi.get<AnimalDetailDto> (
      ANIMAL_PATH.BY_ID(animalId)
    );
    return res.data;
  },

  // 수정
  updateAnimalInfo: async (animalId: number, req: AnimalUpdateReq) => {
    const res = await privateApi.put<void>(
      ANIMAL_PATH.BY_ID(animalId), req
    );
    return res.data;
  },

  updateAnimalImg: async (animalId: number, formData: FormData) => {
    const res = await privateApi.put<void>(
      FILE_PATH.FILES_BY_ANIMAL(animalId), formData,
      {headers: {"Content-Type": 'multipart/form-data'}}
    );
    return res.data;
  },


  // 삭제 - 보류
  deleteAnimal: async (animalId: number): Promise<void> => {
    await privateApi.delete(ANIMAL_PATH.BY_ID(animalId));
  }
}