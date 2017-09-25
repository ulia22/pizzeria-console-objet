/**
 * 
 */
package fr.pizzeria.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ETY9
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ToString {
	
	boolean upperCase() default false;
	
	
	/**
	 * Template pour déterminer comment afficher la value
	 * Code : ## est la chaine à remplacer
	 * @return
	 */
	String template() default "";
}
