import { BASE } from "../common/base.path";

const USER_PREFIX = `${BASE}/users`

export const USER_PATH = {
  ROOT: `${USER_PREFIX}`,
  BY_ID: (userId: number) => `${USER_PREFIX}/${userId}`,

  VIEW_MY: `${USER_PREFIX}/me`,
  UPDATE_MY: `${USER_PREFIX}/me`,

  LIST: `${USER_PREFIX}`,
  UPDATE_USER: (userId: number) => `${USER_PREFIX}/${userId}`,
  
}