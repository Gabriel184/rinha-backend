package br.com.muralis.gabriel.henrique.rinhabackend.core.exceptions;

public class DomainException404 extends AbstractDomainException{

    public DomainException404(String msg, int status) {
        super(msg);
        this.status = status;
    }

    public DomainException404(){
        super("");
        this.status = 404;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    public static DomainException404 my404ErrorDescription(String message){
        return new DomainException404(message, 404);
    }
}
