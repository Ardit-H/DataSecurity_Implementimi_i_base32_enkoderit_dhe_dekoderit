import java.util.Arrays;

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
        }
    }
}