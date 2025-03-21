package view;

import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;

import controller.mainMVC;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class View_Accueil {

	private JFrame frame;



	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public View_Accueil() throws ClassNotFoundException, SQLException {
		mainMVC.getM().getall();
		initialize();
		frame.setVisible(true);
		for (int i=0 ; i!=mainMVC.getM().getListLivre().size();i++)
		{
			System.out.println(mainMVC.getM().getListLivre().get(i).getTitre());
		}

		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\benja\\Downloads\\library_image_436x263.jpg"));
		frame.setBounds(100, 100, 609, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de livre");
		lblNewLabel.setBounds(36, 27, 127, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_nblivre = new JLabel("New label");
		lblNewLabel_nblivre.setBounds(173, 27, 49, 14);
		frame.getContentPane().add(lblNewLabel_nblivre);
		
		lblNewLabel_nblivre.setText(String.valueOf(mainMVC.getM().getListLivre().size()));
		
		JButton btnNewButton_catalogue = new JButton("CATALOGUE");
		btnNewButton_catalogue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Catalogue vc = new View_Catalogue();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_catalogue.setBounds(36, 129, 150, 50);
		frame.getContentPane().add(btnNewButton_catalogue);
		
		JButton btnNewButton_retour = new JButton("Retourner un livre");
		btnNewButton_retour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Retour vr = new View_Retour();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_retour.setBounds(215, 131, 158, 47);
		frame.getContentPane().add(btnNewButton_retour);
		
		JButton btnNewButton_compte = new JButton("Mon compte");
		btnNewButton_compte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Compte vc = new View_Compte();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_compte.setBounds(401, 129, 138, 50);
		frame.getContentPane().add(btnNewButton_compte);
		
		JButton btnNewButton_emprunt = new JButton("Emprunter un livre");
		btnNewButton_emprunt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					View_Emprunt ve = new View_Emprunt();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_emprunt.setBounds(222, 225, 151, 50);
		frame.getContentPane().add(btnNewButton_emprunt);
	}
}
