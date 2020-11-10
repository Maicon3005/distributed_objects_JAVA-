/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distributed.objects;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author Maicon Alisson Alves Dias
 */
public class SocketClient {

    public static void main(String[] args) {

        try {
            //cria um vetor de inteiros com 10 elementos
            int vector[] = {1, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50};

            //chama o método reponsável por mostrar os valores do vetor o passando como argumento
            showNumbers(vector);

            // cria um socket cliente com o endereço e porta destino
            Socket client = new Socket("localhost", 12345);

            //cria o objeto reponsável por receber o vetor
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            //escreve o vetor
            out.writeObject(vector);

            //cria uma estrutura de recebimento de objetos
            ObjectInputStream receive = new ObjectInputStream(client.getInputStream());

            //atribui a resposta do servidor a uma variável fazando o casting para string
            String response = (String) receive.readObject();

            //exibe a resposta do servidor
            System.out.println("Mensagem: " + response);

            //encerra a entrada de fluxo
            receive.close();

            out.close();

            System.out.println("Conexão encerrada");

        } catch (Exception e) {

            //trata as possíveis exceções
            System.out.println("Erro: " + e.getMessage());

        }

    }

    //método responsável por percorrer o vetor apresentando seu valor atual
    public static void showNumbers(int[] vector) {
        System.out.println("** Initial Vector **");
        System.out.print("[");

        //estrutura de repetição responsável por percorrer o vetor
        for (int i = 0; i < vector.length; i++) {
            //operador ternário responsável por deixar a visualização do vetor mais agradável
            System.out.print(i == (vector.length - 1) ? vector[i] + "] \n" : vector[i] + ",");
        }
    }

}
