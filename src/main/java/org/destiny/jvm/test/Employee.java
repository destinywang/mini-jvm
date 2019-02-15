package org.destiny.jvm.test;


public class Employee {

    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void sayHello(String name) {
        System.out.println( "hello " + name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        Employee employee = new Employee("destiny", 20);
        employee.sayHello("wangkang");
    }
}
