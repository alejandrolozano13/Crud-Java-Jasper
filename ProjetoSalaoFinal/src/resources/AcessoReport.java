
import ConexaoBD.ConexaoBancoDeDados;
import ConexaoBD.ConexaoJasper;
import java.sql.Connection;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author aleja
 */
public  class AcessoReport {
    
    private static JasperReport jreport;
    private static JasperViewer jviewer;
    private static JasperPrint jprint;
    
    ConexaoBancoDeDados bd = new ConexaoBancoDeDados();
    ConexaoJasper conJ = new ConexaoJasper();
    
    public static void createReport(Connection connection, Map<String,Object>map, InputStream by){
        try{
            
            jreport = (JasperReport)JRLoader.loadObject(by);
            jprint = JasperFillManager.fillReport(jreport, map, connection);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
        
    public static void showReport(){
        jviewer = new JasperViewer(jprint);
        jviewer.setVisible(true);
    }
    
    /*public void printReport(){
        Map map = new HashMap<String, Object>();
        Connection con = bd.connectDb();
        
        Report.createReport(con, map, conJ.getReport("report_name", "report_jasper"));
        Report.showReport();
    }*/
}
