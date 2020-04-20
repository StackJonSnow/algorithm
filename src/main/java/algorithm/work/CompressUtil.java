package algorithm.work;

import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author JonSnow
 * @desc 16进制字符串编码，解码工具
 *       原理：将两个(0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f)范围(总共16个)内字符使用一个字节代替
 *             刚好一个字节有256个数字，能代表上述组合情况
 *             (0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f) -> (0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f)笛卡尔积可对应至如下byte二维数组
 * @date 2019/7/24
 */
public class CompressUtil {

    /**
     * byte数组,16×16=256个数字，代表（0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f）共16个字符两个一组的总共分组数量
     * 此数组可以看成是一个二维矩阵，特征如下：
     * 每一行从左到右依次增大，每一列从上到下依次增大
     */
    private static byte[][] mapperTable = {
            {-0x80,-0x7f,-0x7e,-0x7d,-0x7c,-0x7b,-0x7a,-0x79,-0x78,-0x77,-0x76,-0x75,-0x74,-0x73,-0x72,-0x71},
            {-0x70,-0x6f,-0x6e,-0x6d,-0x6c,-0x6b,-0x6a,-0x69,-0x68,-0x67,-0x66,-0x65,-0x64,-0x63,-0x62,-0x61},
            {-0x60,-0x5f,-0x5e,-0x5d,-0x5c,-0x5b,-0x5a,-0x59,-0x58,-0x57,-0x56,-0x55,-0x54,-0x53,-0x52,-0x51},
            {-0x50,-0x4f,-0x4e,-0x4d,-0x4c,-0x4b,-0x4a,-0x49,-0x48,-0x47,-0x46,-0x45,-0x44,-0x43,-0x42,-0x41},
            {-0x40,-0x3f,-0x3e,-0x3d,-0x3c,-0x3b,-0x3a,-0x39,-0x38,-0x37,-0x36,-0x35,-0x34,-0x33,-0x32,-0x31},
            {-0x30,-0x2f,-0x2e,-0x2d,-0x2c,-0x2b,-0x2a,-0x29,-0x28,-0x27,-0x26,-0x25,-0x24,-0x23,-0x22,-0x21},
            {-0x20,-0x1f,-0x1e,-0x1d,-0x1c,-0x1b,-0x1a,-0x19,-0x18,-0x17,-0x16,-0x15,-0x14,-0x13,-0x12,-0x11},
            {-0x10,-0x0f,-0x0e,-0x0d,-0x0c,-0x0b,-0x0a,-0x09,-0x08,-0x07,-0x06,-0x05,-0x04,-0x03,-0x02,-0x01},
            { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f},
            { 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x17, 0x18, 0x19, 0x1a, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f},
            { 0x20, 0x21, 0x22, 0x23, 0x24, 0x25, 0x26, 0x27, 0x28, 0x29, 0x2a, 0x2b, 0x2c, 0x2d, 0x2e, 0x2f},
            { 0x30, 0x31, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x3a, 0x3b, 0x3c, 0x3d, 0x3e, 0x3f},
            { 0x40, 0x41, 0x42, 0x43, 0x44, 0x45, 0x46, 0x47, 0x48, 0x49, 0x4a, 0x4b, 0x4c, 0x4d, 0x4e, 0x4f},
            { 0x50, 0x51, 0x52, 0x53, 0x54, 0x55, 0x56, 0x57, 0x58, 0x59, 0x5a, 0x5b, 0x5c, 0x5d, 0x5e, 0x5f},
            { 0x60, 0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6a, 0x6b, 0x6c, 0x6d, 0x6e, 0x6f},
            { 0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7a, 0x7b, 0x7c, 0x7d, 0x7e, 0x7f}
    };

    /**
     * 生成二维码内容字段分隔符
     */
    private static final String SEPARATOR = "|";

    /**
     * 占位符
     */
    private static final char PLACEHOLDER = '#';

    /**
     * 补位字符
     */
    private static final String FILL_CHAR = "0";

    /**
     * 生成二维码内容字段个数
     */
    private static final int NUMBER_OF_FIELDS = 4;

    /**
     * 补位标识
     * 1000 0000 ==> 补位
     * 0000 0000 ==> 未补位
     */
    private static final byte FILL_FLAG = -0X80;
    private static final byte NOT_FILL_FLAG = 0X00;

    /**
     * 缩减字符串长度
     * @param originStr
     * @return
     */
    public static String encode(String originStr) {

        String[] fields = originStr.split("\\" + SEPARATOR);

        if (fields.length != NUMBER_OF_FIELDS) {
            throw new IllegalArgumentException("待压缩字符串字段缺失");
        }

        byte[] resultBytes = null;
        for (int i = 0; i < fields.length; i++) {
            boolean isFill = false;
            if (fields[i].length() % 2 > 0) {
                /**
                 * 字符串长度非2的整数倍时末位补0
                 * 并记录补位标识
                 */
                fields[i] = fields[i].concat(FILL_CHAR);
                isFill = true;
            }

            /**
             * 字符串长度为2的整数倍时压缩该字符串
             */
            char[] chars = fields[i].toCharArray();
            byte[] reduceBytes = new byte[chars.length/2 + 1];
            int rIndex = 1;
            byte flag = isFill ? FILL_FLAG : NOT_FILL_FLAG;

            reduceBytes[0] = (byte) (chars.length/2 | flag);
            for (int j = 0; j < chars.length; j += 2) {
                char charBef = chars[j], charAft = chars[j + 1];
                int x = getCharIndex(charBef), y = getCharIndex(charAft);
                reduceBytes[rIndex++] = mapperTable[x][y];
            }

            if (!Objects.isNull(resultBytes)) {
                byte[] tempBytes = new byte[resultBytes.length + reduceBytes.length];
                System.arraycopy(resultBytes, 0, tempBytes, 0, resultBytes.length);
                resultBytes = tempBytes;
            } else {
                resultBytes = reduceBytes;
                continue;
            }

            System.arraycopy(reduceBytes, 0, resultBytes, resultBytes.length - reduceBytes.length, reduceBytes.length);
        }

        if (Objects.isNull(resultBytes)) {
            throw new IllegalStateException("压缩过程执行有误");
        }

        return Base64.encodeBase64URLSafeString(resultBytes);
    }

    /**
     * 还原字符串
     * @param reducedStr
     * @return
     */
    public static String decode(String reducedStr) {
        byte[] decodeBytes = Base64.decodeBase64(reducedStr);

        StringBuilder restoreStrBuilder = new StringBuilder();

        int beginIndex = 0;

        while(beginIndex < decodeBytes.length) {
            byte flag = decodeBytes[beginIndex];

            boolean isFill = flag < 0 ? true : false;
            byte bytesLength = isFill ? (byte)(flag ^ FILL_FLAG): (byte)(flag ^ NOT_FILL_FLAG);

            byte[] fieldBytes = Arrays.copyOfRange(decodeBytes, beginIndex + 1, beginIndex + bytesLength + 1);

            char[] resChars = new char[fieldBytes.length * 2];

            restoreChars(fieldBytes, resChars);

            if (isFill) {
                char[] originChars = new char[resChars.length - 1];
                System.arraycopy(resChars, 0, originChars, 0, originChars.length);
                restoreStrBuilder.append(originChars);
            } else {
                restoreStrBuilder.append(resChars);
            }

            restoreStrBuilder.append(SEPARATOR);

            beginIndex += bytesLength + 1;
        }

        return restoreStrBuilder.toString().substring(0, restoreStrBuilder.length() - 1);
    }

    /**
     * 还原字符(有序矩阵特定值搜索)
     * @param decryptBytes
     * @param chars
     */
    private static void restoreChars(byte[] decryptBytes, char[] chars) {
        int index = 0;

        for (int l = 0; l < decryptBytes.length; l++) {
            char[] charPair = {PLACEHOLDER, PLACEHOLDER};

            lookForCharPair(charPair, decryptBytes[l], 0, 15);

            char charB = charPair[0], charA = charPair[1];
            if (charB != PLACEHOLDER && charA != PLACEHOLDER) {
                chars[index++] = charB;
                chars[index++] = charA;
            }
        }
    }

    /**
     * byte --> two chars
     * 行列坐标均在范围[0,15]内
     * 从矩阵右上角开始搜索，基于有序矩阵（每一行从左到右依次增大，每一列从上到下依次增大）的特征
     * 如果指定值比当前矩阵元素小则从该点左移一步的点开始搜索
     * 如果指定值比当前矩阵元素大则从该点下移一步的点开始搜索
     * 如果指定值与当前矩阵元素大小一致则匹配成功
     * @param b
     * @return
     */
    private static void lookForCharPair(char[] charPair, byte b, int x, int y) {

        if (x < 0 || y > 15) {
            return;
        }

        if (mapperTable[x][y] == b) {
            charPair[0] = x < 10 ? (char) (x + 48) : (char) (x + 87);
            charPair[1] = y < 10 ? (char) (y + 48) : (char) (y + 87);
            return;
        }

        if (mapperTable[x][y] > b) lookForCharPair(charPair, b, x, --y);

        if (mapperTable[x][y] < b) lookForCharPair(charPair, b, ++x, y);
    }

    /**
     * 获取字符所在映射表中行或列下标
     * @param ch
     * @return
     */
    private static int getCharIndex(char ch) {
        if (ch > 57) {
            return ch - 87;
        } else {
            return ch - 48;
        }
    }

    public static void main(String[] args) {
        String originStr = "12345|34332|1232|998";
        String encodeStr = encode(originStr);
        System.out.println(encodeStr);
        String decodeStr = decode(encodeStr);
        System.out.println(decodeStr);
        System.out.println(decodeStr.equals(originStr));
    }
}
