/**
 * 
 */
package fr.pizzeria.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Option du menu permettant de quitter le programme.
 * @author ETY9
 *
 */
public class FinProgrammeOptionMenu implements OptionMenu {

	/** LOG : Logger */
	private static final Logger LOG = LoggerFactory.getLogger(AjouterPizzaOptionMenu.class);
	
	/* (non-Javadoc)
	 * @see fr.pizzeria.ihm.OptionMenu#execute()
	 */
	@Override
	public void execute() {
		LOG.info("Aurevoir \u2639");
	}

}
