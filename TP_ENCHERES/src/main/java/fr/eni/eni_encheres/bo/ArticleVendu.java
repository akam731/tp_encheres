package fr.eni.eni_encheres.bo;

import java.util.Date;

/**
 * Classe modélisant un article vendu
 * @date 29/01/2024
 * @version Projet Enchères - v1.0
 * @author Amélie DUCASSE
 */
public class ArticleVendu {

	/* ********* *
	 * ATTRIBUTS *
	 * ********* */
	private int noArticle;
	private int miseAPrix;
	private int prixVente;
	
	private boolean etatVente;
	
	private String nomArticle;
	private String description;
	
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	
	private Utilisateur noUtilisateur;
	private Categorie noCategorie;
	
	/* ************* *
	 * CONSTRUCTEURS *
	 * ************* */
	public ArticleVendu() {
		super();
	}

	public ArticleVendu( int noArticle, String nomArticle, String description, Date dateDebutEncheres,
						 Date dateFinEncheres, Utilisateur noUtilisateur, boolean etatVente, Categorie noCategorie ) {
		super();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.noUtilisateur = noUtilisateur;
		this.etatVente = etatVente;
		this.noCategorie = noCategorie;
	}

	public ArticleVendu( int noArticle, String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres, 
						 Utilisateur noUtilisateur, boolean etatVente, Categorie noCategorie, int miseAPrix, int prixVente ) {
		this( noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, noUtilisateur, etatVente, noCategorie );
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
	}

	/* ***************** *
	 * GETTERS / SETTERS *
	 * ***************** */
	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle( int noArticle ) {
		this.noArticle = noArticle;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	public boolean isEtatVente() {
		return etatVente;
	}
	public void setEtatVente(boolean etatVente) {
		this.etatVente = etatVente;
	}

	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	
	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	
	public Utilisateur getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(Utilisateur noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public Categorie getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(Categorie noCategorie) {
		this.noCategorie = noCategorie;
	}

	/* Pour test :
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", miseAPrix=" + miseAPrix + ", prixVente=" + prixVente
				+ ", etatVente=" + etatVente + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres
				+ ", noUtilisateur=" + noUtilisateur + ", noCategorie=" + noCategorie + "]";
	}
}
