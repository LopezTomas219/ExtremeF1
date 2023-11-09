package view;

import controller.Championship;
import javax.swing.*;
import java.awt.*;

public class Frame_Game extends Frame_Basic {
    
    public Frame_Game(Championship controller){
        super(controller);
        setLayout(new BorderLayout());
        
    }
}
