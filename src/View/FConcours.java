package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

public class FConcours extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FConcours frame = new FConcours();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FConcours() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 573, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNouveauConcours = new JLabel("Nouveau concours");
		lblNouveauConcours.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNouveauConcours.setBounds(183, 43, 213, 40);
		contentPane.add(lblNouveauConcours);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(37, 134, 56, 16);
		contentPane.add(lblNom);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(37, 172, 56, 16);
		contentPane.add(lblDate);
	}

}
