package batalhanaval;

import java.util.ArrayList;

public class TabuleiroDefesa extends Tabuleiro{
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
    }

    public void colocarEmbarcacao(Embarcacao e, Posicao p, boolean orientacao){
        //orientacao == 1(vertical); 0(horizontal)

        if (orientacao){
            int tam = e.getTamanho();
            Posicao[] posicoes = new Posicao[tam];
            int c = (int)p.getLinha();
            for (int i = 0; i < tam; i++){
                posicoes[i].setLinha((char)c);
                posicoes[i].setColuna(p.getColuna());
                c++;
            }
        } else {
            int tam = e.getTamanho();
            Posicao[] posicoes = new Posicao[tam];
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
        if (e instanceof Contratorpedeiro){
            contraTorpedeiros.add(e);
        }
        if (e instanceof NavioTanque){
            naviosTanque.add(e);
        }
    }


}
