/*    */ package frameself.analyzer;
/*    */ 
/*    */ import frameself.format.Rfc;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class RfcStore
/*    */ {
/*  8 */   private static ArrayList<Rfc> rfcs = new ArrayList();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void store(ArrayList<Rfc> rfcs)
/*    */   {
/* 15 */     rfcs.addAll(rfcs);
/*    */   }
/*    */   
/*    */   public static ArrayList<Rfc> getRfcs() {
/* 19 */     return rfcs;
/*    */   }
/*    */   
/*    */   public static void setRfcs(ArrayList<Rfc> rfcs) {
/* 23 */     rfcs = rfcs;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/analyzer/RfcStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */