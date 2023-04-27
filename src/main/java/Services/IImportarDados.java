
package Services;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;


public interface IImportarDados {
    
    public ArrayList<Integer> importarDados(File arquivo);
    public boolean verificaArquivo(JFileChooser fileChooser, int result);

    
}
