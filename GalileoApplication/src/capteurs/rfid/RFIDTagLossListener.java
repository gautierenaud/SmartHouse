
package capteurs.rfid;

import java.util.Observable;
import com.phidgets.event.TagLossListener;
import com.phidgets.event.TagLossEvent;

public class RFIDTagLossListener extends Observable  implements TagLossListener {
    
	public RFIDTagLossListener()
	{
		super();
	}
	
	public void tagLost(TagLossEvent oe)
	{
		this.setChanged();
		this.notifyObservers(oe);
	}
    
}
