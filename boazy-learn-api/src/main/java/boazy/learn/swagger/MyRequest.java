package boazy.learn.swagger;

import java.io.Serializable;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/18
 */
public class MyRequest<RT> implements Serializable {
    private static final long serialVersionUID = -9143608046236605155L;

    private String exchangeId;
    private RT requestData;

    public MyRequest() {
    }

    public String getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(String exchangeId) {
        this.exchangeId = exchangeId;
    }

    public RT getRequestData() {
        return requestData;
    }

    public void setRequestData(RT requestData) {
        this.requestData = requestData;
    }

}
