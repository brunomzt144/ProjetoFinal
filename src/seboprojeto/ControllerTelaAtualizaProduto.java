/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package seboprojeto;

import java.net.URL;
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
public class ControllerTelaAtualizaProduto  implements Initializable {
        @FXML
    private Label labelTitulo;

    @FXML
    private TextField txtPaginas;

    @FXML
    private Label labelEditora;

    @FXML
    private Label lid;

    @FXML
    private Button btnFechar;

    @FXML
    private TextField txtEditora;

    @FXML
    private TextField txtid;

    @FXML
    private Label labelPaginas;

    @FXML
    private Label labelValor;

    @FXML
    private Label labelQuantidade;

    @FXML
    private TextField txtValor;

    @FXML
    private TextField txtQntd;

    @FXML
    private Button btnConfirma;

    @FXML
    private TextField txtTitulo;
    
    
     @Override
     public void initialize(URL url, ResourceBundle rb){
     btnConfirma.setOnAction(this::EfetuaAtualizacao);
     btnFechar.setOnAction(this::fechar);
     }
     
        public void fechar(ActionEvent e){
         Stage stage = (Stage) btnFechar.getScene().getWindow();
        stage.close();
      }
     
    public void EfetuaAtualizacao(ActionEvent e){
                
     try{
     Produto p = new Produto();
     DAOProduto dao = new DAOProduto();
     p.setTitulo(txtTitulo.getText());
     p.setEditora(txtEditora.getText());
     int quantidade;
     quantidade = Integer.parseInt(txtQntd.getText());
     p.setQuantidade(quantidade);
     float valor;
     valor = Float.parseFloat(txtValor.getText());
     p.setValor(valor);
     int npaginas;
     npaginas = Integer.parseInt(txtPaginas.getText());
     p.setnPaginas(npaginas);
     int id;
     id = Integer.parseInt(txtid.getText());
     p.setId(id);
     dao.AlteraProduto(p);
     Stage stage = (Stage) btnConfirma.getScene().getWindow();
        stage.close();
     }catch(NumberFormatException exp){
         exp.getStackTrace();
    }
}
    
    public void preencherCampos(Produto p) {
  
     
        String id;
        id = String.valueOf(p.getId());
        txtid.setText(id);
        txtTitulo.setText(p.getTitulo());
        txtEditora.setText(p.getEditora());
        String nPaginas;
        nPaginas = String.valueOf(p.getnPaginas());
        txtPaginas.setText(nPaginas);
        String Valor;
        Valor = String.valueOf(p.getValor());
        txtValor.setText(Valor);
        String qtde;
        qtde = String.valueOf(p.getQuantidade());
        txtQntd.setText(qtde);
        
    }
    
    
}
