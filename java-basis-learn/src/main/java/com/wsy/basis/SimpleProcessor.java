package com.wsy.basis;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.util.Set;
import javax.annotation.processing.Processor;
/**
 * @SupportedAnnotationTypes 支持的注解
 * @SupportedSourceVersion 支持的jdk
 *
 * javac -processor  com.wsy.basis.SimpleProcessor SimpleTest.java
 */

//@AutoService(Processor.class)
@SupportedAnnotationTypes("com.wsy.basis.Simple")
//@SupportedSourceVersion(SourceVersion.RELEASE_14)
//class SimpleProcessor implements Processor{
class SimpleProcessor extends AbstractProcessor {
    /**
     *
     * @param annotations 哪些注解是存在的
     * @param roundEnv 保留了剩余信息
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for(TypeElement t: annotations)
            System.out.println(t);
        for(Element el:roundEnv.getElementsAnnotatedWith(Simple.class) )
            display(el);
        return false;
    }
    private void display(Element el){
        System.out.println("=== "+el+" ===");
        System.out.println(el.getKind() +
                " : " + el.getModifiers() + //修饰符
                " : " + el.getSimpleName() +
                " : " + el.asType());
        if(el.getKind().equals(ElementKind.CLASS)){
            TypeElement te = (TypeElement)el;
            System.out.println(te.getQualifiedName());
            System.out.println(te.getSuperclass());
            System.out.println(te.getEnclosedElements());
        }
        if(el.getKind().equals(ElementKind.METHOD)){
            ExecutableElement ex = (ExecutableElement) el;
            System.out.print(ex.getReturnType() + " ");
            System.out.print(ex.getSimpleName() + "(");
            System.out.println(ex.getParameters() + ")");
        }
    }
}