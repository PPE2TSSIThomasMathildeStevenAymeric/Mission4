package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionSQL {
	
			//Déclaration des attributs
			private String nomBDD, login, mdp, url;
			private Connection conn;
			private ResultSet result;
			private Statement state;
			
			//Déclaration d'un constructeur d'objet ConnexionSQL
			public ConnexionSQL(String pNomBDD, String pLogin, String pMdp, String pUrl) {
				this.nomBDD = pNomBDD;
				this.login = pLogin;
				this.mdp = pMdp;
				this.url = pUrl;
			}
			
			public ConnexionSQL() {
		        this.nomBDD = "ffbsq";     // mettre le nom de la bdd entre les double-quote
		        this.login = "root";
		        this.mdp = "root";
		        this.url = "127.0.0.1";
		        this.SeConnecter();
		    }
			
			//Méthode permettant la connexion au serveur
			public void SeConnecter(){
				try {
					Class.forName("com.mysql.jdbc.Driver").newInstance();
				    System.out.println("Driver O.K.");

				    String chemin = "jdbc:mysql://" + this.url + "/" + this.nomBDD;
				    
				     //conn = DriverManager.getConnection(chemin + "?user=" + this.login + "&password=" + this.mdp);
				    conn = DriverManager.getConnection(chemin, this.login, this.mdp);
				    
				    System.out.println("Connexion à la BDD effective !");    
				} 
				catch (Exception e) {
					e.printStackTrace();
					System.out.println(e);
				}
			}
			
			//Méthode qui execute la requête passé en paramètre retournant un résultat de requête
			public ResultSet requeteRetourneDonnees(String pRequete) throws SQLException{
				//Création d'un objet Statement
			    state = conn.createStatement(); 
			    //L'objet ResultSet contient le résultat de la requête SQL
			    result = state.executeQuery(pRequete);
			    //On récupère les MetaData
			    
			    return result;
			}	
			
			//requete executant une requete sans retour (UPDATE,INSERT..)
			public void requeteSansDonnes(String pRequete) throws SQLException{
				//Création d'un objet Statement
			    Statement state = conn.createStatement();
			    //L'objet ResultSet contient le résultat de la requête SQL
			    state.execute(pRequete);
			    state.close();
			}
			
			//Méthode fermant la connexion au serveur
			public void fermerConnexion() throws SQLException{
				state.close();
				result.close();
			}
		}

