/*    */ package frameself.monitor;
/*    */ 
/*    */ import frameself.format.Event;
/*    */ import frameself.knowledge.KnowledgeManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import org.drools.runtime.StatefulKnowledgeSession;
/*    */ import org.drools.runtime.rule.FactHandle;
/*    */ 
/*    */ public class EventAgregator
/*    */ {
/*    */   private StatefulKnowledgeSession ksession;
/*    */   
/*    */   public EventAgregator(String ruleName)
/*    */   {
/* 16 */     this.ksession = KnowledgeManager.createStatefullKnowledge(ruleName);
/*    */   }
/*    */   
/*    */   public ArrayList<Event> agregate(ArrayList<Event> inputEvents)
/*    */   {
/* 21 */     for (Event event : inputEvents) {
/* 22 */       this.ksession.insert(event);
/*    */     }
/*    */     
/* 25 */     this.ksession.fireAllRules();
/*    */     
/* 27 */     ArrayList<Event> outputEvents = new ArrayList();
/* 28 */     Object objects = this.ksession.getObjects();
/* 29 */     ArrayList<FactHandle> eventHandles = new ArrayList();
/*    */     
/* 31 */     for (Object object : (Collection)objects) {
/* 32 */       if ((object instanceof Event)) {
/* 33 */         outputEvents.add((Event)object);
/* 34 */         eventHandles.add(this.ksession.getFactHandle((Event)object));
/*    */       }
/*    */     }
/*    */     
/* 38 */     for (FactHandle eventHandle : eventHandles) {
/* 39 */       this.ksession.retract(eventHandle);
/*    */     }
/*    */     
/* 42 */     return outputEvents;
/*    */   }
/*    */   
/*    */   public StatefulKnowledgeSession getKsession() {
/* 46 */     return this.ksession;
/*    */   }
/*    */   
/*    */   public void setKsession(StatefulKnowledgeSession ksession) {
/* 50 */     this.ksession = ksession;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/EventAgregator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */