package Model;

import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;

public class modeleMatch extends AbstractTableModel {
	private final Match[] lesMatch;

	private final String[] entetes = { "ID_Match", "Id_Equipe1", "Nom_Equipe1", "Id_Equipe2", "Nom_Equipe2", "Gagnant", "Valider" };

	public modeleMatch() {
		super();
		 lesMatch = new Match[0];
	}

	public int getRowCount() {
		return lesMatch.length;
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
			return lesMatch[rowIndex].getIdMatch();
		case 1:
			return lesMatch[rowIndex].getEquipe1().getIdEquipe();
		case 2:
			return lesMatch[rowIndex].getEquipe1().getNomEquipe();
		case 3:
			return lesMatch[rowIndex].getEquipe2().getIdEquipe();
		case 4:
			return lesMatch[rowIndex].getEquipe2().getNomEquipe();
		case 5:
			return null; // Combox
		case 6:
			return null; // button valider
		default:
			return lesMatch[rowIndex]; // Retourne un objet de type "Match"
		}
	}
}