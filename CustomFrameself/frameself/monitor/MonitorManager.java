/*     */ package frameself.monitor;
/*     */ 
/*     */ import frameself.format.Event;
/*     */ import frameself.format.Symptom;
/*     */ import frameself.gui.GuiAdmin;
/*     */ import frameself.main.Admin;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MonitorManager
/*     */ {
/*     */   private EventCollector eventCollector;
/*     */   private EventFormatter eventFormatter;
/*     */   private EventFilter eventFilter;
/*     */   private EventAgregator eventAgregator;
/*     */   private SymptomInference symptomInference;
/*     */   private SymptomStore symptomStore;
/*     */   ArrayList<Event> events;
/*     */   ArrayList<Symptom> symptoms;
/*     */   
/*     */   public MonitorManager(String eventNormalizerRule, String eventFilterRule, String eventAgregatorRule, String symptomInferenceRule)
/*     */   {
/*  31 */     this.eventCollector = new EventCollector();
/*  32 */     new Thread(this.eventCollector).start();
/*  33 */     this.eventFormatter = new EventFormatter(eventNormalizerRule);
/*  34 */     this.eventFilter = new EventFilter(eventFilterRule);
/*  35 */     this.eventAgregator = new EventAgregator(eventAgregatorRule);
/*  36 */     this.symptomInference = new SymptomInference(symptomInferenceRule);
/*  37 */     this.symptomStore = new SymptomStore();
/*  38 */     this.events = new ArrayList();
/*  39 */     this.symptoms = new ArrayList();
/*     */   }
/*     */   
/*     */   public Object[] createEventObject(Event e) {
/*  43 */     return new Object[] { Integer.valueOf(Admin.getLoopCounter()), e.getId(), e.getCategory(), e.getValue(), e.getSensor(), e.getLocation(), Integer.valueOf(e.getPriority()), Integer.valueOf(e.getSeverity()), e.getDescription(), e.getTimestamp(), e.getExpiry() };
/*     */   }
/*     */   
/*     */   public Object[] createSymptomObject(Symptom s) {
/*  47 */     return new Object[] { Integer.valueOf(Admin.getLoopCounter()), s.getId(), s.getCategory(), s.getValue(), s.getLocation(), Integer.valueOf(s.getPriority()), Integer.valueOf(s.getSeverity()), s.getDescription(), s.getTimestamp(), s.getExpiry() };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArrayList<Symptom> monitor()
/*     */   {
/*  55 */     this.events = EventCollector.getEvents();
/*     */     
/*  57 */     this.events = this.eventFormatter.format(this.events);
/*     */     
/*  59 */     System.out.println();
/*  60 */     System.out.println("\nNormalized Events: (" + this.events.size() + ")");
/*  61 */     SwingUtilities.invokeLater(new Runnable() {
/*  62 */       public void run() { for (Event e : MonitorManager.this.events) {
/*  63 */           System.out.println(e.getId() + ", " + e.getValue() + ", " + e.getTimestamp() + ", " + e.getExpiry());
/*     */           
/*  65 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getNormalizedEvents().getModel();
/*  66 */           model.addRow(MonitorManager.this.createEventObject(e));
/*     */         }
/*  68 */       } });
/*  69 */     this.events = this.eventFilter.filter(this.events);
/*     */     
/*  71 */     System.out.println();
/*  72 */     System.out.println("\nFiltered Events: (" + this.events.size() + ")");
/*  73 */     SwingUtilities.invokeLater(new Runnable() {
/*  74 */       public void run() { for (Event e : MonitorManager.this.events) {
/*  75 */           System.out.println(e.getId() + ", " + e.getValue() + ", " + e.getTimestamp() + ", " + e.getExpiry());
/*  76 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getFiltredEvents().getModel();
/*  77 */           model.addRow(MonitorManager.this.createEventObject(e));
/*     */         }
/*  79 */       } });
/*  80 */     this.events = this.eventAgregator.agregate(this.events);
/*     */     
/*  82 */     System.out.println();
/*  83 */     System.out.println("\nAgregated Events: (" + this.events.size() + ")");
/*  84 */     SwingUtilities.invokeLater(new Runnable() {
/*  85 */       public void run() { for (Event e : MonitorManager.this.events) {
/*  86 */           System.out.println(e.getId() + ", " + e.getValue() + ", " + e.getTimestamp() + ", " + e.getExpiry());
/*  87 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getAgregatedEvents().getModel();
/*  88 */           model.addRow(MonitorManager.this.createEventObject(e));
/*     */         }
/*  90 */         for (Event e : MonitorManager.this.events) {
/*  91 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getEventsTable().getModel();
/*  92 */           model.addRow(MonitorManager.this.createEventObject(e));
/*     */         }
/*  94 */       } });
/*  95 */     this.symptoms = this.symptomInference.infer(this.events);
/*  96 */     System.out.println();
/*  97 */     System.out.println("\nInfered symptoms:(" + this.symptoms.size() + ")");
/*  98 */     SwingUtilities.invokeLater(new Runnable() {
/*  99 */       public void run() { for (Symptom s : MonitorManager.this.symptoms) {
/* 100 */           System.out.println(s.getId() + ", " + s.getValue() + ", " + s.getTimestamp() + ", " + s.getExpiry());
/* 101 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getInferedSymptoms().getModel();
/* 102 */           model.addRow(MonitorManager.this.createSymptomObject(s));
/*     */         }
/*     */         
/* 105 */         for (Symptom s : MonitorManager.this.symptoms) {
/* 106 */           System.out.println(s.getId() + ", " + s.getValue() + ", " + s.getTimestamp() + ", " + s.getExpiry());
/* 107 */           DefaultTableModel model = (DefaultTableModel)GuiAdmin.getSymptomsTable().getModel();
/* 108 */           model.addRow(MonitorManager.this.createSymptomObject(s));
/*     */         }
/*     */       }
/* 111 */     });
/* 112 */     this.symptomStore.store(this.symptoms);
/* 113 */     System.out.println();
/*     */     
/* 115 */     EventCollector.getEvents().clear();
/* 116 */     return this.symptoms;
/*     */   }
/*     */   
/*     */   public EventCollector getEventCollector() {
/* 120 */     return this.eventCollector;
/*     */   }
/*     */   
/*     */   public void setEventCollector(EventCollector eventCollector) {
/* 124 */     this.eventCollector = eventCollector;
/*     */   }
/*     */   
/*     */   public EventFilter getEventFilter() {
/* 128 */     return this.eventFilter;
/*     */   }
/*     */   
/*     */   public void setEventFilter(EventFilter eventFilter) {
/* 132 */     this.eventFilter = eventFilter;
/*     */   }
/*     */   
/*     */   public EventAgregator getEventAgregator() {
/* 136 */     return this.eventAgregator;
/*     */   }
/*     */   
/*     */   public void setEventAgregator(EventAgregator eventAgregator) {
/* 140 */     this.eventAgregator = eventAgregator;
/*     */   }
/*     */   
/*     */   public SymptomInference getSymptomInference() {
/* 144 */     return this.symptomInference;
/*     */   }
/*     */   
/*     */   public void setSymptomInference(SymptomInference symptomInference) {
/* 148 */     this.symptomInference = symptomInference;
/*     */   }
/*     */   
/*     */   public SymptomStore getSymptomStore() {
/* 152 */     return this.symptomStore;
/*     */   }
/*     */   
/*     */   public void setSymptomStore(SymptomStore symptomStore) {
/* 156 */     this.symptomStore = symptomStore;
/*     */   }
/*     */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/monitor/MonitorManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */