
public class StringsCodeAndNotes {
    public static void main(String[] args) {

        /* Watch the following videos on ASCII vs. Unicode:
            -> https://www.youtube.com/watch?v=Xu86LrEyhKc
            -> https://www.youtube.com/watch?v=XHrdHfkccwE

           The key difference between the two is that an ASCII representation uses 7 bits, whereas Unicode uses 16,
           allowing you to represent more characters. If you write "A", that is in ASCII, if you want a Unicode, you
           could do "\u00ea", corresponding to Hex of (0)(0)(E)(A) = (0000)(0000)(1110)(1010) = 234 (in decimal) which
           would be some weird character.

           Since Java uses 7 bits to represent chars, you can have 2^7 = 128 possible characters.

           I THINK THE FOLLOWING NOTE IS FOR HOW JAVA FILES ARE SAVED, NOT STRINGS IN JAVA CODE:
           Note that java uses UTF-16 for all char encryptions (if it fits), so all characters will have
           16 bits = 2 bytes, unless they don't fit in which case they use 4 bytes.
           -> https://stackoverflow.com/questions/24095187/char-size-8-bit-or-16-bit
           -> https://docs.oracle.com/javase/tutorial/i18n/text/string.html
         */

        char ch = 'A';
        int chno = (int) ch;
        System.out.println(System.out.format("ch: %c, chno: %d, chHex: %x", ch, chno, chno));
    }
}