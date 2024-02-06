package fr.eni.eni_encheres.bll;

import java.util.List;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Categorie;
import fr.eni.eni_encheres.dal.CategorieDAO;
import fr.eni.eni_encheres.dal.DAOFactory;

public class CategorieManager {

	private static CategorieDAO catDAO;
	
	/* CONSTRUCTEUR */
	public CategorieManager() throws BusinessException {
		catDAO = DAOFactory.getCategorieDAO();
	}
	
	/* GETTER */
	/**
	 * Récupère la liste des catégories
	 * @return listeCategories : List<Categorie>
	 * @throws BusinessException
	 */
	public List<Categorie> getListeCategorie() throws BusinessException {
		List<Categorie> listeCategories = null;
		
		try {
			listeCategories = catDAO.selectAllCategories();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("Erreur récupération liste des catégories");
		}
		return listeCategories;
	}
}
