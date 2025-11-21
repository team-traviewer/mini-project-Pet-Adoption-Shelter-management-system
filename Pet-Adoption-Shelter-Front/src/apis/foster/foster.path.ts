import { BASE } from "../common/base.path";


export const FOSTER_PREFIX = `${BASE}/foster`

export const FOSTER_PATH = {
  ROOT: FOSTER_PREFIX,
  BY_ID: (fosterId: number) => `${FOSTER_PREFIX}/${fosterId}`,
  HISTORY: (fosterId: number) => `${FOSTER_PREFIX}/${fosterId}/history`,
  // 이건 잘 모르겠음

}