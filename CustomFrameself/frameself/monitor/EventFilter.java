/*    */ package frameself.monitor;
/*    */ 
/*    */ import frameself.format.Clock;
/*    */ import frameself.format.Event;
/*    */ import frameself.knowledge.KnowledgeManager;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collection;
/*    */ import java.util.Date;
/*    */ import org.drools.runtime.StatefulKnowledgeSession;
/*    */ import org.drools.runtime.rule.FactHandle;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EventFilter
/*    */ {
/*    */   private StatefulKnowledgeSession ksession;
/*    */   
/*    */   public EventFilter(String ruleName)
/*    */   {
/* 21 */     this.ksession = KnowledgeManager.createStatefullKnowledge(ruleName);
/*    */   }
/*    */   
/*    */   public ArrayList<Event> filter(ArrayList<Event> inputEvents)
/*    */   {
/* 26 */     for (Event event : inputEvents) {
/* 27 */       this.ksession.insert(event);
/*    */     }
/*    */     
/* 30 */     Clock clock = new Clock(new Date());
/* 31 */     this.ksession.insert(clock);
/* 32 */     this.ksession.fireAllRules();
/*    */     
/* 34 */     Object outputEvents = new ArrayList();
/* 35 */     Collection<Object> objects = this.ksession.getObjects();
/* 36 */     ArrayList<FactHandle> eventHandles = new ArrayList();
/*    */     
/* 38 */     for (Object object : objects) {
/* 39 */       if ((object instanceof Event)) {
/* 40 */         ((ArrayList)outputEvents).add((Event)object);
/* 41 */         eventHandles.add(this.ksession.getFactHandle((Event)object));
/*    */       }
/*    */     }
/*    */     
/* 45 */     for (FactHandle eventHandle : eventHandles) {
/* 46 */       this.ksession.retract(eventHandle);
/*    */     }
/* 48 */     this.ksession.retract(this.ksession.getFactHandle(clock));
/*    */     
/* 50 */     return (ArrayList<Event>)outputEvents;
/*    */   }
/*    */   
/*    */   public StatefulKnowledgeSession getKsession() {
/* 54 */     return this.ksession;
/*    */   }
/*    */   
/*    */   public void setKsession(StatefulKnowledgeSession ksession) {
/* 58 */     this.ksession = ksession;
/*    */   }
/*    */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/EventFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */