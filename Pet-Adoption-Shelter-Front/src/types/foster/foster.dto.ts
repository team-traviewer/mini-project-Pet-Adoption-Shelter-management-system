import type { Status } from "@/apis/foster/foster.path";

export interface FosterCreateReq{
  animalId: number;
  fosterUserId: number;
  startDate: string;
  endDate?: string;
  status: 'ACTIVE'|'CLOSED'|'CANCELED';
  // enum은 이렇게 넣음
  note?: string;
}

export interface FosterListDto {
  fosterId: number;
  animalId: number;
  fosterUserId: number;
  startDate: string;
  endDate?: string;
  status: string;
}

export type FosterListResponse = FosterListDto[];

export interface FosterDetailResponse {
id:	number;
animalId:	number;
fosterUserId:	number;
startDate:	string;
endDate:	string;
status:	String;
note:	string;
createdAt:	string;
message:	string
}
