/**
 * 
 */
package fr.pizzeria.ihm;

import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * Option du menu permettant d'afficher la liste des pizzas.
 * @author ETY9
 *
 */
public class ListerPizzasOptionMenu extends OptionMenu {

	/** Permet d'executer l'algorithme pour afficher le menu.*/
	public void execute() {
		System.out.println("Liste des pizzas.");
		for (Pizza p : PizzaDao.getInstance().getListePizza()) {
			System.out.println(p.toString());
		}
	}

}
