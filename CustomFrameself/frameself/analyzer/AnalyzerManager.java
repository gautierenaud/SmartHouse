/*    */ package frameself.analyzer;
/*    */ 
/*    */ import frameself.format.Rfc;
/*    */ import frameself.format.Symptom;
/*    */ import frameself.gui.GuiAdmin;
/*    */ import frameself.main.Admin;
/*    */ import java.io.PrintStream;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.table.DefaultTableModel;
/*    */ 
/*    */ 
/*    */ public class AnalyzerManager
/*    */ {
/*    */   private RfcInference rfcInference;
/*    */   private RfcStore rfcStore;
/*    */   ArrayList<HashMap<String, String>> nonNormalizedSymptoms;
/*    */   ArrayList<Symptom> symptoms;
/*    */   ArrayList<Rfc> rfcs;
/*    */   
/*    */   public Object[] createSymptomObject(Symptom s)
/*    */   {
/* 25 */     return new Object[] { Integer.valueOf(Admin.getLoopCounter()), s.getId(), s.getCategory(), s.getValue(), s.getLocation(), Integer.valueOf(s.getPriority()), Integer.valueOf(s.getSeverity()), s.getDescription(), s.getTimestamp(), s.getExpiry() };
/*    */   }
/*    */   
/*    */   public Object[] createRfcObject(Rfc r) {
/* 29 */     return new Object[] { Integer.valueOf(Admin.getLoopCounter()), r.getId(), r.getCategory(), r.getValue(), r.getLocation(), Integer.valueOf(r.getPriority()), Integer.valueOf(r.getSeverity()), r.getDescription(), r.getTimestamp(), r.getExpiry() };
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public AnalyzerManager(String rfcInferenceRule)
/*    */   {
/* 36 */     this.rfcInference = new RfcInference(rfcInferenceRule);
/* 37 */     this.rfcStore = new RfcStore();
/* 38 */     this.symptoms = new ArrayList();
/* 39 */     this.nonNormalizedSymptoms = new ArrayList();
/*    */   }
/*    */   
/*    */   public ArrayList<Rfc> analyze(ArrayList<Symptom> symps)
/*    */   {
/* 44 */     this.symptoms = symps;
/*    */     
/* 46 */     System.out.println();
/* 47 */     System.out.println("\nReceived Symptoms: (" + this.symptoms.size() + ")");
/* 48 */     SwingUtilities.invokeLater(new Runnable() {
/* 49 */       public void run() { for (Symptom s : AnalyzerManager.this.symptoms) {
/* 50 */           System.out.println(s.getId() + ", " + s.getValue() + ", " + s.getTimestamp() + ", " + s.getExpiry());
/* 51 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getReceivedSymptoms().getModel();
/* 52 */           model.addRow(AnalyzerManager.this.createSymptomObject(s));
/*    */         }
/*    */       }
/* 55 */     });
/* 56 */     this.rfcs = this.rfcInference.infer(this.symptoms);
/* 57 */     System.out.println();
/* 58 */     System.out.println("\nInfered rfcs:(" + this.rfcs.size() + ")");
/* 59 */     SwingUtilities.invokeLater(new Runnable() {
/* 60 */       public void run() { for (Rfc r : AnalyzerManager.this.rfcs) {
/* 61 */           System.out.println(r.getCategory() + ", " + r.getValue() + ", " + r.getTimestamp() + ", " + r.getExpiry());
/* 62 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getInferedRfcs().getModel();
/* 63 */           model.addRow(AnalyzerManager.this.createRfcObject(r));
/*    */         }
/*    */         
/* 66 */         for (Rfc r : AnalyzerManager.this.rfcs) {
/* 67 */           System.out.println(r.getCategory() + ", " + r.getValue() + ", " + r.getTimestamp() + ", " + r.getExpiry());
/* 68 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getRfcsTable().getModel();
/* 69 */           model.addRow(AnalyzerManager.this.createRfcObject(r));
/*    */         }
/* 71 */       } });
/* 72 */     this.rfcStore.store(this.rfcs);
/* 73 */     System.out.println();
/*    */     
/* 75 */     return this.rfcs;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/analyzer/AnalyzerManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */