/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seboprojeto;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerCliente  implements Initializable{
    
    @FXML
    private TextField txtcpf;

    @FXML
    private Label lcpf;
    @FXML
    private Label lnome;

    @FXML
    private Label ltelefone;

    @FXML
    private TextField txttelefone;

    @FXML
    private Button btn_confirma;

    @FXML
    private TextField txtnome;
    
    @Override
       public void initialize(URL url, ResourceBundle rb){
           btn_confirma.setOnAction(this::InsereCliente);
      }
       public void InsereCliente(ActionEvent e) {
           Cliente cliente = new Cliente();
           cliente.setNome(txtnome.getText());
           cliente.setTelefone(txttelefone.getText());
           cliente.setCpf(txtcpf.getText());
           DAOCliente dao = new DAOCliente();
           System.out.println("Depois Dao nome: "+cliente.getNome());
          try{
          dao.InserirCliente(cliente);
          Stage stage = (Stage) btn_confirma.getScene().getWindow();
        stage.close();
          }catch(Exception exp){
              System.out.println("Deu errado cliente produto");
          }
       }
}
