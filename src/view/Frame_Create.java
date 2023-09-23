package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;

public class Frame_Create extends Frame_Basic {
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Create frame = new Frame_Create();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    public Frame_Create(){
        
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        

    }
}
