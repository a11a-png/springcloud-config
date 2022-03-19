package com.wjg.vo;

import java.io.Serializable;

public class JsonMsg implements Serializable {

       private int code;

       private boolean state;

       private String msg;

       private Object data;

       public int getCode() {
              return code;
       }

       public void setCode(int code) {
              this.code = code;
       }

       public boolean isState() {
              return state;
       }

       public void setState(boolean state) {
              this.state = state;
       }

       public String getMsg() {
              return msg;
       }

       public void setMsg(String msg) {
              this.msg = msg;
       }

       public Object getData() {
              return data;
       }

       public void setData(Object data) {
              this.data = data;
       }

       @Override
       public String toString() {
              return "JsonMsg{" +
                      "code=" + code +
                      ", state=" + state +
                      ", msg='" + msg + '\'' +
                      ", data=" + data +
                      '}';
       }
}
