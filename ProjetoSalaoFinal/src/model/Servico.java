/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aleja
 */
public class Servico {
    
    private String descricao_servico;
    private int codigo_servico;

    public Servico() {
    }
    
    
    
    public Servico(String descricao_servico, int codigo_servico){
        this.descricao_servico = descricao_servico;
        this.codigo_servico = codigo_servico;
    }

    public String getDescricao_servico() {
        return descricao_servico;
    }

    public void setDescricao_servico(String descricao_servico) {
        this.descricao_servico = descricao_servico;
    }

    public int getCodigo_servico() {
        return codigo_servico;
    }

    public void setCodigo_servico(int codigo_servico) {
        this.codigo_servico = codigo_servico;
    }
}
