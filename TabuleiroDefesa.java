package battleship;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TabuleiroDefesa extends Tabuleiro{ 
//    private Embarcacao[] submarinos;
//    private Embarcacao[] portaAvioes;
//    private Embarcacao[] contraTorpedeiros;
//    private Embarcacao[] naviosTanque;
//    private Embarcacao[][] embarcacoes = {submarinos, portaAvioes, contraTorpedeiros, naviosTanque};

//    public TabuleiroDefesa(){
//        super();
//        int i;
//        submarinos = new Embarcacao[4];
//        for(i=0; i< 4; i++)
//            submarinos[i] = new Submarino();
//        
//        contraTorpedeiros = new Embarcacao[3];
//        for(i=0; i< 3; i++)
//            submarinos[i] = new ContraTorpedeiro();
//        
//        naviosTanque = new Embarcacao[2];
//        for(i=0; i< 2; i++)
//            submarinos[i] = new NavioTanque();
//        
//        portaAvioes = new Embarcacao[1];
//        portaAvioes[0] = new PortaAvioes();
//        
//        
//        for(int linha = 0; linha < 10; linha++){
//            for (int coluna = 0; coluna < 10;coluna++){
//                TabuleiroDefesa.DefenseButtonHandler handler = new TabuleiroDefesa.DefenseButtonHandler(grelha[linha][coluna]);
//                botoes[linha][coluna].addActionListener(handler);
//            }
//        }
//    }
    
    private ArrayList<Embarcacao> submarinos;
    private ArrayList<Embarcacao> portaAvioes;
    private ArrayList<Embarcacao> contraTorpedeiros;
    private ArrayList<Embarcacao> naviosTanque;

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
    
    public void colocarEmbarcacao(Embarcacao e, Posicao p, boolean orientacao) throws PosInvalidaException{
        //orientacao == 1(vertical); 0(horizontal)
        int tam = e.getTamanho();
        Posicao[] posicoes = new Posicao[tam];
        if (orientacao){ 
            int c = (int)p.getLinha();
            
            // Verifica acesso indevido de posicao
            if(c+tam > 10)
                throw new PosInvalidaException();
            
            for (int i = 0; i < tam; i++){
                posicoes[i].setLinha((char)c);
                posicoes[i].setColuna(p.getColuna());
                c++;
            }
        } else {
            
            // Verifica acesso indevido de posicao
            if(p.getColuna()+tam > 10)
                throw new PosInvalidaException();
            
            for (int i = p.getColuna(); i < tam; i++){
                posicoes[i].setLinha(p.getLinha());
                posicoes[i].setColuna(i);
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
            JButton b = (JButton) event.getSource();
            b.setEnabled(false);   
            System.out.println(posicao);
        }
    }
}
