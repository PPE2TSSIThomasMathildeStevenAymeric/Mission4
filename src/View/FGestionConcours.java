package View;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.Concours;
import Model.Equipe;
import Model.Match;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FGestionConcours extends JFrame {
	
	public static DefaultListModel<Match> modeleMatch = new DefaultListModel();
	private JList listMatch = new JList(modeleMatch);

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
	
private class affichageMatch extends DefaultListCellRenderer {
		
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			
			Match pMatch = (Match) value; // On récupère l'objet (un Joueur dans ce cas)
			
			String pgagnant = "Non défini";
			
			
			if (pMatch.getEquipegagnante() != null) {
				pgagnant = pMatch.getEquipegagnante().getNomEquipe();
				
				if (pMatch.getEquipegagnante().equals(pMatch.getEquipePerdante())) {
					pgagnant = pMatch.getEquipegagnante().getNomEquipe() + " (Match blanc)";
				}
			}
			
			String labelText = pMatch.getIdMatch() + " - Tour " + pMatch.getConcoursTourNum() + " - " + pMatch.getEquipe1().getNomEquipe() + " vs " + pMatch.getEquipe2().getNomEquipe() + " - Gagnant : " + pgagnant;
			setText(labelText); // On l'affiche

			list.repaint(); // On rafraichi la liste

			return this;
		}

	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public FGestionConcours(Concours leConcours) throws SQLException {
		
		
		setTitle("Gestion du concours");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 868, 541);		
		getContentPane().setLayout(null);
		
		JLabel lblInformationsPropos = new JLabel("Informations \u00E0 propos du concours");
		lblInformationsPropos.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformationsPropos.setFont(new Font("Verdana", Font.BOLD, 14));
		lblInformationsPropos.setBounds(12, 13, 319, 53);
		getContentPane().add(lblInformationsPropos);
		
		JLabel lblId = new JLabel("Id :");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblId.setBounds(22, 79, 56, 16);
		getContentPane().add(lblId);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDate.setBounds(22, 115, 56, 16);
		getContentPane().add(lblDate);
		
		JLabel lblCatgorie = new JLabel("Cat\u00E9gorie :");
		lblCatgorie.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCatgorie.setBounds(22, 154, 77, 16);
		getContentPane().add(lblCatgorie);
		
		JLabel lblNature = new JLabel("Nature :");
		lblNature.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNature.setBounds(161, 115, 56, 16);
		getContentPane().add(lblNature);
		
		JLabel lblSexe = new JLabel("Sexe :");
		lblSexe.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSexe.setBounds(161, 79, 56, 16);
		getContentPane().add(lblSexe);
		
		JLabel lblIdReponse = new JLabel(String.valueOf(leConcours.getConcNum()));
		lblIdReponse.setBounds(71, 79, 61, 16);
		getContentPane().add(lblIdReponse);
		
		JLabel lblSexeReponse = new JLabel(leConcours.getConcSexe());
		lblSexeReponse.setBounds(222, 79, 109, 16);
		getContentPane().add(lblSexeReponse);
		
		JLabel lblNatureReponse = new JLabel(leConcours.getConcNature());
		lblNatureReponse.setBounds(222, 115, 109, 16);
		getContentPane().add(lblNatureReponse);
		
		JLabel lblDateReponse = new JLabel(leConcours.getConcDate().toString());
		lblDateReponse.setBounds(71, 115, 78, 16);
		getContentPane().add(lblDateReponse);
		
		JLabel lblCategorieReponse = new JLabel(leConcours.getConcCat());
		lblCategorieReponse.setBounds(104, 154, 134, 16);
		getContentPane().add(lblCategorieReponse);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 198, 332, 2);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(330, 0, 2, 494);
		getContentPane().add(separator_1);
		
		JButton btnCrationEquipe = new JButton("Cr\u00E9ation des \u00E9quipes");
		btnCrationEquipe.setEnabled(false);
		btnCrationEquipe.setBounds(81, 255, 167, 25);
		getContentPane().add(btnCrationEquipe);
		
		JLabel lblDbutDuTournoi = new JLabel("D\u00E9but du tournoi");
		lblDbutDuTournoi.setHorizontalAlignment(SwingConstants.CENTER);
		lblDbutDuTournoi.setFont(new Font("Verdana", Font.BOLD, 15));
		lblDbutDuTournoi.setBounds(22, 213, 287, 16);
		getContentPane().add(lblDbutDuTournoi);
		
		JButton btnDbuterLeTournoi = new JButton("D\u00E9buter le tournoi");
		btnDbuterLeTournoi.setEnabled(false);
		btnDbuterLeTournoi.setBounds(81, 302, 167, 25);
		getContentPane().add(btnDbuterLeTournoi);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(-1, 340, 332, 2);
		getContentPane().add(separator_2);
		
		JButton btnFermerLaFenetre = new JButton("Fermer la fenetre");
		btnFermerLaFenetre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFermerLaFenetre.setBounds(686, 13, 152, 25);
		getContentPane().add(btnFermerLaFenetre);
		
		JLabel lblListeDesMatchs = new JLabel("Liste des matchs");
		lblListeDesMatchs.setFont(new Font("Verdana", Font.BOLD, 15));
		lblListeDesMatchs.setBounds(337, 50, 167, 16);
		getContentPane().add(lblListeDesMatchs);
		

		
		
		JLabel lblTourN = new JLabel("Tour n\u00B0");
		lblTourN.setBounds(12, 377, 56, 16);
		getContentPane().add(lblTourN);
		
		JLabel lblNumTour = new JLabel(String.valueOf(leConcours.getConcTourNum()));
		lblNumTour.setBounds(59, 377, 56, 16);
		getContentPane().add(lblNumTour);
		
		JButton btnTourSuivant = new JButton("Tour suivant");
		btnTourSuivant.setEnabled(false);
		btnTourSuivant.setBounds(667, 430, 158, 25);
		getContentPane().add(btnTourSuivant);
		
		JLabel lblEquipeGagnante = new JLabel("Equipe gagnante :");
		lblEquipeGagnante.setBounds(12, 423, 120, 16);
		getContentPane().add(lblEquipeGagnante);
		
		JLabel lblEquipeGagnanteReponse = new JLabel("Concours en cours");
		lblEquipeGagnanteReponse.setBounds(139, 423, 109, 16);
		getContentPane().add(lblEquipeGagnanteReponse);
		
		JLabel lblTournoiTermin = new JLabel("Concours termin\u00E9 :");
		lblTournoiTermin.setBounds(12, 452, 120, 16);
		getContentPane().add(lblTournoiTermin);
		
		JLabel lblTournoiTerminReponse = new JLabel("Faux");
		lblTournoiTerminReponse.setBounds(139, 452, 109, 16);
		getContentPane().add(lblTournoiTerminReponse);
		
		listMatch.setBounds(343, 96, 495, 293);
		getContentPane().add(listMatch);
		listMatch.setCellRenderer(new affichageMatch()); // Permet de modifier l'affichage des JList
		
		ArrayList<Match> lesmatchDebut = Match.getAllMatchOfConcours(leConcours.getConcNum());
		for (Match unMatch : lesmatchDebut) {
			modeleMatch.addElement(unMatch);
		}
		
		JButton btnDfinirGagnant = new JButton("D\u00E9finir gagnant du match");
		btnDfinirGagnant.setEnabled(false);
		btnDfinirGagnant.setBounds(387, 408, 200, 47);
		getContentPane().add(btnDfinirGagnant);
		
		if (leConcours.getConcTourNum() == 0) {
			btnCrationEquipe.setEnabled(true);
			btnDbuterLeTournoi.setEnabled(true);
			btnTourSuivant.setEnabled(false);
		}
		
		btnCrationEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FCreationEquipe frameCreationEquipe;
				
				try {
					frameCreationEquipe = new FCreationEquipe(leConcours);
					frameCreationEquipe.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
			}
		});
		
		btnTourSuivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					leConcours.increaseTourNum();
					
					
					ArrayList<Match> matchDepart = Match.randomizeRencontre(leConcours.equipeEnListe(), leConcours);
					
					System.out.println(matchDepart.size());
					
					for (Match unMatch : matchDepart){
						modeleMatch.addElement(unMatch);
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnDbuterLeTournoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					leConcours.increaseTourNum();
					ArrayList<Match> matchDepart = Match.randomizeRencontre(Equipe.getAllEquipeByConcoursID(leConcours.getConcNum()), leConcours);
					
					for (Match unMatch : matchDepart){
						modeleMatch.addElement(unMatch);
					}
					
					
					btnTourSuivant.setEnabled(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		listMatch.addListSelectionListener(new ListSelectionListener() {

	            public void valueChanged(ListSelectionEvent arg0) {
	                	Match pMatch = (Match) listMatch.getSelectedValue();
	                	
	                	if (pMatch.getEquipe1().getIdEquipe() == pMatch.getEquipe2().getIdEquipe()) {
	                		btnDfinirGagnant.setEnabled(false);
	                	} else {
	                		btnDfinirGagnant.setEnabled(true);
	                	}
	                	
	                	int pSize = modeleMatch.getSize();
	                	boolean tourSuivant = true;
	                	
	                	for (int a = 0; a < pSize; a++) {
	                		Match unMatch = (Match) modeleMatch.get(a);
	                		if (unMatch.getEquipegagnante() == null) {
	                			tourSuivant = false;
	                		}
	                	}
	                	
	                	btnTourSuivant.setEnabled(tourSuivant);
	                	
	                	
	            }


	        });
		
		listMatch.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				int pSize = modeleMatch.getSize();
            	boolean tourSuivant = true;
            	
            	for (int a = 0; a < pSize; a++) {
            		Match unMatch = (Match) modeleMatch.get(a);
            		if (unMatch.getEquipegagnante() == null) {
            			tourSuivant = false;
            		}
            	}
            	
            	btnTourSuivant.setEnabled(tourSuivant);
				
			}
			
		});
		
		btnDfinirGagnant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Match pMatch = (Match) listMatch.getSelectedValue();
				
				FSelectionGagnantMatch frameGestionGagnant = null;
				frameGestionGagnant = new FSelectionGagnantMatch(pMatch);
				frameGestionGagnant.setVisible(true);
				
			}
		});
		
	}
}
