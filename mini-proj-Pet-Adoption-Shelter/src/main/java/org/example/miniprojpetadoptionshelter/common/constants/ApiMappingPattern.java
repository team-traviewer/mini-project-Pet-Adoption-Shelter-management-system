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

        public static final String ADD_ROLE=  Users.BY_ID + "/roles";
        public static final String REMOVE_ROLE=  Users.BY_ID + "/roles/{rolesName}";
    }

    public static final class Shelters {
        private Shelters () {}

        public static final String ROOT = BASE + "/shelters";
        public static final String ID_ONLY = "/{shelterId}";
        public static final String BY_ID = ROOT + ID_ONLY;
    }

    public static final class Animals {
        private Animals () {}

        public static final String ROOT = BASE + "/animals";
        public static final String ID_ONLY = "/{animalId}";
        public static final String BY_ID = ROOT + ID_ONLY;
        public static final String HISTORY = BY_ID + "/history";
    }

    public static final class Intake {
        private Intake() {}

        public static final String ROOT = BASE + "/intakes";
        public static final String ID_ONLY = "/{intakeId}";
        public static final String BY_ID = ROOT + ID_ONLY;

        public static final String INTAKE_ANIMAL = BASE + "/animals/{animalId}/intakes";

        public static final String DETAIL = BY_ID + "/detail";
        public static final String UPDATE = BY_ID + "/update";
    }

    public static final class Foster {
        private Foster() {}

        public static final String ROOT = BASE + "/foster";
        public static final String ID_ONLY = "/{fosterId}";
        public static final String BY_ID = ROOT + ID_ONLY;

        public static final String DETAIL = BY_ID + "/detail";
        public static final String CLOSE = BY_ID + "/close";
        public static final String CANCEL = BY_ID + "/cancel";
    }

    public static final class Medical {
        private Medical() {}

        public static final String ROOT = BASE + "/medical";
        public static final String ID_ONLY = "/{medicalId}";
        public static final String BY_ID = ROOT + ID_ONLY;

        public static final String MEDICAL_ANIMAL = BASE + "/animals/{animalId}/medical";

        public static final String DETAIL = BY_ID + "/detail";
        public static final String UPDATE = BY_ID + "/update";
    }

    public static final class Applications {
        private Applications () {}

        public static final String ROOT = BASE + "/applications";
        public static final String ID_ONLY = "/{applicationsId}";
        public static final String BY_ID = ROOT + ID_ONLY;
        public static final String REVIEW = BY_ID + "/review";
        public static final String APPROVE = BY_ID + "/approve";
        public static final String REJECT = BY_ID + "/reject";
        public static final String CANCEL = BY_ID + "/cancel";
    }

    public static final class Adoptions {
        private Adoptions () {}

        public static final String ROOT = BASE + "/adoptions";
        public static final String ID_ONLY = "/{adoptionId}";
        public static final String BY_ID = ROOT + ID_ONLY;
    }

    public static final class Dashboard {
        private Dashboard() {}
        private static final String ROOT = BASE + "/shelters/{shelterId}/dashboard";
    }

    public static class Reports {
        private Reports() {}

        public static final String ROOT = BASE + "/reports";

        public static final String ANIMALS = ROOT + "/animals";
        public static final String APPLICATIONS = ROOT + "/applications";
        public static final String ADOPTIONS = ROOT + "/adoptions";
        }
}
