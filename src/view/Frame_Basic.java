package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.Championship;

public class Frame_Basic extends JFrame{
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Basic frame = new Frame_Basic();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private Championship controller;

    public Frame_Basic(){
        setTitle("Extreme F1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/images/Icono.png"));
        setIconImage(icon.getImage());
		
		
    }
	public void setController(Championship controller){
		this.controller= controller;
	}
}
