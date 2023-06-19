/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import ConexaoBD.ConexaoBancoDeDados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author aleja
 */
public class ServicoDao {
    
    ConexaoBancoDeDados con = new ConexaoBancoDeDados();
    
    private PreparedStatement statement;
    private ResultSet result;
    
        public int cadastroServico(String descricao, int codigo){
        String sql;
        
        int res = 0;
        
        try{
            
            sql = "INSERT INTO servicos_salao (Descricao_Servico, Codigo_Servico)"
                    + "VALUES (?, ?)";
            
            statement = con.connectDb().prepareStatement(sql);
            
            statement.setString(1, descricao);
            statement.setInt(2, codigo);
            
            statement.execute();
            
            res = 1;
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return res;
    }
    
}
