/**
 * 
 */
package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;
import java.util.*;
/**
 * PizzaDao est un manager qui permet de gérer les manipulations et le stockage de l'ensemble des pizzas.
 * Ceci se passe de façon opaque quand au autres classe.
 * Le pattern singleton a été utilisé pour garantir l'unicité du manager et ainsi la consistance de l'ensemble de données qu'est l'ensemble des pizzas.
 * @author ETY9
 *
 */
public final class PizzaDao implements IPizzaDao {

	/** L'instance unique du manager de pizza PizzaDao*/
	private static PizzaDao instance = null;
	/** Tableau contenant la liste des pizzas au menu. */
	private List<Pizza> listePizza;



	/**
	 * Le constructeur privé du Singleton.
	 */
	private PizzaDao() {
		super();
		listePizza = new ArrayList<Pizza>();
		Pizza.setCurrentGlobalId(0);

		listePizza.add(new Pizza("PEP", "Pépéroni", 12.5));
		listePizza.add(new Pizza("MAR", "Margherita", 14.00));
		listePizza.add(new Pizza("REIN", "La Reine", 11.50));
		listePizza.add(new Pizza("FRO", "La 4 fromages", 12.00));
		listePizza.add(new Pizza("CAN", "La cannibale", 12.50));
		listePizza.add(new Pizza("SAV", "La savoyarde", 13.00));
		listePizza.add(new Pizza("ORI", "L’orientale", 13.50));
		listePizza.add(new Pizza("IND", "L’indienne", 14.00));

	}

	/**
	 * Est utilisé par les autre classes pour récupérer l'instance du singleton en respectant l'encapsulation.
	 * @return L'instance du singleton.
	 */
	public final static PizzaDao getInstance(){
		if(PizzaDao.instance == null){
			synchronized(PizzaDao.class) {
				if (PizzaDao.instance == null) {
					PizzaDao.instance = new PizzaDao();
				}
			}
		}
		return instance;
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#findAllPizza()
	 */
	public List<Pizza> findAllPizza() {
		return listePizza;
	}
	
	/**Afficher le menu*/
	public void displayPizzaMenu(){
		for(Pizza p : getListePizza()){
			System.out.println(p.toString());
		}
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#saveNexPizza(fr.pizzeria.model.Pizza)
	 */
	public boolean saveNexPizza(Pizza pizza) {
		if(pizza == null){
			return false;
		}
		listePizza.add(pizza);
		return true;
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.String, fr.pizzeria.model.Pizza)
	 */
	public boolean updatePizza(String codePizza, Pizza pizza) {
		// Trouver la bonne pizza à partir de son code et la modifier.
		for(Pizza p : listePizza){
			if(p.getCode().equals(codePizza)){
				p.setCode(pizza.getCode());
				p.setNom(pizza.getNom());
				p.setPrix(pizza.getPrix());
				return true;
			}
		}
		return false;
	}


	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	public boolean deletePizza(String codePizza) {
		// Trouver la pizza et la supprimer.
		for(Pizza p : listePizza){
			if(p.getCode().equals(codePizza)){
				return listePizza.remove(p);
			}
		}
		return false;
	}

	/**
	 * Getter.
	 * @return the listePizza
	 */
	public List<Pizza> getListePizza() {
		return listePizza;
	}

	/**
	 * Setter.
	 * @param listePizza the listePizza to set
	 */
	public void setListePizza(List<Pizza> listePizza) {
		this.listePizza = listePizza;
	}

}
