import { BASE } from "../common/base.path";
import { USER_PATH } from "../user/user.path";

const ROLE_PREFIX = `${BASE}/roles`;

export const ROLE_PATH = {
  ROOT: `${ROLE_PREFIX}`,
  GRANT_ROLE: (userId: number) => `${USER_PATH.ROOT}/${userId}/roles`,
  REVOKE_ROLE: (userId: number, roleName: string) => `${USER_PATH.ROOT}/${userId}/roles/${roleName}`
}