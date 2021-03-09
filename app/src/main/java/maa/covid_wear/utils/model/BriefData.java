package maa.covid_wear.utils.model;

public class BriefData {
    private String title;
    private float number;
    private String color;

    public BriefData(String title, float number, String color) {
        this.title = title;
        this.number = number;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}