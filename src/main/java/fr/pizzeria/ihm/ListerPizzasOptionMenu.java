/**
 * 
 */
package fr.pizzeria.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;

/**
 * Option du menu permettant d'afficher la liste des pizzas.
 * @author ETY9
 *
 */
public class ListerPizzasOptionMenu implements OptionMenu {
	
	private IPizzaDao iPizza;
	
	/** LOG : Logger */
	private static final Logger LOG = LoggerFactory.getLogger(AjouterPizzaOptionMenu.class);
	
	/**
	 * Constructeur, qui a besoin d'une reference vers l'interface pizzaDao contenant l'ensemble des pizzas.
	 * @param iPizza reference vers l'ensemble des pizza
	 */
	public ListerPizzasOptionMenu(IPizzaDao iPizza) {
		super();
		this.iPizza = iPizza;
	}


	/** Permet d'executer l'algorithme pour afficher le menu.*/
	public void execute() {
		LOG.info("Liste des pizzas.");
		this.iPizza.displayPizzaMenu();
	}

}
