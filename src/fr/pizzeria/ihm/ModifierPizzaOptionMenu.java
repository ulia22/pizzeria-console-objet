/**
 * 
 */
package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

/**
 * Option du menu permettant de modifier une pizza du menu.
 * @author ETY9
 *
 */
public class ModifierPizzaOptionMenu extends OptionMenu {

	/**Le scanner pour lire les inputs de la console.*/
	private Scanner sc;

	/**
	 * Constructeur, résupérant le scanner pour lire les inputs de la console.
	 * @param sc Scanner vers le System.in.
	 */
	public ModifierPizzaOptionMenu(Scanner sc) {
		super();
		this.sc = sc;
	}


	/**Permet d'executer l'algorithme pour modifier une pizza du menu.*/
	public void execute() {
		String code;
		// Afficher les pizzas.
		for (Pizza p : PizzaDao.getInstance().getListePizza()) {
			System.out.println(p.toString());
		}
		System.out.println("Veuillez choisir la pizza à modifier.");
		System.out.println("(99 pour abandonner).");

		code = sc.nextLine();
		if (!code.equals("99")) {
			String newCode, nom;
			double prix;

			System.out.println("Veuillez saisir le code");
			newCode = sc.nextLine();

			System.out.println("Veuillez saisir le nom (sans espace)");
			nom = sc.nextLine();

			System.out.println("Veuillez saisir le prix");
			prix = Double.parseDouble(sc.nextLine());

			Pizza p = new Pizza(newCode, nom, prix);

			try{
				PizzaDao.getInstance().updatePizza(code, p);
			}catch(StockageException e){
				System.out.println(e.getMessage());
			}
			

			// Afficher la liste des pizza apres la modification.
			for (Pizza pi : PizzaDao.getInstance().getListePizza()) {
				System.out.println(pi.toString());
			}
		}
	}

}
