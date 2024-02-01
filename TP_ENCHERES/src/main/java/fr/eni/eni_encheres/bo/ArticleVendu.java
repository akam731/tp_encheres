package fr.eni.eni_encheres.bo;


import java.time.LocalDateTime;


/**
 * Classe modélisant un article vendu
 * @date 29/01/2024
 * @version Projet Enchères - v1.0
 * @author Amélie DUCASSE
 */
public class ArticleVendu extends Retrait {

	/* ********* *
	 * ATTRIBUTS *
	 * ********* */
	private Integer noArticle;
	private int miseAPrix;
	private int prixVente;
	
	private boolean etatVente;
	
	private String nomArticle;
	private String description;
	
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	
	private int noUtilisateur;
	private int noCategorie;
	
	/* ************* *
	 * CONSTRUCTEURS *
	 * ************* */
	public ArticleVendu() {
		super();
	}
	
	public ArticleVendu( String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, int noUtilisateur, boolean etatVente, int noCategorie ) {

		super();
		this.nomArticle = nomArticle;
		this.description 	  = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.noUtilisateur = noUtilisateur;
		this.etatVente = etatVente;
		this.noCategorie = noCategorie;
	}


//	public ArticleVendu( int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres,
//			LocalDateTime dateFinEncheres, Utilisateur noUtilisateur, boolean etatVente, Categorie noCategorie ) {
//		this( nomArticle, description, dateDebutEncheres, dateFinEncheres, noUtilisateur, etatVente, noCategorie );
//		this.noArticle = noArticle;//
//	}


//		public ArticleVendu( int noArticle, String nomArticle, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres, 
//						 Utilisateur noUtilisateur, boolean etatVente, Categorie noCategorie, int miseAPrix, int prixVente ) {
//		this( noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, noUtilisateur, etatVente, noCategorie );
//		this.miseAPrix = miseAPrix;
//		this.prixVente = prixVente;
//	}

	/* ***************** *
	 * GETTERS / SETTERS *
	 * ***************** */
	

	public void setNoArticle( Integer noArticle ) {
		this.noArticle = noArticle;
	}

//	public Integer getNoArticle() {
//		return noArticle;
//	}

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
	
	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}

	public void setDateDebutEncheres(LocalDateTime debutEncheres) {
		this.dateDebutEncheres = debutEncheres;
	}

	

	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}

	
	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;

	}
	
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
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
