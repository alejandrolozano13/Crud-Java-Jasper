/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Dao.ClienteDao;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Cliente;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class PesquisaClienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TableView<Cliente> PesquisaTableView;

    @FXML
    private Button btnLimparTabela;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<Cliente, String> col_cpf;

    @FXML
    private TableColumn<Cliente, String> col_data;

    @FXML
    private TableColumn<Cliente, String> col_nome;

    @FXML
    private TableColumn<Cliente, String> col_servico;

    @FXML
    private TableColumn<Cliente, String> col_telefone;
    
    @FXML
    private TableColumn<Cliente, String> col_OrdemServico;

    @FXML
    private ComboBox<String> tipoPesquisa;

    @FXML
    private TextField txtPesquisa;
    
    int ordem;
    
    @FXML
    void aoEscolherPesquisa(ActionEvent event) {
        String selectPesquisa = tipoPesquisa.getValue();
        ClienteDao cDao = new ClienteDao();
        
        if (selectPesquisa.equals("Nome")){
            List<Cliente> resultados = cDao.pesquisaClienteNome(txtPesquisa.getText());
            col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            col_cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
            col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
            col_servico.setCellValueFactory(new PropertyValueFactory<>("servico"));
            col_telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            col_OrdemServico.setCellValueFactory(new PropertyValueFactory<>("ordem_servico"));
            PesquisaTableView.getItems().addAll(resultados);
        } else{
            if (selectPesquisa.equals(("CPF"))){
                List<Cliente> resultados = cDao.pesquisaClienteCpf(txtPesquisa.getText());
                col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
                col_cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
                col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
                col_servico.setCellValueFactory(new PropertyValueFactory<>("servico"));
                col_telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
                col_OrdemServico.setCellValueFactory(new PropertyValueFactory<>("ordem_servico"));
                PesquisaTableView.getItems().addAll(resultados);
            } else{
                if (selectPesquisa.equals("Serviço")){
                    List<Cliente> resultados = cDao.pesquisaClienteServico(txtPesquisa.getText());
                    col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
                    col_cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
                    col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
                    col_servico.setCellValueFactory(new PropertyValueFactory<>("servico"));
                    col_telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
                    col_OrdemServico.setCellValueFactory(new PropertyValueFactory<>("ordem_servico"));
                    PesquisaTableView.getItems().addAll(resultados);
                } else{
                    if(selectPesquisa.equals("Ordem Serviço")){
                        List<Cliente> resultados = cDao.pesquisaClienteOrdemServico(txtPesquisa.getText());
                        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
                        col_cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
                        col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
                        col_servico.setCellValueFactory(new PropertyValueFactory<>("servico"));
                        col_telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
                        col_OrdemServico.setCellValueFactory(new PropertyValueFactory<>("ordem_servico"));
                        PesquisaTableView.getItems().addAll(resultados);
                    }else{
                        List<Cliente> resultados = cDao.pesquisaClienteData(txtPesquisa.getText());
                        col_nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
                        col_cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
                        col_data.setCellValueFactory(new PropertyValueFactory<>("data"));
                        col_servico.setCellValueFactory(new PropertyValueFactory<>("servico"));
                        col_telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
                        col_OrdemServico.setCellValueFactory(new PropertyValueFactory<>("ordem_servico"));
                        PesquisaTableView.getItems().addAll(resultados);
                    }
                }
            }
        }
        
    }
    
    void devolveOrdem(){
        
    }
    
    @FXML
    void aoLimparTabela(ActionEvent event){
        PesquisaTableView.getItems().clear();
    }

    @FXML
    void aoVoltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaCrudFXML.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        
        Stage currentStage = (Stage) btnVoltar.getScene().getWindow();
        
        currentStage.close();
        
        newStage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tipoPesquisa.getItems().addAll("Nome", "CPF", "Serviço", "Telefone", "Ordem Serviço", "Data Marcada");
    }    
    
}
