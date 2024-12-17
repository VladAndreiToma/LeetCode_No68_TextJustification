class Solution:
    def fullJustify(self, words, maxWidth):
        # result is constructed with twice the amount of words for conservative perspectives
        # the worst case in this problem is that each word might occupy one line
        # but for security, we take 2 times that amount of lines for cases where words are very short or the maximum number per line is small
        result = [''] * (len(words) * 2)
        count = 0
        i = 0
        # we start 2 counters from 0

        while i < len(words):
            # we start going over the list of words given through the "main" function list of arguments
            lineLength = 0
            start = i
            # Trying to fit as many words as possible into one line
            # specifically, we want to see that the current lineLength generated + the space occupied by the next word will not exceed the limits imposed by the problem via maxWidth
            while i < len(words) and lineLength + len(words[i]) + (i - start) <= maxWidth:
                lineLength += len(words[i])
                i += 1
                # in this repetitive cycle we just construct as long as we can
            # We established how many words we can put inside
            # now we have to construct the string with the proper justification
            # We will make use of StringBuilder equivalent in Python, i.e., a mutable list of characters
            line = []
            # Now let's format the string with spaces
            numberOfWords = i - start  # i extract the number of words that manage to compose a line as the current position of counter i - start
            totalSpaces = maxWidth - lineLength  # the total spaces that are still allowed is the max length allowed - the length of the line itself

            if i == len(words) or numberOfWords == 1:
                # Last line or single word: left-justified
                # this if is true only if the word is singular or it is the last line - we are imposed to left justify it
                # so we put the amount of spaces at the end, after we finish putting the words with a space in between each of them
                for j in range(start, i):
                    line.append(words[j])
                    if j < i - 1:
                        line.append(' ')
                        # put words with spaces
                while len(line) < maxWidth:
                    line.append(' ')  # fill the remaining spaces
            else:
                # Full justification: distribute spaces evenly between words
                inBetweenSpaces = totalSpaces // (numberOfWords - 1)  # we want to extract how many spaces we should have in between the words
                extraSpaces = totalSpaces % (numberOfWords - 1)  # also how many extra spaces we remain with so we use division and modulo for that

                for j in range(start, i):
                    line.append(words[j])
                    # we have to fill the line with words
                    if j < i - 1:
                        spacesToAdd = inBetweenSpaces + (1 if j - start < extraSpaces else 0)
                        # we make sure that we add spaces and also we account for the left side with them and add leftover spaces
                        # so we make use of the ternary operator (or inline if) to write everything nicely in one line
                        line.extend([' '] * spacesToAdd)  # we append normal spaces in between words

            # in a list, we store the formatted string with right justification
            result[count] = ''.join(line)
            count += 1

        # Trim the result array to the exact size
        finalResult = result[:count]
        return finalResult  # returning the result as a list of strings