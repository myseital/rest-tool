package com.mao.interview.grammar;

/**
 * final, finally, finalize 的区别
 *
 * @author maojiawei
 * @date 2019/1/14
 */
public class FinalVerify {

    public static void main(String[] args) {
        System.out.println(testFinally(null) + "," + testFinally("0") + "," + testFinally(""));
    }

    public static void testFinal() {
        String str = new String("ABC");
        final String str2 = str;
        System.out.println(str2);
        str = "CBA";
        System.out.println(str2);

        System.out.println(str == str2);


//        final String str1 = "Hello KT";
//        System.out.println(str1);
//        str1 = "KT";
//        System.out.println(str1);
    }

    public static int testFinally(String str) {
        // 只有与finally对应的try语句块得到执行的情况下，finally语句块才会执行
        // finally用法特殊，所以会撤销之前的return语句，继续执行最后的finally块中的代码
        try {
            return str.charAt(0) - '0';
        } catch (NullPointerException e) {
            return 1;
        }
        catch (StringIndexOutOfBoundsException e) {
            return 2;
        } catch (Exception e) {
            return 3;
        } finally {
            return 4;
        }
    }
}
