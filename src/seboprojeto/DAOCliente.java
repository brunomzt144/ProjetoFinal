
package seboprojeto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DAOCliente {
    private final String INSERT = "INSERT INTO CLIENTE(CLIENTENOME,CLIENTETELEFONE,CLIENTECPF) VALUES(?,?,?)";
    private final String UPDATE = "UPDATE CLIENTE SET CLIENTENOME = ?, CLIENTETELEFONE = ? WHERE CLIENTECPF = ?";
  
    private final String DELETE = "DELETE FROM CLIENTE WHERE CLIENTEID = ?";
    
    
    public void DeleteCliente(Cliente c){
              if (c!=null){
            Connection con = null;
            try{
                 con = FabricaConexao.getConexao();
                 PreparedStatement pstm;
                 pstm = con.prepareStatement(DELETE);
                 pstm.setInt(1, c.getId());
                 pstm.execute();
                   JOptionPane.showMessageDialog(null, "Excluiu cliente com sucesso");
            }catch(ClassNotFoundException | SQLException ex){
                  JOptionPane.showMessageDialog(null, "Erro na exclusão de cliente");
            }
           }
    }
    
    
    public void InserirCliente(Cliente c){
        if (c!=null){
            Connection con = null;
            try{
                 con = FabricaConexao.getConexao();
                 PreparedStatement pstm;
                 pstm = con.prepareStatement(INSERT);
                 pstm.setString(1, c.getNome());
                 pstm.setString(2, c.getTelefone());
                 pstm.setString(3, c.getCpf());
                 
                 pstm.execute();
                   JOptionPane.showMessageDialog(null, "Cadastro de Cliente feito com sucesso");
            }catch(ClassNotFoundException | SQLException e){
                 JOptionPane.showMessageDialog(null, "Erro ao inserir Cliente no banco de dados "
                        + e.getMessage());
            }
        }
    }
    
    public void AlteraCliente(Cliente c){
            if (c!=null){
            Connection con = null;
            try{
                 con = FabricaConexao.getConexao();
                 PreparedStatement pstm;
                 pstm = con.prepareStatement(UPDATE);
                 pstm.setString(1, c.getNome());
                 pstm.setString(2, c.getTelefone());
                 pstm.setString(3, c.getCpf());
                 pstm.execute();
                   JOptionPane.showMessageDialog(null, "Atualização de cliente feita com sucesso");
            }catch(ClassNotFoundException | SQLException e){
                 JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente");
            }
    }
}
}
