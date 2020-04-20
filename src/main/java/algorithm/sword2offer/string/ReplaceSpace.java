package algorithm.sword2offer.string;

/**
 * @author JonSnow
 * @desc 题目描述
 * 将一个字符串中的空格替换成 "%20"。
 * Input:
 * "We Are Happy"
 * Output:
 * "We%20Are%20Happy"
 * @date 2019/1/2
 */
public class ReplaceSpace {

    public static void main(String[] args) {
        StringBuilder inputStr = new StringBuilder("A B C D E F G    CD F  T");
        System.out.println(solution1(inputStr));
        System.out.println(solution2(inputStr.toString()));
        System.out.println(solution3(inputStr.toString()));
    }

    private static String solution1(StringBuilder inputStr) {
        int p1 = inputStr.length() - 1;

        for (int i = 0; i < inputStr.length(); i++) {
            if (inputStr.charAt(i) == ' ') {
                inputStr.append('>');
                inputStr.append('>');
            }
        }
        int p2 = inputStr.length() - 1;
        while (p1 >= 0) {
            
            if (inputStr.charAt(p1) == ' ') {
                inputStr.setCharAt(p2--, '0');
                inputStr.setCharAt(p2--, '2');
                inputStr.setCharAt(p2--, '%');
            } else {
                inputStr.setCharAt(p2--, inputStr.charAt(p1));
            }
            p1--;
        }

        return inputStr.toString();
    }

    public static String solution2(String string) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ') {
                stringBuilder.append("%20");
            } else {
                stringBuilder.append(string.charAt(i));
            }
        }

        return stringBuilder.toString();
    }

    public static String solution3(String string) {
        return string.replaceAll("\\s", "%20");
    }
}
