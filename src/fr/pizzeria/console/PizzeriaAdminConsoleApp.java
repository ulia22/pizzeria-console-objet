package fr.pizzeria.console;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
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

	/**Liste des options du menu*/
	static Map<Integer, OptionMenu> optionsMenu;

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
		int choice;
		PizzaDao.getInstance().findAllPizza();

		OptionMenu lister = new ListerPizzasOptionMenu();
		OptionMenu ajouter = new AjouterPizzaOptionMenu(sc);
		OptionMenu modifier = new ModifierPizzaOptionMenu(sc);
		OptionMenu supprimer = new SupprimerPizzaOptionMenu(sc);

		optionsMenu = new LinkedHashMap<Integer, OptionMenu>();

		optionsMenu.put(1, lister);
		optionsMenu.put(2, ajouter);
		optionsMenu.put(3, modifier);
		optionsMenu.put(4, supprimer);
		optionsMenu.put(99, null);

		do {
			System.out.println(menu);
			choice = Integer.parseInt(sc.nextLine());

			if(optionsMenu.get(choice) != null){
				optionsMenu.get(choice).execute();
			}
			if(optionsMenu.containsKey(choice) && (optionsMenu.get(choice) == null)){
				System.out.println("Aurevoir \u2639");
			}
			if(!optionsMenu.containsKey(choice)){
				System.out.println("Mauvaise entrée.");
			}

		} while (choice != 99);
		sc.close();
	}
}
