package syso.syso.handler;

import java.util.Map;

public class CustomValidationException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private final Map<String,String> errorMap;

    public CustomValidationException(String message, Map<String,String> map){
        super(message);
        this.errorMap = map;
    }

    public Map<String,String> getErrorMap(){
        return errorMap;
    }

}
