package battleship;
import javax.swing.*;
import java.awt.*;

public abstract class BatalhaNaval{


    public static void main(String[] args) {
        Jogador j = new Jogador();
        JogadorComputador jc = new JogadorComputador();
        jc.ColocarEmbarcacoes();

    }
}
