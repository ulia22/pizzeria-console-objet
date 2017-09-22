/**
 * 
 */
package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

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
	private Pizza[] listePizza;



	/**
	 * Le constructeur privé du Singleton.
	 */
	private PizzaDao() {
		super();
		listePizza = new Pizza[8];
		Pizza.setCurrentGlobalId(0);

		listePizza[0] = new Pizza("PEP", "Pépéroni", 12.5);
		listePizza[1] = new Pizza("MAR", "Margherita", 14.00);
		listePizza[2] = new Pizza("REIN", "La Reine", 11.50);
		listePizza[3] = new Pizza("FRO", "La 4 fromages", 12.00);
		listePizza[4] = new Pizza("CAN", "La cannibale", 12.50);
		listePizza[5] = new Pizza("SAV", "La savoyarde", 13.00);
		listePizza[6] = new Pizza("ORI", "L’orientale", 13.50);
		listePizza[7] = new Pizza("IND", "L’indienne", 14.00);

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
	public Pizza[] findAllPizza() {
		return listePizza;
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#saveNexPizza(fr.pizzeria.model.Pizza)
	 */
	public boolean saveNexPizza(Pizza pizza) {
		if(pizza == null){
			return false;
		}
		// Création d'un nouveau et plus grand tableau de pizza.
		Pizza[] tab = new Pizza[listePizza.length + 1];

		// Transférer le contenu de l'ancien tableau dans le nouveau.
		for (int i = 0; i < listePizza.length; i++) {
			tab[i] = listePizza[i];
		}
		tab[listePizza.length] = pizza;
		listePizza = tab;

		return true;
	}

	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#updatePizza(java.lang.String, fr.pizzeria.model.Pizza)
	 */
	public boolean updatePizza(String codePizza, Pizza pizza) {
		int index = -1;
		// Trouver la bonne pizza à partir de son code.
		for (int i = 0; i < listePizza.length; i++) {
			if (listePizza[i].getCode().equals(codePizza)) {
				index = i;
			}
		}
		if(index == -1){
			return false;
		}
		listePizza[index] = pizza;

		return true;
	}


	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#deletePizza(java.lang.String)
	 */
	public boolean deletePizza(String codePizza) {
		// Trouver la pizza.
		int index = -1;
		for (int i = 0; i < listePizza.length; i++) {
			if (listePizza[i].getCode().equals(codePizza)) {
				index = i;
			}
		}
		if(index == -1){
			return false;
		}

		// Supprimer la pizza.
		listePizza[index] = null;
		Pizza[] tab = new Pizza[listePizza.length - 1];
		int j = 0;
		for (int i = 0; i < listePizza.length; i++) {
			if (listePizza[i] != null) {
				tab[j] = listePizza[i];
				j++;
			}
		}
		listePizza = tab;

		return true;
	}

	/**
	 * Getter.
	 * @return the listePizza
	 */
	public Pizza[] getListePizza() {
		return listePizza;
	}

	/**
	 * Setter.
	 * @param listePizza the listePizza to set
	 */
	public void setListePizza(Pizza[] listePizza) {
		this.listePizza = listePizza;
	}

}
