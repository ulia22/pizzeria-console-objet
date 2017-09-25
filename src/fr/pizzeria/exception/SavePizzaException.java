/**
 * 
 */
package fr.pizzeria.exception;

/**
 * @author ETY9
 *
 */
@SuppressWarnings("serial")
public class SavePizzaException extends StockageException {
	public SavePizzaException(){
		super();
	}
	
	public SavePizzaException(String msg){
		super(msg);
	}
}
