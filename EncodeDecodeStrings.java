package Hashing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by effyfeng on 6/24/17.
 * https://www.tutorialspoint.com/java/lang/string_indexof_ch_index.htm
 *
 String str = "This is tutorialspoint";

 System.out.println("index of letter 's' =  "
 + str.indexOf('s', 10));

 index of letter 's' = 16
 找到的是第二个s
 没什么特别的 全部就是在了解API


 如果有多个# 想这样 [a，#####%, b, cd]
 => a# | #####%#| b# |cd#
 你想读#来切分子串 读第二个分割的时候 你会把第0个index读出来做切割。所以有特殊字符的时候，仅仅用一个#不行。

 */
public class EncodeDecodeStrings {
    //123abc -- 6/123abc
    //123abc ef  -- 3/123  abc 12/ef


    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();

        for(String str : strs){
            //要转成string的格式才append到sb上
            sb.append(String.valueOf(str.length())+"/");
            System.out.println("b"+String.valueOf(str.length()));
            sb.append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new LinkedList<String>();
        int start = 0;
        while(start < s.length()){

            int idx = s.indexOf('/', start);
            int len = Integer.parseInt(s.substring(start, idx));

            start = idx +1;
            String str = s.substring(start, start+len);
            res.add(str);

            start += len;
        }
        return res;
    }

    public static void main(String[] Ags) {
        List<String> strs = new ArrayList<>();
        strs.add("");
      //  strs.add("bc");
        String res;


        EncodeDecodeStrings sol = new EncodeDecodeStrings();
        res = sol.encode(strs);
        System.out.println(res);
        List<String> res2  = sol.decode(res);
        System.out.println(res2);


    }
}
