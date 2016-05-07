/*    */ package frameself.format;
/*    */ 
/*    */ import java.util.Date;
/*    */ 
/*    */ public class Clock {
/*    */   private Date date;
/*    */   
/*    */   public Clock(Date date) {
/*  9 */     this.date = date;
/*    */   }
/*    */   
/*    */   public Date getDate() {
/* 13 */     return new Date();
/*    */   }
/*    */   
/*    */   public void setCurrentDate(Date date) {
/* 17 */     this.date = date;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/format/Clock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */