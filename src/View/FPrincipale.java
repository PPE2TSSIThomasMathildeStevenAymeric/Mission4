package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Concours;
import Model.ConnexionSQL;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

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
		btnNouveauConcours.setBounds(182, 102, 157, 34);
		contentPane.add(btnNouveauConcours);
		
		JButton btnNouveauJoueur = new JButton("Nouveau joueur");
		btnNouveauJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					FJoueur frameCreationJoueur=new FJoueur();
					frameCreationJoueur.setVisible(true);
				}
		});
		btnNouveauJoueur.setBounds(182, 201, 157, 34);
		contentPane.add(btnNouveauJoueur);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 165, 543, 2);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(-12, 71, 555, 5);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 262, 543, 2);
		contentPane.add(separator_2);
		
		JButton btnVoirConcours = new JButton("Voir Concours");
		btnVoirConcours.setBounds(182, 346, 157, 34);
		contentPane.add(btnVoirConcours);
		
		JComboBox comboBoxListConcours = new JComboBox();
		comboBoxListConcours.setBounds(58, 291, 392, 22);
		contentPane.add(comboBoxListConcours);
		
		// String requete = "SELECT id_concours FROM concours"; 
		String requeteStocke = "CALL getConcours()";
		try {
			ConnexionSQL bdd = new ConnexionSQL();
			ResultSet resultbdd = bdd.requeteRetourneDonnees(requeteStocke);
			
			while(resultbdd.next()) {
				
				int id_concours = resultbdd.getInt(1);
				Date pDate = resultbdd.getDate(2);
				String pNature = resultbdd.getString(3);
				String pCategorie = resultbdd.getString(4);
				String pSexe = resultbdd.getString(5);
				int pTourNum = resultbdd.getInt(6);
				int pTerminer = resultbdd.getInt(7);
				
				Concours leConcours = new Concours(id_concours, "", pDate, pNature, pCategorie, pSexe, pTourNum, pTerminer);
				
				comboBoxListConcours.addItem(leConcours);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		btnVoirConcours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Concours leConcours = (Concours) comboBoxListConcours.getSelectedItem(); 
				
				if (leConcours != null) {
					FGestionConcours frameGestionConcours = null;
					try {
						frameGestionConcours = new FGestionConcours(leConcours);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frameGestionConcours.setVisible(true);
				}
			}
		});
		
		
	}
}
