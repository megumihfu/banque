import usecases.ConverterDevice;
import datatransferobject.Response;
import datatransferobject.IRequestHandle;

public class Main {
    public static void main(String[] args){

        ConverterDevice converterDevice = new ConverterDevice(reqHandle);
        Response resp = converterDevice.runConversion();
        System.out.println(resp.getResponseData());
    }
}
