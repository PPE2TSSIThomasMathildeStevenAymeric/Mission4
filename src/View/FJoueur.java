package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Club;
import Model.ConnexionSQL;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class FJoueur extends JFrame {

	private JPanel contentPane;
	private JTextField nom_tf;
	private JTextField prenom_tf;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FJoueur frame = new FJoueur();
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
	public FJoueur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 413);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSexe = new JLabel("Sexe :");
		lblSexe.setBounds(12, 129, 56, 16);
		contentPane.add(lblSexe);
		
		JLabel lblCategorie = new JLabel("Categorie :");
		lblCategorie.setBounds(12, 179, 73, 16);
		contentPane.add(lblCategorie);
		
		JLabel lblClub = new JLabel("Club :");
		lblClub.setBounds(12, 231, 56, 16);
		contentPane.add(lblClub);
		
		JLabel lblCrationDeJoueur = new JLabel("Cr\u00E9ation de joueur");
		lblCrationDeJoueur.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCrationDeJoueur.setBounds(182, 27, 214, 31);
		contentPane.add(lblCrationDeJoueur);
		
		JComboBox categorie_cb = new JComboBox();
		categorie_cb.setBounds(92, 176, 116, 22);
		contentPane.add(categorie_cb);
		categorie_cb.addItem("Enfant");
		categorie_cb.addItem("Adulte");
		categorie_cb.addItem("Senior");
		
		
		JComboBox club_cb = new JComboBox();
		club_cb.setBounds(92, 228, 116, 22);
		contentPane.add(club_cb);
		ArrayList<Club> lesClubs = new ArrayList<Club>();
		try {
			lesClubs = Club.getAllClub();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (Club unClub : lesClubs) {
			club_cb.addItem(unClub.getClubNom());
		}
		
		
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setBounds(12, 83, 56, 16);
		contentPane.add(lblNom);
		
		nom_tf = new JTextField();
		nom_tf.setBounds(92, 80, 116, 22);
		contentPane.add(nom_tf);
		nom_tf.setColumns(10);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom:");
		lblPrnom.setBounds(242, 83, 56, 16);
		contentPane.add(lblPrnom);
		
		prenom_tf = new JTextField();
		prenom_tf.setBounds(310, 80, 116, 22);
		contentPane.add(prenom_tf);
		prenom_tf.setColumns(10);
		
		JComboBox sexe_cb = new JComboBox();
		sexe_cb.setBounds(92, 126, 116, 22);
		contentPane.add(sexe_cb);
		sexe_cb.addItem("Feminin");
		sexe_cb.addItem("Masculin");
		
		JButton btnCrer = new JButton("Cr\u00E9er");
		btnCrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int categorie = categorie_cb.getSelectedIndex()+1;
				int idClub = 0;
				String nom = nom_tf.getText();
				String prenom = prenom_tf.getText();
				System.out.println(nom);
				try {
					idClub = Club.getClubById(club_cb.getSelectedIndex()+1).getClubNum();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				String requeteInsert = "INSERT INTO `licencie`(`nomlicencie`,`prenomlicencie`,`sexelicencie`,`categorielicencie`,`id_ClubLicencie`) VALUE ('"+nom+"','"+prenom+"','"+sexe_cb.getSelectedItem().toString()+"',"+categorie+","+idClub+")";
				ConnexionSQL bdd = new ConnexionSQL();
				
				try {
					bdd.requeteSansDonnes(requeteInsert);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnCrer.setBounds(212, 311, 97, 25);
		contentPane.add(btnCrer);
		
		
	}
}
