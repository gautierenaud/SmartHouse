/*    */ package frameself.executer;
/*    */ 
/*    */ import frameself.format.Action;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.net.DatagramPacket;
/*    */ import java.net.DatagramSocket;
/*    */ import java.net.InetAddress;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class ActionDispatcher
/*    */ {
/*    */   static String serverAddress;
/*    */   static int serverPort;
/*    */   
/*    */   public ActionDispatcher()
/*    */   {
/* 20 */     serverAddress = frameself.main.Admin.prop.getProperty("specific_dispatcher_address");
/* 21 */     serverPort = Integer.parseInt(frameself.main.Admin.prop.getProperty("specific_dispatcher_port"));
/* 22 */     System.out.println("FRAMESELF: will send actions to specific dispatcher on " + serverAddress + ":" + serverPort);
/*    */   }
/*    */   
/*    */   public void dispatch(ArrayList<Action> actions) {
/* 26 */     for (Action action : actions) {
/* 27 */       send(action);
/*    */     }
/*    */   }
/*    */   
/*    */   public static void send(Action action)
/*    */   {
/*    */     try {
/* 34 */       InetAddress address = InetAddress.getByName(serverAddress);
/* 35 */       byte[] eventBytes = serialize(action);
/* 36 */       DatagramPacket packet = new DatagramPacket(eventBytes, eventBytes.length, address, serverPort);
/*    */       
/* 38 */       DatagramSocket socket = new DatagramSocket();
/* 39 */       socket.send(packet);
/* 40 */       System.out.println("FRAMESELF: Sent action " + action.getCategory() + " to specific dispatcher");
/*    */       
/* 42 */       socket.close();
/* 43 */       Thread.sleep(200L);
/*    */     }
/*    */     catch (Exception e) {
/* 46 */       e.printStackTrace();
/* 47 */       System.out.println("Error in sending the Data to UDP Server");
/*    */     }
/*    */   }
/*    */   
/*    */   public static byte[] serialize(Object obj) throws java.io.IOException {
/* 52 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 53 */     ObjectOutputStream os = new ObjectOutputStream(out);
/* 54 */     os.writeObject(obj);
/* 55 */     return out.toByteArray();
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/executer/ActionDispatcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */