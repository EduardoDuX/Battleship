package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TabuleiroDefesa extends Tabuleiro{
    private final ArrayList<Embarcacao> submarinos;
    private final ArrayList<Embarcacao> portaAvioes;
    private final ArrayList<Embarcacao> contraTorpedeiros;
    private final ArrayList<Embarcacao> naviosTanque;
    protected static final int NUM_SUBS = 4;
    protected static final int NUM_TANKS = 2;
    protected static final int NUM_CT = 3;
    private boolean comeca = false;
    private TabuleiroAtaque tAtaque;

    public boolean controleOrientacao = true;
    protected int barcos = 0;

    public TabuleiroDefesa(){
        super();
        submarinos = new ArrayList<>();
        portaAvioes = new ArrayList<>();
        contraTorpedeiros = new ArrayList<>();
        naviosTanque = new ArrayList<>();
        for(int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10;coluna++){
                TabuleiroDefesa.DefenseButtonHandler handler = new TabuleiroDefesa.DefenseButtonHandler(grelha[linha][coluna]);
                botoes[linha][coluna].addActionListener(handler);
            }
        }
    }

    public void setComeca(boolean comeca) {
        this.comeca = comeca;
    }

    public void settAtaque(TabuleiroAtaque tAtaque) {
        this.tAtaque = tAtaque;
    }

    public void trocaOrientacao(){
        controleOrientacao = !controleOrientacao;
    }

    public boolean getControleOrientacao() {
        return controleOrientacao;
    }

    public boolean tabuleiroVerificaAcerto(Posicao p) throws IOException {
        try {
            p = (Posicao) input.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (Embarcacao e: submarinos){
            if (e.embarcacaoVerificaAcerto(p))
                if (output != null)
                    output.writeBoolean(true);
                return true;
        }
        for (Embarcacao e: naviosTanque){
            if (e.embarcacaoVerificaAcerto(p))
                if (output != null)
                    output.writeBoolean(true);
                return true;
        }
        for (Embarcacao e: contraTorpedeiros){
            if (e.embarcacaoVerificaAcerto(p))
                if (output != null)
                    output.writeBoolean(true);
                return true;
        }
        for (Embarcacao e: portaAvioes){
            if (e.embarcacaoVerificaAcerto(p))
                if (output != null)
                    output.writeBoolean(true);
                return true;
        }
        grelha[p.getIntLinha()][p.getColuna()].getBotao().setBackground(Color.WHITE);
        if (output != null)
            output.writeBoolean(false);
        return false;
    }

    public void colocarEmbarcacao(Embarcacao e, Posicao p, boolean orientacao) throws PosInvalidaException{
        //orientacao == 1(vertical); 0(horizontal)
        int tam = e.getTamanho();
        Posicao[] posicoes = new Posicao[tam];
        int linha = (int)p.getLinha() - (int)'A';
        int coluna = p.getColuna();
        JButton b;

        if (orientacao){
            // Verifica acesso indevido de posicao
            if(linha + tam > 10)
                throw new PosInvalidaException();

            for (int i = 0; i < tam; i++){
                if (grelha[linha+i][coluna].getBotao().getBackground().equals(Color.decode("#808080")))
                    throw new PosInvalidaException();
            }

            for (int i = 0; i < tam; i++){
                b = grelha[linha+i][coluna].getBotao();
                b.setBackground(Color.decode("#808080"));
                posicoes[i] = grelha[linha+i][coluna];
            }
        } else {
            // Verifica acesso indevido de posicao
            if(p.getColuna()+tam > 10)
                throw new PosInvalidaException();

            for (int i = 0; i < tam; i++){
                if (grelha[linha][coluna+i].getBotao().getBackground().equals(Color.decode("#808080")))
                    throw new PosInvalidaException();
            }

            for (int i = 0; i < tam; i++){
                b = grelha[linha][coluna+i].getBotao();
                b.setBackground(Color.decode("#808080"));
                posicoes[i] = grelha[linha][coluna+i];
            }
        }
        e.setPosicoes(posicoes);
        if (e instanceof Submarino){
            submarinos.add(e);
        }
        if (e instanceof PortaAvioes){
            portaAvioes.add(e);
        }
        if (e instanceof ContraTorpedeiro){
            contraTorpedeiros.add(e);
        }
        if (e instanceof NavioTanque){
            naviosTanque.add(e);
        }
    }

    private class DefenseButtonHandler implements ActionListener {
        private final Posicao posicao;

        public DefenseButtonHandler(Posicao p){
            this.posicao = p;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            barcos++;
            try {
                if (barcos <= NUM_SUBS) {
                    colocarEmbarcacao(new Submarino(), posicao, controleOrientacao);
                } else if (barcos <= NUM_CT + NUM_SUBS) {
                    colocarEmbarcacao(new ContraTorpedeiro(), posicao, controleOrientacao);
                } else if (barcos <= NUM_TANKS + NUM_CT + NUM_SUBS) {
                    colocarEmbarcacao(new NavioTanque(), posicao, controleOrientacao);
                } else {
                    colocarEmbarcacao(new PortaAvioes(), posicao, controleOrientacao);
                }
            } catch (PosInvalidaException e) {
                barcos--;
            }

            if (barcos == 10){
                ativarBotoes(false);
                tAtaque.ativarBotoes(comeca);
            }
        }
    }
}
