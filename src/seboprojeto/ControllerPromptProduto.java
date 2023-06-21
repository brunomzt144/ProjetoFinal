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


public class ControllerPromptProduto implements Initializable{
       @FXML
    private TableColumn<Produto, String> tableprodutoqntd;

    @FXML
    private TableColumn<Produto, String> tableprodutoid;
        @FXML
    private TableColumn<Produto,String> tableprodutovalor;

    @FXML
    private TableColumn<Produto, String> tableprodutoeditora;
    
        @FXML
    private TableColumn<Produto, String> tableprodutotitulo;
    
         @FXML
    private TableView<Produto> tabelaProduto;

    @FXML
    private Button btn_seleciona;
    
    private Produto produtoSelecionado;
    private ControllerTelaVenda telaVendaController;

    public void setTelaVendaController(ControllerTelaVenda controller) {
             this.telaVendaController = controller;
     }
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        CarregaDados();
        btn_seleciona.setOnAction(this::Selecionar);
        tableprodutotitulo.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getTitulo()));
        tableprodutoeditora.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getEditora()));
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
    public void Selecionar(ActionEvent e){
        produtoSelecionado = tabelaProduto.getSelectionModel().getSelectedItem(); 
        if (telaVendaController != null && produtoSelecionado != null) {
              telaVendaController.setTxtProduto(produtoSelecionado.getId());
      }
        Stage stage = (Stage) btn_seleciona.getScene().getWindow();
        stage.close();
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
    
}
