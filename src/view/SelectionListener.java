package view;

import model.Car;
import model.Pilot;

public interface SelectionListener {
    void onCarSelected(Car car);
    void onPilotSelected(Pilot pilot);
}

