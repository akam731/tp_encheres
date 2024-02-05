package fr.eni.eni_encheres.bo;

import java.util.Date;
import java.util.List;

import org.apache.catalina.User;

/**
 * Classe modélisant une enchère
 * @date 29/01/2024
 * @version Projet Enchères - v1.0
 * @author Amélie DUCASSE
 */
public class Enchere {

	/* ********* *
	 * ATTRIBUTS *
	 * ********* */
	private int noEnchere;
	private int montantEnchere;
	
	private Date dateEnchere;
	private Utilisateur noUtilisateur;
	private ArticleVendu noArticle;
	
	/* ************* *
	 * CONSTRUCTEURS *
	 * ************* */
	public Enchere() {
		super();
	}

	public Enchere( int noEnchere, Date dateEnchere, int montantEnchere, Utilisateur noUtilisateur, ArticleVendu noArticle ) {
		super();
		this.noEnchere 		= noEnchere;
		this.dateEnchere 	= dateEnchere;
		this.montantEnchere = montantEnchere;
		this.noUtilisateur 	= noUtilisateur;
		this.noArticle 		= noArticle;
	}

	/* ***************** *
	 * GETTERS / SETTERS *
	 * ***************** */
	public int getNoEnchere() {
		return noEnchere;
	}
	public void setNoEnchere( int noEnchere ) {
		this.noEnchere = noEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere( int montantEnchere ) {
		this.montantEnchere = montantEnchere;
	}

	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere( Date dateEnchere ) {
		this.dateEnchere = dateEnchere;
	}

	public Utilisateur getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur( Utilisateur noUtilisateur ) {
		this.noUtilisateur = noUtilisateur;
	}

	public ArticleVendu getNoArticle() {
		return noArticle;
	}
	public void setNoArticle( ArticleVendu noArticle ) {
		this.noArticle = noArticle;
	}

	/*
	 * Pour test :
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Enchere [noEnchere=" + noEnchere + ", montantEnchere=" + montantEnchere + ", dateEnchere=" + dateEnchere
				+ ", noUtilisateur=" + noUtilisateur + ", noArticle=" + noArticle + "]";
	}

	public static List<Enchere> getAllActiveEnchere() {
		return null;
	}

	public static List<Enchere> getUserParticipatedEnchere(User currentUser) {
		return null;
	}
	
}
