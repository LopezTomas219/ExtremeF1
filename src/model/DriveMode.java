package model;

public enum DriveMode {
    fast(1),          // Máxima velocidad y máxima exigencia del motor, de los neumáticos y del consumo de combustible
    conservative(0.80),     // Velocidad reducida, cuidado del estado del motor, de los neumáticos y del combustible
    moderate(0.90);            // Modo intermedio entre el modo rápido y el conservador

    private double factor;

    // Constructor para establecer el factor de cada modo de conducción
    DriveMode(double factor) {
        this.factor = factor;
    }

    // Método para obtener el factor asociado a cada modo de conducción
    public double getFactor() {
    	return factor;
    }
}