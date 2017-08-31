package com.hy.trigger.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by haoy on 2017/7/12.
 */
public class testReg {
    public static void main(String[] args) {
//        String str="For my money, the important thing "+"about the meeting was bridge-building";
//        String regEx="a|f"; //表示a或f
//        Pattern p=Pattern.compile(regEx);
//        Matcher m=p.matcher(str);
//        boolean result=m.find();
//        System.out.println(result);
        /**
         * 匹配${}
         */
        Pattern p = Pattern.compile("\\$\\{.*?\\}");
        Matcher m = p.matcher("one ${} ${cat} two ${cats} in the yard cb");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String group = m.group();
            String substring = group.substring(2, group.length() - 1);
            m.appendReplacement(sb, "dog");
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
