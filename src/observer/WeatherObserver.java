package observer;

import model.Weathercondition;

public interface WeatherObserver {
    void updateWeather(Weathercondition newCondition);
}
