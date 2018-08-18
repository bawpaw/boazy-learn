package boazy.learn.swagger;

import java.io.Serializable;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/18
 */
public class MyResponse<RT> implements Serializable {
    private static final long serialVersionUID = -7044387696282807495L;

    private String exchangeId;
    private Integer statusCode;
    private String statusMessage;
    private RT resultData;

    public MyResponse() {
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public RT getResultData() {
        return resultData;
    }

    public void setResultData(RT resultData) {
        this.resultData = resultData;
    }

}
