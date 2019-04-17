package Model;

import java.awt.Component;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(UIManager.getColor("Button.background"));
        }
        setText((value == null) ? "" : value.toString());
        return this;
    }
}


public class modeleMatch extends AbstractTableModel {
	private static Match[] lesMatch;

	private final String[] entetes = { "ID_Match", "Id_Equipe1", "Nom_Equipe1", "Id_Equipe2", "Nom_Equipe2", "Gagnant", "Valider" };

	public modeleMatch(int pNum) throws SQLException {
		super();
		 lesMatch = Match.getAllMatchOfConcours(pNum);
		 System.out.println("coucou" + lesMatch.length);
	}
	
	public static void addMatch(ArrayList<Match> plesMatchs) {
		int sizeMatch = plesMatchs.size();
		int sizeMatchBase = lesMatch.length;
		
		int newSize = sizeMatchBase + sizeMatch - 1;
		
		Match[] matchsTmp = new Match[newSize];
		
		for (int i = 0; i < sizeMatchBase - 1; i++) {
			matchsTmp[i] = lesMatch[i];
		}
		int a = 0;
		for (int x = sizeMatchBase; x < newSize - 1; x++) {
			matchsTmp[x] = plesMatchs.get(a);
			a++;
		}
			
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
			JComboBox comboBox = new JComboBox();
			comboBox.addItem(lesMatch[rowIndex].getEquipe1().getNomEquipe());
			comboBox.addItem(lesMatch[rowIndex].getEquipe2().getNomEquipe());

			return new DefaultCellEditor(new JComboBox()); // Combox
		case 6:
			return new ButtonRenderer(); // button valider
		default:
			return lesMatch[rowIndex]; // Retourne un objet de type "Match"
		}
	}
}