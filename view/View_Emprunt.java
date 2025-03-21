package view;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import controller.mainMVC;
import model.ADHERENT;
import model.LIVRE;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View_Emprunt {

	private JFrame frame;
	private JTextField textField_ad;
	private JTextField textField_ISBN;


	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public View_Emprunt() throws ClassNotFoundException, SQLException {
		mainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("n\u00B0 Adh\u00E9rent :");
		lblNewLabel.setBounds(49, 47, 99, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField_ad = new JTextField();
		textField_ad.setBounds(164, 44, 96, 20);
		frame.getContentPane().add(textField_ad);
		textField_ad.setColumns(10);
		
		JButton btnNewButton_OK = new JButton("OK");

		btnNewButton_OK.setBounds(278, 43, 89, 23);
		frame.getContentPane().add(btnNewButton_OK);
		
		JPanel panel = new JPanel();
		panel.setBounds(60, 103, 336, 137);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ISBN :");
		lblNewLabel_1.setBounds(35, 28, 49, 14);
		panel.add(lblNewLabel_1);
		
		textField_ISBN = new JTextField();
		textField_ISBN.setBounds(166, 25, 96, 20);
		panel.add(textField_ISBN);
		textField_ISBN.setColumns(10);
		
		JButton btnNewButton_emprunt = new JButton("EMPRUNTER");

		btnNewButton_emprunt.setBounds(108, 72, 138, 39);
		panel.add(btnNewButton_emprunt);
		
		JLabel lblNewLabel_message = new JLabel("Veuillez entrer votre num\u00E9ro d'adh\u00E9rent");
		lblNewLabel_message.setBounds(82, 75, 285, 14);
		frame.getContentPane().add(lblNewLabel_message);
		
		JButton btnNewButton = new JButton("Retour Accueil");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnNewButton.setBounds(0, 0, 168, 23);
		frame.getContentPane().add(btnNewButton);
		panel.setVisible(false);
		btnNewButton_OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numAd = textField_ad.getText();
				ADHERENT ad;
				ad=mainMVC.getM().findadherent(numAd);
				if (ad==null)
				{
					lblNewLabel_message.setText("Erreur Adherent inconnu");
				}
				else
				{
					panel.setVisible(true);
					textField_ad.setEnabled(false);
					lblNewLabel_message.setText("Veuillez entrer l'ISBN du livre à emprunter");
					
				}
			}
		});
		
		btnNewButton_emprunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ISBN = textField_ISBN.getText();
			
				LIVRE l;
				l=mainMVC.getM().findlivre(ISBN);
				if(l==null)
				{
					lblNewLabel_message.setText("Erreur ISBN inconnu");
				}
				else
				{
					if(l.getEmprunteur()!=null)
					{
						lblNewLabel_message.setText("Erreur Livre non disponbile");
					}
					else
					{
						try {
							mainMVC.getM().emprunterlivre(textField_ad.getText(), ISBN);
							lblNewLabel_message.setText("Livre emprunté ! vous pouvez saisir un autre ISBN");
							mainMVC.getM().getall();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});
	}
}
