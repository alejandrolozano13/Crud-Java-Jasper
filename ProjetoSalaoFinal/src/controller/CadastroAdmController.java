/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Dao.LoginUserDao;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Usuario;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class CadastroAdmController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    private Button btnCadastro;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtUser;
    
    @FXML
    private Button btnVoltar;
    
    @FXML
    void aoVoltar(ActionEvent event) {
        
        Stage stage = (Stage) btnVoltar.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void aoCadastrarAdm(ActionEvent event) {
        try{
            boolean adm = true;
            Usuario user = new Usuario();
            LoginUserDao login = new LoginUserDao();
            int resultadoCadastro = 0;
            List<String> erros = new ArrayList<>();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            user.setNome(txtUser.getText());
            user.setEmail(txtEmail.getText());
            user.setSenha(txtSenha.getText());

            if (!user.getEmail().isEmpty() && !user.getNome().isEmpty()
                        && !user.getSenha().isEmpty()){
                    resultadoCadastro = login.cadastroUser(txtEmail.getText(), txtUser.getText(), txtSenha.getText(), adm);
                }

                if (user.getEmail().isEmpty()){
                    erros.add("O Email deve ser preenchido");
                }

                if (user.getNome().isEmpty()){
                    erros.add("O Nome deve ser preenchido");
                }

                if (user.getSenha().isEmpty()){
                    erros.add("A Senha deve ser preenchida.");
                }

                if (resultadoCadastro == 1){

                    alert.setTitle("Beauty");
                    alert.setContentText("Usuario criado com sucesso");
                    alert.showAndWait();
                    
                    txtUser.setText("");
                    txtEmail.setText("");
                    txtSenha.setText("");

                } else{

                    alert.setTitle("Beauty");
                    alert.setContentText(erros.toString());
                    alert.showAndWait();
                }

        }catch(Exception e){e.printStackTrace();}
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
