/*    */ package frameself.planner;
/*    */ 
/*    */ import frameself.format.Action;
/*    */ import frameself.format.Attribute;
/*    */ import frameself.format.Effector;
/*    */ import frameself.format.Rfc;
/*    */ import frameself.gui.GuiAdmin;
/*    */ import frameself.knowledge.KnowledgeManager;
/*    */ import frameself.main.Admin;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ 
/*    */ public class PlannerManager
/*    */ {
/*    */   private RfcCollector rfcCollector;
/*    */   private PlanInference planInference;
/*    */   private PlanStore planStore;
/*    */   ArrayList<java.util.HashMap<String, String>> nonNormalizedRfcs;
/*    */   ArrayList<Rfc> rfcs;
/*    */   ArrayList<Action> actions;
/*    */   
/*    */   public Object[] createRfcObject(Rfc r)
/*    */   {
/* 27 */     return new Object[] { Integer.valueOf(Admin.getLoopCounter()), r.getId(), r.getCategory(), r.getValue(), r.getLocation(), Integer.valueOf(r.getPriority()), Integer.valueOf(r.getSeverity()), r.getDescription(), r.getTimestamp(), r.getExpiry() };
/*    */   }
/*    */   
/*    */   public Object[] createActionObject(Action a) {
/* 31 */     return new Object[] { Integer.valueOf(Admin.getLoopCounter()), a.getId(), a.getCategory(), a.getName(), ((Attribute)a.getAttributes().get(0)).getName() + " = " + ((Attribute)a.getAttributes().get(0)).getValue(), Integer.valueOf(a.getPriority()), a.getDescription(), a.getEffector().getName(), a.getTimestamp() };
/*    */   }
/*    */   
/*    */   public PlannerManager(String planInferenceRule)
/*    */   {
/* 36 */     this.rfcs = new ArrayList();
/*    */     
/*    */ 
/*    */ 
/* 40 */     this.planInference = new PlanInference(planInferenceRule);
/* 41 */     this.planStore = new PlanStore();
/* 42 */     this.nonNormalizedRfcs = new ArrayList();
/*    */   }
/*    */   
/*    */   public ArrayList<Action> plan(ArrayList<Rfc> rf) {
/* 46 */     this.rfcs = rf;
/* 47 */     System.out.println();
/* 48 */     System.out.println("\nReceived Rfcs: (" + this.rfcs.size() + ")");
/* 49 */     SwingUtilities.invokeLater(new Runnable() {
/* 50 */       public void run() { for (Rfc r : PlannerManager.this.rfcs) {
/* 51 */           System.out.println(r.getCategory() + ", " + r.getValue() + ", " + r.getTimestamp() + ", " + r.getExpiry());
/* 52 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getReceivedRfcs().getModel();
/* 53 */           model.addRow(PlannerManager.this.createRfcObject(r));
/*    */         }
/* 55 */       } });
/* 56 */     ArrayList<Effector> effectors = KnowledgeManager.getEffectors();
/* 57 */     ArrayList<frameself.format.Policy> policies = KnowledgeManager.getPolicies();
/* 58 */     this.actions = this.planInference.infer(this.rfcs, effectors, policies);
/* 59 */     System.out.println();
/* 60 */     System.out.println("\nInfered plans:(" + this.actions.size() + ")");
/* 61 */     SwingUtilities.invokeLater(new Runnable() {
/* 62 */       public void run() { for (Action a : PlannerManager.this.actions) {
/* 63 */           System.out.println(a.getName() + ", " + ((Attribute)a.getAttributes().get(0)).getName() + ", " + ((Attribute)a.getAttributes().get(0)).getValue() + ", " + a.getEffector().getName() + ", " + a.getTimestamp());
/* 64 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getInferedPlans().getModel();
/* 65 */           model.addRow(PlannerManager.this.createActionObject(a));
/*    */         }
/* 67 */         for (Action a : PlannerManager.this.actions) {
/* 68 */           System.out.println(a.getName() + ", " + ((Attribute)a.getAttributes().get(0)).getName() + ", " + ((Attribute)a.getAttributes().get(0)).getValue() + ", " + a.getEffector().getName() + ", " + a.getTimestamp());
/* 69 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getActionsTable().getModel();
/* 70 */           model.addRow(PlannerManager.this.createActionObject(a));
/*    */         }
/*    */       }
/* 73 */     });
/* 74 */     this.planStore.store(this.actions);
/* 75 */     System.out.println();
/*    */     
/* 77 */     return this.actions;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/planner/PlannerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */