public class BatalhaNaval {
    public static void main(String[] args) {
        Jogador j = new Jogador();
        JogadorComputador jc = new JogadorComputador();
        j.setOponente(jc);
        jc.setOponente(j);
        jc.colocarEmbarcacoes();
    }
}
