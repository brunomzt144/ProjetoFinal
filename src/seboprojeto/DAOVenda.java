    
package seboprojeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DAOVenda {
    final String INSERT_ITENS   = "INSERT INTO ITENSVENDA(produtoid,vendaid,produtoquantidade,produtodesc) VALUES(?,?,?,?)";
    final String INSERT_VENDA = "INSERT INTO VENDA (VALORTOTAL,CLIENTEID,CLIENTENOME) VALUES(?,?,?);";
    final String UPDATEESTOQUE = "UPDATE PRODUTO SET QUANTIDADE = ? WHERE PRODUTOID = ?";

    
    
    public void MovtaEstoque(ArrayList<Produto> lista){
        int qtde;
        int id;
         for(Produto p : lista){
            if(p !=null){
                Connection con = null;
                try{
                        con = FabricaConexao.getConexao();
                        PreparedStatement statement;       
                         Statement pstm = con.createStatement();
                         ResultSet rs = pstm.executeQuery("select produtoid, quantidade from produto where produtoid ="+p.getId()); 
                         while (rs.next()) {
                                id = rs.getInt("produtoid");
                                qtde = rs.getInt("quantidade");
                                System.out.println("Qtde atual"+qtde);
                                System.err.println("Id atual"+id);
                                qtde -= p.getQuantidade();
                                System.out.println("qtde movta"+qtde);
                                 PreparedStatement pstmMovto;
                                 pstmMovto = con.prepareStatement("UPDATE PRODUTO SET QUANTIDADE = ? WHERE PRODUTOID =?"); 
                                 pstmMovto.setInt(1, qtde);
                                 pstmMovto.setInt(2, id);
                                 pstmMovto.execute();
                           }       
                }
                catch(Exception e){
                    e.printStackTrace();
                 }
            }
         }
    }
    public void IncluiVenda(ArrayList<Cliente> listaClientes, float total){
        for(Cliente cliente : listaClientes){
            if(cliente !=null){
                Connection con = null;
                try{
                      con = FabricaConexao.getConexao();
                      PreparedStatement pstm;
                      pstm = con.prepareStatement(INSERT_VENDA);
                      pstm.setFloat(1, total);
                      pstm.setInt(2, cliente.getId());
                      pstm.setString(3, cliente.getNome());
                      pstm.execute();
                        JOptionPane.showMessageDialog(null, "Venda feita com sucesso!!");
                }catch(ClassNotFoundException | SQLException e){
                      JOptionPane.showMessageDialog(null, "Erro ao incluir venda!");
                }
            }
        }
    }
            
    
    
    public void IncluiItens(ArrayList<Produto> lista){
        int id = 0;
        for (Produto produto : lista) {
             if(produto!=null){
                    Connection con = null;
                    try{
                            con = FabricaConexao.getConexao();
                            PreparedStatement statement;
                                
                         Statement pstm = con.createStatement();
                         ResultSet rs = pstm.executeQuery("select vendaid from venda order by vendaid desc limit 1");
                           while (rs.next()) {
                                id = rs.getInt("vendaid");
                           }       
                            statement = con.prepareStatement(INSERT_ITENS);
                            statement.setInt(1, produto.getId());
                            statement.setInt(2, id);
                            statement.setInt(3, produto.getQuantidade());
                            statement.setString(4, produto.getTitulo());
                            statement.execute();
                
                     }catch(ClassNotFoundException | SQLException e){
                          JOptionPane.showMessageDialog(null, "Não foi possivel incluir um item!");
                    }
    }
    }
}
    

    
}
