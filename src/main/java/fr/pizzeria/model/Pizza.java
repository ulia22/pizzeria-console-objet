package fr.pizzeria.model;

import java.lang.reflect.Field;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.ihm.AjouterPizzaOptionMenu;

/** Classe représentant une pizza par instance. */
public class Pizza {


	/**
	 * ID global des pizzas. Contient un ID unique libre à associer à la
	 * prochaine instance de Pizza, lequel est incrémenté de 1 appres chaque
	 * utilisation afin de rester unique.
	 */
	private static int currentGlobalId;
	/** Id unique de l'instance courrante de Pizza. */
	private int id;
	/**
	 * Code litteral d'identification de la pizza représenté par l'instance
	 * courrante de Pizza.
	 */
	@ToString(template = "## ")
	private String code;
	/** Nom de la pizza représenté par l'instance courrante de Pizza. */
	@ToString(upperCase = true, template = "## ")
	private String nom;
	/** Prix de la pizza représenté par l'instance courrante de Pizza. */
	@ToString(template = "(##€) ")
	private double prix;
	/** Categorie de la pizza (Viande, sans_viande, poisson, ...)*/
	@ToString(template = "Categorie : ##")
	private CategoriePizza categorie;

	/** LOG : Logger */
	private static final Logger LOG = LoggerFactory.getLogger(AjouterPizzaOptionMenu.class);


	/**
	 * @param code
	 *            Code litteral d'identification de la pizza représenté par
	 *            l'instance courrante de Pizza.
	 * @param nom
	 *            Nom de la pizza représenté par l'instance courrante de Pizza.
	 * @param prix
	 *            Prix de la pizza représenté par l'instance courrante de Pizza.
	 */
	public Pizza(String code, String nom, double prix, CategoriePizza categorie) {
		this.id = Pizza.currentGlobalId;
		Pizza.currentGlobalId++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
	}

	@Override
	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		Class<? extends Pizza> context = this.getClass();
		Field[] fields = context.getDeclaredFields();

		for (Field field : fields) {
			Object val = null;
			try {
				val = field.get(this);
			} catch (Exception e) {
				String msg = e.getMessage();
				LOG.info(msg);
			}
			if(field.isAnnotationPresent(ToString.class) && val != null){

				if(field.getAnnotation(ToString.class).upperCase()){
					val = val.toString().toUpperCase();
				}
				strBuilder.append(field.getAnnotation(ToString.class).template().replaceAll("##", val.toString()));
			}
		}
		return strBuilder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.nom).append(this.prix).append(this.code).append(this.categorie).append(this.id).toHashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pizza)){
			return false;
		}

		Pizza other = (Pizza)o;
		EqualsBuilder builder = new EqualsBuilder();
		builder.append(this.code, other.getCode()).append(this.nom, other.getNom()).append(this.prix, other.getPrix()).append(this.categorie, other.getCategorie());
		return builder.isEquals();
	}

	/**
	 * Getter.
	 * 
	 * @return the currentGlobalId
	 */
	public static int getCurrentGlobalId() {
		return currentGlobalId;
	}

	/**
	 * Setter.
	 * 
	 * @param currentGlobalId
	 *            the currentGlobalId to set
	 */
	public static void setCurrentGlobalId(int currentGlobalId) {
		Pizza.currentGlobalId = currentGlobalId;
	}

	/**
	 * Getter.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter.
	 * 
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter.
	 * 
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter.
	 * 
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter.
	 * 
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter.
	 * 
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Setter.
	 * 
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * @return the categorie
	 */
	public CategoriePizza getCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

}
