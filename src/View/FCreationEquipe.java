package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Model.Concours;
import Model.Equipe;
import Model.Joueur;
import Model.modeleJoueur;

import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import java.awt.ScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FCreationEquipe extends JFrame {
	
	// Il reste à faire les foncions pour retirer une équipe et retire un joueur d'une équipe

	private JPanel contentPane;

	private modeleJoueur modeleJoueurs = new modeleJoueur();
	private JTable tableJoueurs = new JTable(modeleJoueurs);

	public static DefaultListModel modeleEquipe = new DefaultListModel();
	private JList listEquipes = new JList(modeleEquipe);

	private JTextField txtNomEquipe;

	private DefaultListModel<Joueur> listModelJoueursEquipe = new DefaultListModel<Joueur>();
	private JList<Joueur> listJoueursEquipe;

	private String pClub = "";
	private String pSexe = "";
	private String pCategorie = "";

	private String filtreClub = "";
	private String filtreSexe = "";
	private String filtreCategorie = "";

	private List<RowFilter<Object, Object>> rfs = new ArrayList<RowFilter<Object, Object>>(3);
	private TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableJoueurs.getModel());

	/**
	 * Launch the application.
	 */
	 
	 // Cette frame permet de créer des équipes pour un concours donné.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Pour créer des équipes, il faut forcément un concours.
					// Pour tester la frame, on charge les infos d'un concours de la base de donnée à partir d'un id.
					Concours unConcoursTest = Concours.getConcoursDB(18);
					FCreationEquipe frame = new FCreationEquipe(unConcoursTest);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 
	 // Les DefaultListCellRenderer permettent de modifier l'affichage des JList
	 
	private class affichageJoueur extends DefaultListCellRenderer {
		
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			
			
			Joueur pJoueur = (Joueur) value; // On récupère l'objet (un Joueur dans ce cas)

			String labelText = pJoueur.getNom() + " " + pJoueur.getPrenom(); // On récupère son Nom, son prénom ...
			setText(labelText); // On l'affiche

			list.repaint(); // On rafraichi la liste

			return this;
		}

	}
	
	// Même système qu'au dessus
	private class affichageEquipe extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

			Equipe pEquipe = (Equipe) value;

			String labelText = pEquipe.getNomEquipe() + " - " + pEquipe.getIdEquipe();
			setText(labelText);

			list.repaint();

			return this;
		}

	}
	
	// Dans cette frame, on a un tableau qui permet de faire des tris selon les champs
	// Dans l'application, on a 3 critères 
	// La catégorie du concours (Mixte, Adulte, Sénior, Enfant), le sexe du concours (Homme, Femme, Mixte) et le club
	// Quand on crée une équipe, il faut que les joueurs de l'équipe soit du même club
	
	private void actualiseTableau(Concours leConcours) { // Système de filtre du tableau
		rfs.clear();
		 
		if (leConcours.getConcSexe().equals("Homme")) {
			pSexe = "Masculin";
		} else if (leConcours.getConcSexe().equals("Femme")) {
			pSexe = "Feminin";
		} else {
			pSexe = "";
		}

		if (leConcours.getConcCat().equals("Mixte")) {
			pCategorie = "";
		} else {
			pCategorie = leConcours.getConcCat();
		}

		if (pClub != "") {
			filtreClub = "(?i)^" + pClub + "$";
		} else {
			filtreClub = "";
		}
		
		if (pSexe != "") {
			filtreSexe = "(?i)^" + pSexe + "$";
		}
		if (pCategorie != "") {
			filtreCategorie = "(?i)^" + pCategorie + "$";
		}

		rfs.add(RowFilter.regexFilter(filtreClub, 3)); // Club
		rfs.add(RowFilter.regexFilter(filtreSexe, 4)); // Sexe
		rfs.add(RowFilter.regexFilter(filtreCategorie, 5)); // Categorie
		RowFilter<Object, Object> af = RowFilter.andFilter(rfs);
		sorter.setRowFilter(af);
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public FCreationEquipe(Concours leConcours) throws SQLException {
		setTitle("Gestion des \u00E9quipes du concours");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1037, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listEquipes.setBounds(8, 57, 182, 366);
		contentPane.add(listEquipes);
		listEquipes.setCellRenderer(new affichageEquipe()); // Permet de modifier l'affichage des JList

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(198, 0, 2, 458);
		contentPane.add(separator);

		JLabel lblNom = new JLabel("Nom :");
		lblNom.setBounds(223, 58, 56, 16);
		contentPane.add(lblNom);

		tableJoueurs.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableJoueurs.setBounds(463, 47, 490, 338);
		tableJoueurs.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		JScrollPane scrollPane = new JScrollPane(tableJoueurs);
		scrollPane.setBounds(504, 47, 503, 338);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		// Filtre
		tableJoueurs.setRowSorter(sorter);
	

		actualiseTableau(leConcours);

		txtNomEquipe = new JTextField();
		txtNomEquipe.setBounds(278, 55, 182, 22);
		contentPane.add(txtNomEquipe);
		txtNomEquipe.setColumns(10);

		JLabel lblListeDesJoueurs = new JLabel("Liste des joueurs :");
		lblListeDesJoueurs.setBounds(223, 106, 171, 16);
		contentPane.add(lblListeDesJoueurs);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(481, 0, 2, 458);
		contentPane.add(separator_1);

		JLabel lblCrationDquipe = new JLabel("Cr\u00E9ation d'\u00E9quipe");
		lblCrationDquipe
				.setFont(lblCrationDquipe.getFont().deriveFont(lblCrationDquipe.getFont().getStyle() | Font.BOLD));
		lblCrationDquipe.setBounds(278, 14, 142, 16);
		contentPane.add(lblCrationDquipe);

		JLabel lblListeDesquipes = new JLabel("Liste des \u00E9quipes");
		lblListeDesquipes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListeDesquipes
				.setFont(lblListeDesquipes.getFont().deriveFont(lblListeDesquipes.getFont().getStyle() | Font.BOLD));
		lblListeDesquipes.setBounds(8, 14, 182, 16);
		contentPane.add(lblListeDesquipes);

		JLabel lblListeDesJoueurs_1 = new JLabel("Liste des joueurs");
		lblListeDesJoueurs_1.setFont(
				lblListeDesJoueurs_1.getFont().deriveFont(lblListeDesJoueurs_1.getFont().getStyle() | Font.BOLD));
		lblListeDesJoueurs_1.setBounds(637, 14, 142, 16);
		contentPane.add(lblListeDesJoueurs_1);

		listJoueursEquipe = new JList<Joueur>(listModelJoueursEquipe);
		listJoueursEquipe.setBounds(223, 135, 237, 222);
		listJoueursEquipe.setCellRenderer(new affichageJoueur());
		contentPane.add(listJoueursEquipe);

		JButton btnCreerLequipe = new JButton("Cr\u00E9er l'\u00E9quipe");
		btnCreerLequipe.setEnabled(false);
		btnCreerLequipe.setBounds(267, 433, 142, 25);
		contentPane.add(btnCreerLequipe);

		JButton btnAjouterLeJoueur = new JButton("Ajouter le joueur");
		btnAjouterLeJoueur.setBounds(710, 398, 142, 25);
		contentPane.add(btnAjouterLeJoueur);

		JButton btnRetirerLeJoueur = new JButton("Retirer le joueur");
		btnRetirerLeJoueur.setBounds(267, 370, 142, 25);
		contentPane.add(btnRetirerLeJoueur);

		JButton btnRetirerLquipe = new JButton("Retirer l'\u00E9quipe");
		btnRetirerLquipe.setBounds(36, 433, 119, 25);
		contentPane.add(btnRetirerLquipe);
		
		for(Equipe loadEquipe : Equipe.getAllEquipeByConcoursID(leConcours.getConcNum())) {
			modeleEquipe.addElement(loadEquipe);
		}

		btnAjouterLeJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int nbJoueurParEquipe = 0;
				
				// On vérifie combien de Joueur il y a par équipe

				if (leConcours.getConcNature().equals("Individuelle")) {
					nbJoueurParEquipe = 1;
				} else if (leConcours.getConcNature().equals("Doublette")) {
					nbJoueurParEquipe = 2;
				} else if (leConcours.getConcNature().equals("Triplette")) {
					nbJoueurParEquipe = 3;
				} else if (leConcours.getConcNature().equals("Quadrette")) {
					nbJoueurParEquipe = 4;
				}
				
				// Si il y a moins de joueurs que le nombre de joueurs max, on poursuit
				if (listModelJoueursEquipe.size() < nbJoueurParEquipe) {
					int row = tableJoueurs.getSelectedRow();
					int pID = (int) tableJoueurs.getValueAt(row, 0);
					
					// On récupère l'id du joueur selectionné dans le tableau

					Joueur unJoueur;
					try {
						unJoueur = Joueur.getJoueurByID(pID); // Puis on récupère ses infos dans la base de donnée
						// On aurait aussi pu le faire directement avec les donnés du tableau, mais flemme de faire 5 lignes
						
						listModelJoueursEquipe.addElement(unJoueur); 
						if (listModelJoueursEquipe.size() == nbJoueurParEquipe) {
							btnAjouterLeJoueur.setEnabled(false);
							btnCreerLequipe.setEnabled(true);
							// Si nbJoueurs = nbJoueursMax, on grise le bouton qui permet d'ajouter des joueurs
						}

						pClub = unJoueur.getNomClub(); // On rajoute un filtre sur le club
						actualiseTableau(leConcours); // On actualise le tableau

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		btnCreerLequipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String equipeNom = txtNomEquipe.getText();

				ArrayList<Joueur> lesJoueurs = new ArrayList<Joueur>();
				for (int a = 0; a < listModelJoueursEquipe.getSize(); a++) {
					lesJoueurs.add(listModelJoueursEquipe.get(a));
				}

				Equipe pEquipe = new Equipe(equipeNom, leConcours.getConcNum(), lesJoueurs);
				try {
					pEquipe.addToDB();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				modeleEquipe.addElement(pEquipe);
				
				listModelJoueursEquipe.clear(); // On vide la JList Joueurs
				
				txtNomEquipe.setText("");
				
				pClub = ""; // On enleve le filtre du club puis on actualise le tableau
				actualiseTableau(leConcours);
				btnAjouterLeJoueur.setEnabled(true); // On réactive le bouton "Ajouter le joueur"
				btnCreerLequipe.setEnabled(false);
			}
		});
		
		btnRetirerLquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipe pEquipe = (Equipe) listEquipes.getSelectedValue();
				try {
					pEquipe.removeFromDB();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				modeleEquipe.removeElement(pEquipe);
				
				
			}
		});
		
		btnRetirerLeJoueur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Joueur pJoueur = listJoueursEquipe.getSelectedValue();
				listModelJoueursEquipe.removeElement(pJoueur);
			
				
				if (listModelJoueursEquipe.size() == 0) {
					pClub = ""; // On rajoute un filtre sur le club
					actualiseTableau(leConcours); // On actualise le tableau
				}
			}
		});

	}
}
