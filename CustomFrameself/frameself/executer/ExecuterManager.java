/*    */ package frameself.executer;
/*    */ 
/*    */ import frameself.format.Action;
/*    */ import frameself.format.Attribute;
/*    */ import frameself.format.Effector;
/*    */ import frameself.gui.GuiAdmin;
/*    */ import frameself.main.Admin;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ExecuterManager
/*    */ {
/*    */   ActionDispatcher actionDispatcher;
/*    */   ActionResultReceiver actionResultReceiver;
/*    */   ArrayList<Action> actions;
/*    */   
/*    */   public ExecuterManager()
/*    */   {
/* 27 */     this.actionDispatcher = new ActionDispatcher();
/* 28 */     this.actionResultReceiver = new ActionResultReceiver();
/* 29 */     new Thread(this.actionResultReceiver).start();
/* 30 */     this.actions = new ArrayList();
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void execute(ArrayList<Action> ac)
/*    */   {
/* 38 */     this.actions = ac;
/* 39 */     SwingUtilities.invokeLater(new Runnable() {
/* 40 */       public void run() { for (Action a : ExecuterManager.this.actions) {
/* 41 */           System.out.println(a.getName() + " " + a.getResult());
/* 42 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getActions().getModel();
/* 43 */           model.addRow(ExecuterManager.this.createActionObject(a));
/*    */         }
/*    */         
/*    */       }
/* 47 */     });
/* 48 */     this.actionDispatcher.dispatch(this.actions);
/*    */   }
/*    */   
/*    */ 
/*    */   public Object[] createActionObject(Action a)
/*    */   {
/* 54 */     String parameters = "";
/* 55 */     for (int i = 0; i < a.getAttributes().size(); i++) {
/* 56 */       parameters = 
/* 57 */         parameters + ((Attribute)a.getAttributes().get(i)).getName() + "=" + ((Attribute)a.getAttributes().get(i)).getValue();
/* 58 */       if (i > 0) {
/* 59 */         parameters = parameters + "&";
/*    */       }
/*    */     }
/* 62 */     return new Object[] { Integer.valueOf(Admin.getLoopCounter()), a.getId(), 
/* 63 */       a.getCategory(), a.getName(), parameters, Integer.valueOf(a.getPriority()), 
/* 64 */       a.getDescription(), a.getEffector().getName(), a.getTimestamp() };
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/executer/ExecuterManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */