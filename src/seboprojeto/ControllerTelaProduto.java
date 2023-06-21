
package seboprojeto;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ControllerTelaProduto implements Initializable {
        @FXML
    private Button btn_incluiproduto;

    @FXML
    private TableColumn<Produto, String> tableprodutoqntd;

    @FXML
    private TableColumn<Produto, String> tableprodutoid;

    @FXML
    private Button btn_alteraproduto;

    @FXML
    private TableColumn<Produto,String> tableprodutovalor;

    @FXML
    private TableColumn<Produto, String> tableprodutoeditora;
    
    @FXML
    private TableColumn<Produto, String> tableprodutopaginas;
    

    @FXML
    private Button btn_excluiproduto;

    @FXML
    private TableColumn<Produto, String> tableprodutotitulo;
    
    @FXML
    private TableView<Produto> tabelaProduto;

    
        @Override
    public void initialize(URL url, ResourceBundle rb) {


            CarregaDados();
            btn_incluiproduto.setOnAction(this::IncluirProduto);
            btn_alteraproduto.setOnAction(this::AlteraProduto);
            btn_excluiproduto.setOnAction(this::ExcluiProduto);
            tableprodutotitulo.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTitulo()));
            tableprodutoeditora.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getEditora()));
            
            tableprodutopaginas.setCellValueFactory(param -> {
           Integer paginas = param.getValue().getnPaginas();
           return new SimpleStringProperty(String.valueOf(paginas));
             });
           tableprodutoid.setCellValueFactory(param -> {
           Integer id = param.getValue().getId();
           return new SimpleStringProperty(String.valueOf(id));
             });
           tableprodutovalor.setCellValueFactory(param -> {
           Float  valor = param.getValue().getValor();
           return new SimpleStringProperty(String.valueOf(valor));
             });
            tableprodutoqntd.setCellValueFactory(param -> {
           Integer  qtd = param.getValue().getQuantidade();
           return new SimpleStringProperty(String.valueOf(qtd));
             });
            


    }
        public void ExcluiProduto(ActionEvent e){
               try{
                   DAOProduto dao = new DAOProduto();
                  Produto p =  tabelaProduto.getSelectionModel().getSelectedItem(); 
                    
                   dao.ExcluiProduto(p);
                    tabelaProduto.getItems().remove(p);
                    tabelaProduto.refresh();
               }catch(Exception ep){
                   System.out.println("Excluir não deu certo!");
               }
         
        }
    
    
    
    
    
    
    public void CarregaDados(){

        try{
            Connection con = FabricaConexao.getConexao();
            Statement pstm = con.createStatement();
            ResultSet rs = pstm.executeQuery("SELECT * FROM produto");
            
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("produtoid"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setEditora(rs.getString("editora"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setValor(rs.getFloat("valor"));
                produto.setnPaginas(rs.getInt("npaginas"));
                tabelaProduto.getItems().add(produto);
            
            }
             
        }catch(ClassNotFoundException | SQLException e){
           e.printStackTrace();
    }
    }
    
    public void AlteraProduto(ActionEvent e){
  
   
        try {
        Produto produto = tabelaProduto.getSelectionModel().getSelectedItem(); 
        // Carregar o arquivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaAtualizaProduto.fxml"));
        Parent root = loader.load();
    
        ControllerTelaAtualizaProduto controller = loader.getController();
         
        
         controller.preencherCampos(produto);
        // Criar uma nova cena com o conteúdo do arquivo FXML
        Scene scene = new Scene(root);

       Stage novaJanela = new Stage();
      novaJanela.setScene(scene);
      novaJanela.setTitle("Atualizar Produto"); // Defina um título para a nova janela
      novaJanela.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
        
    }
    public void IncluirProduto(ActionEvent e){
         try {
        // Carregar o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("CadastroProduto.fxml"));
     
        // Criar uma nova cena com o conteúdo do arquivo FXML
        Scene scene = new Scene(root);

        // Obter o palco (stage) atual a partir do evento
        Stage stage = (Stage) btn_incluiproduto.getScene().getWindow();

        Stage novaJanela = new Stage();
      novaJanela.setScene(scene);
      novaJanela.setTitle("Cadastro de Produto"); // Defina um título para a nova janela
      novaJanela.show();
      tabelaProduto.refresh();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
}
