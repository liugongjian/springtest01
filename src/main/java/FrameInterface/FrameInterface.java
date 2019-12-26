package FrameInterface;

public interface FrameInterface {

    //default是在java8中引入的关键字，也可称为Virtual
    //extension methods——虚拟扩展方法。是指，在接口内部包含了一些默认的方法实现（
    // 也就是接口中可以包含方法体，这打破了Java之前版本对接口的语法限制），
    // 从而使得接口在进行扩展的时候，不会破坏与接口相关的实现类代码。
    default void beforMethod(){
    };

    default void afterMethod() {
    }
}
