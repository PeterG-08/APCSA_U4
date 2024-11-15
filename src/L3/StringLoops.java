package L3;

import java.util.List;

public class StringLoops {
    public static void main(String[] args) {
        StringLoops loops = new StringLoops();
        System.out.println(loops.countVowels("Apples and bananas"));
        System.out.println(loops.countVowels("Hello Joe"));
        System.out.println(loops.countVowels("Hmm.. pssh!"));
        System.out.println(loops.countVowels("I"));
        System.out.println(loops.countVowels("Supercalifragilisticexpialidocious"));
    }

    // default constructor; no instance variables
    public StringLoops() {
    }

    /* Returns the number of times "searchString" appears in "origString";
   matches should NOT be case sensitive.

   Examples:
   - if searchString = "an" and origString = "Apples and bananas",
     this method returns 3: Apples and bananas
   - if searchString = "tat" and origString = "Ratatattat",
     this method returns 3: Ratatattat (note that two overlap)
   - if searchString = "lower" and origString = "sunflower",
     this method returns 1: sunflower
   - if searchString = "haha" and origString = "Hahahahaha",
     this method returns 4: Hahahahaha
(note that two overlap)
   - if searchString = "HAHA" and origString = "Hahahahaha",
     this method returns 4: Hahahahaha (note that two overlap)
   - if searchString = "rain" and origString = "it’s the brain drain pain train",
     this method returns 3: it’s the brain drain pain train
   - if searchString = "was" and origString = "I was about to call you, wasn’t I?",
     this method returns 2
   - if searchString = "but" and origString = "I was about to call you, wasn’t I?",
     this method returns 0
   - if searchString = "i" and origString = "Supercalifragilisticexpialidocious",
     this method returns 7
  */
    public int countString(String searchString, String origString) {
        /* to be implemented */
        searchString = searchString.toLowerCase();
        origString = origString.toLowerCase();

//        searchString.
        return 0;
    }

    /* Returns the number of vowels ("a", "e", "i", "o", "u") that appear in "origString";
    matches should NOT be case sensitive.

    (Note: you could do this by simply calling your countCharacters method 5 times in a
    row, one for each vowel, but that would lead to traversing the entire origString 5
    times -- it’s more efficient to only traverse the string once, so don’t use your
    countCharacters method for this!)

     Examples:
     - if origString = "Apples and bananas", this method returns 6: Apples and bananas
     - if origString = "Hello Joe", this method returns 4: Hello Joe
     - if origString = "Hmm.. pssh!", this method returns 0
     - if origString = "I", this method returns 1
     - if origString = "Supercalifragilisticexpialidocious", this method returns 16
    */
    public int countVowels(String origString)
    {
        origString = origString.toLowerCase();

        List<Character> vowels = List.of('a', 'e', 'i', 'o', 'u');

        int vowelCount = 0;

        for (char letter: origString.toCharArray()) {
            if (vowels.contains(letter)) {
                vowelCount ++;
            }
        }

        return vowelCount;
    }

    //    /* Returns the number of times "character" appears in "searchString"
//
//       This should be case sensitive!
//
//       Examples:
//       - if character = "a" and searchString = "Apples and bananas",
//         this method returns 4 (it finds BOTH "A" and "a")
//       - if character = "A" and searchString = "Apples and bananas",
//         this method returns 1 (it finds BOTH "A" and "a")
//       - if character = "!" and searchString = "Hello! Nice day!",
//         this method returns 2
//      */
//    public int countCharacters(String character, String searchString) {
//        /* if you finished this in the last lesson, start with “reverseString2” */
//        /* if you did not, complete this now! */
//    }
//
//    /* Returns the original string reversed
//
//     Examples:
//     - if origString = "hello!" this method returns "!olleh"
//     - if origString = "Apples and bananas" this method returns "sananab dna selppA"
//    */
    public String reverseString(String origString) {
        String out = "";

        for (int i=origString.length() - 1; i>=0; i--) {
            out = out + origString.charAt(i);
        }

        return out;
    }

// --- FIVE NEW METHODS TO ADD & IMPLEMENT ARE BELOW ---

    /* Returns the original string reversed -- SECOND IMPLEMENTATION

     Examples:
     - if origString = "hello!" this method returns "!olleh"
     - if origString = "Apples and bananas" this method returns "sananab dna selppA"
    */
    public String reverseString2(String origString) {
        String out = "";

        for (int i=0; i<origString.length(); i++) {
            out = origString.charAt(i) + out;
        }

        return out;
    }
}
