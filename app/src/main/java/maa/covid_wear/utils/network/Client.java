package maa.covid_wear.utils.network;

import java.lang.reflect.Array;
import java.util.ArrayList;

import maa.covid_wear.utils.Constant;
import maa.covid_wear.utils.interfaces.Data;
import maa.covid_wear.utils.model.AllData;
import maa.covid_wear.utils.model.Countries;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

    private Data mData;
    private static Client mClient;

    private Client() {
        Retrofit mRetrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        mData = mRetrofit.create(Data.class);
    }

    public static Client getClient() {
        if (mClient == null) mClient = new Client();
        return mClient;
    }

    public Call<AllData> getAllData() {
        return mData.getAllData();
    }

    public Call<ArrayList<Countries>> getAllCountries() {
        return mData.getAllCountries();
    }
}