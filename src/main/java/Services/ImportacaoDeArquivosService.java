package Services;

import Model.CsvDados;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class ImportacaoDeArquivosService {

    ArrayList<IImportarDados> importarArquivos;

    public ImportacaoDeArquivosService() {
        
        importarArquivos = new ArrayList();
        importarArquivos.add(new CsvDados());
    }
    
    public ArrayList<Integer> importarDados(File Arquivo,int result, JFileChooser fileChooser){
        
       
    
        
        for(IImportarDados arquivo : importarArquivos){
            if(arquivo.verificaArquivo(fileChooser, result)){
                return arquivo.importarDados(Arquivo);
            }
        }
        
        return new ArrayList();
                
    }
}
