public class PortaAvioes extends Embarcacao {

    private Posicao[] posicoes;

    public PortaAvioes(){
        posicoes = new Posicao[tamanho];
        this.tamanho = 5;
    }
}
