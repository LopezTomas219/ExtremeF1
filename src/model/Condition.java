package model;

public enum Condition {
    SUNNY("Sunny", 30),
    CLOUDY("Cloudy", 20),
    RAINY("Rainy", 10),
    FOG("Fog", 0);

    private String conditionName;
    private int temperatureRange;

    Condition(String conditionName, int temperatureRange) {
        this.conditionName = conditionName;
        this.temperatureRange = temperatureRange;
    }

    public String getConditionName() {
        return conditionName;
    }

    public int getTemperatureRange() {
        return temperatureRange;
    }

   
}
