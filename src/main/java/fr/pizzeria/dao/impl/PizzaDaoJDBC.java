/**
 * 
 */
package fr.pizzeria.dao.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UnknowPizzaCategorieException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author ETY9
 *
 */
public class PizzaDaoJDBC implements IPizzaDao {

	/** dbConnection : Connection */
	private Connection dbConnection;
	/** instance : PizzaDaoJDBC */
	private static PizzaDaoJDBC instance;

	/*
	 * Config de la DB. 
	 */
	/** PATH_CONFIG : String */
	private static final String PATH_CONFIG = "db_config";
	/** configConnectionDB : HashMap<String,String> */
	private HashMap<String, String> configConnectionDB;
	
	/*
	 * Requetes de la DB
	 */
	/** PATH_QUERIES : String */
	private static final String PATH_QUERIES = "queries";
	/** queriesDB : HashMap<String,String> */
	private HashMap<String, String> queriesDB;
	
	/** LOG : Logger */
	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoJDBC.class);

	

	/**
	 * Est utilisé par les autre classes pour récupérer l'instance du singleton en respectant l'encapsulation.
	 * @return L'instance du singleton.
	 */
	public static final PizzaDaoJDBC getInstance(){
		if(PizzaDaoJDBC.instance == null){
			PizzaDaoJDBC.instance = new PizzaDaoJDBC();
		}
		return instance;
	}
	
	/**
	 * 
	 */
	public PizzaDaoJDBC() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			initConfig();
		} catch (ClassNotFoundException | IOException e) {
			LOG.info(e.getLocalizedMessage());
		} 		
	}

	/**
	 * @throws IOException
	 */
	private void initConfig() throws IOException{	
			configConnectionDB = new HashMap<String, String>();
			ResourceBundle rb = ResourceBundle.getBundle(PATH_CONFIG);
			configConnectionDB.put("protocole", rb.getString("protocole"));
			configConnectionDB.put("sous-protocole", rb.getString("sous-protocole"));
			configConnectionDB.put("complement", rb.getString("complement"));
			configConnectionDB.put("username", rb.getString("username"));
			configConnectionDB.put("password", rb.getString("password"));
			
			queriesDB = new HashMap<String, String>();
			rb = ResourceBundle.getBundle(PATH_QUERIES);
			queriesDB.put("pizza.select", rb.getString("pizza.select"));
			queriesDB.put("pizza.insert", rb.getString("pizza.insert"));
			queriesDB.put("pizza.update", rb.getString("pizza.update"));
			queriesDB.put("pizza.delete", rb.getString("pizza.delete"));
			queriesDB.put("pizza.selectwhereid", rb.getString("pizza.selectwhereid"));
			queriesDB.put("categoriepizza.selectall", rb.getString("categoriepizza.selectall"));
	}


	/**
	 * @throws SQLException
	 */
	public void initDBConnection() throws SQLException{
		dbConnection = getDbConnection();
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#findAllPizza()
	 */
	@Override
	public List<Pizza> findAllPizza() {
		try {
			PreparedStatement selectAllPizza = dbConnection.prepareStatement(queriesDB.get("pizza.select"));
			ResultSet result = selectAllPizza.executeQuery();
			List<Pizza> l = new ArrayList<>();
			while(result.next()){
				int id = result.getInt("id");
				String code = result.getString("code");
				String nom = result.getString("nom");
				double prix = result.getDouble("prix");
				int categorieId = result.getInt("categorieId");
				CategoriePizza categorie = findCategorieFromDdId(categorieId);
				l.add(new Pizza(id, code, nom, prix, categorie));
			}
			return l;
		} catch (SQLException | UnknowPizzaCategorieException e) {
			LOG.info(e.getLocalizedMessage());
		}

		return new ArrayList<>();
	}
	

	/**
	 * @param id
	 * @return
	 * @throws UnknowPizzaCategorieException
	 */
	private CategoriePizza findCategorieFromDdId(int id) throws UnknowPizzaCategorieException{

		try{
			PreparedStatement st = getDbConnection().prepareStatement(queriesDB.get("pizza.selectwhereid"));
			st.setInt(1, id);
			ResultSet res = st.executeQuery();
			
			res.next();
			CategoriePizza categorie = CategoriePizza.libelToCategorie(res.getString("libel")).orElseThrow(()->new UnknowPizzaCategorieException());
			
			st.close();
			return categorie;
		}catch(SQLException | UnknowPizzaCategorieException e){
			
		}finally {
			try {closeDbConnection();} catch (SQLException e) {LOG.info(e.getLocalizedMessage());}
		}
		throw new UnknowPizzaCategorieException();
	}

	/**
	 * @param cat
	 * @return
	 * @throws UnknowPizzaCategorieException
	 */
	private int findDbIdFromCategorie(CategoriePizza cat) throws UnknowPizzaCategorieException{
		try{
			PreparedStatement st = getDbConnection().prepareStatement(queriesDB.get("categoriepizza.selectall"));
			ResultSet res = st.executeQuery();

			while(res.next()){
				CategoriePizza categorie = CategoriePizza.libelToCategorie(res.getString("libel"))
						.orElseThrow(()->new UnknowPizzaCategorieException());
				
				if(categorie.equals(cat)){
					return res.getInt("id");
				}
			}
			st.close();
		}catch(SQLException | UnknowPizzaCategorieException e){
			LOG.info(e.getLocalizedMessage());
		}finally {
			try {closeDbConnection();} catch (SQLException e) {LOG.info(e.getLocalizedMessage());}
		}
		throw new UnknowPizzaCategorieException();
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#saveNextPizza(fr.pizzeria.model.Pizza)
	 */
	@Override
	public void saveNextPizza(Pizza pizza) throws SavePizzaException {
		try {
			getDbConnection();
			PreparedStatement insertNextPizza = dbConnection.prepareStatement(queriesDB.get("pizza.insert"));
			insertNextPizza.setString(1, pizza.getCode());
			insertNextPizza.setString(2, pizza.getNom());
			insertNextPizza.setBigDecimal(3, BigDecimal.valueOf(pizza.getPrix()));
			insertNextPizza.setInt(4, findDbIdFromCategorie(pizza.getCategorie()));
			insertNextPizza.executeUpdate();
			closeDbConnection();
		} catch (SQLException | UnknowPizzaCategorieException e) {
			LOG.info(e.getLocalizedMessage());
		}

	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.String, fr.pizzeria.model.Pizza)
	 */
	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		try {
			getDbConnection();
			PreparedStatement updateAPizza = dbConnection.prepareStatement(queriesDB.get("pizza.update"));
			updateAPizza.setString(1, pizza.getCode());
			updateAPizza.setString(2, pizza.getNom());
			updateAPizza.setBigDecimal(3, BigDecimal.valueOf(pizza.getPrix()));
			updateAPizza.setInt(4, findDbIdFromCategorie(pizza.getCategorie()));
			updateAPizza.setString(5, codePizza);
			updateAPizza.executeUpdate();
			closeDbConnection();
		} catch (SQLException | UnknowPizzaCategorieException e) {
			LOG.info(e.getLocalizedMessage());
		}
		

	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	@Override
	public void deletePizza(String codePizza) throws DeletePizzaException {
		try {
			getDbConnection();
			PreparedStatement deleteAPizza = dbConnection.prepareStatement(queriesDB.get("pizza.delete"));
			deleteAPizza.setString(1, codePizza);
			deleteAPizza.executeUpdate();
			closeDbConnection();
		} catch (SQLException e) {
			LOG.info(e.getLocalizedMessage());
		}
		

	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#displayPizzaMenu()
	 */
	@Override
	public void displayPizzaMenu() {
		List<Pizza> l = findAllPizza();
		l.stream().forEach(p -> LOG.info(p.toString()));
	}

	/**
	 * Getter for dbConnection.
	 * @return the dbConnection
	 * @throws SQLException 
	 */
	private Connection getDbConnection() throws SQLException {
		if(dbConnection == null || dbConnection.isClosed()){
			StringBuilder connect = new StringBuilder();
			connect.append(configConnectionDB.get("protocole"))
			.append(":")
			.append(configConnectionDB.get("sous-protocole"))
			.append(":")
			.append(configConnectionDB.get("complement"));
			
			dbConnection = DriverManager.getConnection(connect.toString(), configConnectionDB.get("username"), configConnectionDB.get("password"));
		}
		return dbConnection;
	}
	
	/**
	 * @throws SQLException
	 */
	private void closeDbConnection() throws SQLException{
		if(dbConnection != null || !dbConnection.isClosed()){
			dbConnection.close();
		}
	}

}
