/**
 * 
 */
package fr.pizzeria.ihm;

import fr.pizzeria.dao.IPizzaDao;

/**
 * Option du menu permettant d'afficher la liste des pizzas.
 * @author ETY9
 *
 */
public class ListerPizzasOptionMenu extends OptionMenu {
	
	private IPizzaDao IPizza;
	
	
	/**
	 * Constructeur, qui a besoin d'une reference vers l'interface pizzaDao contenant l'ensemble des pizzas.
	 * @param iPizza reference vers l'ensemble des pizza
	 */
	public ListerPizzasOptionMenu(IPizzaDao iPizza) {
		super();
		this.IPizza = iPizza;
	}


	/** Permet d'executer l'algorithme pour afficher le menu.*/
	public void execute() {
		System.out.println("Liste des pizzas.");
		this.IPizza.displayPizzaMenu();
	}

}
