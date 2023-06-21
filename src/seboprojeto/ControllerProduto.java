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
import javax.swing.JOptionPane;

/**
 *
 * @author bruno
 */
public class ControllerProduto implements Initializable{
        @FXML
    private TextField txtEditora;

    @FXML
    private Label labelTitulo;

    @FXML
    private Label labelPaginas;

    @FXML
    private TextField txtPaginas;

    @FXML
    private Label labelEditora;

    @FXML
    private Label labelValor;

    @FXML
    private Label labelQuantidade;

    @FXML
    private Button btnFechar;

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
          btnConfirma.setOnAction(this::InserirProduto);
          btnFechar.setOnAction(this::fechar);
      }
      public void fechar(ActionEvent e){
         Stage stage = (Stage) btnConfirma.getScene().getWindow();
        stage.close();
      }
     public void InserirProduto(ActionEvent e){
         Produto prod = new Produto();
         prod.setTitulo( txtTitulo.getText());
         prod.setEditora(txtEditora.getText());
         try {
             int quantidade = Integer.parseInt(txtQntd.getText());
              prod.setQuantidade(quantidade);
               } catch (NumberFormatException exp) {
              }
         try{
             int paginas = Integer.parseInt(txtPaginas.getText());
                     prod.setnPaginas(paginas);
              }catch(NumberFormatException exp){
             }
         try{
             float valor = Float.parseFloat(txtValor.getText());
             prod.setValor(valor);
         }catch(NumberFormatException exp){
             
         }
        String titulo = txtTitulo.getText();
        if(titulo.isEmpty()){
            JOptionPane.showMessageDialog(null, "Nome obrigatorio!!!");
     } else{
            DAOProduto dao = new DAOProduto();
         dao.InserirProduto(prod);
        Stage stage = (Stage) btnConfirma.getScene().getWindow();
        stage.close();
        }
     }
}
