package cn.appsys.tools;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by bdqn on 2016/4/21.
 */
@Data
public class ReturnResult implements Serializable {

    private int status;
    private Object data;
    private String message = "操作成功";
    private static final int SUCCESS = 1;
    private static final int FAIL = 0;

    public ReturnResult() {

    }

    /**
     * 返回成功状态
     */
    public ReturnResult returnSuccess(Object obj) {
        this.status = SUCCESS;
        this.data = obj;
        return this;
    }

    /**
     * 返回默认成功状态
     */
    public ReturnResult returnSuccess() {
        this.status = SUCCESS;
        return this;
    }

    /**
     * 返回失败状态
     */
    public ReturnResult returnFail(String message) {
        this.status = FAIL;
        this.message = message;
        return this;
    }

    public ReturnResult(String message, int status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ReturnResult(Object data) {
        this.status = SUCCESS;
        this.data = data;
    }


}
