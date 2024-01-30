package fr.eni.eni_encheres.bll;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Utilisateur;
import fr.eni.eni_encheres.dal.DAOFactory;
import fr.eni.eni_encheres.dal.EncheresDAO;

public class EniEnchereManager {
	
	private EncheresDAO enchereDAO;
	
	public EniEnchereManager() {
		this.enchereDAO = DAOFactory.getEncheresDAO();
	}
	
	public Utilisateur setNewUser(String pseudo,String nom,String prenom,String mail,String tel,String rue,String codePostal,String ville,String motDePasse) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		
		Utilisateur utilisateur = null;
		
		if(!businessException.hasErreurs()) {
			
			
			utilisateur = new Utilisateur();
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setPseudo(pseudo);
			utilisateur.setEmail(mail);
			utilisateur.setTelephone(tel);
			utilisateur.setRue(rue);
			utilisateur.setCodePostal(codePostal);
			utilisateur.setVille(ville);
			utilisateur.setMotDePasse(motDePasse);
			utilisateur.setAdministrateur(false);
			utilisateur.setCredit(0);
			
			this.enchereDAO.setNewUser(utilisateur);
		}else {
			throw businessException;
		}
		return utilisateur;
		
	}
	
}
