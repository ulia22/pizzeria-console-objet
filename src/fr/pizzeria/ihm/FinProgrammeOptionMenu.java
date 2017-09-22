/**
 * 
 */
package fr.pizzeria.ihm;

/**
 * Option du menu permettant de quitter le programme.
 * @author ETY9
 *
 */
public class FinProgrammeOptionMenu extends OptionMenu {

	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute()
	 */
	@Override
	public void execute() {
		System.out.println("Aurevoir \u2639");
	}

}
