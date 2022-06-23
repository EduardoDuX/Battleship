package battleship;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TabuleiroDefesa extends Tabuleiro{
    private ArrayList<Embarcacao> submarinos;
    private ArrayList<Embarcacao> portaAvioes;
    private ArrayList<Embarcacao> contraTorpedeiros;
    private ArrayList<Embarcacao> naviosTanque;
    protected static final int NUM_SUBS = 4;
    protected static final int NUM_TANKS = 3;
    protected static final int NUM_CT = 2;
    protected static final int NUM_PA = 1;

    public boolean controleOrientacao = true;
    protected static int barcos = 0;

    public TabuleiroDefesa(){
        super();
        submarinos = new ArrayList<Embarcacao>();
        portaAvioes = new ArrayList<Embarcacao>();
        contraTorpedeiros = new ArrayList<Embarcacao>();
        naviosTanque = new ArrayList<Embarcacao>();
        for(int linha = 0; linha < 10; linha++){
            for (int coluna = 0; coluna < 10;coluna++){
                TabuleiroDefesa.DefenseButtonHandler handler = new TabuleiroDefesa.DefenseButtonHandler(grelha[linha][coluna]);
                botoes[linha][coluna].addActionListener(handler);
            }
        }
    }

    public void trocaOrientacao(){
        controleOrientacao = !controleOrientacao;
    }

    public boolean getControleOrientacao() {
        return controleOrientacao;
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
                if (grelha[linha+i][coluna].getBotao().getBackground().equals(Color.decode("#808080")));
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
                if (grelha[linha][coluna+i].getBotao().getBackground().equals(Color.decode("#808080")));
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
        private Posicao posicao;

        public DefenseButtonHandler(Posicao p){
            this.posicao = p;
        }

        public Posicao getPosicao() {
            return posicao;
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            barcos++;
            if (barcos <= NUM_SUBS){
                colocarEmbarcacao(new Submarino(),  posicao, controleOrientacao);
            } else if (barcos <= NUM_TANKS + NUM_SUBS) {
                colocarEmbarcacao(new ContraTorpedeiro(),  posicao, controleOrientacao);
            } else if (barcos <= NUM_TANKS + NUM_CT + NUM_SUBS) {
                colocarEmbarcacao(new NavioTanque(),  posicao, controleOrientacao);
            } else {
                colocarEmbarcacao(new PortaAvioes(),  posicao, controleOrientacao);
            }

            if (barcos == 10){
                for(int linha = 0; linha < 10; linha++){
                    for (int coluna = 0; coluna < 10;coluna++){
                        botoes[linha][coluna].setEnabled(false);
                    }
                }
            }


        }
    }
}
