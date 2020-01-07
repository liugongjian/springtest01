package jvm;

import java.io.IOException;
import java.io.InputStream;

public class ClassInit {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        //自定义Classloader
        ClassLoader loader = new ClassLoader() {
            @Override
            @Deprecated
            public Class<?> loadClass(String name) throws ClassNotFoundException {

                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    //当class文件存在——则返回这个类对象
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException();
                }
            }
        };
        String className = "jvm.ClassInit";

        Class c1 = loader.loadClass(className);

        //System.out.println(ClassInit.class);
        //输出false
        System.out.println(c1 == ClassInit.class);

        Object o1 = ClassInit.class.getClassLoader().loadClass(className).newInstance();
        Object o2 = c1.newInstance();
        System.out.println(o1 instanceof jvm.ClassInit);
        System.out.println(o2 instanceof jvm.ClassInit);


    }
}