package toulouse.insa.smartcontrol.communicate;

import java.util.Date;

import frameself.Collector;
import frameself.format.Event;

/**
 * Created by gautierenaud on 09/05/16.
 */
public class SpecificCollector {

    // Create a collector that publishes events to FRAMSELF on 127.0.0.1:5000
    public static Collector collector = new Collector("192.168.150.4",5000);

    public static void start() {
        while(true){
            // Example of event (To be replaced by the real Phidgets event)
            Event event = new Event();
            event.setCategory("Luminosity");
            event.setValue("300");
            event.setSensor("LuminositySensor");
            event.setLocation("Home");
            event.setTimestamp(new Date());
            event.setExpiry(new Date(System.currentTimeMillis()+20000));
            // Send the event to FRAMSELF
            collector.send(event);
            try {
                // waiting for 1 second
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
