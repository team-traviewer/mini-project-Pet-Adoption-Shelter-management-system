import { ANIMAL_PREFIX } from "../animal/animal.path";
import { BASE } from "../common/base.path";


export const MEDICAL_PREFIX = `${BASE}`;

export const MEDICAL_PATH = { 
  ROOT: MEDICAL_PREFIX,
  BY_ID: (medicalId: number) => `${MEDICAL_PREFIX}/${medicalId}`,
  MEDICAL_ANIMAL: (animalId: number) => `${ANIMAL_PREFIX}/${animalId}/medical`,
}