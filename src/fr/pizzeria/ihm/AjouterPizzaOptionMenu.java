/**
 * 
 */
package fr.pizzeria.ihm;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.Pizza;

/**
 * Option du menu permettant d'ajouter une pizza au menu.
 * @author ETY9
 *
 */
public class AjouterPizzaOptionMenu extends OptionMenu {

	/**Le scanner pour lire les inputs de la console.*/
	private Scanner sc;

	/**
	 * Constructeur, résupérant le scanner pour lire les inputs de la console.
	 * @param sc Scanner vers le System.in.
	 */
	public AjouterPizzaOptionMenu(Scanner sc) {
		super();
		this.sc = sc;
	}


	/** Permet d'executer l'algorithme pour ajouter une pizza au menu.*/
	public void execute(){
		System.out.println("Ajout d’une nouvelle pizza");
		String code, nom;
		double prix;

		// Créer la nouvelle pizza.
		System.out.println("Veuillez saisir le code");
		code = sc.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace)");
		nom = sc.nextLine();
		System.out.println("Veuillez saisir le prix");
		prix = Double.parseDouble(sc.nextLine());

		// Création d'un nouveau et plus grand tableau de pizza.
		Pizza p = new Pizza(code, nom, prix);

		try{
		PizzaDao.getInstance().saveNexPizza(p);
		}catch(StockageException e){
			System.out.println((e.getMessage()));
		}
	}

}
