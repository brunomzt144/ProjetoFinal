
package seboprojeto;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ControllerTelaCliente implements Initializable  {
        @FXML
    private Button btn_alteracliente;

    @FXML
    private Button btn_excluicliente;

    @FXML
    private TableColumn<Cliente, String> tableclientenome;

    @FXML
    private Button btn_incluicliente;

    @FXML
    private TableColumn<Cliente, String> tableclienteid;

    @FXML
    private TableColumn<Cliente, String> tableclientefone;
        @FXML
    private TableColumn<Cliente, String> tabelaclientecpf;
     @FXML
    private TableView<Cliente> tabelaCliente;
    
        @Override
     public void initialize(URL url, ResourceBundle rb) {
        btn_incluicliente.setOnAction(this::IncluirCliente);
        btn_excluicliente.setOnAction(this::ExcluiCliente);
        btn_alteracliente.setOnAction(this::AtualizaCliente);
        CarregaDados();
        tableclientenome.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getNome()));
        tabelaclientecpf.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCpf()));
        tableclientefone.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTelefone()));
           tableclienteid.setCellValueFactory(param -> {
           Integer id = param.getValue().getId();
           return new SimpleStringProperty(String.valueOf(id));
             });  
    }
     public void AtualizaCliente(ActionEvent e){
          try {
        Cliente c = tabelaCliente.getSelectionModel().getSelectedItem();
        
        // Carregar o arquivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaAtualizaCliente.fxml"));
        Parent root = loader.load();

        // Obter a instância do controlador associada à cena
        ControllerTelaAtualizaCliente controller = loader.getController();
        
        // Carregar os dados no controlador
        controller.CarregaDados(c, controller);

        // Criar uma nova cena com o conteúdo do arquivo FXML
        Scene scene = new Scene(root);

        // Obter o palco (stage) atual a partir do evento
        Stage stage = (Stage) btn_alteracliente.getScene().getWindow();
      Stage novaJanela = new Stage();
      novaJanela.setScene(scene);
      novaJanela.setTitle("Atualiza Cliente"); // Defina um título para a nova janela
      novaJanela.show();
    }catch (IOException ex) {
        ex.printStackTrace();
    }
     }
       public void ExcluiCliente(ActionEvent e){
               try{
                   DAOCliente dao = new DAOCliente();
                  Cliente c =  tabelaCliente.getSelectionModel().getSelectedItem(); 
                   dao.DeleteCliente(c);
                   tabelaCliente.getItems().remove(c);
                   tabelaCliente.refresh();
               }catch(Exception ep){
                     System.out.println("Erro exclusão");
               }
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
      public void IncluirCliente(ActionEvent e){
         try {
        // Carregar o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("CadastroCliente.fxml"));
     
        // Criar uma nova cena com o conteúdo do arquivo FXML
        Scene scene = new Scene(root);

        // Obter o palco (stage) atual a partir do evento
        Stage stage = (Stage) btn_incluicliente.getScene().getWindow();

          Stage novaJanela = new Stage();
      novaJanela.setScene(scene);
      novaJanela.setTitle("Cadastro de Cliente"); // Defina um título para a nova janela
      novaJanela.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
}
