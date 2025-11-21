import type { Status } from "@/apis/foster/foster.path";

export interface FosterCreateReq{
  animalId: number;
  fosterUserId: number;
  startDate: string;
  endDate?: string;
  status: Status;
  // PATH 쪽에  export type Status = 'ACTIVE'|'CLOSED'|'CANCELED'; 선언 되어있음.
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
