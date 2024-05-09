package dal;
import java.sql.*;

public class ModuloConexao {
    //modulo responsavel por
    //estabelecer a conexão 
    //com o BD.
    
    public static Connection conector(){
        java.sql.Connection conexao = null;
        //a linha abaixo chama o driver
        String driver = "com.mysql.cj.jdbc.Driver";
        String url="jdbc:mysql://localhost: 3307/agenda";
        String user = "root";
        String password = "";
        //estabelecendo a conexão com o banco
        try{
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
                    return conexao;
        } catch (Exception e){
            return null;
        }
        
        
        
        
        
    }
    
}
