package test;
import java.io.File;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

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
			bdd.setCon(DriverManager.getConnection(bdd.getUrl(), bdd.getNom(), bdd.getMdp()));
			Statement stmt = bdd.getCon().createStatement();
			String query = "Create table Objets(nom text, adresse text, tag1 text, tag2 text,"
							+ "tag3 text, tag4 text, points int, segments int, faces int, Description String);";
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
			bdd.setCon(DriverManager.getConnection(bdd.getUrl(), bdd.getNom(), bdd.getMdp()));
			
			Statement stmt = bdd.getCon().createStatement();
			String query = "Select * from Objets";
			ResultSet rs = stmt.executeQuery(query);
			assertTrue(rs.next());
			assertFalse(rs.next());
					
		}
		catch(Exception e){
			System.out.println(e);
		}
		try{bdd.getCon().close();} catch (Exception e){System.out.println(e.getMessage());}
	}
	
	@Test
	public void testInsert2() {
		bdd.insert("test2", "test", "test2", "testx", "test2", "test3", 20, 10, 5, "test");
		bdd.insert("test", "test", "test", "test1", "test2", "test3", 20, 10, 5, "test");
		
		try{
			bdd.setCon(DriverManager.getConnection(bdd.getUrl(), bdd.getNom(), bdd.getMdp()));
			
			Statement stmt = bdd.getCon().createStatement();
			String query = "Select * from Objets";
			ResultSet rs = stmt.executeQuery(query);
			assertTrue(rs.next());
			assertTrue(rs.next());
			assertFalse(rs.next());
					
		}
		catch(Exception e){
			System.out.println(e);
		}
		try{bdd.getCon().close();} catch (Exception e){System.out.println(e.getMessage());}
	}
	
	@Test
	public void testRecherche1() {
		bdd.insert("test2", "test2", "test2", "test3", "testx", "test4", 20, 10, 5, "test");
		String [] tab = new String [1];
		tab[0] = "test2";
		HashMap<String, HashMap<String, String>> resultat = bdd.recherche(tab);
		assertTrue(resultat.containsKey("test2"));
	}
	
	@Test
	public void testRecherche2() {
		bdd.insert("test2", "test2", "test2", "test3", "testx", "test4", 20, 10, 5, "test");
		String [] tab = new String [2];
		tab[0] = "test2";
		tab[1] = "test3";
		HashMap<String, HashMap<String, String>> resultat = bdd.recherche(tab);
		assertTrue(resultat.containsKey("test2"));
	}
	
	@Test
	public void testRecherche3() {
		bdd.insert("test2", "test2", "test2", "test3", "testx", "test4", 20, 10, 5, "test");
		String [] tab = new String [2];
		tab[0] = "test2";
		tab[1] = "test6";
		HashMap<String, HashMap<String, String>> resultat = bdd.recherche(tab);
		assertFalse(resultat.containsKey("test2"));
	}
	
	@Test
	public void testSelect() {
		bdd.insert("test", "test", "test", "test1", "test2", "test3", 20, 10, 5, "test");
		bdd.insert("test2", "test2", "test2", "test3", "testx", "test4", 20, 10, 5, "test");
		HashMap<String, HashMap<String, String>> resultat = bdd.select();
		assertNotNull(resultat);
		assertTrue(resultat.containsKey("test"));
		assertTrue(resultat.containsKey("test2"));
		assertFalse(resultat.containsKey("test7"));
	}
	
	@Test
	public void testDelete() {
		bdd.insert("test", "test", "test", "test1", "test2", "test3", 20, 10, 5, "test");
		HashMap<String, HashMap<String, String>> resultat = bdd.select();
		assertTrue(resultat.containsKey("test"));
		bdd.delete("test");
		resultat = bdd.select();
		assertFalse(resultat.containsKey("test"));
	}
	
	@Test
	public void testEstDeja1(){
		bdd.insert("test", "test", "test", "test1", "test2", "test3", 20, 10, 5, "test");
		assertTrue(bdd.estDeja("test"));
	}
	
	@Test
	public void testEstDeja2(){
		assertFalse(bdd.estDeja("test"));
	}
	
	

	@After
	public void fin(){
		File fichier = new File("src/resources/bdd/test.db");
		fichier.delete();
	}
}
