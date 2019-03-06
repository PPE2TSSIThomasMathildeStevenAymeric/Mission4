package View;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class FJoueur extends JFrame {

	private JPanel contentPane;

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
		lblSexe.setBounds(103, 129, 56, 16);
		contentPane.add(lblSexe);
		
		JLabel lblCategorie = new JLabel("Categorie :");
		lblCategorie.setBounds(103, 179, 73, 16);
		contentPane.add(lblCategorie);
		
		JLabel lblClub = new JLabel("Club :");
		lblClub.setBounds(103, 231, 56, 16);
		contentPane.add(lblClub);
		
		JLabel lblEquipe = new JLabel("Equipe :");
		lblEquipe.setBounds(103, 278, 56, 16);
		contentPane.add(lblEquipe);
		
		JLabel lblCrationDeJoueur = new JLabel("Cr\u00E9ation de joueur");
		lblCrationDeJoueur.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCrationDeJoueur.setBounds(182, 27, 214, 31);
		contentPane.add(lblCrationDeJoueur);
	}
}
