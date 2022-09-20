package LetCodeTool;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassUtil core = new ClassUtil();
        List<Object> list = core.getAllClassByInterface(LetCode.class);
        LetCode letCode;
        for(Object obj : list){
            letCode = (LetCode) obj;
            if(letCode.needTest()){
                System.out.println("currently run -> " + letCode.getClass().getName());
                letCode.test();
            }
        }
    }


}
