package order;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ArrayBlockingQueue;

import toulouse.insa.smartcontrol.params.Parameters;

/**
 * Created by gautierenaud on 14/05/16.
 */
public class OrderSender extends Thread{

    private DatagramSocket clientSocket;
    private BufferedOutputStream sWriter;
    public static ArrayBlockingQueue<Order> orderQueue;

    // will start the thread by default if it's not already the case
    public OrderSender(){
        if (!this.isAlive()) {
            this.start();
            this.orderQueue = new ArrayBlockingQueue<>(50);
        }
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                Order orderToSend = orderQueue.take();

                ObjectMapper mapper = new ObjectMapper();
                String sendString = null;
                sendString = mapper.writeValueAsString(orderToSend);
                Log.d("OrderSender", "sending: " + sendString);

                InetAddress address = InetAddress.getByName(Parameters.getFrameselfAddress());
                System.out.println(sendString.getBytes().length);
                DatagramPacket packet = new DatagramPacket(sendString.getBytes(), sendString.getBytes().length, address, 8000);

                clientSocket.send(packet);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
