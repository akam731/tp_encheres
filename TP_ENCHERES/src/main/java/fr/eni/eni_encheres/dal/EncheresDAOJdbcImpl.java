package fr.eni.eni_encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.eni_encheres.BusinessException;
import fr.eni.eni_encheres.bo.Utilisateur;

public class EncheresDAOJdbcImpl implements EncheresDAO{
	
	private final String AJOUT_UTILISATEUR = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private final String IS_EMAIL_EXISTE = "SELECT COUNT(*) FROM utilisateurs WHERE email = ?,";
	private final String IS_PSEUDO_EXISTE = "SELECT COUNT(*) FROM utilisateurs WHERE pseudo = ?";
	private final String SELECT_USER_BY_ID = "SELECT * FROM utilisateurs WHERE no_utilisateur = ? ";
	private final String SELECT_USER_BY_PSEUDO = "SELECT * FROM utilisateurs WHERE pseudo = ?";
	private final String DELETE_USER_BY_ID = "DELETE FROM UTILISATEURS WHERE no_utilisateur = ?";
	private final String EDIT_USER_BY_ID = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone = ?, rue = ?, code_postal = ?, ville = ?, mot_de_passe = ?, credit = ?, administrateur = ? WHERE no_utilisateur = ?";

	
	public Utilisateur editUserById(Utilisateur user, int id) throws BusinessException{
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			try(PreparedStatement pstmt = cnx.prepareStatement(EDIT_USER_BY_ID)){
				
				

				try {
				pstmt.setString(1, user.getPseudo());
				pstmt.setString(2, user.getNom());
				pstmt.setString(3, user.getPrenom());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getTelephone());
				pstmt.setString(6, user.getRue());
				pstmt.setString(7, user.getCodePostal());
				pstmt.setString(8, user.getVille());
				pstmt.setString(9, user.getMotDePasse());
				pstmt.setInt(10, user.getCredit());
				pstmt.setBoolean(11, user.isAdministrateur());
				
				pstmt.setInt(12, id);
				pstmt.executeUpdate();
				
				user.setNoUtilisateur(id);
				}catch (Exception e) {
					user.setPseudo("ERREUR_WRONG_FORMAT");
				}
				return user;
				
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	
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

	public Utilisateur getUserById(int id) throws BusinessException{
		Utilisateur utilisateur = new Utilisateur();
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_ID)){
				pstmt.setInt(1, id);
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
	public Utilisateur getUserByPseudo(String pseudo) throws BusinessException{
		Utilisateur utilisateur = new Utilisateur();
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			try(PreparedStatement pstmt = cnx.prepareStatement(SELECT_USER_BY_PSEUDO)){
				pstmt.setString(1, pseudo);
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
	

	public Boolean isPseudoExiste(String pseudo) throws BusinessException{
		try(Connection cnx = ConnectionProvider.getConnection()){
					
					try (PreparedStatement pstmt = cnx.prepareStatement(IS_PSEUDO_EXISTE)){
						pstmt.setString(1, pseudo);
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
	public Boolean isEmailExiste(String email) throws BusinessException{
		
		try(Connection cnx = ConnectionProvider.getConnection()){
			
			try (PreparedStatement pstmt = cnx.prepareStatement(IS_EMAIL_EXISTE)){
				pstmt.setString(1, email);
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
	public Utilisateur setNewUser(Utilisateur utilisateur) throws BusinessException{

		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(AJOUT_UTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS)){	
			try {
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
			}catch (Exception e) {
				utilisateur.setPseudo("ERREUR_WRONG_FORMAT");
			}
			return utilisateur;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return utilisateur;
	}
}
