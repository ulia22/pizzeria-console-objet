/**
 * 
 */
package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

/**
 * Option du menu permettant de supprimer une pizza du menu.
 * @author ETY9
 *
 */
public class SupprimerPizzaOptionMenu extends OptionMenu {

	/**Le scanner pour lire les inputs de la console.*/
	Scanner sc;

	/**Reference vers le singleton IPizzaDao qui accede à l'ensembles des pizza et fournis des méthodes pour le manipuler.*/
	private IPizzaDao iPizza;
	
	/**
	 * Constructeur, résupérant le scanner pour lire les inputs de la console.
	 * @param sc Scanner vers le System.in.
	 */
	public SupprimerPizzaOptionMenu(Scanner sc, IPizzaDao iPizza) {
		super();
		this.sc = sc;
		this.iPizza = iPizza;
	}

	/** Permet d'executer l'algorithme pour supprimer une pizza du menu.*/
	public void execute() {
		String code;
		// Afficher les pizzas.
		iPizza.displayPizzaMenu();
		System.out.println("Veuillez choisir la pizza à supprimer.");
		System.out.println("(99 pour abandonner).");

		code = sc.nextLine();
		try{
			if (!code.equals("99")) {
				iPizza.deletePizza(code);
			}
		}catch(StockageException e){
			System.out.println(e.getMessage());
		}
		catch(Exception n){
			System.out.println(n.getMessage());
		}
	}

}
