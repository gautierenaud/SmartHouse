/*    */ package frameself.monitor;
/*    */ 
/*    */ import frameself.format.Event;
/*    */ import frameself.knowledge.KnowledgeManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import org.drools.runtime.StatefulKnowledgeSession;
/*    */ import org.drools.runtime.rule.FactHandle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EventFormatter
/*    */ {
/*    */   private StatefulKnowledgeSession ksession;
/*    */   
/*    */   public EventFormatter(String ruleName)
/*    */   {
/* 20 */     this.ksession = KnowledgeManager.createStatefullKnowledge(ruleName);
/*    */   }
/*    */   
/*    */   public ArrayList<Event> format(ArrayList<Event> nonNormalizedEvents) {
/* 24 */     ArrayList<FactHandle> nonNormalizedEventHandles = new ArrayList();
/* 25 */     for (Event nonNormalizedEvent : nonNormalizedEvents) {
/* 26 */       this.ksession.insert(nonNormalizedEvent);
/* 27 */       nonNormalizedEventHandles.add(this.ksession.getFactHandle(nonNormalizedEvent));
/*    */     }
/*    */     
/* 30 */     this.ksession.fireAllRules();
/*    */     
/* 32 */     ArrayList<Event> normalizedEvents = new ArrayList();
/* 33 */     Object objects = this.ksession.getObjects();
/* 34 */     ArrayList<FactHandle> normalizedEventHandles = new ArrayList();
/*    */     
/* 36 */     for (Object object : (Collection)objects) {
/* 37 */       if ((object instanceof Event)) {
/* 38 */         normalizedEvents.add((Event)object);
/* 39 */         normalizedEventHandles.add(this.ksession.getFactHandle((Event)object));
/*    */       }
/*    */     }
/*    */     
/* 43 */     for (FactHandle nonNormalizedEventHandle : nonNormalizedEventHandles) {
/* 44 */       this.ksession.retract(nonNormalizedEventHandle);
/*    */     }
/*    */     
/* 47 */     for (FactHandle normalizedEventHandle : normalizedEventHandles) {
/* 48 */       this.ksession.retract(normalizedEventHandle);
/*    */     }
/*    */     
/* 51 */     return normalizedEvents;
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


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/EventFormatter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */