package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.ConnexionSQL;
import Model.Equipe;
import Model.Match;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FSelectionGagnantMatch extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Match pMatch = null;
					FSelectionGagnantMatch frame = new FSelectionGagnantMatch(pMatch);
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
	public FSelectionGagnantMatch(Match unMatch) {
		setTitle("Gestion du gagnant d'un match");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 282);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIdDuMatch = new JLabel("ID du match :");
		lblIdDuMatch.setBounds(38, 47, 109, 16);
		contentPane.add(lblIdDuMatch);
		
		JLabel lblGagnant = new JLabel("Gagnant :");
		lblGagnant.setBounds(38, 95, 81, 16);
		contentPane.add(lblGagnant);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(144, 92, 384, 22);
		contentPane.add(comboBox);
		
		comboBox.addItem(unMatch.getEquipe1());
		comboBox.addItem(unMatch.getEquipe2());
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem() != null) {
					
					int id_match = unMatch.getIdMatch();
					Equipe equipeGagnante = (Equipe)comboBox.getSelectedItem();
					Equipe equipePerdante = null;
					
					if (equipeGagnante.equals(unMatch.getEquipe1())) {
						equipePerdante = unMatch.getEquipe2();
					} else {
						equipePerdante = unMatch.getEquipe1();
					}
					
					String requeteStocke = "CALL setEquipeGagnanteMatch(" + id_match + "," + equipeGagnante.getIdEquipe() + "," + equipePerdante.getIdEquipe() + ")";
					ConnexionSQL bdd = new ConnexionSQL();
					try {
						bdd.requeteSansDonnes(requeteStocke);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					unMatch.setEquipegagnante(equipeGagnante);
					unMatch.setEquipePerdante(equipePerdante);
				
				}
				
					dispose();
				
			}
		});
		btnValider.setBounds(209, 161, 97, 25);
		contentPane.add(btnValider);
		
		JLabel lblNumMatch = new JLabel(String.valueOf(unMatch.getIdMatch()));
		lblNumMatch.setBounds(144, 47, 56, 16);
		contentPane.add(lblNumMatch);
	}
}
