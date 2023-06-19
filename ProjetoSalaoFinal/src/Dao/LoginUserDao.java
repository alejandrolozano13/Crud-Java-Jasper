/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import ConexaoBD.ConexaoBancoDeDados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author aleja
 */
public class LoginUserDao {
    
    ConexaoBancoDeDados con = new ConexaoBancoDeDados();
    
    private Connection conexao;
    private PreparedStatement statement;
    private ResultSet result;
    
    public int loginUser(String nome, String senha){
        int resultado = 0;
        try{
            String sql = "SELECT * FROM usuarios WHERE Nome_Usuario = ? and Senha_Usuario = ?";
            
            statement = con.connectDb().prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, senha);
            
            result = statement.executeQuery();
            
            if (result.next()){
                
                resultado = 1;
                
            } else{
               resultado = -1; 
            }
            
        }catch(Exception e){e.printStackTrace();}
        
        return resultado;
    }
    
    public String devolveTipoUsuario(String nome, String senha) throws SQLException{
        String tipoUsuario = null, sql;
        
        sql = "SELECT Tipo_Usuario FROM usuarios WHERE Nome_Usuario = ? and Senha_Usuario = ?";
        
        statement = con.connectDb().prepareStatement(sql);
        statement.setString(1, nome);
        statement.setString(2, senha);
        
        result = statement.executeQuery();
        
        if (result.next()){
            tipoUsuario = result.getString("Tipo_Usuario");
        }
        return tipoUsuario;
    }
    
    public int cadastroUser(String email, String nome, String senha, boolean adm){
        int resultadoCadastro = 0;
        
        try{
            if(adm == false){
                String sql = "INSERT INTO usuarios (Nome_Usuario, Senha_Usuario, Tipo_Usuario, E_Mail) VALUES (?, ?, ?, ?)";
                statement = con.connectDb().prepareStatement(sql);

                statement.setString(1, nome);
                statement.setString(2, senha);
                statement.setString(3, "Padrão");
                statement.setString(4, email);

                statement.execute();

                resultadoCadastro = 1;
            } else{
                String sql = "INSERT INTO usuarios (Nome_Usuario, Senha_Usuario, Tipo_Usuario, E_Mail) VALUES (?, ?, ?, ?)";
                statement = con.connectDb().prepareStatement(sql);

                statement.setString(1, nome);
                statement.setString(2, senha);
                statement.setString(3, "Administrador");
                statement.setString(4, email);

                statement.execute();

                resultadoCadastro = 1;
            }
            
            
        }catch(Exception e){e.printStackTrace();}
        
        return resultadoCadastro;
    }
}
