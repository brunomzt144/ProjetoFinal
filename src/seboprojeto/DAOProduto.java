
package seboprojeto;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DAOProduto {
     private final String INSERT = "INSERT INTO PRODUTO (TITULO,NPAGINAS,VALOR,EDITORA,QUANTIDADE)VALUES(?,?,?,?,?)";
     private final String UPDATE  = "UPDATE PRODUTO SET TITULO = ?,NPAGINAS = ?,VALOR = ?,EDITORA = ?, QUANTIDADE =? WHERE PRODUTOID = ?";
     private final String DELETE = "DELETE FROM PRODUTO WHERE PRODUTOID = ?";
     
         public void ExcluiProduto(Produto p){
         if(p!=null){
          Connection con = null;
             try{
                 con = FabricaConexao.getConexao();
                 PreparedStatement pstm;
                 pstm = con.prepareStatement(DELETE);
                pstm.setInt(1, p.getId());
                 pstm.execute();  
                 JOptionPane.showMessageDialog(null, "Excluiu produto com sucesso");
             }catch(HeadlessException | ClassNotFoundException | SQLException e){
                   JOptionPane.showMessageDialog(null, "Erro na exclusão do produto");
             }
         }
         }
     
     
     
     public void AlteraProduto(Produto p){
         if(p!=null){
          Connection con = null;
             try{
                 con = FabricaConexao.getConexao();
                 PreparedStatement pstm;
                pstm = con.prepareStatement(UPDATE);
                pstm.setString(1, p.getTitulo());
                pstm.setInt(2,p.getnPaginas());
                pstm.setFloat(3, p.getValor());
                pstm.setString(4, p.getEditora());
                pstm.setInt(5, p.getQuantidade());
                pstm.setInt(6, p.getId());
                 System.out.println("Id do produo é : "+p.getId());
                pstm.execute();
                  JOptionPane.showMessageDialog(null, "Atualização de produto feito com sucesso");
             }catch(ClassNotFoundException | SQLException e){
                  JOptionPane.showMessageDialog(null, "Erro ao atualizar produto no banco de dados "
                        + e.getMessage());
                  e.printStackTrace();
             }
         }
     }
     
     
     
     
     public void InserirProduto(Produto p){
         
         if(p !=null){
             Connection con = null;
             try{
                 con = FabricaConexao.getConexao();
                 PreparedStatement pstm;
                pstm = con.prepareStatement(INSERT);
                pstm.setString(1, p.getTitulo());
                pstm.setInt(2,p.getnPaginas());
                pstm.setFloat(3, p.getValor());
                pstm.setString(4, p.getEditora());
                pstm.setInt(5, p.getQuantidade());
                pstm.execute();
                  JOptionPane.showMessageDialog(null, "Cadastro de produto feito com sucesso");
             }catch(ClassNotFoundException | SQLException e){
                  JOptionPane.showMessageDialog(null, "Erro ao inserir produto no banco de dados "
                        + e.getMessage());
             }
         }
     }
}
