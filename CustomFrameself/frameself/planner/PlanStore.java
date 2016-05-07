/*    */ package frameself.planner;
/*    */ 
/*    */ import frameself.format.Action;
/*    */ import java.util.ArrayList;
/*    */ 
/*    */ public class PlanStore
/*    */ {
/*  8 */   private ArrayList<Action> actions = new ArrayList();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void store(ArrayList<Action> actions)
/*    */   {
/* 15 */     this.actions.addAll(actions);
/*    */   }
/*    */   
/*    */   public ArrayList<Action> getPlans() {
/* 19 */     return this.actions;
/*    */   }
/*    */   
/*    */   public void setActions(ArrayList<Action> actions) {
/* 23 */     this.actions = actions;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/planner/PlanStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */