package maa.covid_wear.utils.model;


import java.io.IOException;

public class Countries {
    private long updated;
    private String country;
    private CountryInfo countryInfo;
    private long cases;
    private long todayCases;
    private long deaths;
    private long todayDeaths;
    private long recovered;
    private long todayRecovered;
    private long active;
    private long critical;
    private long casesPerOneMillion;
    private double deathsPerOneMillion;
    private long tests;
    private long testsPerOneMillion;
    private long population;
    private Continent continent;
    private long oneCasePerPeople;
    private long oneDeathPerPeople;
    private long oneTestPerPeople;
    private double activePerOneMillion;
    private double recoveredPerOneMillion;
    private double criticalPerOneMillion;

    public long getUpdated() {
        return updated;
    }

    public void setUpdated(long value) {
        this.updated = value;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String value) {
        this.country = value;
    }

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }

    public void setCountryInfo(CountryInfo value) {
        this.countryInfo = value;
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long value) {
        this.cases = value;
    }

    public long getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(long value) {
        this.todayCases = value;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setDeaths(long value) {
        this.deaths = value;
    }

    public long getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(long value) {
        this.todayDeaths = value;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long value) {
        this.recovered = value;
    }

    public long getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(long value) {
        this.todayRecovered = value;
    }

    public long getActive() {
        return active;
    }

    public void setActive(long value) {
        this.active = value;
    }

    public long getCritical() {
        return critical;
    }

    public void setCritical(long value) {
        this.critical = value;
    }

    public long getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public void setCasesPerOneMillion(long value) {
        this.casesPerOneMillion = value;
    }

    public double getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public void setDeathsPerOneMillion(double value) {
        this.deathsPerOneMillion = value;
    }

    public long getTests() {
        return tests;
    }

    public void setTests(long value) {
        this.tests = value;
    }

    public long getTestsPerOneMillion() {
        return testsPerOneMillion;
    }

    public void setTestsPerOneMillion(long value) {
        this.testsPerOneMillion = value;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long value) {
        this.population = value;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent value) {
        this.continent = value;
    }

    public long getOneCasePerPeople() {
        return oneCasePerPeople;
    }

    public void setOneCasePerPeople(long value) {
        this.oneCasePerPeople = value;
    }

    public long getOneDeathPerPeople() {
        return oneDeathPerPeople;
    }

    public void setOneDeathPerPeople(long value) {
        this.oneDeathPerPeople = value;
    }

    public long getOneTestPerPeople() {
        return oneTestPerPeople;
    }

    public void setOneTestPerPeople(long value) {
        this.oneTestPerPeople = value;
    }

    public double getActivePerOneMillion() {
        return activePerOneMillion;
    }

    public void setActivePerOneMillion(double value) {
        this.activePerOneMillion = value;
    }

    public double getRecoveredPerOneMillion() {
        return recoveredPerOneMillion;
    }

    public void setRecoveredPerOneMillion(double value) {
        this.recoveredPerOneMillion = value;
    }

    public double getCriticalPerOneMillion() {
        return criticalPerOneMillion;
    }

    public void setCriticalPerOneMillion(double value) {
        this.criticalPerOneMillion = value;
    }
}


enum Continent {
    AFRICA, ASIA, AUSTRALIA_OCEANIA, EMPTY, EUROPE, NORTH_AMERICA, SOUTH_AMERICA;

    public String toValue() {
        switch (this) {
            case AFRICA:
                return "Africa";
            case ASIA:
                return "Asia";
            case AUSTRALIA_OCEANIA:
                return "Australia/Oceania";
            case EMPTY:
                return "";
            case EUROPE:
                return "Europe";
            case NORTH_AMERICA:
                return "North America";
            case SOUTH_AMERICA:
                return "South America";
        }
        return null;
    }

    public static Continent forValue(String value) throws IOException {
        if (value.equals("Africa")) return AFRICA;
        if (value.equals("Asia")) return ASIA;
        if (value.equals("Australia/Oceania")) return AUSTRALIA_OCEANIA;
        if (value.equals("")) return EMPTY;
        if (value.equals("Europe")) return EUROPE;
        if (value.equals("North America")) return NORTH_AMERICA;
        if (value.equals("South America")) return SOUTH_AMERICA;
        throw new IOException("Cannot deserialize Continent");
    }
}

// CountryInfo.java

