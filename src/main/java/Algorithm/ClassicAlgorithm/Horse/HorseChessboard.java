package Algorithm.ClassicAlgorithm.Horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;


//马环游棋盘问题，走日
public class HorseChessboard {

    private static int X;//棋盘的行数
    private static int Y;//棋盘的列


    //棋盘的所有格子是否被访问过
    private static boolean[] visitied;

    //标记是否棋盘的所有位置都已经被访问过了
    private static boolean finished;

    public static void main(String[] args) {

        System.out.println("开始运行");
        X = 8;
        Y = 8;
        int row = 1;//马的初始位置
        int col = 1;

        int[][] chessboard = new int[X][Y];
        visitied = new boolean[X*Y];//初始值都是false
        traversalChessboard(chessboard,row-1,col-1,1);

        for (int[] rows : chessboard){
            for (int step:rows){
                System.out.print(step+"\t");
            }
            System.out.println();
        }

    }

    /**
     *
     * @param chessBoard
     * @param row 马当前的行
     * @param col 马当前的列
     * @param step 当前是第几步，从第一步开始
     */
    public static void traversalChessboard(int[][] chessBoard,int row, int col,int step){

        chessBoard[row][col] = step;
        visitied[row*X+col] = true;
        ArrayList<Point> ps = next(new Point(col,row));

        //贪心算法，对ps进行非递减排序，可以优化，最终解也是一样的
        sort(ps);
        while (!ps.isEmpty()){
            Point p = ps.remove(0);//取出下一个可以走的位置
            if (!visitied[p.y*X+p.x]){//说明没访问过
                traversalChessboard(chessBoard,p.y,p.x,step+1);
            }
        }

        if (step<X*Y && !finished){
            chessBoard[row][col] = 0;
            visitied[row*X+col] = false;
        }else {
            finished = true;
        }
    }

    //计算马还能走哪些位置
    public static ArrayList<Point> next(Point curPoint){

        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();

        if ((p1.x = curPoint.x-2) >= 0 && (p1.y = curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x-1) >= 0 && (p1.y = curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x+1) < X && (p1.y = curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x+2) < X && (p1.y = curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x+2) < X && (p1.y = curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x+1) < X && (p1.y = curPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x-1) >= 0 && (p1.y = curPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        if ((p1.x = curPoint.x-2) >=0 && (p1.y = curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        return ps;

    }

    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();

                if (count1<count2){
                    return -1;
                }else if (count1 == count2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
    }
}
