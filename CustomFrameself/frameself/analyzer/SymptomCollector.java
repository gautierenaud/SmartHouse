/*    */ package frameself.analyzer;
/*    */ 
/*    */ import frameself.format.Symptom;
/*    */ import frameself.monitor.SymptomStore;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ public class SymptomCollector
/*    */   implements Runnable
/*    */ {
/*    */   private ArrayList<Symptom> symptoms;
/*    */   
/*    */   public void run()
/*    */   {
/* 15 */     this.symptoms = new ArrayList();
/*    */     for (;;) {
/* 17 */       this.symptoms.addAll(SymptomStore.getSymptoms());
/*    */       try {
/* 19 */         Thread.sleep(2000L);
/*    */       }
/*    */       catch (InterruptedException e) {
/* 22 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public ArrayList<Symptom> getSymptoms() {
/* 28 */     return this.symptoms;
/*    */   }
/*    */   
/*    */   public void setSymptoms(ArrayList<Symptom> symptoms) {
/* 32 */     this.symptoms = symptoms;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/analyzer/SymptomCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */