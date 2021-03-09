package maa.covid_wear.utils.interfaces;

import maa.covid_wear.utils.model.BriefCountryData;
import maa.covid_wear.utils.model.BriefData;

public interface CountryCallback {
    void onCountryClicked(BriefCountryData briefCountryData);
}
