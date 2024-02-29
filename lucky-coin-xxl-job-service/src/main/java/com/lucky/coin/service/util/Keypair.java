package com.lucky.coin.service.util;

import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.InvalidKeySpecException;

public class Keypair {
    private static final X9ECParameters CURVE_PARAMS = CustomNamedCurves.getByName("secp256r1");
    public static final ECDomainParameters CURVE = new ECDomainParameters(CURVE_PARAMS.getCurve(),
            CURVE_PARAMS.getG(), CURVE_PARAMS.getN(), CURVE_PARAMS.getH());

    public static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static byte[] hexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static void checkArgument(boolean expression, Object errorMessage) {
        if (!expression) {
            throw new IllegalArgumentException(String.valueOf(errorMessage));
        }
    }

    public static byte[] bigIntegerToBytes(BigInteger b, int numBytes) {
        checkArgument(b.signum() >= 0, "b must be positive or zero");
        checkArgument(numBytes > 0, "numBytes must be positive");
        byte[] src = b.toByteArray();
        byte[] dest = new byte[numBytes];
        boolean isFirstByteOnlyForSign = src[0] == 0;
        int length = isFirstByteOnlyForSign ? src.length - 1 : src.length;
        checkArgument(length <= numBytes, "The given number does not fit in " + numBytes);
        int srcPos = isFirstByteOnlyForSign ? 1 : 0;
        int destPos = numBytes - length;
        System.arraycopy(src, srcPos, dest, destPos, length);
        return dest;
    }

    public static String[] generateKeyPair() {
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        ECKeyGenerationParameters keygenParams = new ECKeyGenerationParameters(CURVE, new SecureRandom());
        generator.init(keygenParams);
        AsymmetricCipherKeyPair keypair = generator.generateKeyPair();
        ECPrivateKeyParameters privParams = (ECPrivateKeyParameters) keypair.getPrivate();
        ECPublicKeyParameters pubParams = (ECPublicKeyParameters) keypair.getPublic();
        BigInteger priv = privParams.getD();
        String privHex = bytesToHex(bigIntegerToBytes(priv, 32));
        String pubHex = bytesToHex(pubParams.getQ().getEncoded(true));
        return new String[]{privHex, pubHex};
    }

    public static ECPrivateKey generatePrivateKey(byte[] keyBin) throws InvalidKeySpecException, NoSuchAlgorithmException {
        ECNamedCurveParameterSpec spec = ECNamedCurveTable.getParameterSpec("secp256r1");
        KeyFactory kf = KeyFactory.getInstance("EC", new BouncyCastleProvider());
        ECNamedCurveSpec params = new ECNamedCurveSpec("secp256k1", spec.getCurve(), spec.getG(), spec.getN());
        ECPrivateKeySpec privKeySpec = new ECPrivateKeySpec(new BigInteger(keyBin), params);
        return (ECPrivateKey) kf.generatePrivate(privKeySpec);
    }

    public static String sign(byte[] message, PrivateKey ecPrvKey) {
        try {
            Signature dsa = Signature.getInstance("SHA256withECDSA");
            dsa.initSign(ecPrvKey);
            dsa.update(message);
            return bytesToHex(dsa.sign());
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
//        String[] keys = generateKeyPair();
//        System.out.println("prv key:"+keys[0]);
//        System.out.println("pub key:"+keys[1]);
//
//
//        byte[] msg = hexToBytes("6675636b657");
//        ECPrivateKey prvKey = generatePrivateKey(hexToBytes(keys[0]));
//        String sig = sign(msg, prvKey);
//        System.out.println("sig:"+sig);
//    }

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String[] keys = new String[]{"13853dea88fe91248d955b8e5491b890fe322731dd6930fd63177859c5edb804", "037aab8017820995166b7d5738be7044c43f3f104e52b827c579b16e6139a3cf88"};
        System.out.println("prv key:" + keys[0]);
        System.out.println("pub key:" + keys[1]);

        String ceshi = "POST|/api/v2/keygenV1|1659425276726|{\"network\":\"BTC\",\"vault_name\":\"Mining_Team_Address_Test\"}";
        byte[] msg = ceshi.getBytes(StandardCharsets.UTF_8);
        //byte[] msg = hexToBytes("POST|/api/v2/keygenV1|1659425276726|{\"network\":\"BTC\",\"vault_name\":\"Mining_Team_Address_Test\"}");

        ECPrivateKey prvKey = generatePrivateKey(hexToBytes(keys[0]));
        String sig = sign(msg, prvKey);
        System.out.println("sig:" + sig);
    }
}
