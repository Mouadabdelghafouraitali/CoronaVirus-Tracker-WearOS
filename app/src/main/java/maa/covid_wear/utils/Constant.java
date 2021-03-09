package maa.covid_wear.utils;

import java.util.ArrayList;

import maa.covid_wear.R;
import maa.covid_wear.utils.model.Items;

public class Constant {

    public static final String BASE_URL = "https://corona.lmao.ninja/v3/covid-19/";

    public static ArrayList<Items> getItems() {
        ArrayList<Items> itemsList = new ArrayList<>();
        itemsList.add(new Items("OVERVIEW", R.drawable.overview));
        itemsList.add(new Items("COUNTRIES", R.drawable.countries));
        itemsList.add(new Items("MAP", R.drawable.map));
        return itemsList;
    }
}