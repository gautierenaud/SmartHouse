package frameself.main;

import frameself.analyzer.AnalyzerManager;
import frameself.executer.ExecuterManager;
import frameself.format.Event;
import frameself.format.Rfc;
import frameself.format.Symptom;
import frameself.gui.GuiAdmin;
import frameself.monitor.MonitorManager;
import frameself.planner.PlannerManager;
import java.util.ArrayList;
import javax.swing.JLabel;

public class Loop implements Runnable
{
  MonitorManager monitorManager;
  AnalyzerManager analyzerManager;
  PlannerManager plannerManager;
  ExecuterManager executerManager;
  boolean loop;
  
  public Loop(MonitorManager monitorManager, AnalyzerManager analyzerManager, PlannerManager plannerManager, ExecuterManager executerManager)
  {
    this.monitorManager = monitorManager;
    this.analyzerManager = analyzerManager;
    this.plannerManager = plannerManager;
    this.executerManager = executerManager;
  }
  


  public void run()
  {
    this.loop = true;
    ArrayList<Event> events = new ArrayList<Event>();
    ArrayList<Symptom> symptoms = new ArrayList();
    ArrayList<Rfc> rfcs = new ArrayList();
    ArrayList<frameself.format.Action> actions = new ArrayList();
    int loopNumber = ((Integer)GuiAdmin.getSpinner().getValue()).intValue();
    boolean isFixedLoop = GuiAdmin.getFixLoop().isSelected();
    while ((this.loop) && (((isFixedLoop) && (loopNumber > 0)) || (!isFixedLoop)))
    {

      GuiAdmin.getCounter().setText(Admin.loopCounter + " loops");
      symptoms = this.monitorManager.monitor();
      rfcs = this.analyzerManager.analyze(symptoms);
      actions = this.plannerManager.plan(rfcs);
      this.executerManager.execute(actions);
      Admin.loopCounter += 1;
      try {
        Thread.sleep(GuiAdmin.getSlider().getValue() * 1000);
      }
      catch (InterruptedException localInterruptedException) {}
      
      loopNumber--;
    }
    
    GuiAdmin.getStartButton().setSelected(false);
    Admin.loopCounter += 1;
  }
  
  public void stop()
  {
    this.loop = false;
  }
}


/* Location:              /home/gautierenaud/Documents/Lien vers Documents/INSA/4IR/Projet_tut/SmartHouse/SmartControl/app/libs/frameself.jar!/frameself/main/Loop.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */