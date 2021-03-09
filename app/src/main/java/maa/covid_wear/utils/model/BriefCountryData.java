package maa.covid_wear.utils.model;

public class BriefCountryData {
    private float cases;
    private float recovered;
    private float deaths;
    private float active;

    public BriefCountryData(float cases, float recovered, float deaths, float active) {
        this.cases = cases;
        this.recovered = recovered;
        this.deaths = deaths;
        this.active = active;
    }

    public float getCases() {
        return cases;
    }

    public void setCases(float todayCases) {
        this.cases = todayCases;
    }

    public float getRecovered() {
        return recovered;
    }

    public void setRecovered(float recovered) {
        this.recovered = recovered;
    }

    public float getDeaths() {
        return deaths;
    }

    public void setDeaths(float deaths) {
        this.deaths = deaths;
    }

    public float getActive() {
        return active;
    }

    public void setActive(float active) {
        this.active = active;
    }
}