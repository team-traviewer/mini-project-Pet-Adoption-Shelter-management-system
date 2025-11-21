import { BASE } from "../common/base.path";

const AUTH_PREFIX = `${BASE}/auth`;

export const AUTH_PATH = {
  ROOT: AUTH_PREFIX,
  SIGNUP: `${AUTH_PREFIX}/signup`,
  LOGIN: `${AUTH_PREFIX}/login`,
  LOGOUT: `${AUTH_PREFIX}/logout`,
  REFRESH: `${AUTH_PREFIX}/refresh`,
  PASSWORD_RESET: `${AUTH_PREFIX}/password/reset`,
  VERIFY: `${AUTH_PREFIX}/verify`,
  EMAIL_SEND: `${AUTH_PREFIX}/email/send`,
}