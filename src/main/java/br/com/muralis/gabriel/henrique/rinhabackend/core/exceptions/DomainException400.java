package br.com.muralis.gabriel.henrique.rinhabackend.core.exceptions;

public class DomainException400 extends AbstractDomainException{

    public DomainException400(String msg, int status) {
        super(msg);
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    public static DomainException400 my400ErrorDescription(String message){
        return new DomainException400(message, 400);
    }

}
