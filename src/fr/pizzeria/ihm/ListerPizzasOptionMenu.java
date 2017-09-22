/**
 * 
 */
package fr.pizzeria.ihm;

import fr.pizzeria.dao.PizzaDao;

/**
 * Option du menu permettant d'afficher la liste des pizzas.
 * @author ETY9
 *
 */
public class ListerPizzasOptionMenu extends OptionMenu {

	/** Permet d'executer l'algorithme pour afficher le menu.*/
	public void execute() {
		System.out.println("Liste des pizzas.");
		PizzaDao.getInstance().displayPizzaMenu();
	}

}
