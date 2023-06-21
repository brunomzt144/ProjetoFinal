/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seboprojeto;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author bruno
 */
public class ControllerTelaAtualizaCliente  implements Initializable {
        @FXML
    private Label lnome;

    @FXML
    private Label ltelefone;

    @FXML
    private TextField txttelefone;

    @FXML
    private TextField txtnome;

    @FXML
    private Button btn_fecha;

    @FXML
    private Button btn_atualiza;
 
    
   @FXML
    private TextField txtcpf;
   
    @FXML
    private Label lcpf;
    
    
        @Override
     public void initialize(URL url, ResourceBundle rb){
     btn_atualiza.setOnAction(this::EfetuaAtualizacao);
     btn_fecha.setOnAction(this::fechar);
            }
    public void fechar(ActionEvent e){
         Stage stage = (Stage) btn_fecha.getScene().getWindow();
        stage.close();
      }
 public void EfetuaAtualizacao(ActionEvent e){
     try{
        
     Cliente c = new Cliente();
     DAOCliente dao = new DAOCliente();
     c.setNome(txtnome.getText());
     c.setTelefone(txttelefone.getText());
     c.setCpf(txtcpf.getText());
     
     dao.AlteraCliente(c);
     Stage stage = (Stage) btn_atualiza.getScene().getWindow();
        stage.close();
     }catch(Exception exp){

 }
 }    
     
     
     
     
     
     
 public void CarregaDados(Cliente c, ControllerTelaAtualizaCliente controller) {
    try {
        String select = "SELECT * FROM CLIENTE WHERE CLIENTEID = ?";
        Connection con = FabricaConexao.getConexao();
        PreparedStatement pstm = con.prepareStatement(select);
        pstm.setInt(1, c.getId());

        ResultSet rs = pstm.executeQuery();

        if (rs.next()) {
            Cliente cliente = new Cliente();
            cliente.setId(rs.getInt("clienteid"));
            cliente.setNome(rs.getString("clientenome"));
            cliente.setTelefone(rs.getString("clientetelefone"));
            cliente.setCpf(rs.getString("clientecpf"));
            controller.txtnome.setText(cliente.getNome());
            controller.txttelefone.setText(cliente.getTelefone());
            controller.txtcpf.setText(cliente.getCpf());
        } else {
            System.out.println("Nenhum cliente encontrado.");
        }

        rs.close();
        pstm.close();
        con.close();
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
 }
}

