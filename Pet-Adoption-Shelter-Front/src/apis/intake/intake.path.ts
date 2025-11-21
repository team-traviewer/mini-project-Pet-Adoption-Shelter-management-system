import { ANIMAL_PREFIX } from "../animal/animal.path";
import { BASE } from "../common/base.path";


export const INTAKE_PREFIX = `${BASE}`;

export const INTAKE_PATH = { 
  ROOT: INTAKE_PREFIX,
  BY_ID: (intakeId: number) => `${INTAKE_PREFIX}/${intakeId}`,
  INTAKES_ANIMAL: (animalId: number) => `${ANIMAL_PREFIX}/${animalId}/intakes`,
}