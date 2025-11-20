import { BASE } from "../common/base.path";

const APPLICATION_PREFIX = `${BASE}/applications`

export const APPLICATION_PATH = {
  ROOT: APPLICATION_PREFIX,
  BY_ID: (applicationId: number) => `${APPLICATION_PREFIX}/${applicationId}`,
  
  REVIEW: (applicationId: number) => `${APPLICATION_PREFIX}/${applicationId}/review`,
  APPROVE: (applicationId: number) => `${APPLICATION_PREFIX}/${applicationId}/approve`,
  REJECT: (applicationId: number) => `${APPLICATION_PREFIX}/${applicationId}/reject`,
  CANCEL: (applicationId: number) => `${APPLICATION_PREFIX}/${applicationId}/cancel`
}