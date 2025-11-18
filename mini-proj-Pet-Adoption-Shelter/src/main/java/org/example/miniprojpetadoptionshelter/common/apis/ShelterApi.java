package org.example.miniprojpetadoptionshelter.common.apis;

public class ShelterApi {
    private ShelterApi () {}

    public static final String ROOT = ApiBase.BASE + "/shelters";
    public static final String ID_ONLY = "/{shelterId}";
    public static final String BY_ID = ROOT + ID_ONLY;
}
