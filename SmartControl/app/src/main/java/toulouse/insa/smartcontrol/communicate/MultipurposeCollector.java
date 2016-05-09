package toulouse.insa.smartcontrol.communicate;

import java.util.concurrent.ArrayBlockingQueue;

import frameself.Collector;
import frameself.format.Event;

/**
 * Created by gautierenaud on 09/05/16.
 */
public class MultipurposeCollector {

    public static Collector collector = new Collector("192.168.150.4",5000);

    public static ArrayBlockingQueue<Event> eventQueue = new ArrayBlockingQueue<>(50);

    public static void start(){
        while(true) {
            try {
                Event e = eventQueue.take();
                collector.send(e);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }
}
