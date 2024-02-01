package fr.eni.eni_encheres.dal;

import java.util.List;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Categorie;

public interface CategorieDAO {

	public void insertCategorie(Categorie categorie) throws BusinessException;
	
	public List<Categorie> selectAllCategories() throws BusinessException;
	public Categorie selectCategorieById(int noCategorie) throws BusinessException;
	
	public void updateCategorie(Categorie categorie) throws BusinessException;
	
	public void deleteCategorie(int noCategorie) throws BusinessException;
	
	
}
