package maa.covid_wear.utils.interfaces;

import java.util.ArrayList;

import maa.covid_wear.utils.model.AllData;
import maa.covid_wear.utils.model.Countries;
import retrofit2.Call;
import retrofit2.http.GET;


public interface Data {
    @GET("all")
    public Call<AllData> getAllData();

    @GET("countries")
    public Call<ArrayList<Countries>> getAllCountries();
}