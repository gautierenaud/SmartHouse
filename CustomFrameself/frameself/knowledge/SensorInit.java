/*    */ package frameself.knowledge;
/*    */ 
/*    */ import frameself.format.Sensor;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import org.drools.runtime.StatefulKnowledgeSession;
/*    */ import org.drools.runtime.rule.FactHandle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SensorInit
/*    */ {
/*    */   private StatefulKnowledgeSession ksession;
/*    */   
/*    */   public SensorInit(String ruleName)
/*    */   {
/* 19 */     this.ksession = KnowledgeManager.createStatefullKnowledge(ruleName);
/*    */   }
/*    */   
/*    */   public ArrayList<Sensor> init()
/*    */   {
/* 24 */     this.ksession.fireAllRules();
/*    */     
/* 26 */     ArrayList<Sensor> output = new ArrayList();
/* 27 */     Collection<Object> objects = this.ksession.getObjects();
/* 28 */     ArrayList<FactHandle> handles = new ArrayList();
/*    */     
/* 30 */     for (Object object : objects) {
/* 31 */       if ((object instanceof Sensor)) {
/* 32 */         output.add((Sensor)object);
/* 33 */         handles.add(this.ksession.getFactHandle((Sensor)object));
/*    */       }
/*    */     }
/*    */     
/* 37 */     for (FactHandle handle : handles) {
/* 38 */       this.ksession.retract(handle);
/*    */     }
/*    */     
/* 41 */     return output;
/*    */   }
/*    */   
/*    */   public StatefulKnowledgeSession getKsession() {
/* 45 */     return this.ksession;
/*    */   }
/*    */   
/*    */   public void setKsession(StatefulKnowledgeSession ksession) {
/* 49 */     this.ksession = ksession;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/knowledge/SensorInit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */