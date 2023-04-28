
package Model;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;



public interface IImportarDados {
    
    public ArrayList<Integer> importarDados(File arquivo);
    public boolean verificaArquivo(JFileChooser fileChooser, int result);

    
}