/**
 * 
 */
package fr.pizzeria.exception;

/**
 * @author ETY9
 *
 */
@SuppressWarnings("serial")
public class UpdatePizzaException extends StockageException {
	public UpdatePizzaException(){
		super();
	}
	
	public UpdatePizzaException(String msg){
		super(msg);
	}
}
