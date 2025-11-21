import { BASE } from "../common/base.path";


export const FOSTER_PREFIX = `${BASE}/foster`
export type Status = 'ACTIVE'|'CLOSED'|'CANCELED';

export const FOSTER_PATH = {
  
  ROOT: FOSTER_PREFIX,
  BY_ID: (fosterId: number) => `${FOSTER_PREFIX}/${fosterId}`,
  CHANGE_STATUS: (fosterId: number, status: Status) => `${FOSTER_PREFIX}/${fosterId}/${status}`,
}