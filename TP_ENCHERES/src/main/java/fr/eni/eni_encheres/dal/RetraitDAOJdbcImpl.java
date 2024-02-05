package fr.eni.eni_encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements RetraitDAO{
	
	private static final String INSERT_RETRAIT =  "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (?, ?, ?, ?)";
	
	@Override
	public Retrait insertRetrait(Retrait retrait) throws BusinessException {
		
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement ps = cnx.prepareStatement(INSERT_RETRAIT,PreparedStatement.RETURN_GENERATED_KEYS);){
			
			try {
				
				ps.setInt(1, retrait.getNoArticle());
				ps.setString(2, retrait.getRue());
				ps.setString(3, retrait.getCodePostal());
				ps.setString(4, retrait.getVille());
				
		        int nbRows = ps.executeUpdate();
		        ResultSet rs = null;
				if ( nbRows == 1 ) 
				{
	                 rs = ps.getGeneratedKeys();

	                if ( rs.next() )
	                {
	                	retrait.setNoArticle(rs.getInt(1));
	                }

	                rs.close();
				}
				return retrait;
			}catch (Exception e) {
				retrait.setVille("ERREUR_WRONG_FORMAT");
				return retrait;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Retrait> selectAllRetrait() throws BusinessException {
		return null;
	}

	@Override
	public Retrait selectRetraitById(int noRetrait) throws BusinessException {
		return null;
	}

	@Override
	public void deleteRetrait(int noRetrait) throws BusinessException {
		
	}

	@Override
	public void updateRetrait(Retrait retrait) throws BusinessException {
		
	}


	
}
