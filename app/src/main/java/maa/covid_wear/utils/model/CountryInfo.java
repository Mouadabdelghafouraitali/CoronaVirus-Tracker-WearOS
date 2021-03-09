package maa.covid_wear.utils.model;

import com.google.gson.annotations.SerializedName;

public class CountryInfo {
   private Long id;
   private String iso2;
   private String iso3;
   private double lat;
   @SerializedName("long")
   private double countryInfoLong;
   private String flag;


    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Long getID() {
       return id;
   }

   public void setID(Long value) {
       this.id = value;
   }

   public String getIso2() {
       return iso2;
   }

   public void setIso2(String value) {
       this.iso2 = value;
   }

   public String getIso3() {
       return iso3;
   }

   public void setIso3(String value) {
       this.iso3 = value;
   }

   public double getLat() {
       return lat;
   }

   public void setLat(double value) {
       this.lat = value;
   }

   public double getCountryInfoLong() {
       return countryInfoLong;
   }

   public void setCountryInfoLong(double value) {
       this.countryInfoLong = value;
   }
}