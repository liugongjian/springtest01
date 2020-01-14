package thread.Volatile;

public class VolatileAssembly {
    volatile Long v1 = null;

    public static void main(String[] args) {

        VolatileAssembly ex = new VolatileAssembly();
        ex.readAndWrite();
    }

    void readAndWrite() {
        v1 = 1L;
    }
}



/**
 * java -XX:+UnlockDiagnosticVMOptions -XX:CompileCommand=print,*thread.Volatile.VolatileAssembly
 *
 *
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssmbly
 *
java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp -XX:CompileCommand=dontinline,*VolatileAssembly.readAndWrite -XX:CompileCommand=compileonly,*VolatileAssembly.readAndWrite thread.Volatile.VolatileAssembly
 */