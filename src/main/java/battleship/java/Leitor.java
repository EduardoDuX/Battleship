
package battleship.java;

import java.io.IOException;
import java.io.ObjectInputStream;

public class Leitor implements Runnable {

    private ObjectInputStream input;
    private TabuleiroAtaque tAtaque;
    private TabuleiroDefesa tDefesa;
    public Leitor(ObjectInputStream input, TabuleiroDefesa tDefesa, TabuleiroAtaque tAtaque) {
        this.input = input;
        this.tAtaque = tAtaque;
        this.tDefesa = tDefesa;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object b = input.readObject();
                System.out.println("objeto lido");
                if (b instanceof Posicao){
                    System.out.println("era posicao");
                    Posicao p = (Posicao) b;
                    tDefesa.tabuleiroVerificaAcerto(p);
                    tAtaque.ativarBotoes(true);
                } else {
                    System.out.println("era booleano " + (boolean) b);
                    tAtaque.setAcertou((boolean) b);
//                    tAtaque.setEsperando(false);
                    System.out.println("qualquer porra");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
    }

}