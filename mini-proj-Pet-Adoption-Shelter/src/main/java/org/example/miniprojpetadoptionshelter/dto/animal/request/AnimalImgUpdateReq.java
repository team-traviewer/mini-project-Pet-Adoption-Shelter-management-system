package org.example.miniprojpetadoptionshelter.dto.animal.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record AnimalImgUpdateReq(
        List<Long> keepFileIds,
        List<MultipartFile> newFiles
) {
}
