import { BASE } from "../common/base.path";

export const ADOPTION_PREFIX = `${BASE}/adoptions`;

export const ADOPTION_PATH = {
  ROOT: ADOPTION_PREFIX,
  BY_ID: (adoptionId: number) => `${ADOPTION_PREFIX}/${adoptionId}`,
};