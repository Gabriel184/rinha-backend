package br.com.muralis.gabriel.henrique.rinhabackend.core.exceptions;

public abstract class AbstractDomainException extends RuntimeException{

    protected int status;

    public AbstractDomainException(String msg){
        super(msg);
    }

    public abstract int getStatus();

    public abstract void setStatus(int status);
}
