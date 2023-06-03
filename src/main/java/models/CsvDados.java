package models;

import models.interfaces.IImportarDados;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JFileChooser;




public class CsvDados implements IImportarDados {

    @Override
    public  ArrayList<Double> importarDados(File arquivo) {
        
        ArrayList<Double> dadosArquivo = new ArrayList();
             
            try(Scanner scanner = new Scanner(arquivo)){
                while(scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    double numero = Double.parseDouble(linha);
                    dadosArquivo.add(numero); 
                }
                scanner.close();
                
            } catch(FileNotFoundException e) {
                System.out.println("Arquivo nao encontrado!");
            }            
            return dadosArquivo;
    }
    
       
    @Override
    public boolean verificaArquivo(JFileChooser fileChooser, int result) {
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf(".") + 1);
            return extension.equals("csv");

        }
        return false;
    }

}
