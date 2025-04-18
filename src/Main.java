import java.util.Arrays;
import java.io.ByteArrayOutputStream;

public class Main {

    private static final String BASE32_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
    private static final int[] REVERSE_ALPHABET = new int[128];

    static {
        Arrays.fill(REVERSE_ALPHABET, -1);
        for (int i = 0; i < BASE32_ALPHABET.length(); i++) {
            REVERSE_ALPHABET[BASE32_ALPHABET.charAt(i)] = i;
        }
    }

    public static String encode(byte[] input) {
        StringBuilder encoded = new StringBuilder();
        int i = 0;
        int index = 0;
        int currByte;
        int nextByte;
        int digit;

        while (i < input.length) {
            currByte = (input[i] >= 0) ? input[i] : (input[i] + 256);

            if (index > 3) {
                if ((i + 1) < input.length) {
                    nextByte = (input[i + 1] >= 0) ? input[i + 1] : (input[i + 1] + 256);
                } else {
                    nextByte = 0;
                }

                digit = currByte & (0xFF >> index);
                index = (index + 5) % 8;
                digit <<= index;
                digit |= nextByte >> (8 - index);

                i++;
            } else {
                digit = (currByte >> (8 - (index + 5))) & 0x1F;
                index = (index + 5) % 8;
                if (index == 0) i++;
            }

            encoded.append(BASE32_ALPHABET.charAt(digit));
        }

        while (encoded.length() % 8 != 0) {
            encoded.append("=");
        }

        return encoded.toString();
    }

    public static byte[] decode(String input) {
        input = input.replace("=", "").toUpperCase();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int buffer = 0;
        int bitsLeft = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= REVERSE_ALPHABET.length || REVERSE_ALPHABET[c] == -1) {
                throw new IllegalArgumentException("Invalid Base32 character: " + c);
            }
            buffer <<= 5;
            buffer |= REVERSE_ALPHABET[c];
            bitsLeft += 5;
            if (bitsLeft >= 8) {
                baos.write((buffer >> (bitsLeft - 8)) & 0xFF);
                bitsLeft -= 8;
            }
        }
        return baos.toByteArray();
    }

    public static void main(String[] args) {
        String[] testStrings = {"f", "fo", "foo", "foob", "fooba", "foobar"};
        for (String input : testStrings) {
            System.out.println("Input" + input);

            String encoded = encode(input.getBytes());
            byte[] decoded = decode(encoded);
            String decodedStr = new String(decoded);

            System.out.println("Base 32 Encoded :" + encoded);
            System.out.println("Base 32 Decoded :" + decodedStr);
            System.out.println("Match:" + input.equals(decodedStr));

        }
    }

}
