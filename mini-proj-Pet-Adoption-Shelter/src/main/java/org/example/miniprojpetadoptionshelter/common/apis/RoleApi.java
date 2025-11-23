package org.example.miniprojpetadoptionshelter.common.apis;

public class RoleApi {
    private RoleApi() {};

    public static final String ROOT = ApiBase.BASE + "/roles";
    public static final String BY_ID = UserApi.ROOT + "/{userId}/roles";
    public static final String BY_ID_ROLE = UserApi.ROOT + "/{userId}/roles/{roleName}";
}
