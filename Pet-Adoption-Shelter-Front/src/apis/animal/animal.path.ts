import { BASE } from "../common/base.path";

const ANIMAL_PREFIX = `${BASE}/animals`

export const ANIMAL_PATH = {
  ROOT: ANIMAL_PREFIX,
  BY_ID: (animalId: number) => `${ANIMAL_PREFIX}/${animalId}`,
  HISTORY: (animalId: number) => `${ANIMAL_PREFIX}/${animalId}/history`
}