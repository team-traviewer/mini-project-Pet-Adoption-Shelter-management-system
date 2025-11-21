import { BASE } from "../common/base.path"

const ADMIN_PREFIX = `${BASE}/admin`

export const ADMIN_PATH = {
  ROOT: `${ADMIN_PREFIX}`,
  LIST: `${ADMIN_PREFIX}/users`,
  BY_ID: (userId: number) => `${ADMIN_PREFIX}/users/${userId}`,
}