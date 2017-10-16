/**
 * 
 */
package fr.pizzeria.model;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author ETY9
 *
 */
public enum CategoriePizza {
	VIANDE ("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans Viande");
	
	private String libel;
	
	private CategoriePizza(String libel){
		this.libel = libel;
	}
	
	public String getLibel(){
		return this.libel;
	}
	
	public static Optional<CategoriePizza> libelToCategorie(String libel){
		CategoriePizza[] values = values();
		return Stream.of(values).filter(c -> c.getLibel().equals(libel)).findFirst();
	}
}