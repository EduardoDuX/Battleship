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
        GridBagConstraints c = new GridBagConstraints();

        // Cria tabuleiros
        tAtaque = new TabuleiroAtaque();
        tDefesa = new TabuleiroDefesa();
        tDefesa.settAtaque(tAtaque);

        // Jogador comeca com tabuleiro de ataque desativado,
        // ativa apos posicionar barcos
        tAtaque.ativarBotoes(false);

        // Cria descricoes para os tabuleiros
        JLabel textoDefesa = new JLabel("Utilize esse tabuleiro para posicionar seus navios", SwingConstants.CENTER);
        JLabel textoAtaque = new JLabel("Utilize esse tabuleiro atingir os navios do oponente", SwingConstants.CENTER);

        // Cria botao de trocar orientacao do barco
        JButton botao = new JButton(tDefesa.getControleOrientacao() ? "vertical" : "horizontal");
        Jogador.OrientacaoButtonHandler handler = new Jogador.OrientacaoButtonHandler();
        botao.addActionListener(handler);
        botao.setPreferredSize(new Dimension(100,20));

        // Adiciona elementos ao painel de jogo
        c.fill = GridBagConstraints.HORIZONTAL;

        //adiciona descricao tabuleiro defesa
        c.gridx = 0;
        c.gridy = 0;
        pane.add(textoDefesa, c);

        // Adiciona espaco entre as descricoes
        c.gridx = 1;
        pane.add(Box.createHorizontalStrut(50),c);

        // Adiciona descricao tabuleiro ataque
        c.gridx = 2;
        pane.add(textoAtaque, c);

        // Adiciona tabuleiro defesa
        c.gridx = 0;
        c.gridy = 1;
        pane.add(tDefesa, c);

        // Adiciona botao troca orientacao barco
        c.gridy = 1;
        c.gridx = 1;
        pane.add(botao, c);

        // Adiciona tabuleiro ataque
        c.gridx = 2;
        c.gridy = 1;
        pane.add(tAtaque, c);

        // Ultimas configuracoes da janela
        pane.pack();
        pane.setResizable(false);
        pane.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void comecar(boolean b){
        tDefesa.setComeca(b);
    }

    public void setOponenteSolo(Jogador oponente) {
        tDefesa.setOponente(oponente);
        tAtaque.setOponente(oponente);
    }

    public void setInputOutput(ObjectInputStream input,ObjectOutputStream output) {
        // Configura output nos tabuleiros (static)
        Tabuleiro.setOutput(output);

        // Cria thread para receber informacoes por rede e inicia
        Leitor leitor = new Leitor(input, tDefesa, tAtaque);
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
