package com.springboot.models.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.springboot.enums.ResMessage;
import com.springboot.utils.JsonUtil;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseClass {

    private Integer status;
    private String message;
    private boolean success = true;
    private Integer total;
    @JsonRawValue
    private Object data;

    public ResponseClass() {
    }

    public ResponseClass(Integer status, String message, Integer total, Object data) {
        this.status = status;
        this.message = message;
        this.total = total;

        judgeSuccess();
        parseJson(data);
    }

    private void judgeSuccess() {
        if(!ResMessage.SUCCESS_LIST.contains(this.status)) {
            this.success = false;
        }
    }

    private void parseJson(Object data) {
        if (data instanceof String) {
        	this.data = JsonUtil.convertObjectToJsonString(((String) data).replace("\"", ""));
        } else {
            try {
                this.data = JsonUtil.convertObjectToJsonString(data);
            } catch (Exception e) {
                judgeSuccess();
                this.status = ResMessage.JSON_PARSE_FAIL_STRING.getCode();
                this.message = ResMessage.JSON_PARSE_FAIL_STRING.getDesc();
            }
        }
    }

    private void judgeTotal() {
        if (this.total != null) {
            return;
        }

        if (this.data instanceof List) {
            this.total = ((List) this.data).size();
        } else if (this.data == null){
            this.total = 0;
        } else {
            this.total = 1;
        }
    }

    public ResponseClass(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.total = builder.total;
        this.data = builder.data;
        judgeTotal();
        judgeSuccess();
        parseJson(this.data);
    }

    public static Builder getBuilder(ResMessage resMessage){
        return new Builder(resMessage.getCode(), resMessage.getDesc());
    }

    public static final class Builder {
        private Integer status;
        private String message;

        private Integer total;
        private Object data;

        public Builder(Integer status, String message){
            this.status = status;
            this.message = message;
        }

        public Builder setStatus(Integer status) {
            this.status = status;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setTotal(Integer total) {
            this.total = total;
            return this;
        }

        public Builder setData(Object data) {
            this.data = data;
            return this;
        }

        public ResponseClass build() {
            return new ResponseClass(this);
        }
    }

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
