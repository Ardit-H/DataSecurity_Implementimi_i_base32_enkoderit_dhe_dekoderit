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
}