/*     */ package frameself.format;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Rfc {
/*   6 */   private static int counter = 1;
/*     */   private String id;
/*     */   private String category;
/*     */   private String value;
/*     */   private String location;
/*     */   private Date timestamp;
/*     */   private Date expiry;
/*     */   private int priority;
/*     */   private int severity;
/*     */   private String description;
/*     */   
/*     */   public Rfc() {
/*  18 */     this.id = ("Rfc" + counter++);
/*     */   }
/*     */   
/*     */ 
/*     */   public Rfc(String category, String value, String location, String description, int priority, int severity, Date timestamp, Date expiry)
/*     */   {
/*  24 */     this.id = ("Rfc" + counter++);
/*  25 */     this.category = category;
/*  26 */     this.value = value;
/*  27 */     this.location = location;
/*  28 */     this.description = description;
/*  29 */     this.priority = priority;
/*  30 */     this.severity = severity;
/*  31 */     this.timestamp = timestamp;
/*  32 */     this.expiry = expiry;
/*     */   }
/*     */   
/*     */   public static int getCounter() {
/*  36 */     return counter;
/*     */   }
/*     */   
/*     */   public static void setCounter(int counter) {
/*  40 */     counter = counter;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  44 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  48 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getCategory() {
/*  52 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(String category) {
/*  56 */     this.category = category;
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  60 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(String value) {
/*  64 */     this.value = value;
/*     */   }
/*     */   
/*     */   public String getLocation() {
/*  68 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(String location) {
/*  72 */     this.location = location;
/*     */   }
/*     */   
/*     */   public Date getTimestamp() {
/*  76 */     return this.timestamp;
/*     */   }
/*     */   
/*     */   public void setTimestamp(Date timestamp) {
/*  80 */     this.timestamp = timestamp;
/*     */   }
/*     */   
/*     */   public Date getExpiry() {
/*  84 */     return this.expiry;
/*     */   }
/*     */   
/*     */   public void setExpiry(Date expiry) {
/*  88 */     this.expiry = expiry;
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
/*     */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/format/Rfc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */