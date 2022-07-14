
package battleship.java;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeitorRede implements Runnable {
    private final ObjectInputStream input;
    private final TabuleiroAtaque tAtaque;
    private final TabuleiroDefesa tDefesa;
    private boolean iniciou = false;
    private final boolean comeca;
    public LeitorRede(ObjectInputStream input, TabuleiroDefesa tDefesa, TabuleiroAtaque tAtaque, boolean comeca) {
        this.input = input;
        this.tAtaque = tAtaque;
        this.tDefesa = tDefesa;
        this.comeca = comeca;
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
                    // Se for booleano, verificamos se eh o sinal de inicio
                    if (!iniciou){
                        if (comeca){
                            tAtaque.ativarBotoes(true);
                        }
                        iniciou = true;
                    }
                    else {
                        // Se nao for o sinal de inicio, e a resposta de um ataque
                        boolean booleano = (boolean) Recebido;
                        System.out.println("era booleano: " + booleano);
                        tAtaque.respostaAtaque((boolean) Recebido);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                // Oponente se desconecta ao ganhar
                JOptionPane.showMessageDialog(tDefesa, "O oponente venceu!");
                System.exit(0);
            }

        }
    }

}