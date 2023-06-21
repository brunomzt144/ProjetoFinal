/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package seboprojeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author bruno
 */
public class SeboProjeto extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocumentSebo.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
//        try{
//         Class.forName("com.mysql.cj.jdbc.Driver");
//         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sebo","root","");
//            System.out.println("Conexão com o banco de dados feita com sucesso!");
//        }
//        catch(ClassNotFoundException e1){
//            throw new ClassNotFoundException(
//            "Driver MySQL não foi encontrado" + e1.getMessage());
//        } catch(SQLException e2){
//            throw new SQLException("Erro ao conectar "
//            + " com a base de dados " + e2.getMessage());
//        }
//        FabricaConexao fb = new FabricaConexao();
//        fb.getConexao();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
       launch(args);

    }
    
}
