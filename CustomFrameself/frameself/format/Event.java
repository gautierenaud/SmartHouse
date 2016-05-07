/*     */ package frameself.format;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Event
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  13 */   private static int counter = 1;
/*     */   
/*     */   private String id;
/*     */   private String category;
/*     */   private String value;
/*     */   private Date timestamp;
/*     */   private Date expiry;
/*     */   private int priority;
/*     */   private int severity;
/*     */   private String description;
/*     */   private int reportTime;
/*     */   private String reporterID;
/*     */   private String reporterLocation;
/*     */   private String sensor;
/*     */   private String location;
/*     */   
/*     */   public Event()
/*     */   {
/*  31 */     this.id = ("Event" + counter++);
/*     */   }
/*     */   
/*     */   public Event(String Category, String value, String sensor, String location, String description, int priority, int severity, Date timestamp, Date expiry) {
/*  35 */     this.id = ("Event" + counter++);
/*  36 */     this.category = Category;
/*  37 */     this.value = value;
/*  38 */     this.timestamp = timestamp;
/*  39 */     this.expiry = expiry;
/*  40 */     this.sensor = sensor;
/*  41 */     this.location = location;
/*  42 */     this.description = description;
/*  43 */     this.priority = priority;
/*  44 */     this.severity = severity;
/*     */   }
/*     */   
/*     */   public String getId() {
/*  48 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  52 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getValue() {
/*  56 */     return this.value;
/*     */   }
/*     */   
/*     */   public void setValue(String value) {
/*  60 */     this.value = value;
/*     */   }
/*     */   
/*     */   public Date getTimestamp() {
/*  64 */     return this.timestamp;
/*     */   }
/*     */   
/*     */   public void setTimestamp(Date timestamp) {
/*  68 */     this.timestamp = timestamp;
/*     */   }
/*     */   
/*     */   public Date getExpiry() {
/*  72 */     return this.expiry;
/*     */   }
/*     */   
/*     */   public void setExpiry(Date expiry) {
/*  76 */     this.expiry = expiry;
/*     */   }
/*     */   
/*     */   public int getPriority() {
/*  80 */     return this.priority;
/*     */   }
/*     */   
/*     */   public void setPriority(int priority) {
/*  84 */     this.priority = priority;
/*     */   }
/*     */   
/*     */   public int getSeverity() {
/*  88 */     return this.severity;
/*     */   }
/*     */   
/*     */   public void setSeverity(int severity) {
/*  92 */     this.severity = severity;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  96 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/* 100 */     this.description = description;
/*     */   }
/*     */   
/*     */   public int getReportTime() {
/* 104 */     return this.reportTime;
/*     */   }
/*     */   
/*     */   public void setReportTime(int reportTime) {
/* 108 */     this.reportTime = reportTime;
/*     */   }
/*     */   
/*     */   public String getReporterID() {
/* 112 */     return this.reporterID;
/*     */   }
/*     */   
/*     */   public void setReporterID(String reporterID) {
/* 116 */     this.reporterID = reporterID;
/*     */   }
/*     */   
/*     */   public String getReporterLocation() {
/* 120 */     return this.reporterLocation;
/*     */   }
/*     */   
/*     */   public void setReporterLocation(String reporterLocation) {
/* 124 */     this.reporterLocation = reporterLocation;
/*     */   }
/*     */   
/*     */   public String getSensor() {
/* 128 */     return this.sensor;
/*     */   }
/*     */   
/*     */   public void setSensor(String sensor) {
/* 132 */     this.sensor = sensor;
/*     */   }
/*     */   
/*     */   public String getLocation() {
/* 136 */     return this.location;
/*     */   }
/*     */   
/*     */   public void setLocation(String location) {
/* 140 */     this.location = location;
/*     */   }
/*     */   
/*     */   public String getCategory() {
/* 144 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(String category) {
/* 148 */     this.category = category;
/*     */   }
/*     */ }


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/format/Event.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */