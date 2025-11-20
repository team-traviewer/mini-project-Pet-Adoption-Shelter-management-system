import { ANIMAL_PREFIX } from "../animal/animal.path";
import { SHELTER_PREFIX } from "../shelter/shelter.path";

export const FILE_PREFIX = `/files`;

export const FILE_PATH = {
  FILE_BY_ID: (fileId: number) => `${FILE_PREFIX}/${fileId}`,

  FILES_BY_ANIMAL: (animalId: number) => `${ANIMAL_PREFIX}/${animalId}/${FILE_PREFIX}`,
  FILE_BY_ID_IN_ANIMAL: (fileId: number) => `${ANIMAL_PREFIX}/${FILE_PREFIX}/${fileId}`,

  FILES_BY_SHELTER: (shelterId: number) => `${SHELTER_PREFIX}/${shelterId}/${FILE_PREFIX}`,
  FILE_BY_ID_IN_SHELTER: (fileId: number) => `${SHELTER_PREFIX}/${FILE_PREFIX}/${fileId}`,

  FILES_BY_ADOPTION: (adoptionId: number) => `${ADOPTION_PREFIX}/${adoptionId}/${FILE_PREFIX}`,
  FILE_BY_ID_IN_ADOPTION: (fileId: number) => `${ADOPTION_PREFIX}/${FILE_PREFIX}/${fileId}`
}