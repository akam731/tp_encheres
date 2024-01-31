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
	
	public Utilisateur editUserById(String pseudo,String nom,String prenom,String mail,String tel,String rue,String codePostal,String ville,String motDePasse, int credits, Boolean admin, int id) throws BusinessException{
		
		BusinessException businessException = new BusinessException();
		
		Utilisateur utilisateur = null;
		
		if(!businessException.hasErreurs()){
			
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
			utilisateur.setAdministrateur(admin);
			utilisateur.setCredit(credits);
			
			utilisateur = this.enchereDAO.editUserById(utilisateur, id);
			return utilisateur;
			
		}else {
			throw businessException;
		}
		
	}
	
	public void deleteUserById(int id) throws BusinessException{

		BusinessException businessException = new BusinessException();
		
		if(!businessException.hasErreurs()) {
			
			this.enchereDAO.deleteUser(id);
			
		}else {
			throw businessException;
		}
		
	}
	
	public Utilisateur getUserBy(String colonne, String value) throws BusinessException{
		
		BusinessException businessException = new BusinessException();
		
		Utilisateur utilisateur = null;
		
		if(!businessException.hasErreurs()) {
			
			utilisateur = this.enchereDAO.getUserBy(colonne, value);
			
		}else {
			throw businessException;
		}
		return utilisateur;
	}
	
	public Boolean isColloneExiste(String collone, String value) throws BusinessException{
		return this.enchereDAO.isColloneExiste(collone, value);
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
			
			utilisateur = this.enchereDAO.setNewUser(utilisateur);
		}else {
			throw businessException;
		}
		return utilisateur;
		
	}
	
}
