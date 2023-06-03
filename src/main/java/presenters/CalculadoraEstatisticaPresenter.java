package presenters;

import Services.ImportacaoDeArquivosService;
import View.CalculadoraEstatisticaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CalculadoraEstatisticaPresenter {

    final private CalculadoraEstatisticaView tela;
    final private ImportacaoDeArquivosService importarArquivoService;
    final private ResultadosCalculosPresenter resultadosCalculos;
    private ArrayList<Double> dados;

    public CalculadoraEstatisticaPresenter() {

        this.tela = new CalculadoraEstatisticaView();
        this.resultadosCalculos = new ResultadosCalculosPresenter();
        this.importarArquivoService = new ImportacaoDeArquivosService();


        configuraView();
    }

    private void configuraView() {

        tela.getjMenuItem2().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    importaArquivos();
                } catch (RuntimeException excecao) {
                    JOptionPane.showMessageDialog(null, excecao);
                }
                
            }
        });

        tela.getjMenuItem1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verificaArquivoImportado();
                } catch (RuntimeException excecao) {
                    JOptionPane.showMessageDialog(null, excecao);
                }
            }
        });

        tela.getjMenuItem3().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verificaCalculoRealizado();
                } catch (RuntimeException excecao) {
                    JOptionPane.showMessageDialog(null, excecao);
                }
            }
        });

    }

    private void importaArquivos() {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        dados = importarArquivoService.importarDados(fileChooser.getSelectedFile(), result, fileChooser);

        if (!dados.isEmpty()) {
            preencheTabela(dados);
        } else if (dados.isEmpty() && fileChooser.getSelectedFile() != null) {
            throw new RuntimeException("Arquivo inválido. Por favor, selecione um arquivo CSV ou TXT!");
        }
    }

    private void preencheTabela(ArrayList<Double> dados) {

        DefaultTableModel model = (DefaultTableModel) tela.getJTable1().getModel();
        model.setRowCount(0);

        for (int i = 0; i < dados.size(); i++) {
            model.addRow(new Object[]{dados.get(i)});
        }

        JLabel label = tela.getjLabel1();
        label.setText("Quantidade de dados: " + dados.size());

    }

    private void verificaArquivoImportado() {
        if (dados == null) {
            throw new RuntimeException("Nenhum Arquivo foi importado");
        } else {
            resultadosCalculos.realizarCalculos(dados);
        }
    }

    private void verificaCalculoRealizado() {
        if (dados == null) {
            throw new RuntimeException("Nenhum calculo foi realizado!");
        } else {
            resultadosCalculos.visualizarCalculos(dados);
        }
    }

}
