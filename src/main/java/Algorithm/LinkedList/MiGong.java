package Algorithm.LinkedList;

public class MiGong {

    private static int[][] map = new int[8][7];

    public static void main(String[] args) {
        for(int i =0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        for(int i =0;i<7;i++){
            map[7][i]=1;
            map[0][i]=1;
        }
        map[3][1]=1;
        map[3][2]=1;

        setWay(map,1,1);
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2){
            return true;
        }else {
            if(map[i][j] == 0){
                map[i][j]=2;
                if(setWay(map,i+1,j)){
                    //向下走
                    return true;
                }else if(setWay(map,i,j+1)){
                    return true;
                }else if(setWay(map,i-1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else {
                    //说明i,j该点是死路
                    map[i][j]=3;
                    return false;
                }
            }else {//如果ij点不是0
                return false;
            }
        }
    }
}
