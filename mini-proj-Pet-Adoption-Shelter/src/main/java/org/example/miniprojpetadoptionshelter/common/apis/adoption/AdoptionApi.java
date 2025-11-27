package org.example.miniprojpetadoptionshelter.common.apis.adoption;

import org.example.miniprojpetadoptionshelter.common.apis.ApiBase;

public class AdoptionApi {
    private AdoptionApi() {}
    public static final String ROOT = ApiBase.BASE + "/adoptions";
    public static final String BY_ID = ROOT + "/{adoptionId}";
    public static final String UPDATE = ROOT + "/{adoptionId}";
}
