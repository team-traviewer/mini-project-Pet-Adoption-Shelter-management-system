package org.example.miniprojpetadoptionshelter.common.apis;

public class IntakeApi {
    private IntakeApi () {}

    public static final String ROOT = ApiBase.BASE + "/intakes";

    public static final String ID_ONLY = "/{intakeId}";
    public static final String BY_ID = ROOT + ID_ONLY;
    public static final String INTAKEANIMAL = AnimalApi.BY_ID + "/intakes";
}
