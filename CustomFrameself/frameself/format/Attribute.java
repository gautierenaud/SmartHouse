/*    */ package frameself.format;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ 
/*    */ public class Attribute implements Serializable {
/*    */   private String name;
/*    */   private String value;
/*    */   
/*    */   public Attribute(String name, String value) {
/* 10 */     this.name = name;
/* 11 */     this.value = value;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 15 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 19 */     this.name = name;
/*    */   }
/*    */   
/*    */   public String getValue() {
/* 23 */     return this.value;
/*    */   }
/*    */   
/*    */   public void setValue(String value) {
/* 27 */     this.value = value;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/format/Attribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */