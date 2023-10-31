package datatransferobject;

public class Response {
    private String respData;

    public Response(String respData){
        this.respData = respData;
    }

    public String getResponseData(){
        return respData;
    }

    public void setRespData(String respData){
        this.respData = respData;
    }
}
