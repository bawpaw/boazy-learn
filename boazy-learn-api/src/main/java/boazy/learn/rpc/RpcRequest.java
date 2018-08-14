package boazy.learn.rpc;

import java.io.Serializable;

/**
 * @author boazy
 * @company boazy
 * @date 2018/8/14
 */
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = -971101644187665929L;

    private String className;
    private String methodName;
    private Object[] parameters;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

}
