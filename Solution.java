class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        // result is constructed with twice the ammounts of words for conservative perspectives
        // the worst case in this problem is that each word might occupy one line
        // but for security we take 2 times that amount of lines for cases where words are very short or the maximum number per line is small
        String[] result = new String[words.length * 2];
        int count = 0;
        int i = 0;
        // we start 2 contors from 0;

        while (i < words.length) {
            // we start going over the list of words given through the "main" function list of arguments
            int lineLength = 0;
            int start = i;
            // Trying to fit as many words as possible into one line
            // specifically we want tot see that the current lineLength generated + the space occupied by the next word will not exceed the limits imposed by the problem via maxWidth
            while (i < words.length && lineLength + words[i].length() + (i - start) <= maxWidth) {
                lineLength += words[i].length();
                i++;
                // in this repetitive cycle we just construct as long as we can
            }
            // We established how many words we can put inside
            // now we have to construct the string with the proper justification
            // We will make usage of StringBuilder:    since it is a mutable class.
            //      an object instance of this class can be worked upon many times and remains the same object , no new object is created no other memory is allocated
            //      it is not shareable safely around threads, but in this case we do not care
            //      it is the best choice for many string operations like the ones we are about to do next
            StringBuilder line = new StringBuilder();
            // Now let's format the string with spaces
            int numberOfWords = i - start;  // i extract the number of words that mange to compose a line as current position of contor i - start
            int totalSpaces = maxWidth - lineLength;  // the total spaces that are still allowed is the max length allowed - the length of the line itself

            if (i == words.length || numberOfWords == 1) {
                // Last line or single word: left-justified
                // this if is true only if the word is singular or it is the last line - we are imposed to left justify it
                // so we put the amount of spaces at the end , after we finish putting the words with a space in between each of them
                for (int j = start; j < i; j++) {
                    line.append(words[j]);
                    if (j < i - 1) {
                        line.append(' ');
                        //put words with spaces 
                    }
                }
                while (line.length() < maxWidth) {
                    line.append(' '); // fill the remaining spaces
                }
            } else {
                // Full justification: distribute spaces evenly between words
                int inBetweenSpaces = totalSpaces / (numberOfWords - 1); // we want to extract how many space we should have in between the words
                int extraSpaces = totalSpaces % (numberOfWords - 1); // also how many extra spaces we remain with so we use division and modulo for that

                for (int j = start; j < i; j++) {
                    line.append(words[j]);
                    // we have to fill the line with words
                    if (j < i - 1) {
                        int spacesToAdd = inBetweenSpaces + (j - start < extraSpaces ? 1 : 0);
                        // we make sure that we add spaces and also we account from the left side with them and add leftover spaces 
                        // so we make use of ternary operator to write everything nicely in one line
                        for (int k = 0; k < spacesToAdd; k++) {
                            line.append(' '); // we append normal space in between words 
                        }
                    }
                }
            }
            // in a list we store the formated string with right justification
            result[count++] = line.toString();
        }

        // Trim the result array to the exact size
        String[] finalResult = new String[count];
        for (int j = 0; j < count; j++) {
            finalResult[j] = result[j];
        }
        return List.of(finalResult); // Converting array to List for return
        // it has to be a list of strings so thats what we are converting it in
    }
}
