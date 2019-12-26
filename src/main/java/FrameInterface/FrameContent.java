package FrameInterface;

import org.springframework.beans.factory.annotation.Autowired;

public class FrameContent {


    public static void invokeFrame(FrameInterface fi) {
        if (fi != null) {
            fi.beforMethod();
            //框架流程
            fi.afterMethod();
        }

        //框架流程：当fi为空时的执行代码
    }

}
