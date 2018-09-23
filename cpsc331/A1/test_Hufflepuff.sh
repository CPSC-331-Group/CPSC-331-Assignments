echo ' '
echo 'Dynamic Tests for the Main Method of Hufflepuff'
echo ' '
echo 'Executing java.Hufflepuff'
echo ' '
echo 'Expected and Actual Output:'
echo 'Silly muggle! One integer input is required.'
java Hufflepuff
echo ' '
echo 'Rationale: No command-line inputs have been provided.'
echo ' '
echo ' '
echo 'Executing java.Hufflepuff 2 4'
echo ' '
echo 'Expected and Actual Output:'
echo 'Silly muggle! One integer input is required.'
java Hufflepuff 2 4
echo ' '
echo 'Rationale: More than one command-line input has been provided.'
echo ' '
echo ' '
echo 'Executing java.Hufflepuff xy14'
echo ' '
echo 'Expected and Actual Output:'
echo 'Silly muggle! One integer input is required.'
java Hufflepuff xy14
echo ' '
echo 'Rationale: The input is a string, rather than an integer.'
echo ' '
echo ' '
echo 'Executing java.Hufflepuff -2'
echo ' '
echo 'Expected and Actual Output:'
echo 'Silly muggle! The integer input cannot be negative.'
java Hufflepuff -2
echo ' '
echo 'Rationale: The input really is a negative integer.'
echo ' '
echo ' '
echo 'For all remaining tests, the input is a non-negative integer and'
echo 'the expected output is the corresponding Hufflepuff number.'
echo ' '
echo ' '
echo 'Executing Hufflepuff 0'
echo ' '
echo 'Expected and Actual Output:'
echo '10'
java Hufflepuff 0
echo ' '
echo ' '
echo 'Executing Hufflepuff 1'
echo ' '
echo 'Expected and Actual Output:'
echo '9'
java Hufflepuff 1
echo ' '
echo ' '
echo 'Executing Hufflepuff 2'
echo ' '
echo 'Expected and Actual Output:'
echo '8'
java Hufflepuff 2
echo ' '
echo ' '
echo 'Executing Hufflepuff 3'
echo ' '
echo 'Expected and Actual Output:'
echo '7'
java Hufflepuff 3
echo ' '
echo ' '
echo 'Executing Hufflepuff 4'
echo ' '
echo 'Expected and Actual Output:'
echo '6'
java Hufflepuff 4
echo ' '
echo ' '
echo 'Executing Hufflepuff 5'
echo ' '
echo 'Expected and Actual Output:'
echo '5'
java Hufflepuff 5
echo ' '
echo ' '
echo 'Executing Hufflepuff 6'
echo ' '
echo 'Expected and Actual Output:'
echo '4'
java Hufflepuff 6
echo ' '
echo ' '
echo 'Executing Hufflepuff 7'
echo ' '
echo 'Expected and Actual Output:'
echo '3'
java Hufflepuff 7
echo ' '
echo ' '
echo 'Executing Hufflepuff 8'
echo ' '
echo 'Expected and Actual Output:'
echo '2'
java Hufflepuff 8
echo ' '
echo ' '
echo 'Executing Hufflepuff 9'
echo ' '
echo 'Expected and Actual Output:'
echo '1'
java Hufflepuff 9
echo ' '
echo ' '
echo 'Executing Hufflepuff 10'
echo ' '
echo 'Expected and Actual Output:'
echo '0'
java Hufflepuff 10
echo ' '
echo ' '
echo 'Executing Hufflepuff 11'
echo ' '
echo 'Expected and Actual Output:'
echo '-1'
java Hufflepuff 11
echo ' '
echo ' '
