package Presenter;

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

    final private CalculadoraEstatisticaView tela = new CalculadoraEstatisticaView();
    final private ImportacaoDeArquivosService importarArquivoService;
    final private ResultadosCalculosPresenter resultadosCalculos;
    private ArrayList<Double> dados;
    private boolean statusImportacao = false;
    private boolean statusCalculo = false;
    

    public CalculadoraEstatisticaPresenter() {

        this.resultadosCalculos = new ResultadosCalculosPresenter();
        this.importarArquivoService = new ImportacaoDeArquivosService();

        importacaoDados();
        calcularEstatisticas();
        mostrarEstatistica();
    }

    private void importacaoDados() {
        tela.getjMenuItem2().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);

                dados = importarArquivoService.importarDados(fileChooser.getSelectedFile(), result, fileChooser);
                if (!dados.isEmpty()) {
                    preencheTabela(dados);
                }else if(dados.isEmpty() && fileChooser.getSelectedFile() != null){
                    JOptionPane.showMessageDialog(null, "Arquivo inválido. Por favor, selecione um arquivo CSV ou TXT.");
                }
            }
        });
    }

    private void preencheTabela(ArrayList<Double> dados) {

        DefaultTableModel model = (DefaultTableModel) tela.getJTable1().getModel();
        model.setRowCount(0);
     

        for (int i = 0; i < dados.size(); i++) {
            model.addRow(new Object[]{dados.get(i)});
        }

        JLabel label = tela.getjLabel1();
        label.setText("Quantidade de dados: " + dados.size());
        this.statusImportacao = true;

    }

    private void calcularEstatisticas() {

        tela.getjMenuItem1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!statusImportacao) {
                    JOptionPane.showMessageDialog(null, "Nenhum arquivo foi importado!");

                } else {
                    resultadosCalculos.realizarCalculos(dados);
                    statusCalculo = true;

                }

            }
        });
    }

    private void mostrarEstatistica() {
        tela.getjMenuItem3().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!statusCalculo) {
                    JOptionPane.showMessageDialog(null, "Nenhum calculo foi realizado!");

                } else {
                    resultadosCalculos.visualizarCalculos(dados);

                }

            }
        });
    }

}
