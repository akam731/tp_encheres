package fr.eni.eni_encheres.bo;

/**
 * Classe modélisant une catégorie
 * @date 29/01/2024
 * @version Projet Enchères - v1.0
 * @author Amélie DUCASSE
 */
public class Categorie {

	/* ********* *
	 * ATTRIBUTS *
	 * ********* */
	private int noCategorie;
	private String libelle;
	
	/* ************* *
	 * CONSTRUCTEURS *
	 * ************* */
	public Categorie() {
		super();
	}

	public Categorie( String libelle ) {
		super();
		this.libelle 	 = libelle;
	}
	
	public Categorie( int noCategorie, String libelle ) {
		this( libelle );
		this.noCategorie = noCategorie;
	}
	
	/* ***************** *
	 * GETTERS / SETTERS *
	 * ***************** */
	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie( int noCategorie ) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}
	public void setLibelle( String libelle ) {
		this.libelle = libelle;
	}

	/*
	 * Pour test :
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + "]";
	}
}
