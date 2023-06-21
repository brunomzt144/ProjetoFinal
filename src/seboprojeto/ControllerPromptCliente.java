/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seboprojeto;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author bruno
 */
public class ControllerPromptCliente implements Initializable{
    @FXML
    private Button btn_seleciona;
    
      @FXML
    private TableColumn<Cliente, String> tableclientenome;
      
    @FXML
    private TableColumn<Cliente, String> tableclienteid;

    @FXML
    private TableColumn<Cliente, String> tableclientefone;
    
     @FXML
    private TableColumn<Cliente, String> tabelaclientecpf;
     private Cliente clienteSelecionado;
    
     @FXML
    private TableView<Cliente> tabelaCliente;
    private ControllerTelaVenda telaVendaController;
     
   
        @Override
     public void initialize(URL url, ResourceBundle rb) {
         CarregaDados();
         btn_seleciona.setOnAction(this::Selecionar);
        tableclientenome.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNome()));
        tabelaclientecpf.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCpf()));
        tableclientefone.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTelefone()));
        tableclienteid.setCellValueFactory(param -> {
           Integer id = param.getValue().getId();
           return new SimpleStringProperty(String.valueOf(id));
             });  
     }
        public void Selecionar(ActionEvent e){
        clienteSelecionado = tabelaCliente.getSelectionModel().getSelectedItem(); 
        if (telaVendaController != null && clienteSelecionado != null) {
              telaVendaController.setTxtCliente(clienteSelecionado.getId());
      }
          Stage stage = (Stage) btn_seleciona.getScene().getWindow();
            stage.close();
    
    }
   public void setTelaVendaController(ControllerTelaVenda controller){
       this.telaVendaController = controller;
   }     
        
         public void CarregaDados()  {
        try{
            Connection con = FabricaConexao.getConexao();
            Statement pstm = con.createStatement();
            ResultSet rs = pstm.executeQuery("SELECT * FROM cliente");
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("clienteid"));
                cliente.setNome(rs.getString("clientenome"));
                cliente.setTelefone(rs.getString("clientetelefone"));
                cliente.setCpf(rs.getString("clientecpf"));
                tabelaCliente.getItems().add(cliente);
              

            }
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Nao carregou dados");
        }
       }
}
