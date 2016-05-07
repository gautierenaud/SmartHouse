/*    */ package frameself.monitor;
/*    */ 
/*    */ import frameself.format.Event;
/*    */ import frameself.format.Symptom;
/*    */ import frameself.knowledge.KnowledgeManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import org.drools.runtime.StatefulKnowledgeSession;
/*    */ import org.drools.runtime.rule.FactHandle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SymptomInference
/*    */ {
/*    */   private StatefulKnowledgeSession ksession;
/*    */   
/*    */   public SymptomInference(String ruleName)
/*    */   {
/* 20 */     this.ksession = KnowledgeManager.createStatefullKnowledge(ruleName);
/*    */   }
/*    */   
/*    */   public ArrayList<Symptom> infer(ArrayList<Event> events)
/*    */   {
/* 25 */     ArrayList<FactHandle> eventHandles = new ArrayList();
/* 26 */     for (Event event : events) {
/* 27 */       FactHandle eventhandle = this.ksession.insert(event);
/* 28 */       eventHandles.add(eventhandle);
/*    */     }
/* 30 */     this.ksession.fireAllRules();
/*    */     
/* 32 */     for (FactHandle eventHandle : eventHandles) {
/* 33 */       this.ksession.retract(eventHandle);
/*    */     }
/*    */     
/* 36 */     ArrayList<Symptom> symptoms = new ArrayList();
/* 37 */     Object objects = this.ksession.getObjects();
/* 38 */     ArrayList<FactHandle> symptomHandles = new ArrayList();
/*    */     
/* 40 */     for (Object object : (Collection)objects) {
/* 41 */       if ((object instanceof Symptom)) {
/* 42 */         symptoms.add((Symptom)object);
/* 43 */         symptomHandles.add(this.ksession.getFactHandle((Symptom)object));
/*    */       }
/*    */     }
/*    */     
/* 47 */     for (FactHandle symptomHandle : symptomHandles) {
/* 48 */       this.ksession.retract(symptomHandle);
/*    */     }
/*    */     
/* 51 */     return symptoms;
/*    */   }
/*    */   
/*    */   public StatefulKnowledgeSession getKsession() {
/* 55 */     return this.ksession;
/*    */   }
/*    */   
/*    */   public void setKsession(StatefulKnowledgeSession ksession) {
/* 59 */     this.ksession = ksession;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/SymptomInference.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */