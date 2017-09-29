package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Usuario;

public class UsuarioDAO{
    
            public void create(Usuario usu){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("insert into usuario (login,nome,senha) values (?,?,?)");
            stmt.setString(1, usu.getLogin());
            stmt.setString(2, usu.getNome());
            stmt.setString(3, usu.getSenha());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "salvo");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "erro" + ex);
        }finally{
            ConnectionFactory.closeConnection(connection,stmt);
        }   
        }
            
        public boolean verificaLogin(Usuario usu){
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
                try {
                    stmt = connection.prepareStatement("select usuario.login, usuario.senha from usuario where usuario.login= '" + usu.getLogin().toString() + "' AND usuario.senha = '" + usu.getSenha().toString()+"'");
                        
                    ResultSet rs = stmt.executeQuery();
                    
                    if (rs != null && rs.next()){
                        JOptionPane.showMessageDialog(null, "login feito com sucesso!");
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(null, "login incorreto!");
                        return false;
                    }
                    
                    
                    
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "erro" + ex);
                    return false;
                }finally{
                    ConnectionFactory.closeConnection(connection,stmt);
                }   
        }

        public int getId_usuario(String login, String senha){
            int retorno = 0;
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
                try {
                    stmt = connection.prepareStatement("select usuario.id_usuario from usuario where usuario.login = '" +login + "' and usuario.senha = '" + senha + "';");
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next()){
                        retorno = (int) rs.getObject(1);
                    }
                    
                    return retorno;
                    
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "erro" + ex);
                    return 0;
                }finally{
                    ConnectionFactory.closeConnection(connection,stmt);
                }   
        }
}
