import javax.swing.*;
import java.awt.*;

public abstract class BatalhaNaval{


    public static void main(String[] args) {
        Tabuleiro tAtaque = new TabuleiroAtaque();
        Tabuleiro tDefesa = new TabuleiroDefesa();
        JFrame pane = new JFrame();
        pane.setVisible(true);
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel textoDefesa = new JLabel("Utilize esse tabuleiro para posicionar seus navios", SwingConstants.CENTER);
        JLabel textoAtaque = new JLabel("Utilize esse tabuleiro atingir os navios do oponente", SwingConstants.CENTER);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(textoDefesa, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        pane.add(textoAtaque, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(tDefesa, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        pane.add(tAtaque,c );
        pane.pack();
        pane.setResizable(false);
        pane.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
