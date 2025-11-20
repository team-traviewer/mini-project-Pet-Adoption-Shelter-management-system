package org.example.miniprojpetadoptionshelter.common.apis;

public class ApiBase {
    private ApiBase () {}

    public static final String API = "/api";
    public static final String V1 = "/v1";
    public static final String BASE = API + V1;
    public static final String FILE = "/files";
    public static final String FILE_BY_ID = FILE + "/{fileId}";
}
