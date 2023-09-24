package br.com.muralis.gabriel.henrique.rinhabackend.core.exceptions;

public class DomainException422 extends AbstractDomainException{

    public DomainException422(String msg, int status){
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

    public static DomainException422 my422ErrorDescription(String message){
        return new DomainException422(message, 422);
    }

}
