/*    */ package frameself.monitor;
/*    */ 
/*    */ import frameself.format.Symptom;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class SymptomStore
/*    */ {
/*  8 */   private static ArrayList<Symptom> symptoms = new ArrayList();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void store(ArrayList<Symptom> symptoms)
/*    */   {
/* 15 */     symptoms.addAll(symptoms);
/*    */   }
/*    */   
/*    */   public static ArrayList<Symptom> getSymptoms() {
/* 19 */     return symptoms;
/*    */   }
/*    */   
/*    */   public static void setSymptoms(ArrayList<Symptom> symptoms) {
/* 23 */     symptoms = symptoms;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/SymptomStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */