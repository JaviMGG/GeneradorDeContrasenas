/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxmlapplication;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author javim
 */
public class GeneraContraseñasController implements Initializable {

    @FXML
    private Label textoA;
    @FXML
    private Label textoB;
    @FXML
    private RadioButton boton1;
    @FXML
    private RadioButton boton2;
    public static String ultimaRes = "";
    @FXML
    private Label textoC;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textoA.setVisible(false);
        textoB.setVisible(false);
        textoC.setVisible(false);
    }    

    @FXML
    private void generar_contraseña(ActionEvent event) throws IOException{
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("Contraseña Generada");
        a.setContentText("Su contraseña generada fue: " + crearContraseña());
        a.show();
    }

    @FXML
    private void historial(ActionEvent event) {
        try {
            String contenido = new String(Files.readAllBytes(Paths.get("contraseñas.txt")));
            
            // Crear nueva ventana
            Stage stage = new Stage();
            TextArea textArea = new TextArea(contenido);
            javafx.scene.Scene scene = new javafx.scene.Scene(textArea, 600, 400);
            
            stage.setTitle("ÚLTIMA CONTRASEÑA GENERADA: ");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No se pudo abrir el archivo");
            alert.setContentText("El archivo crear.txt no existe o no se puede leer.");
            alert.showAndWait();
        }
    }
    

    @FXML
    private void mostrar_texto_crear(ActionEvent event) {
        if(boton1.isSelected()){
            textoA.setVisible(true);
        }
        else{
            textoA.setVisible(false);
        }
    }

    @FXML
    private void mostrar_texto_historial(ActionEvent event) {
        if(boton2.isSelected()){
            textoB.setVisible(true);
            textoC.setVisible(true);
        }
        else{
            textoB.setVisible(false);
            textoC.setVisible(false);
        }
    }
    
    // Crear una contraseña de 30 carácteres y guardarla en un archivo
    public static String crearContraseña() throws IOException{

        //System.out.println("Voy a crear una contraseña de 30 carácteres");
        
        String res = "";
        String [] caracteres = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M","N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m","n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",  ",", ".", "/","!", "@", "#", "$", "%", "^", "&", "|", ":", "\"", "<", ">", "?",};
        Date fecha = new Date();
        
        while(res.length() < 30){
            int pos = (int)(Math.random() * caracteres.length);
            res += caracteres[pos];
        }

        try{
            // La contraseña debe ser guardada en un archivo llamado "contraseñas.txt"
            File archivo = new File("contraseñas.txt");
            // Si el archivo no existe, lo crea
            if(!archivo.exists()){
                archivo.createNewFile(); // Si el archivo existe, lo sobreescribe
            }else{}   

            // Escribir en el archivo
                PrintWriter pw = new PrintWriter("contraseñas.txt");
                pw.println("Tu nueva contraseña es:    " + res + "    y fue creada el " + fecha);
                pw.flush();
                pw.close();
                ultimaRes = res;
        
        }catch(FileNotFoundException e){ System.out.println("NO SE ENCUENTRA EL ARCHIVO");}    
            return res;
    }
}
