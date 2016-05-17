package actionneurs.led;

import com.serial.SerialCommunication;

public class LEDManager implements Runnable 
{

	private Tableau_led tabLED;
	private int x;
	private int y;
	private int index;
	private String text;
	private SerialCommunication serialcom;
	private boolean thread_running;
	private int limit;
	private String[] textab;
	public LEDManager(Tableau_led tabLED, String text, SerialCommunication serialcom)
	{
		this.tabLED = tabLED;
		this.textab = tabLED.getText(text);
		this.limit = textab[0].length();
		this.x=0;//ligne
		this.y=10;//colonne
		this.text = text;
		this.index = 0;
		this.serialcom = serialcom;
		this.thread_running = true;
	}
	
	public void stopRunning()
	{
		this.thread_running = false;
	}
	
	@Override
	public void run() 
	{
		
		while(thread_running)
		{
			p++;
			String currentTab[] = new String[textab.length];
			for(int i=0;i<currentTab.length;i++)
			{
				currentTab[i] = "";
			}
			
			for(int i=0;i<textab.length;i++)
			{
				if(x+21>limit)
				{
					currentTab[i] = textab[i].substring(x, limit)/* + textab[i].substring(0, x%21)*/;
					while(currentTab[i].length()<21)
					{
						currentTab[i] += "0";
					}
				}
				else
				{
					currentTab[i] = textab[i].substring(x, x+21);
				}
			}
			String finalTab[] = new String[21];
			for(int i=0;i<y;i++)
			{
				finalTab[i] = "000000000000000000000";
			}
			for(int i = y; i < y+textab.length;i++)
			{
				finalTab[i] = currentTab[i-y];
			}
			for(int i = y + textab.length;i<21;i++)
			{
				finalTab[i] = "000000000000000000000";
			}
			String data = "";
			for(int i=0;i<21;i++)
			{
				data +=finalTab[i];
			}
			serialcom.write(data.getBytes());
			x++;
			if(x>=limit)
			{
				x = 0;
			}
			try 
			{
				Thread.sleep(500);
			} 
			catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Thread manager LED finished.");
	}
}
