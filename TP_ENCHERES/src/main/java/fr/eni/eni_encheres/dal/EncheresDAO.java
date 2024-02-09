package fr.eni.eni_encheres.dal;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Utilisateur;

public interface EncheresDAO {
	
	public Utilisateur setNewUser(Utilisateur utilisateur) throws BusinessException;
	public Boolean isPseudoExiste(String pseudo) throws BusinessException;
	public Boolean isEmailExiste(String email) throws BusinessException;
	public Utilisateur getUserById(int id) throws BusinessException;
	public Utilisateur getUserByPseudo(String pseudo) throws BusinessException;
	public void deleteUser(int userID) throws BusinessException;
	public Utilisateur editUserById(Utilisateur utilisateur, int id) throws BusinessException;
	
}
