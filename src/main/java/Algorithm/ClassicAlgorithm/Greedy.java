package Algorithm.ClassicAlgorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Greedy {

    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcast = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcast.put("K1",hashSet1);
        broadcast.put("K2",hashSet2);
        broadcast.put("K3",hashSet3);
        broadcast.put("K4",hashSet4);
        broadcast.put("K5",hashSet5);

        HashSet<String> allArea = new HashSet<>();
        allArea.add("北京");
        allArea.add("上海");
        allArea.add("天津");
        allArea.add("广州");
        allArea.add("成都");
        allArea.add("杭州");
        allArea.add("大连");
        allArea.add("深圳");

        ArrayList<String> selects = new ArrayList<>();

        HashSet<String> temp = new HashSet<>();

        String maxKey = null;
        while (allArea.size() != 0){

            maxKey = null;
            for (String key : broadcast.keySet()){

                temp.clear();
                HashSet<String> areas = broadcast.get(key);
                temp.addAll(areas);
                //交集会赋给temp
                temp.retainAll(allArea);

                if (temp.size() > 0 && (maxKey == null || temp.size() > broadcast.get(maxKey).size())){
                    maxKey = key;
                }
            }

            if (maxKey != null){
                selects.add(maxKey);
                allArea.removeAll(broadcast.get(maxKey));
            }
        }

        System.out.println("得到的结果是：" + selects);//k1,k2,k3,k5

    }
}
