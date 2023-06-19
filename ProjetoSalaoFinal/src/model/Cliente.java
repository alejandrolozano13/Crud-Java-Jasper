/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aleja
 */
public class Cliente {
    
    private String nome, cpf, telefone, data, servico;
    private int ordem_servico;
    
    public Cliente(int ordem_servico ,String nome, String cpf, String telefone, String data, String servico){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.data = data;
        this.servico = servico;
        this.ordem_servico = ordem_servico;
    }
    
    public Cliente(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public int getOrdem_servico() {
        return ordem_servico;
    }

    public void setOrdem_servico(int ordem_servico) {
        this.ordem_servico = ordem_servico;
    }
}
