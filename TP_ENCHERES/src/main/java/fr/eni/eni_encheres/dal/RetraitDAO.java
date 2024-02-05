package fr.eni.eni_encheres.dal;

import java.util.List;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Retrait;

public interface RetraitDAO {

	
	public Retrait insertRetrait(Retrait retrait) throws BusinessException;
	public List<Retrait> selectAllRetrait() throws BusinessException;
	public  Retrait selectRetraitById(int noRetrait)throws BusinessException;
	public void deleteRetrait(int noRetrait)throws BusinessException;
	public void updateRetrait(Retrait retrait)throws BusinessException;
	
}
