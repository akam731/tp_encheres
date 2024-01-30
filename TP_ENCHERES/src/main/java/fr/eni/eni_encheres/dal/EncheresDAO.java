package fr.eni.eni_encheres.dal;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Utilisateur;

public interface EncheresDAO {
	
	public void setNewUser(Utilisateur utilisateur) throws BusinessException;
	public Boolean isColloneExiste(String colonne, String value) throws BusinessException;
	public Utilisateur getUserBy(String colonne, String value) throws BusinessException;
	
}
