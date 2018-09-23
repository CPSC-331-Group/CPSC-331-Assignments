echo ' '
echo 'Dynamic Tests for the Main Method of SHufflepuff'
echo ' '
echo 'Executing java.SHufflepuff'
echo ' '
echo 'Expected and Actual Output:'
echo 'Silly muggle! One integer input is required.'
java SHufflepuff
echo ' '
echo 'Rationale: No command-line inputs have been provided.'
echo ' '
echo ' '
echo 'Executing java.SHufflepuff 2 4'
echo ' '
echo 'Expected and Actual Output:'
echo 'Silly muggle! One integer input is required.'
java SHufflepuff 2 4
echo ' '
echo 'Rationale: More than one command-line input has been provided.'
echo ' '
echo ' '
echo 'Executing java.SHufflepuff xy14'
echo ' '
echo 'Expected and Actual Output:'
echo 'Silly muggle! One integer input is required.'
java SHufflepuff xy14
echo ' '
echo 'Rationale: The input is a string, rather than an integer.'
echo ' '
echo ' '
echo 'Executing java.SHufflepuff -2'
echo ' '
echo 'Expected and Actual Output:'
echo 'Silly muggle! The integer input cannot be negative.'
java SHufflepuff -2
echo ' '
echo 'Rationale: The input really is a negative integer.'
echo ' '
echo ' '
echo 'For all remaining tests, the input is a non-negative integer and'
echo 'the expected output is the corresponding Hufflepuff number.'
echo ' '
echo ' '
echo 'Executing SHufflepuff 0'
echo ' '
echo 'Expected and Actual Output:'
echo '10'
java SHufflepuff 0
echo ' '
echo ' '
echo 'Executing SHufflepuff 1'
echo ' '
echo 'Expected and Actual Output:'
echo '9'
java SHufflepuff 1
echo ' '
echo ' '
echo 'Executing SHufflepuff 2'
echo ' '
echo 'Expected and Actual Output:'
echo '8'
java SHufflepuff 2
echo ' '
echo ' '
echo 'Executing SHufflepuff 3'
echo ' '
echo 'Expected and Actual Output:'
echo '7'
java SHufflepuff 3
echo ' '
echo ' '
echo 'Executing SHufflepuff 4'
echo ' '
echo 'Expected and Actual Output:'
echo '6'
java SHufflepuff 4
echo ' '
echo ' '
echo 'Executing SHufflepuff 5'
echo ' '
echo 'Expected and Actual Output:'
echo '5'
java SHufflepuff 5
echo ' '
echo ' '
echo 'Executing SHufflepuff 6'
echo ' '
echo 'Expected and Actual Output:'
echo '4'
java SHufflepuff 6
echo ' '
echo ' '
echo 'Executing SHufflepuff 7'
echo ' '
echo 'Expected and Actual Output:'
echo '3'
java SHufflepuff 7
echo ' '
echo ' '
echo 'Executing SHufflepuff 8'
echo ' '
echo 'Expected and Actual Output:'
echo '2'
java SHufflepuff 8
echo ' '
echo ' '
echo 'Executing SHufflepuff 9'
echo ' '
echo 'Expected and Actual Output:'
echo '1'
java SHufflepuff 9
echo ' '
echo ' '
echo 'Executing SHufflepuff 10'
echo ' '
echo 'Expected and Actual Output:'
echo '0'
java SHufflepuff 10
echo ' '
echo ' '
echo 'Executing SHufflepuff 11'
echo ' '
echo 'Expected and Actual Output:'
echo '-1'
java SHufflepuff 11
echo ' '
echo ' '
