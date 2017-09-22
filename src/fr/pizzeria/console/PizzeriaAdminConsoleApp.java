package fr.pizzeria.console;

import java.util.Locale;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaDao;
import fr.pizzeria.ihm.AjouterPizzaOptionMenu;
import fr.pizzeria.ihm.ListerPizzasOptionMenu;
import fr.pizzeria.ihm.ModifierPizzaOptionMenu;
import fr.pizzeria.ihm.OptionMenu;
import fr.pizzeria.ihm.SupprimerPizzaOptionMenu;

/**
 * Classe contenant le point d'entré du programme, utilisant la console pour
 * recevoir/envoyer les inputs/outputs du programme.
 * 
 * @author Clément B.
 * 
 */
public class PizzeriaAdminConsoleApp {

	/** Chaine de caractère contenant le menu. */
	private static String menu = new String(
			"***** Pizzeria Administration *****\n" + "1. Lister les pizzas\n" + "2. Ajouter une nouvelle pizza\n"
					+ "3. Mettre à jour une pizza\n" + "4. Supprimer une pizza\n" + "99. Sortir\n");

	/** Scanner qui sera utilisé dans toute l'application */
	public static Scanner sc = new Scanner(System.in);

	/**
	 * Point d'entré de l'application.
	 * 
	 * @param args
	 *            Non-utilisés dans le cadre de l'application.
	 */
	public static void main(String[] args) {
		sc.useLocale(Locale.US);
		String choice;
		PizzaDao.getInstance().findAllPizza();

		OptionMenu lister = new ListerPizzasOptionMenu();
		OptionMenu ajouter = new AjouterPizzaOptionMenu(sc);
		OptionMenu modifier = new ModifierPizzaOptionMenu(sc);
		OptionMenu supprimer = new SupprimerPizzaOptionMenu(sc);

		do {
			System.out.println(menu);
			choice = sc.nextLine();
			switch (choice) {
			case "1":
				lister.execute();
				break;
			case "2":
				ajouter.execute();
				break;
			case "3":
				modifier.execute();
				break;
			case "4":
				supprimer.execute();
				break;
			case "99":
				System.out.println("Aurevoir \u2639");
				break;
			default:
				System.out.println("Mauvaise entrée.");
				break;

			}
		} while (!choice.equals("99"));
		sc.close();
	}
}
