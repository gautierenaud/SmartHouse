/*    */ package frameself.monitor;
/*    */ 
/*    */ import frameself.format.Event;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.net.DatagramSocket;
/*    */ 
/*    */ public class UDPServer
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 14 */     int serverPort = 1111;
/* 15 */     System.out.println("Monitor Listening in " + serverPort);
/*    */     try {
/* 17 */       DatagramSocket socket = new DatagramSocket(serverPort);
/* 18 */       byte[] msgBuffer = new byte['Ѐ'];
/* 19 */       java.net.DatagramPacket packet = new java.net.DatagramPacket(msgBuffer, msgBuffer.length);
/*    */       for (;;)
/*    */       {
/* 22 */         socket.receive(packet);
/* 23 */         Event event = (Event)deserialize(msgBuffer);
/* 24 */         System.out.println("Monitor: Event received = " + event.getCategory());
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 28 */       e.printStackTrace();
/* 29 */       System.out.println("Error in getting the Data from UDP Client");
/*    */     }
/*    */   }
/*    */   
/*    */   public static byte[] serialize(Object obj) throws java.io.IOException {
/* 34 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 35 */     ObjectOutputStream os = new ObjectOutputStream(out);
/* 36 */     os.writeObject(obj);
/* 37 */     return out.toByteArray();
/*    */   }
/*    */   
/*    */   public static Object deserialize(byte[] data) throws java.io.IOException, ClassNotFoundException {
/* 41 */     java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(data);
/* 42 */     ObjectInputStream is = new ObjectInputStream(in);
/* 43 */     return is.readObject();
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/UDPServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */