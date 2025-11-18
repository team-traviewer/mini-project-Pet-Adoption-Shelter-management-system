import { BASE } from "../common/base.path";

const SHELTER_PREFIX = `${BASE}/shelters`;

export const SHELTER_PATH = {
  ROOT: SHELTER_PREFIX,
  BY_ID: (shelterId: number) => `${SHELTER_PREFIX}/${shelterId}`,
}