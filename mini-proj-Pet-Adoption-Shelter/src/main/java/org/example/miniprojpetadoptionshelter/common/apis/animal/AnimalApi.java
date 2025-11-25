package org.example.miniprojpetadoptionshelter.common.apis.animal;

import org.example.miniprojpetadoptionshelter.common.apis.ApiBase;

public class AnimalApi {
    private AnimalApi () {}

    public static final String ROOT = ApiBase.BASE + "/animals";
    public static final String ID_ONLY = "/{animalId}";
    public static final String BY_ID = ROOT + ID_ONLY;
    public static final String HISTORY = BY_ID + "/history";
}
