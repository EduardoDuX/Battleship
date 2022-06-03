public class Submarino extends Embarcacao {

    private Posicao[] posicoes;

    public Submarino(){
        posicoes = new Posicao[tamanho];
        this.tamanho = 2;
    }
}
