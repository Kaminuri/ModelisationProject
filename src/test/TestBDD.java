package test;
import java.io.File;
import java.sql.*;
import java.util.HashMap;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import BDD.Base;

public class TestBDD {
	
	Base bdd;
	
	@Before
	public void initialisation(){
		bdd = new Base("test.db");
		try{
			bdd.con = DriverManager.getConnection(bdd.url, bdd.nom, bdd.mdp);
			Statement stmt = bdd.con.createStatement();
			String query = "Create table Objets(nom text, adresse text, tag1 text, tag2 text,"
							+ "tag3 text, tag4 text, points int, segments int, faces int, Description String);";

			//String query = "Insert into Objets values ('" + bdd.nom + "','src\\resources\\models\\" + bdd.nomfichier + "','" + tag1 + "','" + tag2 + "','" + tag3 + "','" + tag4 + "','"  + points + "','" + segments + "','" + faces + "','" + des + "')";
			stmt.executeUpdate(query);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testInsert1() {
		bdd.insert("test", "test", "test", "test1", "test2", "test3", 20, 10, 5, "test");
		
		try{
			bdd.con = DriverManager.getConnection(bdd.url, bdd.nom, bdd.mdp);
			
			Statement stmt = bdd.con.createStatement();
			String query = "Select * from Objets";
			ResultSet rs = stmt.executeQuery(query);
			assertTrue(rs.next());
			assertFalse(rs.next());
					
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	@After
	public void fin(){
		File fichier = new File("src/resources/bdd/test.db");
		fichier.delete();
	}
}
