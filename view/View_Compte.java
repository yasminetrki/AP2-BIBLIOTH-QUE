package view;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import controller.mainMVC;
import model.ADHERENT;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;

public class View_Compte {

	private JFrame frame;
	private JTextField textField_ad;
	private JButton btnNewButton_OK;
	private JLabel lblNewLabel_1;
	private JTextField textField_nom;
	private JLabel lblNewLabel_info;
	private JLabel lblNewLabel_2;
	private JTextField textField_prenom;
	private JLabel lblNewLabel_3;
	private JTextField textField_email;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton_MAJ;
	private JButton btnNewButton_accueil;



	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public View_Compte() throws ClassNotFoundException, SQLException {
		mainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("N\u00B0 Adh\u00E9rent :");
		lblNewLabel.setBounds(24, 24, 81, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField_ad = new JTextField();
		textField_ad.setBounds(138, 21, 177, 20);
		frame.getContentPane().add(textField_ad);
		textField_ad.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(62, 106, 341, 229);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Nom");
		lblNewLabel_1.setBounds(10, 11, 49, 14);
		panel.add(lblNewLabel_1);
		
		textField_nom = new JTextField();
		textField_nom.setBounds(82, 8, 96, 20);
		panel.add(textField_nom);
		textField_nom.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Pr\u00E9nom :");
		lblNewLabel_2.setBounds(10, 39, 49, 14);
		panel.add(lblNewLabel_2);
		
		textField_prenom = new JTextField();
		textField_prenom.setColumns(10);
		textField_prenom.setBounds(82, 36, 96, 20);
		panel.add(textField_prenom);
		
		lblNewLabel_3 = new JLabel("Email :");
		lblNewLabel_3.setBounds(10, 67, 49, 14);
		panel.add(lblNewLabel_3);
		
		textField_email = new JTextField();
		textField_email.setColumns(10);
		textField_email.setBounds(82, 64, 96, 20);
		panel.add(textField_email);
		
		lblNewLabel_4 = new JLabel("Mes livres en cours d'emprunt :");
		lblNewLabel_4.setBounds(10, 105, 180, 14);
		panel.add(lblNewLabel_4);
		
		List list = new List();
		list.setBounds(35, 128, 247, 56);
		panel.add(list);
		
		btnNewButton_MAJ = new JButton("MAJ");
		btnNewButton_MAJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num = textField_ad.getText();
				String nom = textField_nom.getText();
				String prenom = textField_prenom.getText();
				String email = textField_email.getText();
				try {
					mainMVC.getM().MAJ_ad(nom, prenom, email, num);
					lblNewLabel_info.setText("Info Mis à jour");
					mainMVC.getM().getall();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_MAJ.setBounds(213, 24, 89, 23);
		panel.add(btnNewButton_MAJ);
		
		btnNewButton_OK = new JButton("OK");
		btnNewButton_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ad ;
				ad= textField_ad.getText();
				ADHERENT a;
				a = mainMVC.getM().findadherent(ad);
				if (a==null)
				{
					lblNewLabel_info.setText("ERREUR numéro innexistant !");
				}
				else
				{
					textField_ad.setEnabled(false);
					btnNewButton_OK.setEnabled(false);
					lblNewLabel_info.setText("Info Perso : ");
					panel.setVisible(true);
					textField_nom.setText(a.getNom());
					textField_prenom.setText(a.getPrenom());
					textField_email.setText(a.getEmail());
					for (int i=0;i<a.getListLivre().size();i++)
					{
						list.add(a.getListLivre().get(i).getISBN()+ " - " + a.getListLivre().get(i).getTitre());
					}
				}
				
			}
		});
		btnNewButton_OK.setBounds(162, 52, 89, 23);
		frame.getContentPane().add(btnNewButton_OK);
		
		lblNewLabel_info = new JLabel("");
		lblNewLabel_info.setBounds(138, 86, 193, 14);
		frame.getContentPane().add(lblNewLabel_info);
		
		btnNewButton_accueil = new JButton("RETOUR ACCUEIL");
		btnNewButton_accueil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton_accueil.setBounds(303, 52, 141, 43);
		frame.getContentPane().add(btnNewButton_accueil);
		panel.setVisible(false);
	}
}
