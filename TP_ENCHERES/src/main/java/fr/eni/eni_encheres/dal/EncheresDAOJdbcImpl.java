package fr.eni.eni_encheres.dal;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.PStmtKey;

import fr.eni.courses.dal.ConnectionProvider;
import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Utilisateur;

public class EncheresDAOJdbcImpl implements EncheresDAO{
	
	private final String AJOUT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	
	@Override	
	public void setNewUser(Utilisateur utilisateur) throws BusinessException{

		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(AJOUT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS)){	
			
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			if(rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
