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
import model.bean.Receita;
import model.bean.Usuario;

public class ReceitaDAO {
    
        public void create(Receita rec){
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
            try {
                stmt = connection.prepareStatement("insert into receita (nome,ingredientes, receita, id_usuario) values (?,?,?,?)");
                stmt.setString(1, rec.getNome());
                stmt.setString(2, rec.getIngrdientes());
                stmt.setString(3, rec.getReceita());
                stmt.setInt(4, rec.getId_usuario());
            
                stmt.executeUpdate();
            
                JOptionPane.showMessageDialog(null, "salvo");
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "erro" + ex);
            }finally{
                ConnectionFactory.closeConnection(connection,stmt);
            }   
        }
        public String select(String id, String coluna){
            String retorno=null;
            int numColuna = 1;
            if (coluna.equals("nome")){
                numColuna = 3;
            }else if(coluna.equals("ingradientes")){
                numColuna = 5;
            }else if(coluna.equals("receita")){
                numColuna = 2;
            }else if(coluna.equals("id_receita")){
                numColuna = 4;
            }
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
                try {
                    stmt = connection.prepareStatement("select receita."+coluna+" from receita where receita.id_receita = " +id + ";");
                    ResultSet rs = stmt.executeQuery();
                    while(rs.next()){
                        retorno = (String) rs.getObject(1);
                    }
                    
                    return retorno;
                    
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "erro" + ex);
                    return null;
                }finally{
                    ConnectionFactory.closeConnection(connection,stmt);
                }   
        }
        
        public ArrayList<String> select(String nome){
            ArrayList<String>retorno = new ArrayList <String> ();
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
                try {
                    stmt = connection.prepareStatement("select receita.nome, usuario.nome, coalesce(round(avg(nota_rec.nota),2),0) nota "
                            + "from receita left join usuario on usuario.id_usuario = receita.id_usuario left join nota_rec on nota_rec.id_receita = receita.id_receita "
                            + "where usuario.nome like '%"+nome+"%' group by receita.id_receita;");
                        
                    ResultSet rs = stmt.executeQuery();
                    for(int i = 0; rs.next(); i++){ 
                    //while(rs.next()){
                        
                        retorno.add( rs.getObject(1) + ";" + (String) rs.getObject(2) + ";" + rs.getObject(3).toString() );
                        
                    }
                    if (retorno.isEmpty())
                        JOptionPane.showMessageDialog(null, "Nenhum Resultado Encontrado.");
                    return retorno;
                    
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "erro" + ex);
                    return null;
                }finally{
                    ConnectionFactory.closeConnection(connection,stmt);
                }   
        }
        public ArrayList<String> selectRec(String nome){
            ArrayList<String>retorno = new ArrayList <String> ();
            Connection connection = ConnectionFactory.getConnection();
            PreparedStatement stmt = null;
                try {
                    stmt = connection.prepareStatement("select receita.nome, usuario.nome, coalesce(round(avg(nota_rec.nota),2),0) nota, receita.id_receita  "
                            + "from receita left join usuario on usuario.id_usuario = receita.id_usuario left join nota_rec on nota_rec.id_receita = receita.id_receita "
                            + "where receita.nome like '%"+nome+"%' group by receita.id_receita;");
                        
                    ResultSet rs = stmt.executeQuery();
                    for(int i = 0; rs.next(); i++){ 
                    //while(rs.next()){
                        
                        retorno.add( rs.getObject(2) + ";" + (String) rs.getObject(1) + ";" + rs.getObject(4).toString() + ";" + rs.getObject(3).toString() );
                        
                    }
                    if (retorno.isEmpty())
                        JOptionPane.showMessageDialog(null, "Nenhum Resultado Encontrado.");
                    return retorno;
                    
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "erro" + ex);
                    return null;
                }finally{
                    ConnectionFactory.closeConnection(connection,stmt);
                }   
        }
    
}
