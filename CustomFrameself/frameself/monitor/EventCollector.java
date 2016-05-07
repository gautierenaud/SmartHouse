/*    */ package frameself.monitor;
/*    */ 
/*    */ import frameself.format.Event;
/*    */ import frameself.main.Admin;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.net.DatagramPacket;
/*    */ import java.net.DatagramSocket;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Properties;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ 
/*    */ public class EventCollector implements Runnable
/*    */ {
/*    */   private static ArrayList<Event> events;
/*    */   private Event event;
/*    */   int serverPort;
/*    */   
/*    */   public EventCollector()
/*    */   {
/* 22 */     events = new ArrayList();
/* 23 */     this.serverPort = Integer.parseInt(Admin.prop.getProperty("frameself_events_listener_port"));
/*    */   }
/*    */   
/*    */   public static ArrayList<Event> getEvents()
/*    */   {
/* 28 */     return events;
/*    */   }
/*    */   
/*    */   public Object[] createEventObject(Event e) {
/* 32 */     return new Object[] { Integer.valueOf(Admin.getLoopCounter()), e.getId(), e.getCategory(), e.getValue(), e.getSensor(), e.getLocation(), Integer.valueOf(e.getPriority()), Integer.valueOf(e.getSeverity()), e.getDescription(), e.getTimestamp(), e.getExpiry() };
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void run()
/*    */   {
/* 39 */     System.out.println("FRAMESELF: listening for events on port " + this.serverPort);
/*    */     try {
/* 41 */       DatagramSocket socket = new DatagramSocket(this.serverPort);
/* 42 */       byte[] msgBuffer = new byte['Ѐ'];
/* 43 */       DatagramPacket packet = new DatagramPacket(msgBuffer, msgBuffer.length);
/* 44 */       packet.setLength(msgBuffer.length);
/*    */       for (;;) {
/* 46 */         socket.receive(packet);
/* 47 */         this.event = ((Event)deserialize(msgBuffer));
/* 48 */         System.out.println("Monitor: Event received = " + this.event.getCategory());
/* 49 */         javax.swing.SwingUtilities.invokeLater(new Runnable()
/*    */         {
/*    */           public void run() {
/* 52 */             System.out.println(EventCollector.this.event.getId() + ", " + EventCollector.this.event.getValue() + ", " + EventCollector.this.event.getTimestamp() + ", " + EventCollector.this.event.getExpiry());
/* 53 */             EventCollector.events.add(EventCollector.this.event);
/* 54 */             DefaultTableModel model = (DefaultTableModel)frameself.gui.GuiAdmin.getReceivedEvents().getModel();
/* 55 */             model.addRow(EventCollector.this.createEventObject(EventCollector.this.event));
/*    */           }
/*    */           
/*    */ 
/*    */         });
/*    */       }
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 64 */       e.printStackTrace();
/* 65 */       System.out.println("Error in getting the Data from UDP Client");
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public static Object deserialize(byte[] data)
/*    */     throws java.io.IOException, ClassNotFoundException
/*    */   {
/* 73 */     ByteArrayInputStream in = new ByteArrayInputStream(data);
/* 74 */     ObjectInputStream is = new ObjectInputStream(in);
/* 75 */     return is.readObject();
/*    */   }
/*    */   
/*    */   public static void setEvents(ArrayList<Event> events)
/*    */   {
/* 80 */     events = events;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/EventCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */