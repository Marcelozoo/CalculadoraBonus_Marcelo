package presenters;

import services.CalculadoraEstatisticaService;
import services.arquivos.ImportacaoDeArquivosService;
import views.CalculadoraEstatisticaView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.DadosPeso;

public class CalculadoraEstatisticaPresenter {

    final private CalculadoraEstatisticaView tela;
    final private ImportacaoDeArquivosService importarArquivoService;
    final private ResultadosCalculosPresenter resultadosCalculos;
    final private CalculadoraEstatisticaService calculadora;
    private ArrayList<Double> dados;
    private DadosPeso peso;

    public CalculadoraEstatisticaPresenter() {

        this.tela = new CalculadoraEstatisticaView();
        this.resultadosCalculos = new ResultadosCalculosPresenter();
        this.importarArquivoService = new ImportacaoDeArquivosService();
        this.calculadora = new CalculadoraEstatisticaService();

        configuraBotoesView();
    }

    private void configuraBotoesView() {

        tela.getjMenuItem2().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    importaArquivos();
                } catch (RuntimeException excecao) {
                    JOptionPane.showMessageDialog(null, excecao.getMessage());
                }

            }
        });

        tela.getjMenuItem1().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verificaArquivoImportado();
                } catch (RuntimeException excecao) {
                    JOptionPane.showMessageDialog(null, excecao.getMessage());
                }
            }
        });

        tela.getjMenuItem3().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    verificaCalculoRealizado();
                } catch (RuntimeException excecao) {
                    JOptionPane.showMessageDialog(null, excecao.getMessage());
                }
            }
        });

    }

    private void importaArquivos() {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION){
            dados = importarArquivoService.importarDados(fileChooser.getSelectedFile(), result, fileChooser);
        }

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
            model.addRow(new Object[] { dados.get(i) });
        }
        alteraJLabelQtdElementos();
    }

    private void alteraJLabelQtdElementos() {
        JLabel label = tela.getjLabel1();
        label.setText("Quantidade de dados: " + dados.size());
    }

    private void verificaArquivoImportado() {
        if (dados == null || dados.isEmpty()) {
            throw new RuntimeException("Nenhum Arquivo foi importado");
        } else {
            mostraResultados();
        }
    }

    private void mostraResultados() {

        peso = new DadosPeso(dados);

        calculadora.calcular(peso);

        resultadosCalculos.mostrarCalculos(peso.getResultados());

    }

    private void verificaCalculoRealizado() {
        if (peso == null) {
            throw new RuntimeException("Nenhum calculo foi realizado!");
        } else {
            resultadosCalculos.mostrarCalculos(peso.getResultados());
        }
    }

}
