package org.example.miniprojpetadoptionshelter.common.apis;

public class AdminApi {
    private AdminApi() {};

    public static final String ROOT = ApiBase.BASE + "/admin";
    public static final String LIST = ROOT + "/users";
    public static final String BY_ID = ROOT + "/users/{userId}";
}
