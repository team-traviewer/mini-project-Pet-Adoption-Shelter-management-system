package org.example.miniprojpetadoptionshelter.common.apis;

public class AuthApi {
    private AuthApi () {}

    public static final String ROOT = ApiBase.BASE + "/auth";
    public static final String SIGNUP = ROOT + "/signup";
    public static final String LOGIN = ROOT + "/login";
    public static final String LOGOUT = ROOT + "/logout";
    public static final String REFRESH = ROOT + "/refresh";
    public static final String PASSWORD_RESET = ROOT + "/password/reset";
    public static final String VERIFY = ROOT + "/verify";
}
