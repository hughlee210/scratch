package com.hlee.scratch.corejava;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

public class Reflection {

    public static void main(String[] args) {
        test();
    }

    static void test() {
        Student student = new Student("hlee", 20);
        List<String> courses = Arrays.asList("cs101", "cs202");
        student.setCourseNames(courses);

        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(student);

        Field[] fields = student.getClass().getDeclaredFields();
        for (Field field : fields) {
            Class<?> clazz = myAccessor.getPropertyType(field.getName());
            System.out.printf("field name = %s, type = %s ...%n", field.getName(), clazz.getSimpleName()); // good old System.out.println() is faster than printf/format method.

            if (clazz != null && Collection.class.isAssignableFrom(clazz)) {
                Collection<?> coll = (Collection<?>) myAccessor.getPropertyValue(field.getName());
                System.out.println("field " + field.getName() + ", value=" + coll + ", can be converted to Collection type.");

                Type genericFieldType = field.getGenericType();
                if (genericFieldType instanceof ParameterizedType) {
                    ParameterizedType aType = (ParameterizedType) genericFieldType;
                    Type[] fieldArgTypes = aType.getActualTypeArguments();
                    for (Type fieldArgType : fieldArgTypes) {
                        Class fieldArgClass = (Class) fieldArgType;
                        System.out.println("fieldArgClass = " + fieldArgClass);
                    }
                }


            }
        }

    }
}

class Student {
    private String name;
    private int age;
    private List<String> courseNames;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    //    public void setName(String name) {
    //        this.name = name;
    //    }

    public int getAge() {
        return age;
    }

    //    public void setAge(int age) {
    //        this.age = age;
    //    }

    public List<String> getCourseNames() {
        return courseNames;
    }

    public void setCourseNames(List<String> courseNames) {
        this.courseNames = courseNames;
    }

}
