package toulouse.insa.smartcontrol.params;

/**
 * Created by gautierenaud on 10/05/16.
 */
public class Parameters {

    private static String frameselfAddress = "192.168.42.1";

    public static String getFrameselfAddress() {
        return frameselfAddress;
    }

    public static void setFrameselfAddress(String frameselfAddress) {
        Parameters.frameselfAddress = frameselfAddress;
    }
}
