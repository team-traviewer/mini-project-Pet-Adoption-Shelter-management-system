package org.example.miniprojpetadoptionshelter.common.apis.file;

public class FileApi {
    private FileApi () {}

    public static final String FILE = "/files";
    public static final String FILE_BY_ID = FILE + "/{fileId}";
    public static final String DELETE = FILE_BY_ID;
    public static final String DOWNLOAD = FILE_BY_ID + "/download";
}
