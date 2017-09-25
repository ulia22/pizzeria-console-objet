/**
 * 
 */
package fr.pizzeria.dao;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
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

		listePizza.add(new Pizza("PEP", "Pépéroni", 12.5, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		listePizza.add(new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		listePizza.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("ORI", "L’orientale", 13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza("IND", "L’indienne", 14.00, CategoriePizza.VIANDE));

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
		for(Iterator<Pizza> it = getListePizza().listIterator(); it.hasNext();){
			System.out.println(it.next().toString());
		}
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#saveNexPizza(fr.pizzeria.model.Pizza)
	 */
	public void saveNexPizza(Pizza pizza) throws SavePizzaException {
		if(pizza == null){
			throw new SavePizzaException("La pizza fournis est une reference null");
		}
		listePizza.add(pizza);
		return ;
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.String, fr.pizzeria.model.Pizza)
	 */
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException{
		// Trouver la bonne pizza à partir de son code et la modifier.
		for(Pizza p : listePizza){
			if(p.getCode().equals(codePizza)){
				p.setCode(pizza.getCode());
				p.setNom(pizza.getNom());
				p.setPrix(pizza.getPrix());
				p.setCategorie(pizza.getCategorie());
				return ;
			}
		}
		throw new UpdatePizzaException("Le code de la pizza n'existe pas.");
	}


	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	public void deletePizza(String codePizza) throws DeletePizzaException{
		// Trouver la pizza et la supprimer.
		for(Pizza p : listePizza){
			if(p.getCode().equals(codePizza)){
				listePizza.remove(p);
				return ;
			}
		}
		throw new DeletePizzaException("Le code de la pizza n'existe pas.");
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
