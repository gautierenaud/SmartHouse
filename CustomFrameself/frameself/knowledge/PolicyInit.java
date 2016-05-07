/*    */ package frameself.knowledge;
/*    */ 
/*    */ import frameself.format.Policy;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import org.drools.runtime.StatefulKnowledgeSession;
/*    */ import org.drools.runtime.rule.FactHandle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PolicyInit
/*    */ {
/*    */   private StatefulKnowledgeSession ksession;
/*    */   
/*    */   public PolicyInit(String ruleName)
/*    */   {
/* 18 */     this.ksession = KnowledgeManager.createStatefullKnowledge(ruleName);
/*    */   }
/*    */   
/*    */   public ArrayList<Policy> init()
/*    */   {
/* 23 */     this.ksession.fireAllRules();
/*    */     
/* 25 */     ArrayList<Policy> output = new ArrayList();
/* 26 */     Collection<Object> objects = this.ksession.getObjects();
/* 27 */     ArrayList<FactHandle> handles = new ArrayList();
/*    */     
/* 29 */     for (Object object : objects) {
/* 30 */       if ((object instanceof Policy)) {
/* 31 */         output.add((Policy)object);
/* 32 */         handles.add(this.ksession.getFactHandle((Policy)object));
/*    */       }
/*    */     }
/*    */     
/* 36 */     for (FactHandle handle : handles) {
/* 37 */       this.ksession.retract(handle);
/*    */     }
/*    */     
/* 40 */     return output;
/*    */   }
/*    */   
/*    */   public StatefulKnowledgeSession getKsession() {
/* 44 */     return this.ksession;
/*    */   }
/*    */   
/*    */   public void setKsession(StatefulKnowledgeSession ksession) {
/* 48 */     this.ksession = ksession;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/knowledge/PolicyInit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */