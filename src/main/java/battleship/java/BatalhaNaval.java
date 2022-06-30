package battleship.java;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        Jogador p1 = new Jogador();

        if (ehServidor){
            ServerSocket server = new ServerSocket(8888, 1);
            System.out.println("aguardando conexao");
            connection = server.accept();
            p1.podeAtacar(true);
        } else {
            connection = new Socket(ip, 8888);
            p1.podeAtacar(false);
        }
        ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
        ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
        p1.setInputOutput(input,output);
    }

    public static void main(String[] args) {
//        painel = new PainelInicial();
        OpcoesConexao opcoes = new OpcoesConexao();
    }
}
