import acm.program.CommandLineProgram;
import java.util.Arrays;

public class SubstitutionCypher extends CommandLineProgram {
    
    public void run() {
        testAllPairs();
        testGetColumn();
        testContainsInSuffix();
        testUnique();
        testIsValid();
        testInvert();
        testCreateKey();
        testEncodeChar();
        testEncodeText();
        testDecodeText();
    }

    public boolean allPairs(char[][] key) {
        boolean correct = true;

        if(key != null){
            for(int i = 0; i < key.length && correct == true; i++){
                if(key[i] == null || key[i].length != 2){
                    correct = false;
                }
            }
        }
        else{
            correct = false;
        }
        return correct;
        //throw new UnsupportedOperationException("Pas 1");
    }
        
    public void testAllPairs() {
        printlnInfo("BEGIN allPairs");
        assertEquals("allPairs1", false, allPairs(null));
        assertEquals("allPairs2", false, allPairs(new char[][] {{'a', 'b'}, null, {'c', 'd'}}));
        assertEquals("allPairs3", true, allPairs(new char[][]{}));
        assertEquals("allPairs4", false, allPairs(new char[][]{{}}));
        assertEquals("allPairs5", true, allPairs(new char[][]{{'a', 'b'}}));
        assertEquals("allPairs6", false, allPairs(new char[][]{{'a', 'b'}, {'a'}}));
        printlnInfo("END allPairs");
        printBar();
    }
    
    public char[] getColumn(char[][] key, int column) {
        char[] resultColumn = new char[key.length];

        for(int i = 0; i < key.length; i++){
            resultColumn[i] = key[i][column];
        }
        return resultColumn;
        //throw new UnsupportedOperationException("Pas 2");
    }
    
    public void testGetColumn() {
        printlnInfo("BEGIN getColumns");
        assertEquals("getColumns1", new char[]{}, getColumn(new char[][]{}, 0));
        assertEquals("getColumns2", new char[]{}, getColumn(new char[][]{}, 1));
        assertEquals("getColumns3", new char[]{'a'}, getColumn(new char[][]{{'a', 'b'}}, 0));
        assertEquals("getColumns4", new char[]{'b'}, getColumn(new char[][]{{'a', 'b'}}, 1));
        assertEquals("getColumns5", new char[]{'a', 'c'}, getColumn(new char[][]{{'a', 'b'}, {'c', 'd'}}, 0));
        assertEquals("getColumns6", new char[]{'b', 'd'}, getColumn(new char[][]{{'a', 'b'}, {'c', 'd'}}, 1));
        printlnInfo("END getColumns");
        printBar();
    }
        
    public boolean containsInSuffix(char[] chars, int initialPos, char c) {
        boolean cInChars = false;

        while(initialPos < chars.length && cInChars == false){
            if(c == chars[initialPos]){
                cInChars = true;
            }
            else{initialPos++;}
        }
        return cInChars;
        //throw new UnsupportedOperationException("Pas 3");
    }
        
    public void testContainsInSuffix() {
        printlnInfo("BEGIN containsInSuffix");
        assertEquals("containsInSuffix1", false, containsInSuffix(new char[] {}, 12, 'a'));
        assertEquals("containsInSuffix2", false, containsInSuffix(new char[] {'a'}, 2, 'a'));
        assertEquals("containsInSuffix3", false, containsInSuffix(new char[] {'a', 'b'}, 1, 'a'));
        assertEquals("containsInSuffix4", true, containsInSuffix(new char[] {'a', 'b'}, 1, 'b'));
        printlnInfo("END containsInSuffix");
        printBar();
    }
    
    public boolean unique(char[] chars) {
        boolean charNotRepeated = true;

        for(int i = 0; i < chars.length && charNotRepeated == true; i++){
                if(containsInSuffix(chars, i+1, chars[i]) == true){
                    charNotRepeated = false;
                }
        }
        return charNotRepeated;
        //throw new UnsupportedOperationException("Pas 4");
    }
    
    public void testUnique() {
        printlnInfo("BEGIN unique");
        assertEquals("unique1", true, unique("".toCharArray()));
        assertEquals("unique2", true, unique("a".toCharArray()));
        assertEquals("unique3", false, unique("aa".toCharArray()));
        assertEquals("unique4", true, unique("abcdef".toCharArray()));
        assertEquals("unique5", false, unique("abcad".toCharArray()));
        printlnInfo("END unique");
        printBar();
    }
    
    public boolean isValid(char[][] key) {
        boolean valid = true;

        if(allPairs(key) == true){
            char[] leftColumn = getColumn(key, 0);
            char[] rightColumn = getColumn(key, 1);

            if(unique(leftColumn) == false){
                valid = false;
            }
            else if(unique(rightColumn) == false){
                valid = false;
            }
        }
        else{
            valid = false;
        }
        return valid;
        //throw new UnsupportedOperationException("Pas 5");
    }
    
    public void testIsValid() {
        printlnInfo("BEGIN isValid");
        assertEquals("isValid1", false, isValid(null));
        assertEquals("isValid2", true, isValid(new char[][]{}));
        assertEquals("isValid3", false, isValid(new char[][]{null}));
        assertEquals("isValid4", true, isValid(new char[][]{{'a', 'b'}}));
        assertEquals("isValid5", false, isValid(new char[][]{{'a'}}));
        assertEquals("isValid6", false, isValid(new char[][]{{'a', 'b'}, {'a', 'c'}}));
        assertEquals("isValid7", false, isValid(new char[][]{{'a', 'b'}, {'c', 'b'}}));
        assertEquals("isValid8", true, isValid(new char[][]{{'a', 'b'}, {'c', 'd'}}));
        printlnInfo("END isValid");
        printBar();
    }
    
    public char[][] invert(char[][] key) {
        char[][] invertedKey = key;
        char[] leftColumn = getColumn(key,0);
        char[] rightColumn = getColumn(key, 1);

        for(int row = 0; row < key.length; row++){
            for(int column = 0; column < key[0].length; column++){
                if(column == 0){
                    invertedKey[row][column] = rightColumn[row];
                }
                else if(column == 1){
                    invertedKey[row][column] = leftColumn[row];
                }
            }
        }

        return invertedKey;
        //throw new UnsupportedOperationException("Pas 6");
    }
     
    public void testInvert() {
        printlnInfo("BEGIN invert");
        assertEquals("invert1", new char[][]{}, invert(new char[][] {}));
        assertEquals("invert2", new char[][]{{'b', 'a'}}, invert(new char[][] {{'a', 'b'}}));
        assertEquals("invert3", new char[][]{{'b', 'a'}, {'d', 'c'}}, invert(new char[][] {{'a', 'b'}, {'c', 'd'}}));
        printlnInfo("END invert");
        printBar();
    }
    
    public char[][] createKey(String left, String right) {
        int keyLength;
        char[] charLeft = left.toCharArray();
        char[] charRight = right.toCharArray();

        if(charRight.length <= charLeft.length){
            keyLength = charRight.length;
        }
        else{
            keyLength = charLeft.length;
        }
        char[][] key = new char[keyLength][2];

        for(int i = 0; i < keyLength; i++){
            for(int j = 0; j < 2; j++){
                if(j == 0){
                    key[i][j] = charLeft[i];
                }
                else {
                    key[i][j] = charRight[i];
                }
            }
        }
        return key;
        //throw new UnsupportedOperationException("Pas 7");
    }
    
    public void testCreateKey() {
        printlnInfo("BEGIN createKey");
        assertEquals("createKey1", new char[][]{}, createKey("", "anything"));
        assertEquals("createKey2", new char[][]{}, createKey("anything", ""));
        assertEquals("createKey3", new char[][]{{'a', 'b'}}, createKey("a", "bc"));
        assertEquals("createKey4", new char[][]{{'a', 'c'}}, createKey("ab", "c"));
        assertEquals("createKey5", new char[][]{{'a', 'c'}, {'b', 'd'}}, createKey("ab", "cd"));
        printlnInfo("END createKey");
        printBar();
    }
    
    public int encodeChar(char[][] key, char c) {
        int encoderResult = 0;
        char[] charColumn = getColumn(key, 0);

        if(containsInSuffix(charColumn, 0, c) == true){
            for(int i = 0; i < charColumn.length; i++){
                if(key[i][0] == c){
                    encoderResult = key[i][1];
                }
            }
        }
        else{
            encoderResult = -1;
        }
        return encoderResult;
        //throw new UnsupportedOperationException("Pas 8");
    }
    
    public void testEncodeChar() {
        printlnInfo("BEGIN encodeChar");
        assertEquals("encodeChar1", -1, encodeChar(new char[][]{}, 'a'));
        assertEquals("encodeChar2", 'b', encodeChar(new char[][]{{'a', 'b'}}, 'a'));
        assertEquals("encodeChar3", 'd', encodeChar(new char[][]{{'a', 'b'}, {'c', 'd'}}, 'c'));
        printlnInfo("END encodeChar");
        printBar();
    }
    
    public String encodeText(char[][] key, String clearText) {
        char[] charClearText = clearText.toCharArray();
        char[] keyLeftColumn = getColumn(key, 0);
        char[] encodedText = new char[charClearText.length];

        for(int i = 0; i < charClearText.length; i++){
            if(containsInSuffix(keyLeftColumn, 0, charClearText[i]) == false){
                return null;
            }
            else{
                encodedText[i] = (char) encodeChar(key,charClearText[i]);
            }
        }
        return new String(encodedText);
        //throw new UnsupportedOperationException("Pas 9");
    }
    
    public void testEncodeText() {
        printlnInfo("BEGIN encodeText");
        assertEquals("encodeText1", "", encodeText(new char[][]{}, ""));
        assertEquals("encodeText2", null, encodeText(new char[][]{{'a', 'b'}}, "aca"));
        assertEquals("encodeText3", "bddb", encodeText(new char[][]{{'a', 'b'}, {'c', 'd'}}, "acca"));
        printlnInfo("END encodeText");
        printBar();
    }
 
    public String decodeText(char[][] key, String encodedText) {
        char[][] invertedKey = invert(key);
        String decodedText = encodeText(invertedKey, encodedText);
        
        return decodedText;
        //throw new UnsupportedOperationException("Pas 10");
    }

    public void testDecodeText() {
        printlnInfo("BEGIN decodeText");
        assertEquals("decodeText1", "", decodeText(new char[][]{}, ""));
        assertEquals("decodeText2", "acca", decodeText(new char[][]{{'a', 'b'}, {'c', 'd'}}, "bddb"));
        printlnInfo("END decodeText");
        printBar();
    }

    // Assert functions
    
    public void assertEquals(String message, boolean expected, boolean computed) {
        if (expected == computed) {
            printlnOk(message);
        } else {
            printlnError(message + " Expected " + expected + " but found " + computed);
        }
    }
    
    public void assertEquals(String message, char[] expected, char[] computed) {
        if (Arrays.equals(expected, computed)) {
            printlnOk(message);
        } else {
            printlnError(message + " Expected " + Arrays.toString(expected)
                    + " but found " + Arrays.toString(computed));
        }
    }
    
    public void assertEquals(String message, int expected, int computed) {
        if (expected == computed) {
            printlnOk(message);
        } else {
            printlnError(message + " Expected " + expected + " but found " + computed);
        }
    }
    
    public void assertEquals(String message, char[][] expected, char[][] computed) {
        if (expected.length != computed.length) {
            printlnError(message + " Expected has length " + expected.length
                    + " but computed has " + computed.length);
            return;
        }
        for (int i = 0; i < computed.length; i += 1) {
            char[] er = expected[i];
            char[] cr = computed[i];
            if (!Arrays.equals(er, cr)) {
                printlnError("\tRow " + i + " of expected is "
                        + Arrays.toString(er) + " in computed is " 
                        + Arrays.toString(cr));
                return;
            }
        }
        printlnOk(message);
    }
    
    public void assertEquals(String message, String expected, String computed) {
        if ( expected == null ? computed == null : expected.equals(computed) ) {
            printlnOk(message);
        } else {
            printlnError(message + " Expected " + expected + " but found " + computed);
        }
    }

    // Colorize output for CommandLineProgram

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public void printlnInfo(String message) {
        if (acm.program.CommandLineProgram.class.isInstance(this))
            println(ANSI_BLUE + message + ANSI_RESET);
        else
            println(message);
    }

    public void printlnOk(String message) {
        if (acm.program.CommandLineProgram.class.isInstance(this))
            println(ANSI_GREEN + "OK: " + message + ANSI_RESET);
        else
            println("OK: " + message);
    }

    public void printlnError(String message) {
        if (acm.program.CommandLineProgram.class.isInstance(this))
            println(ANSI_RED + "ERROR: " + message + ANSI_RESET);
        else
            println("ERROR: " + message);
    }

    public void printBar() {
        println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        new SubstitutionCypher().start(args);
    }
}