/**
 * 
 */
package fr.pizzeria.exception;

/**
 * Classe d'exception a utiliser quand il y a un problème dans la méthode savePizza de PizzaDao.
 * @author ETY9
 *
 */
@SuppressWarnings("serial")
public class SavePizzaException extends StockageException {
	
	/**
	 * Constructeur par defaut.
	 */
	public SavePizzaException(){
		super();
	}
	
	/**
	 * Constructeur avec un message servant à décrire la source de l'exception en paramètre.
	 * @param msg message d'information sur la source de l'exception
	 */
	public SavePizzaException(String msg){
		super(msg);
	}
}
