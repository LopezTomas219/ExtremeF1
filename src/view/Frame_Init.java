package view;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.Championship;


public class Frame_Init extends Frame_Basic{
    


    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Init frame = new Frame_Init(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    private Panel_Init panel_Init = new Panel_Init();
    private Panel_Charge panel_Charge = new Panel_Charge();
    private CardLayout cardLayout = new CardLayout();

    public Frame_Init(Championship controller){

        super(controller);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screenSize.width * 0.40); 
        int height = (int)(screenSize.height * 0.75);  
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(cardLayout); 
       
        add(panel_Init, "Panel_Init");
        add(panel_Charge, "Panel_Charge");
        panel_Init.getBtnStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    
                    controller.CreateSelect();
            }
        });
        panel_Init.getBtnCharge().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToPanel("Panel_Charge");
            }
        });
        panel_Charge.getBtnBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                switchToPanel("Panel_Init");
            }
        });
    }

    public void switchToPanel(String panel){
        cardLayout.show(getContentPane(), panel);
    }
    
}
