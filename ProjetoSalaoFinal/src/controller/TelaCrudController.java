/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import ConexaoBD.ConexaoBancoDeDados;
import ConexaoBD.ConexaoJasper;
import Dao.ClienteDao;
import Dao.LoginUserDao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.util.HashMap;
import model.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author aleja
 */
public class TelaCrudController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private ObservableList<Cliente> clientes;
    private Map<String, Object> map;
    private Connection conexao;
    
    @FXML
    private Button btnImprimir;
    
    @FXML
    private Button add_Btn;

    @FXML
    private ComboBox<String> crud_ServicoCliente;

    @FXML
    private TextField crud_TelefoneCliente;

    @FXML
    private TableColumn<Cliente, String> crud_col_CpfCliente;

    @FXML
    private TableColumn<Cliente, String> crud_col_Data;

    @FXML
    private TableColumn<Cliente, String> crud_col_Servico;

    @FXML
    private TableColumn<Cliente, String> crud_col_nomeCliente;

    @FXML
    private TableColumn<Cliente, String> crud_col_telefone;
    
    @FXML
    private TableColumn<Cliente, String> crud_col_OrdemServico;

    @FXML
    private TextField crud_cpfCliente;

    @FXML
    private TextField crud_nomeCliente;

    @FXML
    private TableView<Cliente> crud_tableView;

    @FXML
    private DatePicker myDatePicker;

    @FXML
    private Button novo_Servico;

    @FXML
    private Button remove_btn;

    @FXML
    private Button search_btn;

    @FXML
    private Button update_btn;

    
    private String data_marcada;
    
     @FXML
    private Button novoUsuarioBtn;
     
     
    @FXML
    private ComboBox<String> idiomas_combobox;
    
    @FXML
    private Label nome_label;
    
    @FXML
    private Label cpf_label;
    
    @FXML
    private Label data_label;
    
    @FXML
    private Label servico_label;
    
    @FXML
    private Label telefone_label;
    
    @FXML
    private Label ordemLabel;
        
    ObservableList<Cliente> listDadosCliente = FXCollections.observableArrayList();

    Alert alert;
    
    int ordema;
    
    @FXML
    void aoPesquisar(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/PesquisaCliente.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
        
            Stage currentStage = (Stage) search_btn.getScene().getWindow();
        
            currentStage.close();
        
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(TelaCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static final String path = "src\\view\\ClientesBeauty.jasper";
    
    public void getPdf() throws JRException{
        ConexaoBancoDeDados bd = new ConexaoBancoDeDados();
        Connection con = bd.connectDb();
        
        JasperPrint jp = JasperFillManager.fillReport(path, null, con);
        JasperViewer.viewReport(jp, false);
    }
    
    @FXML
    void aoImprimir(ActionEvent event) throws JRException{
        //ConexaoJasper c = new ConexaoJasper();
        
        //createReport(conexao, map, c.getReport("report_name", "report_jasper"));
        //showReport();
        
        getPdf();
    }
    
    /*public static void showReport(){
        jviewer = new JasperViewer(jprint);
        jviewer.setVisible(true);
    }*/
    
    //private static JasperReport jreport;
    //private static JasperViewer jviewer;
    //private static JasperPrint jprint;
    
   /* public static void createReport(Connection connection, Map<String,Object>map, InputStream by){
        try{
            
            jreport = (JasperReport)JRLoader.loadObject(by);
            jprint = JasperFillManager.fillReport(jreport, map, connection);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/
    
    /*public void printReport(){
        map = new HashMap<String, Object>();
        ConexaoBancoDeDados bd = new ConexaoBancoDeDados();
        Connection con = bd.connectDb();
        ConexaoJasper cj = new ConexaoJasper();
        
        createReport(con, map, cj.getReport("report_name", "report_jasper"));
        showReport();
    }*/
    
    @FXML
    void aoIncluirNovoServico(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/CadastroServico.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
        
            Stage currentStage = (Stage) novo_Servico.getScene().getWindow();
        
            currentStage.close();
        
            newStage.show();
        } catch (IOException ex) {
            Logger.getLogger(TelaCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ShowReporte(){
        ConexaoBancoDeDados bd = new ConexaoBancoDeDados();
        Connection con = bd.connectDb();
        
        
    }
    
    @FXML
    void aoSelecionarIdioma(ActionEvent event){
        String selectIdioma = idiomas_combobox.getValue();
        
        if(selectIdioma.equals("Espanhol (Spanish)")){
            nome_label.setText("Nombre del cliente");
            cpf_label.setText("Identidad del cliente");
            data_label.setText("Dia Marcado");
            servico_label.setText("Tipo de servicio");
            telefone_label.setText("Telefono del cliente");
            add_Btn.setText("Adicionar");
            update_btn.setText("Actualizar");
            remove_btn.setText("Apagar");
            search_btn.setText("Buscar");
            novo_Servico.setText("Nuevo Servicio");
            crud_col_nomeCliente.setText("Nombre Cliente");
            crud_col_CpfCliente.setText("Identidad");
            crud_col_Data.setText("Dia Marcado");
            crud_col_Servico.setText("Servicio");
            crud_col_telefone.setText("Telefono");
        } else{
            if(selectIdioma.equals("Inglês (English)")){
                nome_label.setText("Client Name");
                cpf_label.setText("Client Identity");
                data_label.setText("Schedule Date");
                servico_label.setText("Kind of Service");
                telefone_label.setText("Customer Phone");
                add_Btn.setText("Add");
                update_btn.setText("Update");
                remove_btn.setText("Remove");
                search_btn.setText("Search");
                novo_Servico.setText("New Service");
                crud_col_nomeCliente.setText("Client Name");
                crud_col_CpfCliente.setText("Identity");
                crud_col_Data.setText("Schedule Date");
                crud_col_Servico.setText("Service");
                crud_col_telefone.setText("Phone");
            } else{
                nome_label.setText("Nome do cliente");
                cpf_label.setText("CPF");
                data_label.setText("Data Marcada");
                servico_label.setText("Tipo de Serviço");
                telefone_label.setText("Telefone");
                add_Btn.setText("Adicionar");
                update_btn.setText("Atualizar");
                remove_btn.setText("Remover");
                search_btn.setText("Pesquisar");
                novo_Servico.setText("Novo Serviço");
                crud_col_nomeCliente.setText("Nome Cliente");
                crud_col_CpfCliente.setText("CPF");
                crud_col_Data.setText("Data Marcada");
                crud_col_Servico.setText("Serviço");
                crud_col_telefone.setText("Telefone");
            }
        }
    }
    
    @FXML
    void updateClientes(ActionEvent event){
        int resultUpd = 0;
        TelaCrudController tc = new TelaCrudController();
        Cliente cliente = new Cliente();
        cliente.setNome(crud_nomeCliente.getText());
        cliente.setCpf(crud_cpfCliente.getText());
        cliente.setData(data_marcada);
        cliente.setServico(crud_ServicoCliente.getValue());
        cliente.setTelefone(crud_TelefoneCliente.getText());
        try{
            ClienteDao cDao = new ClienteDao();
            LocalDate dataAtual = LocalDate.now();
            if (cliente.getNome().equals("") || cliente.getCpf().equals("")
            || myDatePicker == null || cliente.getTelefone().equals("")
            || crud_ServicoCliente.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Por favor, preencha todos os campos");
                alert.showAndWait();
            } else{
                if (tc.validacaoCpf(crud_cpfCliente.getText()) == false){
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Erro");
                    alert.setHeaderText(null);
                    alert.setContentText("Erro, CPF inválido.");
                    alert.showAndWait();
                }else{

                    if (tc.validacaoTelefone(crud_TelefoneCliente.getText()) == false){
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Erro");
                        alert.setHeaderText(null);
                        alert.setContentText("Erro, número de telefone inválido.");
                        alert.showAndWait();
                    }
                    else{
                        if (myDatePicker.getValue() == null || myDatePicker.getValue().isBefore(dataAtual)){
                            alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Erro");
                            alert.setHeaderText(null);
                            alert.setContentText("Erro, ao escolher a data, escolha a partir do dia atual.");
                            alert.showAndWait();
                            resultUpd = -2;
                        } else{
                            
                            resultUpd = cDao.updateCliente(crud_nomeCliente.getText(), data_marcada,
                            crud_ServicoCliente.getValue(), crud_TelefoneCliente.getText(), crud_cpfCliente.getText(), ordema);
                            studentShowData();
                        }
                    }
                }
            }
        
            if (resultUpd == 1){

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Beauty");
                alert.setContentText("Cliente alterado com sucesso");
                alert.showAndWait();
            }
            else{
                if(resultUpd == -2){
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Beauty");
                    alert.setContentText("A data não é permitida, precisa escolher a partir do dia atual.");
                    alert.showAndWait();
                }else{
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Beauty");
                    alert.setContentText("Cliente não encontrado com esse CPF");
                    alert.showAndWait();
                }
            }
            }catch(Exception e){

            }
        }
    
    @FXML
    void aoRemover(ActionEvent event){
        int resultUpd = 0;
        TelaCrudController tc = new TelaCrudController();
        Cliente cliente = new Cliente();
        cliente.setNome(crud_nomeCliente.getText());
        cliente.setCpf(crud_cpfCliente.getText());
        cliente.setData(data_marcada);
        cliente.setServico(crud_ServicoCliente.getValue());
        cliente.setTelefone(crud_TelefoneCliente.getText());
        try{
            cliente.setNome("");
            cliente.setCpf("");
            myDatePicker.setValue(null);
            crud_ServicoCliente.setValue(null);
            cliente.setTelefone("");
            myDatePicker.setValue(null);
            
            crud_nomeCliente.setText("");
            crud_cpfCliente.setText("");
            crud_TelefoneCliente.setText("");
            ClienteDao cDao = new ClienteDao();
            
            //resultUpd = cDao.removerCliente(crud_cpfCliente.getText());
            resultUpd = cDao.removerCliente(ordema);
            studentShowData();
        
            if (resultUpd == 1){
                
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Beauty");
                alert.setContentText("Cliente excluído com sucesso");
                alert.showAndWait();
            }
            else{
                if(resultUpd == -2){
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Beauty");
                    alert.setContentText("A data não é permitida, precisa escolher a partir do dia atual.");
                    alert.showAndWait();
                }else{
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Beauty");
                    alert.setContentText("Cliente não encontrado com esse CPF");
                    alert.showAndWait();
                }
            }
            }catch(Exception e){

            }
    }
    
    @FXML
    void aoInserirNovoUsuario(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/view/CadastroAdm.fxml"));
            Scene scene = new Scene(root);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Sistema Beauty");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(TelaCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    public void add_BtnAcao(ActionEvent event) throws SQLException{
        TelaCrudController tc = new TelaCrudController();
        ClienteDao cDao = new ClienteDao();
        int resultadoCadastro = 0;
        LocalDate dataAtual = LocalDate.now();

        
        Cliente cliente = new Cliente();
        cliente.setNome(crud_nomeCliente.getText());
        cliente.setCpf(crud_cpfCliente.getText());
        cliente.setData(data_marcada);
        cliente.setServico(crud_ServicoCliente.getValue());
        cliente.setTelefone(crud_TelefoneCliente.getText());
        
        /*DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(data_marcada, formatter);
            String dataFormatada = data.format(DateTimeFormatter.ISO_DATE);*/
        
        if (cliente.getNome().equals("") || cliente.getCpf().equals("")
        || myDatePicker == null || cliente.getTelefone().equals("")
        || crud_ServicoCliente.getValue() == null || crud_nomeCliente.getText().equals(null)||
                crud_cpfCliente.getText().equals(null) || data_marcada.equals(null) || crud_ServicoCliente.getValue().equals(null)
                || crud_TelefoneCliente.getText().equals(null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, preencha todos os campos");
            alert.showAndWait();
        } else{
            if (tc.validacaoCpf(crud_cpfCliente.getText()) == false){
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Erro");
                alert.setHeaderText(null);
                alert.setContentText("Erro, CPF inválido.");
                alert.showAndWait();
            }else{
                
                if (tc.validacaoTelefone(crud_TelefoneCliente.getText()) == false){
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Erro");
                    alert.setHeaderText(null);
                    alert.setContentText("Erro, número de telefone inválido.");
                    alert.showAndWait();
                }
                else{
                    if(myDatePicker.getValue() == null || myDatePicker.getValue().isBefore(dataAtual)){
                        alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Erro");
                            alert.setHeaderText(null);
                            alert.setContentText("Erro, ao escolher a data, escolha a partir do dia atual.");
                            alert.showAndWait();
                            resultadoCadastro = -2;
                    }else{
                        resultadoCadastro = cDao.cadastroCliente(crud_cpfCliente.getText(), crud_nomeCliente.getText(),
                        crud_TelefoneCliente.getText(), data_marcada, crud_ServicoCliente.getValue());
                        studentShowData();
                    }
                }
            }
        }
        
        if (resultadoCadastro == 1){

            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Beauty");
            alert.setContentText("Cliente adicionado com sucesso");
            alert.showAndWait();
        }else{
            if(resultadoCadastro == 2){
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Beauty");
                alert.setContentText("A data não é permitida, precisa escolher a partir do dia atual.");
                alert.showAndWait();
            }
        }
    }
    
     

    public boolean validacaoCpf(String cpf){
        boolean res = false;
        
        if (cpf.length()!=11){
            res = false;
        }
        else{
            res = true;
        }
        return res;
    }
    
    public boolean validacaoTelefone(String telefone){
        boolean res = false;
        String [] tel = new String [11];
        tel = telefone.split("");
        if (telefone.length()!=11 || !tel[2].equals("9")){
            res = false;
        }
        else{
            
            res = true;
        }
        return res;
    }
    
    public ObservableList<String> servicosList() {
        ClienteDao clienteDao = new ClienteDao();
        String[] servicosList = clienteDao.selectServicos();
        List<String> sList = new ArrayList<>();
        
        for(String data: servicosList){
            sList.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(sList);
        crud_ServicoCliente.setItems(listData);
        return listData;
    }
    
    public ObservableList<Cliente> clienteListDados() throws SQLException{
        Cliente novoCliente = new Cliente();
        listDadosCliente.add(novoCliente);
        
        
        ClienteDao clienteDao = new ClienteDao();

        listDadosCliente = clienteDao.selectDadosCliente();

        return listDadosCliente;
    }
    

    @FXML
    public void studentSelectData(){
        Cliente scliente = crud_tableView.getSelectionModel().getSelectedItem();
        int num = crud_tableView.getSelectionModel().getSelectedIndex();
        
        if ((num-1) < -1){
            return;
        }
        //Cliente cli = new Cliente();
        crud_nomeCliente.setText(String.valueOf(scliente.getNome()));
        crud_cpfCliente.setText(String.valueOf(scliente.getCpf()));
        crud_ServicoCliente.setValue(scliente.getServico());
        crud_TelefoneCliente.setText(String.valueOf(scliente.getTelefone()));
        myDatePicker.setValue(LocalDate.parse(scliente.getData()));
        Object obj = crud_col_OrdemServico.getCellData(num);
        ordema = (Integer) obj;
        
    }
    
    public void studentShowData() throws SQLException {
        clientes = clienteListDados();
        
        
        crud_col_nomeCliente.setCellValueFactory(new PropertyValueFactory<>("nome"));
        crud_col_CpfCliente.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        crud_col_Data.setCellValueFactory(new PropertyValueFactory<>("data"));
        crud_col_Servico.setCellValueFactory(new PropertyValueFactory<>("servico"));
        crud_col_telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        crud_col_OrdemServico.setCellValueFactory(new PropertyValueFactory<>("ordem_servico"));

        crud_tableView.setItems(clientes);
    }
    
    
    @FXML
    public void getDate(ActionEvent event) {
        //myDatePicker.setValue(LocalDate.now());
        LocalDate selectedDate = myDatePicker.getValue();
        Cliente cliente = new Cliente();
    
    if (selectedDate != null && selectedDate.isBefore(LocalDate.now())) {
        // A data selecionada é anterior ao dia atual, exiba um alerta de erro
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText("Data inválida");
        alert.setContentText("Por favor, selecione uma data posterior a hoje.");
        alert.showAndWait();
    } else {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        data_marcada = selectedDate.format(formatter);
        cliente.setData(data_marcada);
    }
}



    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        servicosList();
        
        idiomas_combobox.getItems().addAll("Espanhol (Spanish)", "Inglês (English)", "Português(Portuguese)");
        try {
            studentShowData();
        } catch (SQLException ex) {
            Logger.getLogger(TelaCrudController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        crud_cpfCliente.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                crud_cpfCliente.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        crud_TelefoneCliente.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                crud_TelefoneCliente.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        crud_nomeCliente.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("[a-zA-Z]*")) {
                crud_nomeCliente.setText(newValue.replaceAll("[^a-zA-Z]", ""));
            }
        });
        
    }    
    
}
