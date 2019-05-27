package Hashing;

import java.util.*;

/**
 * Created by effyfeng on 6/15/17.
 * 主要还是                 allShifts += offset < 0 ? offset + 26 : offset;
 这一行处理技能

["abcd","defg"],["abc","bcd","xyz"],["azy","baz"]]
 按照例子 each of its letter to its successive letter
 只能滚到下一个 那么区别就是长度了 还有顺时针逆时针

 */
public class GroupShiftedStrings {

    public static void main(String[] args) {
//        String[] a = {"abc", "bcd", "bd","acef", "xyz", "az", "ba", "a", "z"};
        String[] a = {"ba",  "az"};

        GroupShiftedStrings sol = new GroupShiftedStrings();
        System.out.print(sol.groupStrings(a));
    }


    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        //存string为key这个深梗
        /**对于一个string 要求两个feature delta和 len
         * 如果你只存delta为key 只能这样存
         * 如果你存map<delta, map<len, List>  >       <---- 错的
         * 这不对！！！！题意不仅包含一个delta，是每个字母之间的delta你都需要记录下来
         * 这个才是题目的要求并不是一个detal 可能是1+2+3这种形式
         *["egh","jlm"]]shift 2+1是一组的 翻译是说delta一样
         * 如果记录每个deta那么长度的信息也不需要记录了
         *
         * **/

        for (String str : strings) {
            String shiftsHash = "";
            /**用了hashing的技巧
             * 相当于把多个delta和len的信息放到了一个维度里面
             * 压缩一下******/
            for (int i = 1; i < str.length(); i++) {
                int offset = str.charAt(i) - str.charAt(i - 1);
                /**shiftsHash的物理意义是 "11" 象征着abc shift2次 每次1 第一个1是ab 第2个1是bc*/

                shiftsHash += offset < 0 ? offset + 26 : offset;


                /**
                 * ba   98     97   97-98 =-1 但是跟az是一个shift 所以+26=25
                 * az  a(97),  122  25
                 *
                 * Output:
                 * ["az"],["ba"]]
                 * Expected:
                 * [["az","ba"]]
                 */

            }
            if (!map.containsKey(shiftsHash))
                map.put(shiftsHash, new ArrayList<String>());
            map.get(shiftsHash).add(str);
            /**这里利用了 相同shift滚动差值是一样的 关键是看出了这个梗 天啊 看不出来怎么办**/
        }

        for (List<String> list : map.values()) {
            result.add(list);
        }
        return result;
    }
    public List<List<String>> groupStringsBetter(String[] strings) {
        List<List<String>> result = new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (String str : strings) {
            String key = "";
            for (int i = 1; i < str.length(); i++) {
                int offset = (str.charAt(i) - str.charAt(i - 1)+ 26)%26;
                //在为负数的时候，当经过z, a的时候，数值大小会很大得反转
                key += offset;
            }
            if (!map.containsKey(key)) map.put(key, new ArrayList<String>());
            map.get(key).add(str);
        }

        for (List<String> list : map.values()) {
            result.add(list);
        }
        return result;

    }





}

//

