public class EmptyParameterException extends Exception {
    public EmptyParameterException(){
        super();
    }

    public EmptyParameterException(String s){
        super(s);
    }
}