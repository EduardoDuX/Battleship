import javax.swing.*;
import java.awt.*;

public abstract class BatalhaNaval{


    public static void main(String[] args) {
        Tabuleiro ataque = new TabuleiroAtaque();
        Tabuleiro defesa = new TabuleiroDefesa();
        JFrame f = new JFrame();
        f.setVisible(true);
        f.setLayout(new BorderLayout());
        f.add(ataque, BorderLayout.EAST);
        JLabel blank = new JLabel("                                ");
        f.add(blank,BorderLayout.CENTER);
        f.add(defesa, BorderLayout.WEST);
        f.pack();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
