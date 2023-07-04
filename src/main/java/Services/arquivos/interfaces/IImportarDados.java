
package services.arquivos.interfaces;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;



public interface IImportarDados {
    
    public ArrayList<Double> importarDados(File arquivo);
    public boolean verificaArquivo(JFileChooser fileChooser, int result);

    
}
