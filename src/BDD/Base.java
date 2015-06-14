package BDD;
import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author Eric Lefebvre 
 */

public class Base  {
	protected String url, mdp, nom;
	protected Connection con;
	
	public Base(String Fichier){
		con = null;
		try{
			Class.forName("org.sqlite.JDBC");
		}
		catch(Exception e){System.out.println(e.getMessage());}
		
		File monFichier = new File("resources/bdd/"+Fichier);
		if(!monFichier.exists()){
			try{
			monFichier.createNewFile();
			}
			catch(Exception e){System.out.println("Impossible de créer le fichier : " + e);}
		}

		url = "jdbc:sqlite:resources/bdd/"+Fichier;
		nom = null;
		mdp = null;
	}
	
	/**
	 * Renvoie l'url de la base
	 * @return l'url de la base sous forme de String
	 */
	public String getUrl(){
		return url;
	}
	
	/**
	 * Renvoie le mot de passe de l'utilisateur sous forme de string
	 * @return le mot de passe en String
	 */
	public String getMdp(){
		return mdp;
	}
	
	/**
	 * Renvoie le nom de l'utilisateur sous forme de string
	 * @return le nom en String
	 */
	public String getNom(){
		return nom;
	}
	
	/**
	 * Renvoie la connexion a la base
	 * @return Connection a la base
	 */
	public Connection getCon(){
		return con;
	}
	
	public void setCon(Connection c){
		con = c;
	}

	/**
	 * Renvoie toutes les informations de la Base de donnee
	 * @return une hasmap de hasmap ayant comme cle le nom du fichier.
	 */
	public TreeMap<String, HashMap<String, String>> select(){
		TreeMap<String, HashMap<String, String>> liste = new TreeMap<String, HashMap<String, String>>();
		try{
			con = DriverManager.getConnection(this.url,this.nom,this.mdp);
			Statement stmt = con.createStatement();
			String query = "Select * from Objets";
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData metadata = rs.getMetaData();
			int nombreColonnes = metadata.getColumnCount();
			while(rs.next()){
				HashMap<String, String> valeur = new HashMap<String, String>();
				for(int i = 0; i < nombreColonnes ; i++){
					String nameColone = metadata.getColumnName(i+1);
					valeur.put(nameColone, rs.getString(nameColone));
				}
				liste.put(valeur.get("nom"), valeur);
			} 	
		}
		catch(Exception e){System.out.println(e.getMessage());}
		finally {
			try{con.close();} catch (Exception e){System.out.println(e.getMessage());}
		}
		return liste;
	}
	
	/**
	 * Pareil que le select() mais avec des mots pour selectionner que les fichier correcpondant
	 * @return une hasmap de hasmap ayant pour le cle le nom du fichier
	 */
	
	public TreeMap<String, HashMap<String, String>> recherche(String[] tags){
		TreeMap<String, HashMap<String, String>> liste = new TreeMap<String, HashMap<String, String>>();
		try{
			con = DriverManager.getConnection(this.url,this.nom,this.mdp);
			Statement stmt = con.createStatement();
			int taille = tags.length;
			String query = "Select * from Objets where (";
			for(int i = 0; i < taille; i++){
				for(int j = 1; j < 4+1; j++){
					query += "Tag" + j +" = '" + tags[i] +"'";
					if(j ==  4){
						query += ")";
					}
					else{
						query += " OR ";
					}
				}
				if( i < taille - 1){
					query += " AND (";
				}
			}
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData metadata = rs.getMetaData();
			int nombreColonnes = metadata.getColumnCount();
			while(rs.next()){
				HashMap<String, String> valeur = new HashMap<String, String>();
				for(int i = 0; i < nombreColonnes ; i++){
					String nameColone = metadata.getColumnName(i+1);
					valeur.put(nameColone, rs.getString(nameColone));
				}
				liste.put(valeur.get("nom"), valeur);
			} 	
		}
		catch(Exception e){System.out.println(e.getMessage());}
		finally {
			try{con.close();} catch (Exception e){System.out.println(e.getMessage());}
		}
		return liste;
	}

	/**
	 * Permet d'inserer un nouveau fichier dans la base de donnees
	 * 
	 */
	public void insert(String nom, String nomfichier, String tag1, String tag2, String tag3, String tag4, int points, int segments, int faces, String des){
		try{
			con = DriverManager.getConnection(this.url,this.nom,this.mdp);
			Statement stmt = con.createStatement();
			String query = "Insert into Objets values ('" + nom + "','resources/models/" + nomfichier + "','" + tag1 + "','" + tag2 + "','" + tag3 + "','" + tag4 + "','"  + points + "','" + segments + "','" + faces + "','" + des + "')";
			stmt.executeUpdate(query);
		}
		catch(Exception e){System.out.println(e.getMessage());}
		finally {
			try{con.close();} catch (Exception e){System.out.println(e.getMessage());}
		}
	}
	
	/**
	 * @param nom le nom du fichier a supprimer
	 *Permet de supprimer un fichier de la base de donnee 
	 */
	
	public void delete(String nom){
		try{
			con = DriverManager.getConnection(this.url,this.nom,this.mdp);
			Statement stmt = con.createStatement();
			String query = "Delete From Objets where nom = '" + nom + "'";
			stmt.executeUpdate(query);
		}
		catch(Exception e){System.out.println(e.getMessage());}
		finally {
			try{con.close();} catch (Exception e){System.out.println(e.getMessage());}
		}
	}
	
	/**
	 * Permet de verifier si le nom existe deja.
	 * @param nom Le nom de l'objet
	 * @return vrai si le nom existe
	 */
	public boolean estDeja(String nom){
		boolean deja = false;
		try{
			con = DriverManager.getConnection(this.url,this.nom,this.mdp);
			Statement stmt = con.createStatement();
			String query = "Select * from Objets where nom = '" + nom + "'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				deja = true;
			}
		}
		catch(Exception e){System.out.println(e.getMessage());}
		finally {
			try{con.close();} catch (Exception e){System.out.println(e.getMessage());}
		}
		return deja;
	}
}