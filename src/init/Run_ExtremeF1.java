package init;

import controller.Championship;
import controller.FileManager;


public class Run_ExtremeF1 {
    public static void main(String[] args) {
        
        Championship controller = new Championship();
        
        FileManager fileManager = new FileManager();
        
        controller.setFiles(FileManager.getListArchs());
        controller.startGame();
        
    }
}

