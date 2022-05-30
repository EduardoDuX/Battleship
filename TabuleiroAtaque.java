package batalhanaval;

public class TabuleiroAtaque extends Tabuleiro{
    private int acertos;

    public TabuleiroAtaque(){
        super();
        acertos = 0;
    }

    public void verificaVitoria(Jogador j){
        if (acertos == 30){
            System.out.println("O jogador " + j + "ganhou!");
            System.exit(0);
        }
    }
}
