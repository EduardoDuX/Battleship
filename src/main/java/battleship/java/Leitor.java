
package battleship.java;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Leitor implements Runnable {
    private final ObjectInputStream input;
    private final TabuleiroAtaque tAtaque;
    private final TabuleiroDefesa tDefesa;
    public Leitor(ObjectInputStream input, TabuleiroDefesa tDefesa, TabuleiroAtaque tAtaque) {
        this.input = input;
        this.tAtaque = tAtaque;
        this.tDefesa = tDefesa;
    }

    @Override
    public void run() {
        // Fica rebendo inputs indefinidamente
        while (true) {
            try {

                // Recebe um objeto
                Object Recebido = input.readObject();
                System.out.print("Objeto lido, ");

                if (Recebido instanceof Posicao posicao){
                    // Se for posicao, verificamos o acerto
                    System.out.println("era posicao: " + posicao);
                    tDefesa.tabuleiroVerificaAcerto(posicao);
                    tAtaque.ativarBotoes(true);
                } else {
                    // Se for booleano, marcamos acerto ou erro
                    boolean booleano = (boolean) Recebido;
                    System.out.println("era booleano: " + booleano);
                    tAtaque.respostaAtaque((boolean) Recebido);
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

}