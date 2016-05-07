/*    */ package frameself.executer;
/*    */ 
/*    */ import frameself.format.Action;
/*    */ import frameself.format.Attribute;
/*    */ import frameself.format.Event;
/*    */ import frameself.main.Admin;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.PrintStream;
/*    */ import java.net.DatagramPacket;
/*    */ import java.net.DatagramSocket;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ 
/*    */ public class ActionResultReceiver implements Runnable
/*    */ {
/*    */   private static ArrayList<Event> actions;
/*    */   int serverPort;
/*    */   private Action action;
/*    */   
/*    */   public ActionResultReceiver()
/*    */   {
/* 22 */     actions = new ArrayList();
/* 23 */     this.serverPort = Integer.parseInt(Admin.prop.getProperty("frameself_actionResults_listener_port"));
/*    */   }
/*    */   
/*    */   public Object[] createEventObject(Event e) {
/* 27 */     return new Object[] { Integer.valueOf(Admin.getLoopCounter()), e.getId(), 
/* 28 */       e.getCategory(), e.getValue(), e.getSensor(), e.getLocation(), 
/* 29 */       Integer.valueOf(e.getPriority()), Integer.valueOf(e.getSeverity()), e.getDescription(), 
/* 30 */       e.getTimestamp(), e.getExpiry() };
/*    */   }
/*    */   
/*    */ 
/*    */   public void run()
/*    */   {
/* 36 */     System.out.println("FRAMESELF: listening for action results from specific dispatcher on port " + this.serverPort);
/*    */     try {
/* 38 */       DatagramSocket socket = new DatagramSocket(this.serverPort);
/* 39 */       byte[] msgBuffer = new byte['Ѐ'];
/* 40 */       DatagramPacket packet = new DatagramPacket(msgBuffer, 
/* 41 */         msgBuffer.length);
/* 42 */       packet.setLength(msgBuffer.length);
/*    */       for (;;) {
/* 44 */         socket.receive(packet);
/* 45 */         this.action = ((Action)deserialize(msgBuffer));
/* 46 */         System.out.println("Monitor: Action received = " + 
/* 47 */           this.action.getName() + " " + this.action.getResult());
/*    */         
/* 49 */         javax.swing.SwingUtilities.invokeLater(new Runnable()
/*    */         {
/*    */           public void run() {
/* 52 */             System.out.println(ActionResultReceiver.this.action.getName() + " " + 
/* 53 */               ActionResultReceiver.this.action.getResult());
/* 54 */             DefaultTableModel model = (DefaultTableModel)
/* 55 */               frameself.gui.GuiAdmin.getActionsResult().getModel();
/* 56 */             model.addRow(ActionResultReceiver.this.createActionResultObject(ActionResultReceiver.this.action));
/*    */             
/* 58 */             System.out.println(ActionResultReceiver.this.action.getName() + " " + 
/* 59 */               ActionResultReceiver.this.action.getResult());
/* 60 */             DefaultTableModel model2 = (DefaultTableModel)
/* 61 */               frameself.gui.GuiAdmin.getActionsResultTable().getModel();
/* 62 */             model2.addRow(ActionResultReceiver.this.createActionResultObject(ActionResultReceiver.this.action));
/*    */           }
/*    */         });
/*    */       }
/*    */     }
/*    */     catch (Exception e) {
/* 68 */       e.printStackTrace();
/* 69 */       System.out.println("Error in getting the Data from UDP Client");
/*    */     }
/*    */   }
/*    */   
/*    */ 
/*    */   public Object[] createActionResultObject(Action a)
/*    */   {
/* 76 */     String parameters = "";
/* 77 */     for (int i = 0; i < a.getAttributes().size(); i++) {
/* 78 */       parameters = 
/* 79 */         parameters + ((Attribute)a.getAttributes().get(i)).getName() + "=" + ((Attribute)a.getAttributes().get(i)).getValue();
/* 80 */       if (i > 0) {
/* 81 */         parameters = parameters + "&";
/*    */       }
/*    */     }
/* 84 */     return new Object[] { Integer.valueOf(Admin.getLoopCounter()), a.getId(), 
/* 85 */       a.getCategory(), a.getName(), parameters, Integer.valueOf(a.getPriority()), 
/* 86 */       a.getDescription(), a.getEffector().getName(), a.getResult(), 
/* 87 */       a.getError(), a.getTimestamp() };
/*    */   }
/*    */   
/*    */   public static Object deserialize(byte[] data) throws java.io.IOException, ClassNotFoundException
/*    */   {
/* 92 */     java.io.ByteArrayInputStream in = new java.io.ByteArrayInputStream(data);
/* 93 */     ObjectInputStream is = new ObjectInputStream(in);
/* 94 */     return is.readObject();
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/executer/ActionResultReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */