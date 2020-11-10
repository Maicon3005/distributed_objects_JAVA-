/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributed.objects;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Maicon Alisson Alves Dias
 */
public class SocketServer {

    public static void main(String args[]) {

        try {

            //iniciar um ServerSocket na porta 12345
            ServerSocket server = new ServerSocket(12345);

            System.out.println("Servidor iniciado na porta 12345");

            while (true) {

                // o método accept() bloqueia a execução até que
                // o servidor receba um pedido de conexão
                Socket client = server.accept();

                //Imprime IP do cliente que solicitou acesso
                System.out.println("Cliente conectado: "
                        + client.getInetAddress().getHostAddress());

                //Objeto responsável por receber o vetor vindo do cliente
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());

                //o vetor vindo do cliente é passado por parâmetro ao método inverter numbers
                inverterNumbers((int[]) in.readObject());

                //cria uma estrutura de envio de objeto
                ObjectOutputStream send = new ObjectOutputStream(client.getOutputStream());

                send.writeObject("Bem vindo ao servidor");

                send.flush();

                send.close();

                client.close();

            }

        } catch (Exception e) {

            //trata as possíveis exceções
            System.out.println("Erro: " + e.getMessage());
        }

    }

    //método responsável por percorrer o vetor invertendo os números
    public static void inverterNumbers(int[] vector) {
        System.out.println("** Final Vector **");
        System.out.print("[");

        //estrutura de repetição responsável por percorrer o vetor
        for (int i = (vector.length - 1); i >= 0; i--) {
            //operador ternário responsável por deixar a visualização do vetor mais agradável
            System.out.print(i == 0 ? vector[i] + "]" : vector[i] + ",");
        }
    }

}
