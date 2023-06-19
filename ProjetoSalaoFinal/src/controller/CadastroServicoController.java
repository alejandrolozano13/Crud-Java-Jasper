/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Dao.ServicoDao;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Servico;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class CadastroServicoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Button aoVoltarbtn;
    
    @FXML
    private Button btnRegistrar;
    
    @FXML
    private TextField txtCodigoServico;

    @FXML
    private TextField txtDescricaoServico;

    @FXML
    void aoVoltar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TelaCrudFXML.fxml"));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        
        Stage currentStage = (Stage) aoVoltarbtn.getScene().getWindow();
        
        currentStage.close();
        
        newStage.show();
    }
    
    @FXML
    void aoRegistrar(ActionEvent event) {
        try{
            
            Servico servico = new Servico();
            ServicoDao sDao = new ServicoDao();
            
            int resultado = 0;
            List<String> erros = new ArrayList<>();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
            servico.setDescricao_servico(txtDescricaoServico.getText());
            servico.setCodigo_servico(Integer.parseInt(txtCodigoServico.getText()));
            
            if(!servico.getDescricao_servico().isEmpty() && !txtCodigoServico.getText().isEmpty()){
                resultado = sDao.cadastroServico(txtDescricaoServico.getText(), servico.getCodigo_servico());
                
            }
            
            if(servico.getDescricao_servico().isEmpty()){
                erros.add("A descrição do serviço deve ser preenchida");
            }
            
            if(txtCodigoServico.getText().isEmpty()){
                erros.add("O código identificador do Serviço deve ser informado");
            }
            
            if(resultado == 1){
                alert.setTitle("Beauty");
                alert.setContentText("Servico criado com sucesso");
                alert.showAndWait();
                
                txtCodigoServico.setText("");
                txtDescricaoServico.setText("");
            } else{
                alert.setTitle("Beauty");
                alert.setContentText(erros.toString());
                alert.showAndWait();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
