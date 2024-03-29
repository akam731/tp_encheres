package fr.eni.eni_encheres.bo;

/**
 * Classe modélisant un retrait d'article
 * @date 29/01/2024
 * @version Projet Enchères - v1.0
 * @author Amélie DUCASSE
 */
public class Retrait {

	/* ********* *
	 * ATTRIBUTS *
	 * ********* */
	private Integer noArticle;
	
	private String rue;
	private String codePostal;
	private String ville; 
	
	/* ************* *
	 * CONSTRUCTEURS *
	 * ************* */
	public Retrait() {
		super();
	}

	public Retrait( Integer noArticle, String rue, String codePostal, String ville ) {
		super();
		this.noArticle 	= noArticle;
		this.rue 		= rue;
		this.codePostal = codePostal;
		this.ville 		= ville;
	}
	
	/**
	 * @param rue
	 * @param codePostal
	 * @param ville
	 */
	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	/* ***************** *
	 * GETTERS / SETTERS *
	 * ***************** */
	public Integer getNoArticle() {
		return noArticle;
	}
	public void setNoArticle( Integer noArticle ) {
		this.noArticle = noArticle;
	}

	public String getRue() {
		return rue;
	}
	public void setRue( String rue ) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal( String codePostal ) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}

	/* 
	 * Pour test :
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Retrait [noArticle=" + noArticle + ", rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville
				+ "]";
	}
	
}
