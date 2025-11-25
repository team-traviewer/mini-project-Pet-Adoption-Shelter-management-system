package org.example.miniprojpetadoptionshelter.common.apis.application;

import org.example.miniprojpetadoptionshelter.common.apis.ApiBase;

public class ApplicationApi {
    private ApplicationApi () {}

    public static final String ROOT = ApiBase.BASE + "/applications";
    public static final String ID_ONLY = "/{applicationId}";
    public static final String BY_ID = ROOT + ID_ONLY;
    public static final String REVIEW = BY_ID + "/review";
    public static final String APPROVE = BY_ID + "/approve";
    public static final String REJECT = BY_ID + "/reject";
    public static final String CANCEL = BY_ID + "/cancel";
}
