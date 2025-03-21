package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class model {
	private ArrayList<LIVRE> ListLivre;
	private ArrayList<AUTEUR> ListAuteur;
	private ArrayList<ADHERENT> ListAdherent;
	private Connection con;

	public void getall() throws SQLException, ClassNotFoundException
	{
		//on vide les listes pour les recharger
		ListAdherent.clear();
		ListAuteur.clear();
		ListLivre.clear();
		ListLivre=new ArrayList<LIVRE> ();
		ListAuteur=new ArrayList<AUTEUR> ();
		ListAdherent=new ArrayList<ADHERENT> ();
		ResultSet resultats;
		String requete;
		Statement stmt = con.createStatement();

		//*********************************************
		//CREATION DES AUTEURS
		//*********************************************
		requete = "SELECT * FROM auteur";
		resultats = stmt.executeQuery(requete);
		while (resultats.next()) {
			AUTEUR a=new AUTEUR(resultats.getString(1), resultats.getString(2), resultats.getString(3), resultats.getString(4),resultats.getString(5));
			ListAuteur.add(a);
		}

		//*********************************************
		//CREATION DES LIVRES
		//*********************************************
		requete = "SELECT * FROM livre";
		resultats = stmt.executeQuery(requete);

		while (resultats.next()) {
			LIVRE l=new LIVRE(resultats.getString(1), resultats.getString(2), resultats.getInt(3),null,null);
			ListLivre.add(l);
		}


		//*********************************************
		//CREATION DES ADHERENTS
		//*********************************************
		requete = "SELECT * FROM adherent";
		resultats = stmt.executeQuery(requete);

		while (resultats.next()) {
			ADHERENT ad=new ADHERENT(resultats.getString(1), resultats.getString(2),  resultats.getString(3), resultats.getString(4), new ArrayList<LIVRE>());
			ListAdherent.add(ad);
		}

		//*********************************************
		//AJOUT liste de livre � ADHERENT
		//AJOUT de l'emprunteur � LIVRE
		//*********************************************
		requete = "SELECT ISBN,adherent FROM livre, adherent WHERE livre.adherent = adherent.num";
		resultats = stmt.executeQuery(requete);
		String ISBN,numadherent ;
		while (resultats.next()) {
			ISBN= resultats.getString(1);
			numadherent=resultats.getString(2);
			findlivre(ISBN).setEmprunteur(findadherent(numadherent));
			findadherent(numadherent).ajouterLivre(findlivre(ISBN));
		}

		//*********************************************
		//Ajout de l'auteur au LIVRE
		//*********************************************
		requete = "SELECT ISBN,auteur FROM livre, auteur WHERE livre.auteur = auteur.num";
		resultats = stmt.executeQuery(requete);
		String numauteur ;
		while (resultats.next()) {
			ISBN= resultats.getString(1);
			numauteur=resultats.getString(2);
			findlivre(ISBN).setAuteur(findauteur(numauteur));

		}


	}
	public AUTEUR findauteur(String num)
	{
		for(int i=0;i<ListAuteur.size();i++)
		{
			if(ListLivre.get(i).getISBN().equals(num))
			{
				return ListAuteur.get(i);
			}
		}

		return null;
	}

	public LIVRE findlivre(String ISBN)
	{
		for(int i=0;i<ListLivre.size();i++)
		{
			if(ListLivre.get(i).getISBN().equals(ISBN))
			{
				return ListLivre.get(i);
			}
		}

		return null;
	}
	public ADHERENT findadherent(String num)
	{
		for(int i=0;i<ListAdherent.size();i++)
		{
			if(ListAdherent.get(i).getNum().equals(num))
			{
				return ListAdherent.get(i);
			}
		}

		return null;
	}

	public model() throws ClassNotFoundException, SQLException {
		ListLivre = new ArrayList<LIVRE>();
		ListAuteur = new ArrayList<AUTEUR>();
		ListAdherent = new ArrayList<ADHERENT>();
		
		String BDD="ap2";
		String url="jdbc:mysql://localhost:3306/"+BDD;
		String user="root";
		String passwd="";
		/*
		String BDD="u937355202_gravouil";
		String url="jdbc:mysql://193.203.168.138:3306/"+BDD+ "?characterEncoding=utf8";
		String user="u937355202_gravouil";
		String passwd="E6Ensitech2024";
		*/
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn;
		conn = DriverManager.getConnection(url, user, passwd);
		System.out.println("Connection OK");
		this.con=conn;
	}

	public ArrayList<LIVRE> getListLivre() {
		return ListLivre;
	}

	public void setListLivre(ArrayList<LIVRE> listLivre) {
		ListLivre = listLivre;
	}

	public ArrayList<AUTEUR> getListAuteur() {
		return ListAuteur;
	}

	public void setListAuteur(ArrayList<AUTEUR> listAuteur) {
		ListAuteur = listAuteur;
	}

	public ArrayList<ADHERENT> getListAdherent() {
		return ListAdherent;
	}

	public void setListAdherent(ArrayList<ADHERENT> listAdherent) {
		ListAdherent = listAdherent;
	}

	public void retourLivre(String ISBN) throws SQLException
	{
		String req;
		req = "UPDATE `livre` SET `adherent` = NULL WHERE `livre`.`ISBN` = '"+ISBN+"'";
		Statement stmt = con.createStatement();
		int maj = stmt.executeUpdate(req);	
	}

	public void emprunterlivre(String num,String ISBN) throws SQLException
	{
		String req;
		req = "UPDATE `livre` SET `adherent` = '"+num+"' WHERE `livre`.`ISBN` = '"+ISBN+"'";
		Statement stmt = con.createStatement();
		int maj = stmt.executeUpdate(req);
	}
	public void MAJ_ad(String nom,String prenom,String email,String num) throws SQLException
	{
		String req;
		req = "UPDATE `adherent` SET  `nom` = '"+nom+"', `prenom` = '"+prenom+"', `email` = '"+email+"' WHERE `adherent`.`num` = '"+num+"';";
		Statement stmt = con.createStatement();
		int maj = stmt.executeUpdate(req);
		System.out.println(maj);
		System.out.println(req);
		
	}

}
