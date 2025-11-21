import type { ApplicationStatus } from "@/types/application/application.type";
import { BASE } from "../common/base.path";

const APPLICATION_PREFIX = `${BASE}/applications`

export const APPLICATION_PATH = {
  ROOT: APPLICATION_PREFIX,
  BY_ID: (applicationId: number) => `${APPLICATION_PREFIX}/${applicationId}`,
  
  STATUS: (applicationId: number, status: ApplicationStatus) => `${APPLICATION_PREFIX}/${applicationId}/${status}`
}