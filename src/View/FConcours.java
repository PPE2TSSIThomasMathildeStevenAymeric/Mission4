package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;

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
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(txtNom.getText());
				System.out.println(dateFormat.format(calendrier.getDate()));
				dispose();
			}
		});
		btnValider.setBounds(365, 288, 97, 25);
		contentPane.add(btnValider);
		
		JLabel lblCatgorie = new JLabel("Cat\u00E9gorie :");
		lblCatgorie.setBounds(101, 217, 81, 16);
		contentPane.add(lblCatgorie);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(183, 214, 116, 22);
		contentPane.add(comboBox);
		comboBox.addItem("individuelle");
		comboBox.addItem("doublette");
		comboBox.addItem("triplette");
		comboBox.addItem("quadrette");
	}
}
