package com.weppapp_be.teuta_qendresa.exception;

public class ReservationAlreadyExistsException extends RuntimeException{
    public ReservationAlreadyExistsException(String msg){
        super(msg);
    }
}
