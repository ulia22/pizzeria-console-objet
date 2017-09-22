/**
 * 
 */
package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.model.Pizza;

/**
 * Option du menu permettant de supprimer une pizza du menu.
 * @author ETY9
 *
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {

	/**Le scanner pour lire les inputs de la console.*/
	Scanner sc;

	/**
	 * Constructeur, résupérant le scanner pour lire les inputs de la console.
	 * @param sc Scanner vers le System.in.
	 */
	public SupprimerPizzaOptionMenu(Scanner sc) {
		super();
		this.sc = sc;
	}

	/** Permet d'executer l'algorithme pour supprimer une pizza du menu.*/
	public void execute() {
		String code;
		// Afficher les pizzas.
		for (Pizza p : PizzaDao.getInstance().getListePizza()) {
			System.out.println(p.toString());
		}
		System.out.println("Veuillez choisir la pizza à supprimer.");
		System.out.println("(99 pour abandonner).");

		code = sc.nextLine();
		if (!code.equals("99")) {
			PizzaDao.getInstance().deletePizza(code);
		}
	}

}
