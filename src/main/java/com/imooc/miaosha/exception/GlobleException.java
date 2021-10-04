package com.imooc.miaosha.exception;

import com.imooc.miaosha.result.CodeMsg;

public class GlobleException extends RuntimeException{

    private CodeMsg cm;

    public GlobleException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

}
