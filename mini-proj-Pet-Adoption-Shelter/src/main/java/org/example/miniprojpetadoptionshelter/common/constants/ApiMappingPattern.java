package org.example.miniprojpetadoptionshelter.common.constants;

public class ApiMappingPattern {
    private ApiMappingPattern() {}
    public static final String API = "/api";
    public static final String V1 = "/v1";
    public static final String BASE = API + V1;

    public static final class Auth {
        private Auth() {}

        public static final String ROOT = BASE + "/auth";

        public static final String LOGIN = ROOT + "/login";
        public static final String LOGOUT = ROOT + "/logout";
        public static final String REFRESH = ROOT + "/refresh";
        public static final String SIGNUP = ROOT + "/signup";
        public static final String EMAIL_SEND = ROOT + "/email/send";
        public static final String EMAIL_VERIFY = ROOT + "/email/verify";
        public static final String PASSWORD_RESET = ROOT + "/password/reset";
        public static final String PASSWORD_VERIFY = ROOT + "/password/verify";
    }

    public static final class Users {
        private Users() {}

        public static final String ROOT = BASE + "/users";
        public static final String ID_ONLY = "/{userId}";

        public static final String ME = ROOT + "/me";
        public static final String ME_UPDATE = ROOT + "/me";

        public static final String BY_ID = ROOT + ID_ONLY;
        public static final String PASSWORD = ID_ONLY + "/password";
    }

    public static final class Roles {
        private Roles () {}
        public static final String ROOT = BASE + "/roles";

        public static final String ADD_ROLE=  "/users/{userId}/roles";
        public static final String REMOVE_ROLE=  "/users/{userId}/roles/{rolesName}";
    }
}
