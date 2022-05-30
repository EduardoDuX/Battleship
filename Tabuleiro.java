package batalhanaval;

public class Tabuleiro {
    protected Posicao[][] grelha;

    public Tabuleiro(){
        grelha = new Posicao[10][10];
        int c = (int) 'a';
        for(int i = 0; i < 11; i++){
            for (int j = 0; j < 11;j++){
                grelha[i][j].setColuna(j);
                grelha[i][j].setLinha((char)c);
                c++;
            }
        }
    }
}
