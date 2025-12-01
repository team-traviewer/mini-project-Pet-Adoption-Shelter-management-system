package org.example.miniprojpetadoptionshelter.common.apis.animal;

import org.example.miniprojpetadoptionshelter.common.apis.ApiBase;

public class AnimalFileApi {
    private AnimalFileApi () {}

    public static final String ROOT = ApiBase.BASE + "/animal-files";

    public static final String FILES_BY_ANIMAL = "/{animalId}/files";

    public static final String UPLOAD = FILES_BY_ANIMAL;

    public static final String LIST = FILES_BY_ANIMAL;

    public static final String UPDATE = FILES_BY_ANIMAL;



}
