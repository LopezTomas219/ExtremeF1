package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Frame_Select extends Frame_Basic {
    
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame_Select frame = new Frame_Select();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    public Frame_Select(){
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)(screenSize.width * 0.75); 
        int height = (int)(screenSize.height * 0.75);  
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(2, 6));
        JPanel southPanel = new JPanel();
        for (int i = 0; i < 12; i++) {
            
            JPanel mainPanel = new JPanel(new BorderLayout());
            Panel_Select panelSelect = new Panel_Select();
            mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            mainPanel.add(panelSelect,BorderLayout.CENTER);

            JButton btnConfirm = new JButton("Seleccionar");
            btnConfirm.setActionCommand("Botón " + (i));
            btnConfirm.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    String actionCommand = e.getActionCommand();
                    System.out.println("Presionado: " + actionCommand);
                    
                }
            });
            mainPanel.add(btnConfirm, BorderLayout.SOUTH);
            topPanel.add(mainPanel);
        }        
        JButton btnBack = new JButton("Volver");
        btnBack.setPreferredSize(new Dimension(120, 40));
        southPanel.setBackground(Color.DARK_GRAY);
        southPanel.add(btnBack);
        add(topPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }
}
