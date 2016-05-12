package toulouse.insa.smartcontrol.communicate;

import java.util.concurrent.ArrayBlockingQueue;

import frameself.Collector;
import frameself.format.Event;
import toulouse.insa.smartcontrol.params.Parameters;

/**
 * Created by gautierenaud on 09/05/16.
 */
// class to collect event from the Android device and send it to Frameself
public class MultipurposeCollector {

    public static Collector collector = new Collector(Parameters.getFrameselfAddress(), 5000);

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
