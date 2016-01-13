package bjs.task28;

public class Main {

    public static void main(String[] args) {
	    AnnotationTest test = new AnnotationTest();
        test.parseAnnotations();
    }
}

/*
All methods, including their annotations
	@MethodAnnotation(returnType = String)
	public String toString();

	public void parseAnnotations();

	@Deprecated
	@MethodAnnotation(returnType = String)
	public String deprecatedMethod();

	@MethodAnnotation(returnType = String)
	public String suppressWarningsMethod();
 */