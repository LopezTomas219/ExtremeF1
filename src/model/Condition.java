package model;

enum Condition {
    SUNNY("Sunny"),
    CLOUDY("Cloudy"),
    RAINY("Rainy"),
    FOG("Fog");

    private String conditionName;

    Condition(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getConditionName() {
        return conditionName;
    }

    public static Condition fromWeatherCondition(Weathercondition weathercondition) {
    	if (weathercondition.getTemp() >= 30) {
    		return SUNNY;
    	} else if (weathercondition.getTemp() >= 20) {
    		return CLOUDY;
    	} else if (weathercondition.getTemp() >= 10) {
    		return RAINY;
    	} else {
    		return FOG;
    	}
    }
}