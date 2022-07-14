
package battleship.java;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LeitorRede implements Runnable {
    private final ObjectInputStream input;
    private final TabuleiroAtaque tAtaque;
    private final TabuleiroDefesa tDefesa;
    private boolean podeComecar = false;
    private boolean comeca;
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
                    // Se for booleano, verificamos se e o sinal de inicio
                    if (!podeComecar){
                        if (comeca){
                            tAtaque.ativarBotoes(true);
                        }
                        podeComecar = true;
                    }
                    else {
                        // Se nao for o sinal de inicio, e a resposta de um ataque
                        boolean booleano = (boolean) Recebido;
                        System.out.println("era booleano: " + booleano);
                        tAtaque.respostaAtaque((boolean) Recebido);
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                // se o oponente desconecta, voce ganha
                JOptionPane.showMessageDialog(tAtaque, "Voce Venceu!");
                System.exit(0);
            }

        }
    }

}