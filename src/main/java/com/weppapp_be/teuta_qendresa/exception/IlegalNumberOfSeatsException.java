package com.weppapp_be.teuta_qendresa.exception;

public class IlegalNumberOfSeatsException extends RuntimeException{
    public IlegalNumberOfSeatsException(String msg){
        super(msg);
    }
}
