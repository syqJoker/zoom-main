package com.mksun.commons.entity;

import lombok.Data;

@Data
public class RtnJSON {
    public int code = 200;
    public String msg = "";
    public String target = "";
    public Object info;

    public RtnJSON success(){
        this.code = 200;
        this.msg = "";
        this.target = "";
        return this;
    }

    public RtnJSON notFound(){
        this.code = 404;
        this.msg = "未找到当前页面";
        return this;
    }

    public RtnJSON dataError(String msg){
        this.code = 500;
        this.msg = msg;
        return this;
    }
}
