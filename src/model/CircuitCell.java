package model;

public class CircuitCell {
    
    private boolean isAvailable; //Indica si esta ocupada
    private int type; //Tipo de celda , 0 recta , 1 curva , 2 zona de adelantamiento , 3 relleno , 4 llegada
    private int direction; // 1 derecha , 2 abajo , 3 izquierda , 4 arriba  , 0 si no se puede mover
    private Player player;
   
    public CircuitCell(int type , int direction){
        this.type = type;
        this.direction = direction;
        isAvailable = true ;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isOccupied) {
        this.isAvailable = isOccupied;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public boolean isOvertakeZone(){
       
        return type == 2;
    }
    public boolean isStartFinish(){
        return type == 4;
    }
    
    
}
