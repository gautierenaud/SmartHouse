/*    */ package frameself.planner;
/*    */ 
/*    */ import frameself.analyzer.RfcStore;
/*    */ import frameself.format.Rfc;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RfcCollector
/*    */   implements Runnable
/*    */ {
/*    */   private ArrayList<Rfc> rfcs;
/*    */   
/*    */   public void run()
/*    */   {
/* 16 */     this.rfcs = new ArrayList();
/*    */     for (;;) {
/* 18 */       this.rfcs.addAll(RfcStore.getRfcs());
/*    */       try {
/* 20 */         Thread.sleep(2000L);
/*    */       }
/*    */       catch (InterruptedException e) {
/* 23 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public ArrayList<Rfc> getRfcs() {
/* 29 */     return this.rfcs;
/*    */   }
/*    */   
/*    */   public void setRfcs(ArrayList<Rfc> rfcs) {
/* 33 */     this.rfcs = rfcs;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/planner/RfcCollector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */