package model;

enum DriveMode {
    fast(1.10),          // Máxima velocidad y máxima exigencia del motor, de los neumáticos y del consumo de combustible
    conservative(0.90),     // Velocidad reducida, cuidado del estado del motor, de los neumáticos y del combustible
    medium(1.00);            // Modo intermedio entre el modo rápido y el conservador

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