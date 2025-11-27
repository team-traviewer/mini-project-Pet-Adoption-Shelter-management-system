package org.example.miniprojpetadoptionshelter.dto.shelter.response;

import org.example.miniprojpetadoptionshelter.dto.file.FileInfoRes;

public record ShelterListRes(
        String name,
        String address,
        String phone,
        FileInfoRes thumbnail
) {
}
