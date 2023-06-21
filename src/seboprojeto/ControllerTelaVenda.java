
package seboprojeto;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControllerTelaVenda  implements Initializable{
       @FXML
    private Button btn_retira;
    
      @FXML
    private TextField txtQtdeProduto;

    @FXML
    private TableColumn<Produto, String> colunaPreço;

    @FXML
    private TextField txtProduto;

    @FXML
    private Button btn_confirma;

    @FXML
    private Label lQtdeProduto;

    @FXML
    private Label lProduto;

    @FXML
    private Label lCliente;

    @FXML
    private TextField txtCliente;

    @FXML
    private TableColumn<Produto, String> colunaDesc;

    @FXML
    private Button btn_confirmaprod;

    @FXML
    private TableColumn<Produto, String> colunaQtde;

    @FXML
    private Button btn_cancela;
    
    @FXML
    private Label ltotal;

    @FXML
    private TextField txtNomeCliente;
    
    
    @FXML
    private TextField txtValorTotal;
    
     @FXML
    private TableView<Produto> tabelaProduto;
     
   @FXML
    private Button btn_ConfirmaCliente;
   
   
    @FXML
    private Button btn_buscaproduto;

    @FXML
    private Button btn_buscacliente;
     
    
    private Produto  produtoSelecionado;
    private float valorTotal = 0.0f;
    
      ArrayList<Produto> listaProdutos = new ArrayList<>();
      ArrayList<Cliente> listaClientes = new ArrayList<>();
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (tabelaProduto.getItems().isEmpty()) {
                valorTotal = 0;
        } 

               btn_confirma.setOnAction(this::FazVenda);
               btn_confirmaprod.setOnAction(this::CarregaDados);
               btn_ConfirmaCliente.setOnAction(this::CarregaCliente);
               btn_retira.setOnAction(this::RetiraProd);
               btn_cancela.setOnAction(this::fechar);
               btn_buscacliente.setOnAction(this::PromptCliente);
               btn_buscaproduto.setOnAction(this::PromptProduto);
               
               colunaDesc.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTitulo()));
               colunaQtde.setCellValueFactory(param -> {
               Integer  qtd = param.getValue().getQuantidade();
               return new SimpleStringProperty(String.valueOf(qtd));
                });
              colunaPreço.setCellValueFactory(param -> {
              Float  valor = param.getValue().getValor();
              return new SimpleStringProperty(String.valueOf(valor));
             });
    }
    
    
    public void PromptProduto(ActionEvent e ){
              try {

        // Carregar o arquivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PromtpProdutos.fxml"));
        Parent root = loader.load();
        // Criar uma nova cena com o conteúdo do arquivo FXML
        Scene scene = new Scene(root);

       Stage novaJanela = new Stage();
      novaJanela.setScene(scene);
      novaJanela.setTitle("Seleciona Produto"); // Defina um título para a nova janela
      ControllerPromptProduto promptProdutoController = loader.getController();
      promptProdutoController.setTelaVendaController(this);

      novaJanela.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
    public void setTxtProduto(int id) {
    txtProduto.setText(String.valueOf(id));
    }
    public void PromptCliente(ActionEvent e ){
           try {

        // Carregar o arquivo FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PromptCliente.fxml"));
        Parent root = loader.load();
        // Criar uma nova cena com o conteúdo do arquivo FXML
        Scene scene = new Scene(root);

       Stage novaJanela = new Stage();
      novaJanela.setScene(scene);
      novaJanela.setTitle("Seleciona Cliente"); // Defina um título para a nova janela
      ControllerPromptCliente ControllerPromptCliente = loader.getController();
      ControllerPromptCliente.setTelaVendaController(this);

      novaJanela.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
      public void setTxtCliente(int id) {
     txtCliente.setText(String.valueOf(id));
    }
    
       public void fechar(ActionEvent e){
         Stage stage = (Stage) btn_cancela.getScene().getWindow();
        stage.close();
      }
    
    public void RetiraProd(ActionEvent e){
         Produto p = tabelaProduto.getSelectionModel().getSelectedItem();
          valorTotal -= p.getValor() * p.getQuantidade();
          tabelaProduto.getItems().remove(p);
          tabelaProduto.refresh();
           txtValorTotal.setText(String.valueOf(valorTotal));
          
    }
    public void FazVenda(ActionEvent e){
        DAOVenda dao = new DAOVenda();
         dao.IncluiVenda(listaClientes, valorTotal);
         dao.IncluiItens(listaProdutos);
         dao.MovtaEstoque(listaProdutos);
         Stage stage = (Stage) btn_confirma.getScene().getWindow();
          // Fecha a janela
         stage.close();
    }
    
    
    public void  CarregaCliente(ActionEvent e){
             String SELECT = "SELECT * FROM CLIENTE WHERE CLIENTEID = ?";
         try{
            Connection con = FabricaConexao.getConexao();
             PreparedStatement pstm;
             int id;
             id = Integer.parseInt(txtCliente.getText());
             pstm = con.prepareStatement(SELECT);
             pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
               while (rs.next()) {
                   Cliente cliente = new Cliente();
                   cliente.setNome(rs.getString("clientenome"));
                   txtNomeCliente.setText(cliente.getNome());
                   cliente.setId(id);
                   listaClientes.add(cliente);
                 }
               
     }
    catch(ClassNotFoundException | SQLException ex){
       ex.printStackTrace();
    }
  }  
    public void CarregaDados(ActionEvent e){
        String SELECT = "SELECT * FROM PRODUTO WHERE PRODUTOID = ?";
         try{
            Connection con = FabricaConexao.getConexao();
             PreparedStatement pstm;
             int id;
             id = Integer.parseInt(txtProduto.getText());
             pstm = con.prepareStatement(SELECT);
             pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("produtoid"));
                produto.setTitulo(rs.getString("titulo"));
                int qtde;
                qtde = Integer.parseInt(txtQtdeProduto.getText());
                produto.setQuantidade(qtde);
                produto.setValor(rs.getFloat("valor"));
                tabelaProduto.getItems().add(produto);
                tabelaProduto.refresh();
                listaProdutos.add(produto);
                valorTotal += produto.getValor() * qtde;
     
            }
             txtValorTotal.setText(String.valueOf(valorTotal));
        }catch(ClassNotFoundException | SQLException ex){
           ex.printStackTrace();
    }
}
    
  
}
