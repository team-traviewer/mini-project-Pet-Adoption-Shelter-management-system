package org.example.miniprojpetadoptionshelter.common.apis.dashboard;

import org.example.miniprojpetadoptionshelter.common.apis.ApiBase;

public class DashboardApi {
    private DashboardApi () {}
    public static final String ROOT = ApiBase.BASE + "/shelters";
    public static final String DASHBOARD = ROOT + "/{shelterId}/dashboard";
}
