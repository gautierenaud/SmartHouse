/*     */ package frameself.format;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Action implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  10 */   private static int counter = 1;
/*     */   private String id;
/*     */   private String category;
/*     */   private String name;
/*     */   private ArrayList<Attribute> attributes;
/*     */   private int priority;
/*     */   private String result;
/*     */   private String Description;
/*     */   private Effector effector;
/*     */   private String Error;
/*     */   private Date timestamp;
/*     */   
/*     */   public Action() {
/*  23 */     this.id = ("Rfc" + counter++);
/*     */   }
/*     */   
/*     */ 
/*     */   public Action(String category, String name, ArrayList<Attribute> attributess, int priority, String description, Effector effector, Date timestamp)
/*     */   {
/*  29 */     this.id = ("Action" + counter++);
/*  30 */     this.category = category;
/*  31 */     this.name = name;
/*  32 */     this.attributes = this.attributes;
/*  33 */     this.priority = priority;
/*  34 */     this.Description = description;
/*  35 */     this.effector = effector;
/*  36 */     this.timestamp = timestamp;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  40 */     return this.Description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  44 */     this.Description = description;
/*     */   }
/*     */   
/*     */   public static int getCounter() {
/*  48 */     return counter;
/*     */   }
/*     */   
/*     */   public static void setCounter(int counter) {
/*  52 */     counter = counter;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  56 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  60 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getCategory() {
/*  64 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(String category) {
/*  68 */     this.category = category;
/*     */   }
/*     */   
/*     */   public int getPriority() {
/*  72 */     return this.priority;
/*     */   }
/*     */   
/*     */   public void setPriority(int priority) {
/*  76 */     this.priority = priority;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  80 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  84 */     this.name = name;
/*     */   }
/*     */   
/*     */   public ArrayList<Attribute> getAttributes() {
/*  88 */     return this.attributes;
/*     */   }
/*     */   
/*     */   public void setAttributes(ArrayList<Attribute> attributes) {
/*  92 */     this.attributes = attributes;
/*     */   }
/*     */   
/*     */   public Effector getEffector() {
/*  96 */     return this.effector;
/*     */   }
/*     */   
/*     */   public void setEffector(Effector effector) {
/* 100 */     this.effector = effector;
/*     */   }
/*     */   
/*     */   public Date getTimestamp() {
/* 104 */     return this.timestamp;
/*     */   }
/*     */   
/*     */   public void setTimestamp(Date timestamp) {
/* 108 */     this.timestamp = timestamp;
/*     */   }
/*     */   
/*     */   public String getResult() {
/* 112 */     return this.result;
/*     */   }
/*     */   
/*     */   public void setResult(String result) {
/* 116 */     this.result = result;
/*     */   }
/*     */   
/* 119 */   public String getError() { return this.Error; }
/*     */   
/*     */   public void setError(String error) {
/* 122 */     this.Error = error;
/*     */   }
/*     */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/format/Action.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */