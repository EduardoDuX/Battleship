package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Jogador {
    protected TabuleiroAtaque tAtaque;
    protected TabuleiroDefesa tDefesa;
    protected JFrame pane;

    public Jogador(){
        // Cria painel de jogo
        pane = new JFrame("Batalha Naval");
        pane.setVisible(true);
        pane.setLayout(new GridBagLayout());
        pane.setContentPane(new JLabel(new ImageIcon("src/main/java/imagens/imagem_bg.jpeg")));

        // Cria tabuleiros
        tAtaque = new TabuleiroAtaque();
        tAtaque.setBounds(690,260,500,260);
        tDefesa = new TabuleiroDefesa();
        tDefesa.setBounds(15,260,500,260);
        tDefesa.settAtaque(tAtaque);

        // Jogador comeca com tabuleiro de ataque desativado,
        // ativa apos posicionar barcos
        tAtaque.ativarBotoes(false);


        // Cria botao de trocar orientacao do barco
        JButton botao = new JButton(tDefesa.getControleOrientacao() ? "vertical" : "horizontal");
        Jogador.OrientacaoButtonHandler handler = new Jogador.OrientacaoButtonHandler();
        botao.addActionListener(handler);
        botao.setFocusable(false);
        botao.setPreferredSize(new Dimension(100,20));
        botao.setBounds(550, 360,100,50);

        // Label tabuleiro defesa
        Font font = new Font("Arial",Font.BOLD, 16);
        JLabel defesa = new JLabel("Este é seu tabuleiro");
        defesa.setFont(font);
        defesa.setBounds(15,240,500, 20);

        // Label tabuleiro ataque
        JLabel ataque = new JLabel("Este é o tabuleiro do oponente");
        ataque.setFont(font);
        ataque.setBounds(690,240,500, 20);

        // Adiciona elementos ao painel de jogo
        pane.add(Box.createHorizontalStrut(50));
        pane.add(tDefesa);
        pane.add(botao);
        pane.add(tAtaque);
        pane.add(defesa);
        pane.add(ataque);

        // Ultimas configuracoes da janela
        pane.pack();
        pane.setResizable(false);
        pane.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setOponenteSolo(Jogador oponente) {
        tDefesa.setOponente(oponente);
        tAtaque.setOponente(oponente);
    }

    public void OpcoesConexao(ObjectInputStream input, ObjectOutputStream output, boolean comeca) {
        // Configura output nos tabuleiros (static)
        Tabuleiro.setOutput(output);

        // Cria thread para receber informacoes por rede e inicia
        LeitorRede leitor = new LeitorRede(input, tDefesa, tAtaque, comeca);
        Thread t = new Thread(leitor);
        t.start();
    }

    public boolean verificaAcerto(Posicao p){
        try {
            // Verifica se o ataque do oponente acertou
            return tDefesa.tabuleiroVerificaAcerto(p);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            // Ativa os botoes de ataque, pois é sua vez
            tAtaque.ativarBotoes(true);
        }
    }
    // Handler do botao de troca de orientacao do barco
    private class OrientacaoButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton b = (JButton) event.getSource();
            tDefesa.trocaOrientacao();
            b.setText(tDefesa.getControleOrientacao() ? "vertical" : "horizontal");
        }
    }
}
