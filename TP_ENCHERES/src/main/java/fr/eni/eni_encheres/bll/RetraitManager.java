package fr.eni.eni_encheres.bll;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Retrait;
import fr.eni.eni_encheres.dal.DAOFactory;
import fr.eni.eni_encheres.dal.RetraitDAO;

public class RetraitManager {

	private static RetraitDAO retraitDAO;
	
	public RetraitManager() {
		RetraitManager.retraitDAO = (RetraitDAO) DAOFactory.getRetraitDAO();
	}
	
	public void updateRetraitById(Retrait retrait) throws BusinessException{
		retraitDAO.updateRetrait(retrait);
	}

	public void deleteRetrait(int id) throws BusinessException{
		retraitDAO.deleteRetrait(id);
	}
	
	public Retrait selectRetraitById(int noRetrait) throws BusinessException {
	
		BusinessException business = new BusinessException();
		
		Retrait retrait = new Retrait();
		
		if(!business.hasErreurs()) {
			retrait = retraitDAO.selectRetraitById(noRetrait);
		}else {
			throw business;
		}
        
        return retrait;
		
	}
	
	public Retrait insertRetrait(int noArticle, String rue, String codePostal, String ville) throws BusinessException {
		
		BusinessException business = new BusinessException();
		
		Retrait retrait = new Retrait();
		
		if(!business.hasErreurs()) {
			
			retrait.setNoArticle(noArticle);
			retrait.setRue(rue);
			retrait.setCodePostal(codePostal);
			retrait.setVille(ville);
            
			retrait = retraitDAO.insertRetrait(retrait);
           
            
		}else {
			throw business;
		}
		
		return retrait;
	}
	
}
