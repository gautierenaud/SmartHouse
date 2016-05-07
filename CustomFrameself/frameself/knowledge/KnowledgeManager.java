/*     */ package frameself.knowledge;
/*     */ 
/*     */ import frameself.format.Effector;
/*     */ import frameself.format.Policy;
/*     */ import frameself.format.Sensor;
/*     */ import frameself.gui.GuiAdmin;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import org.drools.KnowledgeBase;
/*     */ import org.drools.KnowledgeBaseFactory;
/*     */ import org.drools.builder.KnowledgeBuilder;
/*     */ import org.drools.builder.KnowledgeBuilderFactory;
/*     */ import org.drools.builder.ResourceType;
/*     */ import org.drools.io.ResourceFactory;
/*     */ import org.drools.runtime.StatefulKnowledgeSession;
/*     */ 
/*     */ 
/*     */ public class KnowledgeManager
/*     */ {
/*     */   private static ArrayList<Sensor> sensors;
/*     */   private static ArrayList<Policy> policies;
/*     */   private static ArrayList<Effector> effectors;
/*     */   
/*     */   public KnowledgeManager(String sensorInitRule, String effectorInitRule, String policyInitRule)
/*     */   {
/*  28 */     SensorInit sensorInit = new SensorInit(sensorInitRule);
/*  29 */     EffectorInit effectorInit = new EffectorInit(effectorInitRule);
/*  30 */     PolicyInit policyInit = new PolicyInit(policyInitRule);
/*  31 */     sensors = sensorInit.init();
/*  32 */     effectors = effectorInit.init();
/*  33 */     policies = policyInit.init();
/*  34 */     updateGui();
/*     */   }
/*     */   
/*     */   public void updateGui()
/*     */   {
/*  39 */     for (Sensor s : sensors) {
/*  40 */       DefaultTableModel model = (DefaultTableModel)GuiAdmin.getSensors().getModel();
/*  41 */       model.addRow(new Object[] { s.getName() });
/*     */     }
/*     */     
/*  44 */     for (Effector e : effectors) {
/*  45 */       DefaultTableModel model = (DefaultTableModel)GuiAdmin.getEffectors().getModel();
/*  46 */       model.addRow(new Object[] { e.getName() });
/*     */     }
/*     */     
/*  49 */     for (Policy p : policies) {
/*  50 */       DefaultTableModel model = (DefaultTableModel)GuiAdmin.getPolicies().getModel();
/*  51 */       model.addRow(new Object[] { p.getName() });
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public static StatefulKnowledgeSession createStatefullKnowledge(String ruleName)
/*     */   {
/*  58 */     KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
/*  59 */     kbuilder.add(ResourceFactory.newClassPathResource(ruleName), ResourceType.DRL);
/*  60 */     if (kbuilder.hasErrors()) {
/*  61 */       System.err.println(kbuilder.getErrors().toString());
/*     */     }
/*     */     
/*  64 */     KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
/*  65 */     kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
/*  66 */     StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
/*  67 */     return ksession;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ArrayList<Effector> getEffectors()
/*     */   {
/*  84 */     return effectors;
/*     */   }
/*     */   
/*     */   public static void setEffectors(ArrayList<Effector> effectors) {
/*  88 */     effectors = effectors;
/*     */   }
/*     */   
/*     */   public static ArrayList<Sensor> getSensors()
/*     */   {
/*  93 */     return sensors;
/*     */   }
/*     */   
/*     */   public static void setSensors(ArrayList<Sensor> sensors)
/*     */   {
/*  98 */     sensors = sensors;
/*     */   }
/*     */   
/*     */   public static ArrayList<Policy> getPolicies()
/*     */   {
/* 103 */     return policies;
/*     */   }
/*     */   
/*     */   public static void setPolicies(ArrayList<Policy> policies)
/*     */   {
/* 108 */     policies = policies;
/*     */   }
/*     */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/knowledge/KnowledgeManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */