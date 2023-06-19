/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexaoBD;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import net.sf.jasperreports.view.JasperViewer;


public class ConexaoBancoDeDados {
    

    
    private static final String URL = "jdbc:mysql://localhost:3306/ProjetoVicente?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "1234567";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    public Connection connectDb(){
        try{
            Connection conexao = DriverManager.getConnection(URL, USER, PASS);
            return conexao;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
