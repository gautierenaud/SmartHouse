/*     */ package frameself.format;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Symptom {
/*   6 */   private static int counter = 1;
/*     */   
/*     */   private String id;
/*     */   
/*     */   private String category;
/*     */   private String value;
/*     */   private String location;
/*     */   private Date timestamp;
/*     */   private Date expiry;
/*     */   private int priority;
/*     */   private int severity;
/*     */   private String description;
/*     */   
/*     */   public Symptom()
/*     */   {
/*  21 */     this.id = ("Symptom" + counter++);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Symptom(String category, String value, String location, String description, int priority, int severity, Date timestamp, Date expiry)
/*     */   {
/*  29 */     this.id = ("Symptom" + counter++);
/*  30 */     this.category = category;
/*  31 */     this.value = value;
/*  32 */     this.location = location;
/*  33 */     this.description = description;
/*  34 */     this.priority = priority;
/*  35 */     this.severity = severity;
/*  36 */     this.timestamp = timestamp;
/*  37 */     this.expiry = expiry;
/*     */   }
/*     */   
/*     */   public String getId()
/*     */   {
/*  42 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setName(String id)
/*     */   {
/*  47 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  51 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(String value) {
/*  55 */     this.value = value;
/*     */   }
/*     */   
/*     */   public Date getTimestamp() {
/*  59 */     return this.timestamp;
/*     */   }
/*     */   
/*     */   public void setTimestamp(Date timestamp) {
/*  63 */     this.timestamp = timestamp;
/*     */   }
/*     */   
/*     */   public Date getExpiry() {
/*  67 */     return this.expiry;
/*     */   }
/*     */   
/*     */   public void setExpiry(Date expiry) {
/*  71 */     this.expiry = expiry;
/*     */   }
/*     */   
/*     */   public String getCategory()
/*     */   {
/*  76 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(String category) {
/*  80 */     this.category = category;
/*     */   }
/*     */   
/*     */   public String getLocation() {
/*  84 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(String location) {
/*  88 */     this.location = location;
/*     */   }
/*     */   
/*     */   public int getPriority() {
/*  92 */     return this.priority;
/*     */   }
/*     */   
/*     */   public void setPriority(int priority) {
/*  96 */     this.priority = priority;
/*     */   }
/*     */   
/*     */   public int getSeverity() {
/* 100 */     return this.severity;
/*     */   }
/*     */   
/*     */   public void setSeverity(int severity) {
/* 104 */     this.severity = severity;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 108 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/* 112 */     this.description = description;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/* 116 */     this.id = id;
/*     */   }
/*     */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/format/Symptom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */