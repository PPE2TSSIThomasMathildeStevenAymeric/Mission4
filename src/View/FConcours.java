package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Model.Concours;
import Model.ConnexionSQL;

import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class FConcours extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 0, 573, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNouveauConcours = new JLabel("Nouveau concours");
		lblNouveauConcours.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNouveauConcours.setBounds(183, 43, 213, 40);
		contentPane.add(lblNouveauConcours);
		
		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(110, 134, 56, 16);
		contentPane.add(lblNom);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setBounds(110, 178, 56, 16);
		contentPane.add(lblDate);
		
		txtNom = new JTextField();
		txtNom.setBounds(182, 131, 116, 22);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		JDateChooser calendrier = new JDateChooser();
		calendrier.setBounds(183, 172, 116, 22);
		contentPane.add(calendrier);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		JButton btnValider = new JButton("Valider");
		btnValider.setBounds(381, 345, 97, 25);
		contentPane.add(btnValider);
		
		JLabel lblNature = new JLabel("Nature :");
		lblNature.setBounds(101, 217, 81, 16);
		contentPane.add(lblNature);
		
		JComboBox combo_box_nature = new JComboBox();
		combo_box_nature.setBounds(183, 214, 116, 22);
		contentPane.add(combo_box_nature);
		combo_box_nature.addItem("Individuelle");
		combo_box_nature.addItem("Doublette");
		combo_box_nature.addItem("Triplette");
		combo_box_nature.addItem("Quadrette");
		
		JComboBox combo_box_categorie = new JComboBox();
		combo_box_categorie.setBounds(183, 249, 115, 22);
		contentPane.add(combo_box_categorie);
		combo_box_categorie.addItem("Enfant");
		combo_box_categorie.addItem("Adulte");
		combo_box_categorie.addItem("Senior");
		
		JLabel lblCatgorie = new JLabel("Cat\u00E9gorie :");
		lblCatgorie.setBounds(99, 255, 67, 16);
		contentPane.add(lblCatgorie);
		
		JLabel lblSexe = new JLabel("Sexe :");
		lblSexe.setBounds(110, 290, 67, 16);
		contentPane.add(lblSexe);
		
		JComboBox combo_box_sexe = new JComboBox();
		combo_box_sexe.setBounds(183, 287, 115, 22);
		contentPane.add(combo_box_sexe);
		
		combo_box_sexe.addItem("Homme");
		combo_box_sexe.addItem("Femme");
		combo_box_sexe.addItem("Mixte");
		
		
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//System.out.println(txtNom.getText());
				String date=dateFormat.format(calendrier.getDate());
				//dispose();			
				
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); // your template here
				java.util.Date dateStr = null;
				try {
					dateStr = formatter.parse(date);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				java.sql.Date dateDB = new java.sql.Date(dateStr.getTime());
				
				String nom=txtNom.getText();
				String pnature = combo_box_nature.getSelectedItem().toString();	
				int categorie=combo_box_categorie.getSelectedIndex() + 1;
				String sexe=combo_box_sexe.getSelectedItem().toString();
				
				try {
					// Se connecter à la bdd
					// Faire un INSERT INTO avec les parametres
					
					//	String requete = "INSERT INTO Concours (date_concours, Categorie, nature, sexe) VALUES ('" + dateDB + "','"
					//		+ categorie + "','" + pnature + "','" + sexe + "')";
					
					String requeteStocke = "CALL ajouterConcours('" + dateDB + "','" + categorie + "','" + pnature + "','" + sexe + "')";
					ConnexionSQL bdd = new ConnexionSQL();

					bdd.requeteSansDonnes(requeteStocke);
					
					// On va rechercher l'id dans la bdd
					// String requete2 = "SELECT id_concours FROM Concours ORDER BY id_concours DESC LIMIT 1 ";
					String requete2Stocke = "CALL getLastConcoursID()";
					ResultSet pId = bdd.requeteRetourneDonnees(requete2Stocke);
					pId.next();
					
					int ID = pId.getInt(1);
					bdd.fermerConnexion();
					Concours concours = new Concours(ID, nom,dateDB, pnature, categorie, sexe);
					FGestionConcours frameGestionConcours=new FGestionConcours(concours);
					frameGestionConcours.setVisible(true);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				
				
				
				
			}
		});
	}
}
