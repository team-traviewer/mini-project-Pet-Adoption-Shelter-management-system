package org.example.miniprojpetadoptionshelter.common.apis.user;

import org.example.miniprojpetadoptionshelter.common.apis.ApiBase;

public class UserApi {
    private UserApi() {};

    public static final String ROOT = ApiBase.BASE + "/users";
    public static final String BY_ID = ROOT + "/{userId}";
    public static final String ME =  "/me";
}
