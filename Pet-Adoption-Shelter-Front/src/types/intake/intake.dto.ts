export interface CreateIntakeReq {
  animalId: number;
  intakeDate: string;
  intakeReason: string;
  foundLocation?: string;
  note?: string;
}

export interface IntakeListDto {
  intakeId: number;
  animalId: number;
  intakeDate: string;
  intakeReason: string;
  // 최소한의 정보만 보이게 하려면 어떻게 해야하는지 아직 잘 모르겠음.
}

export type IntakeListResponse = IntakeListDto[];

export interface IntakeDetailResponse {
  intakeId: number;
  animalId: number;
  intakeDate: string;
  intakeReason: string;
  foundLocation: string;
  note?: string;
  createdAt: string;
}

export interface IntakeupdateReq {
  intakeDate: string;
  intakeReason: string;
  foundLocation: string;
  note?: string;
}