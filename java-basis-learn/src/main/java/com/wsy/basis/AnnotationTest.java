package com.wsy.basis;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Target 表示该注解可以用于什么地方.可能的ElementType参数包括:
 *   CONSTRUCTOR 构造器的声明
 *   FIELD 域声明(包括enum实例)
 *   LOCAL_VARIABLE 局部变量声明
 *   METHOD 方法声明
 *   PACKAGE 包声明
 *   PARAMETER 参数声明
 *   TYPE 类，接口(包括注解类型) 或enum声明
 * @Retention 表名需要在什么级别保存该注解信息 RetentionPolicy
 *   SOURCE 注解将被编译器丢弃
 *   CLASS 注解在class文件中可用,但会被VM丢弃
 *   RUNTIME VM将在运行期也保留注解
 * @Documented 将此注解包含在Javadoc中
 * @Inherited 允许之类继承父类中的注解
 *
 */
public class AnnotationTest {
}

class Testable {
    public void execute(){
        System.out.println("Executing..");
    }
    @Test
    void testExecute(){execute();}
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Test{}

/**
 * @DBTable
 * @Constraints
 * @SQLString
 * @SQLInteger
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface DBTable{
    public String name() default "";
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Constraints {
    boolean primaryKey() default false;
    boolean allowNull() default true;
    boolean unique() default false;
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLString{
    int value() default 0;
    String name() default "";
    Constraints constraints() default @Constraints;
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLInteger{
    String name() default "";
    Constraints constraints() default @Constraints;
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Uniqueness{
    Constraints constraints() default @Constraints(unique = true);
}
@DBTable(name="MEMBER")
class Member {
   @SQLString(value = 30,name = "first_name") String firstName;
   @SQLString(value =50,name="last_name") String lastName;
   @SQLInteger Integer age;
   @SQLString(value = 30,
           constraints = @Constraints(primaryKey = true))
   String handle;
    static int memberCount;
    public String getHandle(){return handle;}
    public String getFirstName(){return firstName;}
    public String getLastName(){return lastName;}
    public String toString(){return handle;}
    public Integer getAge(){return age;}
}

/**
 * 注解是在类的基础上,传递的参数为class
 * Class.forName(className) 根据className获取clazz
 * clazz.getAnnotation(clazz.class) 根据class类获取clazz的注解信息
 * clazz.getDeclaredFields() 获得某个类的所有的公共（public）的字段，包括父类中的字段
 * field.getDeclaredAnnotations() 获取域上定义的注解
 */
class TableCreator {
    public static void main(String[] args) throws Exception {
        if(args.length<1) {
            System.out.println("arguments: annotated classes");
            System.exit(0);
        }
        for(String className:args){
            //Class方法 getAnnotation
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(dbTable == null){
                System.out.println("No DBTable annotations in class "+className);
            }
            String tableName = dbTable.name();
            if(tableName.length()<1)
                tableName = cl.getName().toUpperCase();
            List<String> columnDefs = new ArrayList<String>();
            //class方法 getDeclaredFields
            for(Field field : cl.getDeclaredFields()){
                String columnName=null;
                Annotation[] anns = field.getDeclaredAnnotations();
                if(anns.length<1) continue;
                if(anns[0] instanceof SQLInteger){
                    SQLInteger sInt = (SQLInteger) anns[0];
                    if(sInt.name().length()<1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sInt.name();
                    columnDefs.add(columnName + " INT" +
                            getConstraints(sInt.constraints()));
                }

                if(anns[0] instanceof SQLString){
                    SQLString sString = (SQLString)anns[0];
                    if(sString.name().length()<1)
                        columnName = field.getName().toUpperCase();
                    else
                        columnName = sString.name();
                    columnDefs.add(columnName + " VARCHAR("+
                            sString.value() + ")" +
                            getConstraints(sString.constraints()));
                    StringBuilder createCommand = new StringBuilder(
                            "CREATE TABLE " + tableName + "("
                    );
                    for(String columnDef:columnDefs)
                        createCommand.append("\n    " + columnDef +",");
                    String tableCreate = createCommand.substring(0,
                            createCommand.length()-1) + ");";
                    System.out.println("Table.Creation SQL for "+
                            className + "  is :\n" +tableCreate);
                }
            }
        }
    }
    private static String getConstraints(Constraints con){
        String constraints = "";
        if(!con.allowNull()) constraints+=" NOT NULL";
        if(con.primaryKey()) constraints+=" PRIMARY KEY";
        if(con.unique()) constraints+=" UNIQUE";
        return constraints;
    }
}


