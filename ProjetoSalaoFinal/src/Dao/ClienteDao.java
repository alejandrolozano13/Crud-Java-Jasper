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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Cliente;

/**
 *
 * @author aleja
 */
public class ClienteDao {
    
    ConexaoBancoDeDados con = new ConexaoBancoDeDados();
    
    private Connection conexao;
    private PreparedStatement statement;
    private ResultSet result;
    
    public String[] selectServicos() {
        String sql, descricaoServico;
        List<String> valores = new ArrayList<>();

        try {
            sql = "SELECT Descricao_Servico FROM servicos_salao";
            statement = con.connectDb().prepareStatement(sql);
            result = statement.executeQuery();

            while (result.next()) {
                descricaoServico = result.getString("Descricao_Servico");
                valores.add(descricaoServico);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Cliente cliente = new Cliente();
        cliente.setServico(valores.toString());

        // Converter a lista de strings em um vetor de strings
        String[] servicosArray = new String[valores.size()];
        servicosArray = valores.toArray(servicosArray);
        

        return servicosArray;
    }
    
    public List<Cliente> pesquisaClienteNome(String nome){
        List<Cliente> resultados = new ArrayList<>();
        String sql;
        
        try{
            sql = "SELECT * FROM servicos_cliente WHERE Nome_Cliente LIKE ?";
            statement = con.connectDb().prepareStatement(sql);
            
            statement.setString(1, "%" + nome + "%");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                int ordemServico = rs.getInt("Ordem_Servico");
                Cliente cliente = new Cliente(ordemServico,rs.getString("Nome_Cliente"), rs.getString("Cpf_Cliente"),
                        rs.getString("Telefone_Cliente"), rs.getString("Data_Marcada"),
                        rs.getString("Servico_Salao"));
                resultados.add(cliente);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return resultados;
    }
    
    public List<Cliente> pesquisaClienteCpf(String cpf){
        List<Cliente> resultados = new ArrayList<>();
        String sql;
        
        try{
            sql = "SELECT * FROM servicos_cliente WHERE Cpf_Cliente LIKE ?";
            statement = con.connectDb().prepareStatement(sql);
            
            statement.setString(1, "%" + cpf + "%");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                int ordemServico = rs.getInt("Ordem_Servico");
                Cliente cliente = new Cliente(ordemServico,rs.getString("Nome_Cliente"), rs.getString("Cpf_Cliente"),
                        rs.getString("Telefone_Cliente"), rs.getString("Data_Marcada"),
                        rs.getString("Servico_Salao"));
                resultados.add(cliente);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return resultados;
    }
    
    public List<Cliente> pesquisaClienteServico(String servico){
        List<Cliente> resultados = new ArrayList<>();
        String sql;
        
        try{
            sql = "SELECT * FROM servicos_cliente WHERE Servico_Salao LIKE ?";
            statement = con.connectDb().prepareStatement(sql);
            
            statement.setString(1, "%" + servico + "%");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                int ordemServico = rs.getInt("Ordem_Servico");
                Cliente cliente = new Cliente(ordemServico,rs.getString("Nome_Cliente"), rs.getString("Cpf_Cliente"),
                        rs.getString("Telefone_Cliente"), rs.getString("Data_Marcada"),
                        rs.getString("Servico_Salao"));
                resultados.add(cliente);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return resultados;
    }
    
    public List<Cliente> pesquisaClienteTelefone(String telefone){
        List<Cliente> resultados = new ArrayList<>();
        String sql;
        
        
        
        try{
            sql = "SELECT * FROM servicos_cliente WHERE Telefone_Cliente LIKE ?";
            statement = con.connectDb().prepareStatement(sql);
            
            statement.setString(1, "%" + telefone + "%");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                int ordemServico = rs.getInt("Ordem_Servico");
                Cliente cliente = new Cliente(ordemServico,rs.getString("Nome_Cliente"), rs.getString("Cpf_Cliente"),
                        rs.getString("Telefone_Cliente"), rs.getString("Data_Marcada"),
                        rs.getString("Servico_Salao"));
                resultados.add(cliente);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return resultados;
    }
    
    public List<Cliente> pesquisaClienteOrdemServico(String ordem){
        List<Cliente> resultados = new ArrayList<>();
        String sql;
        
        int query = Integer.parseInt(ordem);
        
        try{
            sql = "SELECT * FROM servicos_cliente WHERE Ordem_Servico LIKE ?";
            statement = con.connectDb().prepareStatement(sql);
            
            statement.setString(1,"%" + query + "%");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                int ordemServico = rs.getInt("Ordem_Servico");
                Cliente cliente = new Cliente(ordemServico,rs.getString("Nome_Cliente"), rs.getString("Cpf_Cliente"),
                        rs.getString("Telefone_Cliente"), rs.getString("Data_Marcada"),
                        rs.getString("Servico_Salao"));
                resultados.add(cliente);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return resultados;
    }
    
    public List<Cliente> pesquisaClienteData(String data){
        List<Cliente> resultados = new ArrayList<>();
        String sql;
                
        try{
            sql = "SELECT * FROM servicos_cliente WHERE Data_Marcada LIKE ?";
            statement = con.connectDb().prepareStatement(sql);
            
            statement.setString(1,"%" + data + "%");
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                int ordemServico = rs.getInt("Ordem_Servico");
                Cliente cliente = new Cliente(ordemServico,rs.getString("Nome_Cliente"), rs.getString("Cpf_Cliente"),
                        rs.getString("Telefone_Cliente"), rs.getString("Data_Marcada"),
                        rs.getString("Servico_Salao"));
                resultados.add(cliente);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return resultados;
    }
    
    public int cadastroCliente(String cpf, String nome, String telefone, String data, String servico){
        String sqlCliente;
        
        int resultado = 0;
        
        try{
            
            sqlCliente = "INSERT INTO servicos_cliente (Nome_Cliente, Data_Marcada, Servico_Salao, Telefone_Cliente, Cpf_Cliente)"
                + "VALUES (?, ?, ?, ?, ?)";
            
            
            
            statement = con.connectDb().prepareStatement(sqlCliente);
            
            statement.setString(1, nome);
            statement.setString(2, data);
            statement.setString(3, servico);
            statement.setString(4, telefone);
            statement.setString(5, cpf);
            
            statement.execute();
            
            resultado = 1;
            
        } catch(Exception e){
            resultado = -1;
            e.printStackTrace();
        }
        
        return resultado;
    }
    
    public int updateCliente(String nome, String data, String servico, String telefone, String cpf, int ordem) {
    String sql = "UPDATE servicos_cliente SET Nome_Cliente = ?, Data_Marcada = ?, Servico_Salao = ?, Telefone_Cliente = ?, Cpf_Cliente = ? WHERE Ordem_Servico = ?";
    int res = 0;
    
    try {
        PreparedStatement statement = con.connectDb().prepareStatement(sql);
        statement.setString(1, nome);
        statement.setString(2, data);
        statement.setString(3, servico);
        statement.setString(4, telefone);
        statement.setString(5, cpf);
        statement.setInt(6, ordem);
        int rowsAffected = statement.executeUpdate();

        if (rowsAffected > 0) {
            res = 1; // Atualização bem-sucedida
        } else {
            res = 0; // Nenhum registro afetado (CPF não encontrado)
        }
        
    } catch (Exception e) {
        res = -1; // Ocorreu uma exceção durante a atualização
        e.printStackTrace();
    }

        return res;
    }
    
    public int removerCliente(int cpf){
        int res = 0;
        String sql;
        try{
            
            sql = "DELETE FROM servicos_cliente WHERE Ordem_Servico = ?";
            statement = con.connectDb().prepareStatement(sql);
            
            statement.setInt(1, cpf);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                res = 1; // Atualização bem-sucedida
            } else {
                res = 0; // Nenhum registro afetado (CPF não encontrado)
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;
    }

    
    public ObservableList<Cliente> selectDadosCliente() throws SQLException{
        
            String sql;
            
            ObservableList<Cliente> listDadosCliente = FXCollections.observableArrayList();
            
            
            sql = "SELECT * FROM servicos_cliente";
            
            statement = con.connectDb().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            
            while(rs.next()){
                int ordemServico = rs.getInt("Ordem_Servico");
                
                Cliente cliente = new Cliente(ordemServico,rs.getString("Nome_Cliente"), rs.getString("Cpf_Cliente"),
                        rs.getString("Telefone_Cliente"), rs.getString("Data_Marcada"),
                        rs.getString("Servico_Salao"));
                cliente.setOrdem_servico(ordemServico);
                listDadosCliente.add(cliente);
            }
            
            return listDadosCliente;
    }
}
