package Model;

import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class modeleJoueur extends AbstractTableModel {
	private final Joueur[] lesJoueurs;

	private final String[] entetes = { "ID", "Prénom", "Nom", "Club", "Sexe", "Catagorie" };

	public modeleJoueur() throws SQLException {
		super();
		lesJoueurs = Joueur.getAllJoueur();
	}

	public int getRowCount() {
		return lesJoueurs.length;
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return lesJoueurs[rowIndex].getJNum();
		case 1:
			return lesJoueurs[rowIndex].getPrenom();
		case 2:
			return lesJoueurs[rowIndex].getNom();
		case 3:
			return lesJoueurs[rowIndex].getNomClub();
		case 4:
			return lesJoueurs[rowIndex].getSexe();
		case 5:
			return lesJoueurs[rowIndex].getCat();
		default:
			return lesJoueurs[rowIndex]; // Retourne un objet de type "Joueur"
		}
	}
}