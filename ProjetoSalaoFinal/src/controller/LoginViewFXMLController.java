package controller;

import ConexaoBD.ConexaoBancoDeDados;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;
import Dao.LoginUserDao;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;
import model.Usuario;

public class LoginViewFXMLController implements Initializable {


    @FXML
    private Label b;

    @FXML
    private Label b1;

    @FXML
    private Label beauty;

    @FXML
    private Label beauty1;

    @FXML
    private Button cadastro_btn;

    @FXML
    private AnchorPane cadastro_form;

    @FXML
    private Hyperlink create_acc;

    @FXML
    private Hyperlink login_acc;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField su_email;

    @FXML
    private PasswordField su_password;

    @FXML
    private TextField su_username;

    @FXML
    private TextField username;
    
    @FXML
    public void cadastro(ActionEvent event){
        try{
            boolean adm = false;
            LoginUserDao login = new LoginUserDao();
            int resultadoCadastro = 0;
            Usuario usuario = new Usuario();
            
            List<String> erros = new ArrayList<>();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            
            usuario.setEmail(su_email.getText());
            usuario.setNome(su_username.getText());
            usuario.setSenha(su_password.getText());
            
            if (!usuario.getEmail().isEmpty() && !usuario.getNome().isEmpty()
                    && !usuario.getSenha().isEmpty()){
                resultadoCadastro = login.cadastroUser(su_email.getText(), su_username.getText(), su_password.getText(), adm);
            }
            
            if (usuario.getEmail().isEmpty()){
                erros.add("O Email deve ser preenchido");
            }
            
            if (usuario.getNome().isEmpty()){
                erros.add("O Nome deve ser preenchido");
            }
            
            if (usuario.getSenha().isEmpty()){
                erros.add("A Senha deve ser preenchida.");
            }

            if (resultadoCadastro == 1){
                
                alert.setTitle("Beauty");
                alert.setContentText("Usuario criado com sucesso");
                alert.showAndWait();

            } else{
                
                alert.setTitle("Beauty");
                alert.setContentText(erros.toString());
                alert.showAndWait();
            }

        }catch(Exception e){e.printStackTrace();}
    }
    
    @FXML
    public void login(ActionEvent event){        
        try{
            Usuario usuario = new Usuario();
            usuario.setNome(username.getText());
            usuario.setSenha(password.getText());
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            List<String> erros = new ArrayList<>();
            
            if (usuario.getNome().isEmpty()){
                erros.add("O nome do Usuario não pode ser vazio.");
            }
            
            if (usuario.getSenha().isEmpty()){
                erros.add("A senha do Usuario não pode ser vazia");
            }
            
            int numErros = erros.size();
            
            LoginUserDao login = new LoginUserDao();
            int resultado = login.loginUser(usuario.getNome(), usuario.getSenha());
            String tipoUsuario = login.devolveTipoUsuario(usuario.getNome(), usuario.getSenha());
            
            if (resultado == 1){
                
                
                JOptionPane.showMessageDialog(null, "Login efetuado com sucesso", 
                        "Beauty", JOptionPane.INFORMATION_MESSAGE);
                login_btn.getScene().getWindow().hide();
                
                
                if (tipoUsuario.equals("Administrador")){
                    Parent root = FXMLLoader.load(getClass().getResource("/view/TelaCrudFXML.fxml"));

                    Scene scene = new Scene(root);

                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Sistema Beauty");
                    stage.show();
                } else{
                    Parent root = FXMLLoader.load(getClass().getResource("/view/FXML.fxml"));

                    Scene scene = new Scene(root);

                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("Sistema Beauty");
                    stage.show();
                }
            }
            else{
                if (numErros==0){
                    alert.setTitle("Beauty");
                    alert.setContentText("Login Inválido, Usuário não encontrado.");
                    alert.showAndWait();
                }
                else{
                    alert.setTitle("Beauty");;
                    alert.setContentText(erros.toString());
                    alert.showAndWait();
                }
            }
            
        }catch(Exception e){e.printStackTrace();}
    }
    
    
    @FXML
    void textfield(MouseEvent event){
        
        if(event.getSource() == username){
            username.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:3px");
            password.setStyle("-fx-background-color:#eef3ff;"
                    + "-fx-border-width:1px");
        }else if(event.getSource() == password){
            username.setStyle("-fx-background-color:#eef3ff;"
                    + "-fx-border-width:1px");
            password.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:3px");
        }
    }
    
    public void changeForm(ActionEvent event){
        
        if (event.getSource() == login_acc){
            cadastro_form.setVisible(false);
            login_form.setVisible(true);
        } else if(event.getSource() == create_acc){
            cadastro_form.setVisible(true);
            login_form.setVisible(false);
        }
    }
    
    @FXML
    public void exit(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        username.setStyle("-fx-background-color:#fff;"
                    + "-fx-border-width:3px;");
        
        DropShadow original =  new DropShadow(20, Color.valueOf("#6a9ae7"));
        b.setEffect(original);
        beauty.setEffect(original);
        
        b1.setEffect(original);
        beauty1.setEffect(original);
        
        b.setOnMouseEntered((MouseEvent event) -> {
            
            DropShadow shadow =  new DropShadow(50, Color.valueOf("#6a9ae7"));
            
            b.setStyle("-fx-text-fill:#fff");
            b.setEffect(shadow);
            beauty.setEffect(shadow);
        });
        
        b.setOnMouseExited((MouseEvent event) -> {
            DropShadow shadow = new DropShadow(20, Color.valueOf("#6a9ae7"));
            b.setStyle("-fx-text-fill:#6a9ae7");
            b.setEffect(shadow);
            beauty.setEffect(shadow);
        });
        
        beauty.setOnMouseEntered((MouseEvent event) -> {
            
            DropShadow shadow =  new DropShadow(50, Color.valueOf("#6a9ae7"));
            
            b.setStyle("-fx-text-fill:#fff");
            b.setEffect(shadow);
            beauty.setEffect(shadow);
        });
        
        beauty.setOnMouseExited((MouseEvent event) -> {
            DropShadow shadow = new DropShadow(20, Color.valueOf("#6a9ae7"));
            b.setStyle("-fx-text-fill:#6a9ae7");
            b.setEffect(shadow);
            beauty.setEffect(shadow);
        });
        
//      ------------------------------------------------------------------------------------------

        b1.setOnMouseEntered((MouseEvent event) -> {
            
            DropShadow shadow =  new DropShadow(50, Color.valueOf("#6a9ae7"));
            
            b1.setStyle("-fx-text-fill:#fff");
            b1.setEffect(shadow);
            beauty1.setEffect(shadow);
        });
        
        b1.setOnMouseExited((MouseEvent event) -> {
            DropShadow shadow = new DropShadow(20, Color.valueOf("#6a9ae7"));
            b1.setStyle("-fx-text-fill:#6a9ae7");
            b1.setEffect(shadow);
            beauty1.setEffect(shadow);
        });
        
        beauty1.setOnMouseEntered((MouseEvent event) -> {
            
            DropShadow shadow =  new DropShadow(50, Color.valueOf("#6a9ae7"));
            
            b1.setStyle("-fx-text-fill:#fff");
            b1.setEffect(shadow);
            beauty1.setEffect(shadow);
        });
        
        beauty1.setOnMouseExited((MouseEvent event) -> {
            DropShadow shadow = new DropShadow(20, Color.valueOf("#6a9ae7"));
            b1.setStyle("-fx-text-fill:#6a9ae7");
            b1.setEffect(shadow);
            beauty1.setEffect(shadow);
        });
    }
}
