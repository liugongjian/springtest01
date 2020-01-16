package thread.TestSynLock;


//验证cpu对执行指令的重排序
//如果X.Y同时为0，则证明指令重排序了
public class TestDisorder {

    static int x,y,a,b=0;
    public static void main(String[] args) throws InterruptedException {

        int i=0;
        for(;;){
            i++;
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a=1;//这两条指令有可能进行颠倒顺序执行
                    x=b;
                }
            });
            Thread other = new Thread(new Runnable() {
                @Override
                public void run() {
                    b=1;//这两条指令有可能进行颠倒顺序执行
                    y=a;
                }
            });

            one.start();other.start();
            one.join();other.join();

            String result = "第"+i+"次("+x+","+y+")";

            if(x==0 && y==0){
                System.err.println(result);
                break;
            } else {

            }

        }
    }
}
