package fr.eni.eni_encheres.dal;

public abstract class CodesResultatDAL {
	/**
	 * Echec général quand erreur non gérée à l'insertion 
	 */
	public static final int INSERT_OBJET_ECHEC=10000;
	
	/**
	 * Echec général quand erreur non gérée à la selection/lecture de l'article 
	 */
	public static final int LECTURE_ARTICLE_ECHEC=10002;
	
	/**
	 * Echec quand erreur non gérée à la mis à jour d'un article en vente 
	 */
	public static final int MAJ_ARTICLE_ECHEC=10003;
	
	/**
	 * Echec quand erreur non gérée à la suppression d'un article en vente 
	 */
	public static final int SUPPRIMER_ARTICLE_ECHEC=10004;
	
}
