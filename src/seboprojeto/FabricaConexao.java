
package seboprojeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
     private static final String USUARIO = "root";
    private static final String SENHA = "";
    private static final String DATABASE = "sebo";
    private static final String DRIVER_CONEXAO = "com.mysql.cj.jdbc.Driver";
    private static final String STR_CONEXAO = "jdbc:mysql://localhost:3306/";
    
     public  static Connection getConexao() throws ClassNotFoundException, SQLException {
 
       try{
            Class.forName(DRIVER_CONEXAO);
            Connection con = DriverManager.getConnection(STR_CONEXAO+DATABASE,USUARIO,SENHA);
            System.out.println("Conexão com o banco de dados feita com sucesso!");
            return con;
            
//            String query = "Select * from cliente";
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(query);

//            int count = 0;
//            while(rs.next()){
//                String i1 = rs.getString("id");
//                String n1 = rs.getString("nome");
//                String t1 = rs.getString("telefone");
//                String e1 = rs.getString("email");
//                System.out.println(i1 + "--" + n1 + "--" + t1 + "--" + e1);
//                count++;
//            }
//            System.out.println("Registros na tabela do banco de dados: " + count);
            
        } catch(ClassNotFoundException e1){
            throw new ClassNotFoundException(
            "Driver MySQL não foi encontrado" + e1.getMessage());
        } catch(SQLException e2){
            throw new SQLException("Erro ao conectar "
            + " com a base de dados " + e2.getMessage());
        }
        
    }
}
