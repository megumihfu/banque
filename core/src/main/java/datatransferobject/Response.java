package datatransferobject;
import java.util.ArrayList;
import java.util.List;


public class Response {
    private String message;

    public Response(String respData){
        this.message = respData;
    }

    public String getResponseData(){
        return message;
    }

    public void setRespData(String respData){
        this.message = respData;
    }
}
