package stacktrace;

public class StackTrace {
    private static Exception e;
    private static StackTraceElement[] stackTraceElements;
    private static String ste;

    public static String ste (Exception e){
        stackTraceElements = e.getStackTrace();
        for (int i = 0, n = stackTraceElements.length; i < n; i++){
                    ste = stackTraceElements[i].getFileName() + ":" +
                        stackTraceElements[i].getLineNumber() + ">>" +
                        stackTraceElements[i].getMethodName() + "()";
        }
        return  ste;
    }


}
