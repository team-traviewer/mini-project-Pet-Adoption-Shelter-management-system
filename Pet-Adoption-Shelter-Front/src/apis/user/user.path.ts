import { BASE } from "../common/base.path";

const USER_PREFIX = `${BASE}/users`

export const USER_PATH = {
  ROOT: `${USER_PREFIX}`,
  BY_ID: (userId: number) => `${USER_PREFIX}/${userId}`,
  USER_ME: `${USER_PREFIX}/me`,
}