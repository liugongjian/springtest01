package jvm.FatherAndInterfaceWhoIsFirst;

public interface FatherInterface {
    public String s = "I am FatherInterface's print";
    default void print(){
        System.out.println("I am FatherInterface's print");
    }
}
