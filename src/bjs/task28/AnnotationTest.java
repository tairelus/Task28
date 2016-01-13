package bjs.task28;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

@Documented
@Inherited
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface MethodAnnotation {
    String returnType();
}

/**
 * Created by YM on 13.01.2016.
 */
public class AnnotationTest {
    //@Override, @Deprecated, @SuppressWarnings

    @Override
    @MethodAnnotation(returnType = "String")
    public String toString() {
        return "This is the overwritten AnnotationTest.toString() method";
    }

    @Deprecated
    @MethodAnnotation(returnType = "String")
    public String deprecatedMethod() {
        return "This is the AnnotationTest.deprecatedMethod() method";
    }

    @SuppressWarnings(value = {"deprecation", "unchecked"}) //@SuppressWarnings({"deprecation", "unchecked"})
    @MethodAnnotation(returnType = "String")
    public String suppressWarningsMethod() {
        return "This is the AnnotationTest.suppressWarningsMethod() method";
    }

    /**
     * Parse method and annotation
     */
    public void parseAnnotations() {
        /*
        The retention policy for @Override and @SuppressWarnings annotation is SOURCE
        which means this annotation will be discarded completely after compiling the code
        and would not be included in .class file or bytecode.

        So, we cannot get @Override and @SuppressWarnings via Reflection API at the runtime
         */

        Class classObj = getClass();

        //All methods, including private and protected
        System.out.println("\nAll methods, including their annotations");
        Method[] methods = classObj.getDeclaredMethods();

        for (Method method : methods) {
            int methodModifiers = method.getModifiers();
            String methodDescription = "\t";

            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                //Get annotation name
                methodDescription += "@" + annotation.annotationType().getSimpleName();

                if (annotation instanceof MethodAnnotation) {
                    //Get annotation fields and their values
                    MethodAnnotation methodAnnotation = (MethodAnnotation)annotation;
                    methodDescription += "(returnType = " + methodAnnotation.returnType() + ")\n\t";
                }
                else {
                    methodDescription += "\n\t";
                }
            }

            if (Modifier.isAbstract(methodModifiers)) {
                methodDescription += "abstract ";
            }

            if (Modifier.isPublic(methodModifiers)) {
                methodDescription += "public ";
            }

            if (Modifier.isProtected(methodModifiers)) {
                methodDescription += "protected ";
            }

            if (Modifier.isPrivate(methodModifiers)) {
                methodDescription += "private ";
            }

            methodDescription += method.getReturnType().getSimpleName() + " ";
            methodDescription += method.getName() + "(";

            Class[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                methodDescription += paramTypes[i].getSimpleName();
                if (i > 0 && i < paramTypes.length - 1) {
                    methodDescription += ", ";
                }
            }

            methodDescription += ");";
            System.out.println(methodDescription + "\n");
        }

    }

}
