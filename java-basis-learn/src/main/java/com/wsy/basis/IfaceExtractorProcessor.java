package com.wsy.basis;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.Elements;
import java.io.Writer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@AutoService(Processor.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@interface ExtractInterface {
    String interfaceName() default "-!!-";
}
public class IfaceExtractorProcessor extends AbstractProcessor {
    private ArrayList<Element>
            interfaceMethods = new ArrayList<>();
    Elements elementUtils;
    private ProcessingEnvironment processingEnv;
    @Override
    public void init(
            ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
        elementUtils = processingEnv.getElementUtils();
    }
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        for(Element elem:env.getElementsAnnotatedWith(
                ExtractInterface.class)) {
            String interfaceName = elem.getAnnotation(
                    ExtractInterface.class).interfaceName();
            for(Element enclosed :
                    //getEnclosedElements()方法会通过指定的元素生成所有的“闭包”元素。
                    elem.getEnclosedElements()) {
                if(enclosed.getKind() //找到所有的 public 和 static 方法
                        .equals(ElementKind.METHOD) &&
                        enclosed.getModifiers()
                                .contains(Modifier.PUBLIC) &&
                        !enclosed.getModifiers()
                                .contains(Modifier.STATIC)) {
                    interfaceMethods.add(enclosed);
                }
            }
            if(interfaceMethods.size() > 0)
                writeInterfaceFile(interfaceName);
        }
        return false;
    }
    private void
    writeInterfaceFile(String interfaceName) {
        try(
                Writer writer = processingEnv.getFiler()
                        .createSourceFile(interfaceName)
                        .openWriter()
        ) {
            String packageName = elementUtils
                    .getPackageOf(interfaceMethods
                            .get(0)).toString();
            writer.write(
                    "package " + packageName + ";\n");
            writer.write("public interface " +
                    interfaceName + " {\n");
            for(Element elem : interfaceMethods) {
                ExecutableElement method =
                        (ExecutableElement)elem;
                String signature = " public ";
                signature += method.getReturnType() + " ";
                signature += method.getSimpleName();
                signature += createArgList(
                        method.getParameters());
                System.out.println(signature);
                writer.write(signature + ";\n");
            }
            writer.write("}");
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    private String createArgList(
            List<? extends VariableElement> parameters) {
        String args = parameters.stream()
                .map(p -> p.asType() + " " + p.getSimpleName())
                .collect(Collectors.joining(", "));
        return "(" + args + ")";
    }
}
