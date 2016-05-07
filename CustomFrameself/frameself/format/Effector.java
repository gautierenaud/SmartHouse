/*    */ package frameself.format;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Effector
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private String name;
/*    */   
/*    */   public Effector(String name)
/*    */   {
/* 13 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 17 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 21 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/format/Effector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */