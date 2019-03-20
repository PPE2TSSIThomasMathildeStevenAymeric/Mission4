package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.ConnexionSQL;

public class FPrincipale extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FPrincipale frame = new FPrincipale();
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
	public FPrincipale() {
		setTitle("Accueil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 451);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestionDeConcours = new JLabel("Gestion de concours");
		lblGestionDeConcours.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblGestionDeConcours.setBounds(126, 24, 289, 34);
		contentPane.add(lblGestionDeConcours);
		
		JButton btnNouveauConcours = new JButton("Nouveau concours");
		btnNouveauConcours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FConcours frameConcours=new FConcours();
				frameConcours.setVisible(true);
			}
		});
		btnNouveauConcours.setBounds(168, 107, 157, 34);
		contentPane.add(btnNouveauConcours);
		
		JButton btnNouveauJoueur = new JButton("Nouveau joueur");
		btnNouveauJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnexionSQL conn =new ConnexionSQL();
				}
		});
		btnNouveauJoueur.setBounds(168, 179, 157, 34);
		contentPane.add(btnNouveauJoueur);
		
	}
}
