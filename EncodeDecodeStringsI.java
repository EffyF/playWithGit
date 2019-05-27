package Hashing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by effyfeng 11:44 AM 12/14/18
 **/
public class EncodeDecodeStringsI {
    private static final String DELIMITER = "/$";
    private static final char ESCAPE_SIGN = '/';
    private static final String CONVERTED_ESCAPE_SIGN = "//";//用两杯的//来代表原string里面的/
    public String serialize(List<String> input) {
        // sanity check
        if (input == null) {
            return null;
        }
        if (input.isEmpty()) {
            return "";
        }
        StringBuilder encodedString = new StringBuilder();
        for (String element : input) {
            for (int i = 0; i < element.length(); i++) {
                char cur = element.charAt(i);
                //对于原string
                //case1 ： /
                if (cur == ESCAPE_SIGN) {
                    encodedString.append(CONVERTED_ESCAPE_SIGN);
                } else {
                //case2 : abc
                    encodedString.append(cur);
                }
            }
            encodedString.append(DELIMITER);
        }
        return encodedString.toString();
    }

    public List<String> deserialize(String encodedInput) {
        if (encodedInput == null) {
            return null;
        }
        List<String> decodedInput = new ArrayList<>();
        if (encodedInput.isEmpty()) {
            return decodedInput;
        }
        StringBuilder curWord = new StringBuilder();
        for (int i = 0; i < encodedInput.length(); i++) {
            char c = encodedInput.charAt(i);
            if (c == ESCAPE_SIGN) {
                /** 遇见了/*/
                i++;
                char next = encodedInput.charAt(i);
                //case1 下一个是/ 那么说明原string里面有一个/
                if (next == ESCAPE_SIGN) {
                    curWord.append(next);
                } else {
                 //case2 原string的某个单词结束了
                    decodedInput.add(curWord.toString());
                    curWord.setLength(0);
                }
            } else {
                /** 遇见了char*/
                curWord.append(c);
            }
        }
        return decodedInput;
    }
}

