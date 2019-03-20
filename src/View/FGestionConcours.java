package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Concours;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class FGestionConcours extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FGestionConcours frame = new FGestionConcours(null);
					frame.setVisible(true);
					frame.setSize(1000,750);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FGestionConcours(Concours leConcours) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 731, 413);		
		getContentPane().setLayout(null);
		
		if(leConcours.getConcSexe()=="homme")
		{
			
		}
		
	}
}
