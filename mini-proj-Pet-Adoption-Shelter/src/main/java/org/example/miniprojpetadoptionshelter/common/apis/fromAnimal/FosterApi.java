package org.example.miniprojpetadoptionshelter.common.apis.fromAnimal;

import org.example.miniprojpetadoptionshelter.common.apis.ApiBase;

public class FosterApi {
    private FosterApi () {}

    public static final String ROOT = ApiBase.BASE + "/foster";
    public static final String ID_ONLY = "/{fosterId}";
    public static final String BY_ID = ROOT + ID_ONLY;
    public static final String CLOSE = BY_ID + "/close";
    public static final String CANCEL = BY_ID + "/cancel";

}
