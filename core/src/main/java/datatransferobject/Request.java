package datatransferobject;

public class Request {
    private String reqData;

    public Request(String reqData){
        this.reqData = reqData;
    }

    public String getReqData(){
        return reqData;
    }

    public void setReqData(String reqData){
        this.reqData = reqData;
    }
}
