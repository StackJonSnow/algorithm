package algorithm.work;

import sun.security.rsa.RSAPublicKeyImpl;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * @author JonSnow
 * @desc RSA加密解密，加签验签, 支持2048bit与1024bit密钥长度
 * @date 2019/9/5
 */
public class RSAUtils {

    /** 算法名称 */
    private static final String ALGORITHM_NAME = "RSA";

    /** 默认字符编码 */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**最大加密字节数*/
    private static final int MAX_ENCRYPT_SIZE = 117;

    /**最大解密字节数*/
    private static final int MAX_DECRYPT_SIZE = 256;

    /**最大解密字节数*/
    private static final int KEY_SIZE = 1024;

    /**
     * 获取密钥对
     * @return
     * @throws Exception
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_NAME);
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 获取私钥
     * @param keyPair
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(KeyPair keyPair) {
        byte[] encoded = keyPair.getPrivate().getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }

    /**
     * 获取私钥
     * @param keyPair
     * @return
     * @throws Exception
     */
    public static String getPublicKey(KeyPair keyPair) {
        byte[] encoded = keyPair.getPublic().getEncoded();
        return Base64.getEncoder().encodeToString(encoded);
    }

    /**
     * 加密
     * @param publicKey
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String publicKey, String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        Key pubKey = new RSAPublicKeyImpl(Base64.getDecoder().decode(publicKey));
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        byte[] dataBytes = data.getBytes(DEFAULT_CHARSET);

        int offset = 0;
        int dataLenth = dataBytes.length;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while (offset < dataLenth) {
            byte[] segBytes;
            if (dataLenth - offset > MAX_ENCRYPT_SIZE) {
                segBytes = cipher.doFinal(dataBytes, offset, MAX_ENCRYPT_SIZE);
            } else {
                segBytes = cipher.doFinal(dataBytes, offset, dataLenth - offset);
            }
            bos.write(segBytes);
            offset += MAX_ENCRYPT_SIZE;
        }

        return Base64.getEncoder().encodeToString(bos.toByteArray());
    }

    /**
     * 解密
     * @param privateKey
     * @param data
     * @return
     * @throws Exception
     */
    public static String decrypt(String privateKey, String data) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_NAME);

        Cipher cipher = Cipher.getInstance(ALGORITHM_NAME);
        cipher.init(Cipher.DECRYPT_MODE, keyFactory.generatePrivate(keySpec));
        byte[] encryptDataBytes = Base64.getDecoder().decode(data);

        int offset = 0;
        int dataLenth = encryptDataBytes.length;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while (offset < dataLenth) {
            byte[] segBytes;
            if (dataLenth - offset > MAX_DECRYPT_SIZE) {
                segBytes = cipher.doFinal(encryptDataBytes, offset, MAX_DECRYPT_SIZE);
            } else {
                segBytes = cipher.doFinal(encryptDataBytes, offset, dataLenth - offset);
            }
            bos.write(segBytes);
            offset += MAX_DECRYPT_SIZE;
        }

        return new String(bos.toByteArray(), DEFAULT_CHARSET);
    }

    public static void main(String[] args) throws Exception {

        KeyPair keyPair = getKeyPair();
        String privateKey = getPrivateKey(keyPair);
        String publicKey = getPublicKey(keyPair);
        System.out.println("公钥：" + publicKey);
        System.out.println("私钥：" + privateKey);

        String data = "测试RSA加解密测试RSA加解密测试RSA加解密测试RSA加解密测试RSA加解密";

        String encryptData = encrypt(publicKey, data);
        System.out.println("加密数据：" + encryptData);

        String decryptData = decrypt(privateKey, encryptData);
        System.out.println("解密数据：" + decryptData);
    }
}
