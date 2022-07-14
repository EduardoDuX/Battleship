package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BatalhaNaval {

    public static PainelInicial painel;

    public static void JogoSolo() {
        // Cria jogador e bot
        Jogador j = new Jogador();
        JogadorComputador jc = new JogadorComputador();
        j.setOponenteSolo(jc);
        jc.setOponenteSolo(j);

        // Bot posiciona suas embarcacoes
        jc.colocarEmbarcacoes();

        // Jogador sempre comeca
//        j.comecar(true);
    }

    public static void JogoMultiplayer(boolean ehServidor, String ip) throws IOException {
        Socket connection;
        ObjectOutputStream output;
        ObjectInputStream input;

        if (ehServidor){
            // Servidor cria conexao e aguarda cliente
            ServerSocket server = new ServerSocket(8888, 1);
            System.out.println("Aguardando conexao");
            JFrame ac = new JFrame("Aguardando conexao");
            ac.setLayout(new FlowLayout());
            JLabel acl = new JLabel("Aguardando conexao");
            ac.add(acl);
            ac.setVisible(true);
            ac.pack();
            connection = server.accept();
            input = new ObjectInputStream(connection.getInputStream());
            output = new ObjectOutputStream(connection.getOutputStream());
            ac.dispose();
            System.out.println("conectado");
        } else {
            // Cliente acessa um servidor de jogo
            connection = new Socket(ip, 8888);
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
            System.out.println("conectado");
        }
        // Iniciando jogador
        Jogador jogador = new Jogador();
        jogador.OpcoesConexao(input, output, ehServidor); // servidor sempre comeca
    }
    public static void main(String[] args) {
        painel = new PainelInicial();
    }
}
