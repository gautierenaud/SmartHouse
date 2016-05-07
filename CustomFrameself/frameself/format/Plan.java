/*    */ package frameself.format;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class Plan
/*    */ {
/*    */   private String name;
/*    */   private java.util.Date timestamp;
/*  9 */   private ArrayList<Action> action = new ArrayList();
/*    */   
/*    */   public Plan(String name, ArrayList<Action> action, java.util.Date timestamp) {
/* 12 */     this.name = name;
/* 13 */     this.action = action;
/* 14 */     this.timestamp = timestamp;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 18 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 22 */     this.name = name;
/*    */   }
/*    */   
/*    */   public ArrayList<Action> getAction() {
/* 26 */     return this.action;
/*    */   }
/*    */   
/*    */   public void setAction(ArrayList<Action> action) {
/* 30 */     this.action = action;
/*    */   }
/*    */   
/*    */   public java.util.Date getTimestamp() {
/* 34 */     return this.timestamp;
/*    */   }
/*    */   
/*    */   public void setTimestamp(java.util.Date timestamp) {
/* 38 */     this.timestamp = timestamp;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/format/Plan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */