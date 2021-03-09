package maa.covid_wear.utils.model;

import java.util.ArrayList;

public class BriefCountriesData {
    private String name;
    private String flag;

    public BriefCountriesData(String name, String flag) {
        this.name = name;
        this.flag = flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}