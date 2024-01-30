package fr.eni.eni_encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Utilisateur;

public class EncheresDAOJdbcImpl implements EncheresDAO{
	
	private final String AJOUT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private final String IS_EXISTE = "SELECT COUNT(*) FROM utilisateurs WHERE ";
	private final String SELECT_USER_BY = "SELECT * FROM utilisateurs WHERE ";
	private final String DELETE_USER_BY_ID = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";

	public void deleteUser(int userId) throws BusinessException{
		
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			try(PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER_BY_ID)){
				pstmt.setInt(1, userId);
				pstmt.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public Utilisateur getUserBy(String colonne, String value) throws BusinessException{
		
		String sql = SELECT_USER_BY + colonne + " = ?";
		
		Utilisateur utilisateur = new Utilisateur();
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			try(PreparedStatement pstmt = cnx.prepareStatement(sql)){
				pstmt.setString(1, value);
				try(ResultSet rs = pstmt.executeQuery()){
					if(rs.next()) {
						utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
						utilisateur.setPseudo(rs.getString("pseudo"));
						utilisateur.setNom(rs.getString("nom"));
						utilisateur.setPrenom(rs.getString("prenom"));
						utilisateur.setEmail(rs.getString("email"));
						utilisateur.setTelephone(rs.getString("telephone"));
						utilisateur.setRue(rs.getString("rue"));
						utilisateur.setCodePostal(rs.getString("code_postal"));
						utilisateur.setVille(rs.getString("ville"));
						utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
						utilisateur.setCredit(rs.getInt("credit"));
						utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
					}
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return utilisateur;
	}
	
	public Boolean isColloneExiste(String collone, String value) throws BusinessException{
		
		String sql = IS_EXISTE + collone + " = ?;";
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			try (PreparedStatement pstmt = cnx.prepareStatement(sql)){
				pstmt.setString(1, value);
				try(ResultSet rs = pstmt.executeQuery();){
					if(rs.next()) {
						return rs.getInt(1) > 0;
					}
				}
			}
														
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
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
			e.printStackTrace();
		}
	}
}
