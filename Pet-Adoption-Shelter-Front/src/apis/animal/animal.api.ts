import type { ApiResponse } from "@/types/common/ApiResponse";
import { privateApi, publicApi } from "../common/axiosInstance"
import { ANIMAL_PATH } from "./animal.path"
import type { AnimalListResponse, AnimalCreateReq, AnimalDetailDto,  AnimalUpdateReq } from "@/types/animal/animal.dto";
import { FILE_PATH } from "../file/file.path";

export const AnimalApi = {
  createAnimalInfo: async (req: AnimalCreateReq) : Promise<string> => {
    // const formData = new FormData;

    // formData.append(
    //   "request", new Blob([JSON.stringify(req)], {type: "application/json"})
    // );

    // if (req.files && req.files.length > 0) {
    //   req.files.forEach(file => formData.append("files", file))
    // }

    try {
      await privateApi.post<void>(
        ANIMAL_PATH.ROOT, req, {headers: {"Content-Type": "multipart/form-data"}}
      ); 
      
      return "성공";
    } catch (error) {
      console.error(error);
      return "실패";
    }
    
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
    const res = await publicApi.get<ApiResponse<AnimalDetailDto>> (
      ANIMAL_PATH.BY_ID(animalId)
    );
    return res.data;
  },

  // 수정
  updateAnimalInfo: async (animalId: number, req: AnimalUpdateReq): Promise<string> => {
    // const formData = new FormData();

    // formData.append(
    //   "request",
    //   new Blob([JSON.stringify(req)], {type: "application/json"})
    // )

    // if(req.files && req.files.length > 0) {
    //   req.files.forEach(file => formData.append("files", file))
    // }

    try {
      await privateApi.put<ApiResponse<void>>(
        ANIMAL_PATH.BY_ID(animalId), req,
        {headers : { "Content-Type" : "multipart/form-data"}}
      );
      return "성공";
    } catch (error) {
      console.error(error);
      return "실패";
    }
  },


  // 삭제 - 보류
  deleteAnimal: async (animalId: number): Promise<void> => {
    await privateApi.delete(ANIMAL_PATH.BY_ID(animalId));
  }
}