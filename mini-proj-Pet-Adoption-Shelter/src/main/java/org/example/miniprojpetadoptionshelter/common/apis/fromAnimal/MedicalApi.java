package org.example.miniprojpetadoptionshelter.common.apis.fromAnimal;

import org.example.miniprojpetadoptionshelter.common.apis.ApiBase;
import org.example.miniprojpetadoptionshelter.common.apis.animal.AnimalApi;

public class MedicalApi {
    private MedicalApi () {}

    public static final String ROOT = ApiBase.BASE + "/medical";
    public static final String ID_ONLY = "/{medicalId}";
    public static final String BY_ID = ROOT + ID_ONLY;
    public static final String MEDICALANIMAL = AnimalApi.BY_ID + "/medical";
}
