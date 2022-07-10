package battleship.java;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BatalhaNaval {
    public static PainelInicial painel;

    public static void JogoSolo()
    {
        Jogador j = new Jogador();
        JogadorComputador jc = new JogadorComputador();
        j.setOponenteSolo(jc);
        jc.setOponenteSolo(j);
        jc.colocarEmbarcacoes();
    }

    public static void JogoMultiplayer(boolean ehServidor, String ip) throws IOException {
        Socket connection;
        ObjectOutputStream output;
        ObjectInputStream input;
        if (ehServidor){
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
            connection = new Socket(ip, 8888);
            output = new ObjectOutputStream(connection.getOutputStream());
            input = new ObjectInputStream(connection.getInputStream());
            System.out.println("conectado");
        }
        Jogador jogador = new Jogador();
        jogador.setInputOutput(input, output);
        jogador.comecar(ehServidor);
    }

    public static void main(String[] args) {
        painel = new PainelInicial();
//        OpcoesConexao opcoes = new OpcoesConexao();
    }
}
