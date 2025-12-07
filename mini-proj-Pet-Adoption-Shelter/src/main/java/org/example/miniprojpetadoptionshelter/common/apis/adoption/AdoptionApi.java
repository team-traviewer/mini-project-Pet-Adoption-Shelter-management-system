package org.example.miniprojpetadoptionshelter.common.apis.adoption;

import org.example.miniprojpetadoptionshelter.common.apis.ApiBase;

public class AdoptionApi {

    private AdoptionApi() {}

    // 최종 엔드포인트: /api/v1/adoptions
    public static final String ROOT = ApiBase.BASE + "/adoptions";

    // 단건 조회/수정: /api/v1/adoptions/{adoptionId}
    public static final String BY_ID = "/{adoptionId}";

    // UPDATE도 동일 경로 사용
    public static final String UPDATE = "/{adoptionId}";
}