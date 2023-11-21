package observer;

public interface CarObservable {
    
    void addFuelObserver(FuelObserver fuelObserver);
    void removeFuelObserver(FuelObserver fuelObserver);
    void notifyLowFuel();

    void addTiresObserver(TiresObserver observer);
    void removeTiresObserver(TiresObserver observer);
    void notifyWearTires();

    void addEngineObserver(EngineObserver observer);
    void removeEngineObserver(EngineObserver observer);
    void notifyWearEngine();
}
