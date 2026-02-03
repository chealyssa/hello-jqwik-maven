package hw;

public class Padder {

    private Padder(){}

    public static String padStart(String s, int l, String p){
        Objects.requireNonNull(s, "s must not be null");

        if (l <= s.length()){
            return s;
        }

        String pad = p;
        if (pad == null){
            pad = " ";
        }

        if (pad.isEmpty()){
            return s;
        }

        int needed = l - s.length();

        StringBuilder prefix = new StringBuilder(needed);
        while (prefix.length() < needed){
            int remaining = needed - prefix.length();
            if (remaining >= pad.length()){
                prefix.append(pad);
            } else {
                prefic.append(pad, 0, remianing);
            }
        }
        return prefix.append(s).toString();
    }

}
