import java.util.Arrays;
import java.io.ByteArrayOutputStream;

public class Main {

    private static final String BASE32_ALPHABET="ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";
    private static final int[] REVERSE_ALPHABET=new int[128];

    static {
        Arrays.fill(REVERSE_ALPHABET,-1);
        for(int i=0;i<BASE32_ALPHABET.length();i++){
            REVERSE_ALPHABET[BASE32_ALPHABET.charAt(i)]=i;
        }
    }

    public static String encode(byte[] input){
        StringBuilder encoded=new StringBuilder();
        int i=0;
        int index=0;
        int currByte;
        int nextByte;
        int digit;

        while(i<input.length){
            currByte=(input[i]>=0) ? input[i] : (input[i]+256);

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

        return baos.toByteArray();
    }
}