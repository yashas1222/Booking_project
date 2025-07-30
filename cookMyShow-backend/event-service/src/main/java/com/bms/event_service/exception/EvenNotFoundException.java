package com.bms.event_service.exception;

public class EvenNotFoundException  extends  RuntimeException{
    public EvenNotFoundException(){
        super("Show not found");
    }
}
