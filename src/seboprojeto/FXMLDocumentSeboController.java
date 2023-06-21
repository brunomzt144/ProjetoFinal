/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package seboprojeto;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author bruno
 */
public class FXMLDocumentSeboController implements Initializable {
    
     @FXML
    private Button btn_cliente;

    @FXML
    private Button btn_venda;

    @FXML
    private ImageView img_inicial;

    @FXML
    private Button btn_produto;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_produto.setOnAction(this::chamaTelaProduto);
        btn_cliente.setOnAction(this::chamaTelaCliente);
        btn_venda.setOnAction(this::chamaTelaVenda);
        

    }
    
    private void chamaTelaVenda(ActionEvent e){
         try {
        // Carregar o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("TelaVenda.fxml"));
     
        // Criar uma nova cena com o conteúdo do arquivo FXML
        Scene scene = new Scene(root);

        // Obter o palco (stage) atual a partir do evento
        Stage stage = (Stage) btn_venda.getScene().getWindow();

      Stage novaJanela = new Stage();
      novaJanela.setScene(scene);
      novaJanela.setTitle("Tela Venda"); // Defina um título para a nova janela
      novaJanela.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
     private void chamaTelaCliente(ActionEvent e) {
        try {
        // Carregar o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("TelaCliente.fxml"));
     
        // Criar uma nova cena com o conteúdo do arquivo FXML
        Scene scene = new Scene(root);

        // Obter o palco (stage) atual a partir do evento
        Stage stage = (Stage) btn_cliente.getScene().getWindow();

         Stage novaJanela = new Stage();
      novaJanela.setScene(scene);
      novaJanela.setTitle("Produtos"); // Defina um título para a nova janela
      novaJanela.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }

    private void chamaTelaProduto(ActionEvent e) {
        try {
        // Carregar o arquivo FXML
        Parent root = FXMLLoader.load(getClass().getResource("TelaProduto.fxml"));

        // Criar uma nova cena com o conteúdo do arquivo FXML
        Scene scene = new Scene(root);

        // Obter o palco (stage) atual a partir do evento
        Stage stage = (Stage)   btn_produto.getScene().getWindow();

        Stage novaJanela = new Stage();
      novaJanela.setScene(scene);
      novaJanela.setTitle("Clientes"); // Defina um título para a nova janela
      novaJanela.show();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    }
    
}
