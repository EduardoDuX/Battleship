package battleship.java;

public class BatalhaNaval {
    static PainelInicial painel;
    public static void JogoSolo()
    {
        Jogador j = new Jogador();
        JogadorComputador jc = new JogadorComputador();
        j.setOponente(jc);
        jc.setOponente(j);
        jc.colocarEmbarcacoes();
    }
    public static void main(String[] args) {
        painel = new PainelInicial();

    }
}
