/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexaoBD;

//import Report;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author aleja
 */
public class ConexaoJasper {
    
    ConexaoBancoDeDados bd = new ConexaoBancoDeDados();
    
    /*public InputStream  getReport(String report_name, String column_name){
        InputStream input ;
        String query = "SELECT "+column_name+" FROM reports WHERE report_name='"+report_name+"'";
        try{
            PreparedStatement pst = bd.connectDb().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                input = rs.getBinaryStream(1);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return input;
    }*/
}
