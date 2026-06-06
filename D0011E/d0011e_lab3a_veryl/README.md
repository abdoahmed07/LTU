# D0011E Lab 3a (4-bit ALU) Veryl Edition

The goal of this lab is to implement a 4-bit arithmetic unit and a 4-bit logic unit. The labs should be completed individually, **however cooperation is encouraged.**  

All the Veryl code together with this README should be uploaded to GitLab. The uploaded README file should include the filled out tables and the answers to the questions.

## Preparation

Fork this repository and clone your fork as you did for labs 1 and 2.

## Part 1

Based on the correct design chosen in quiz 1, write Veryl code for

- a one-bit full adder. You can use the stub in `full_adder.veryl` as starting point.
- a 4-bit adder that uses four one-bit full adders as components. You can use the stub in `adder.veryl`.
- a 4-bit addition and subtraction unit that uses a 4-bit adder as a component. Stub is in `arith.veryl`.

Based on your understanding of how the ARITH component functions, complete the below table. In the table `U` stands for unsigned decimal, `S` for signed decimal. Notice, that any bit vector can be viewed (interpreted) as either a singed or unsigned value. An arithmetic operation on the other hand (as we will see later) assumes an interpretation for the correctness of the result (shown as `U Ok` and `S Ok` in the table).

|  A   |  B   | Op | R    | U A | U B | U R | U Ok | S A | S B | S R | S Ok | V | C |
|------|------|----|------|-----|-----|-----|------|-----|-----|-----|------|---|---|
| 0100 | 0011 |  + | 0111 |  4  |  3  |  7  | Yes  |  4  |  3  |  7  | Yes  | 0 | 0 |
| 0111 | 0010 |  + | 1001 |  7  |  2  |  9  | Yes  |  7  |  2  | -7  | No   | 1 | 0 |
| 0111 | 0010 |  - | 0101 |  7  |  2  |  5  | Yes  |  7  |  2  |  5  | Yes  | 0 | 1 |
| 0000 | 1000 |  - | 1000 |  0  |  8  |  8  | No   |  0  | -8  | -8  | No   | 1 | 0 |


Verify your design (=check the outputs R, V, C) by performing manual computations.  
**Question 1:** Are the answers _mathematically_ correct? Can the overflow flag be used to detect errors in _unsigned addition/subtraction_? If not, can the carry flag be used to detect errors?

Rows 1, 2 and 3 are correct for unsigned. Row 4 (0 - 8 = -8) can't be represented in 4-bit unsigned so U Ok = No. The overflow flag V can't detect unsigned errors, but the carry flag C can. C=1 means the addition result is too large, and C=0 in subtraction means a borrow happened.

Verify your design (=check the outputs R, V, C) by performing manual computations.  
**Question 2:** Are the answers _mathematically_ correct? Can the overflow flag be used to detect errors in _signed addition/subtraction_? If not, can the carry flag be used to detect errors?

Rows 1 and 3 are correct for signed. Row 2 (7 + 2 = 9) and row 4 (0 - (-8) = 8) are both outside the signed range -8 to +7, so S Ok = No. The overflow flag V can detect signed errors, V=1 means the result is out of range. The carry flag C can't be used for signed overflow.

## Part 2

Based on the correct designs chosen in quiz 1, write Veryl code for a 4-bit logic unit. You can find a stub for it in `logic.veryl`.

## Part 3 Upload your source code and the README file

The repository should include:

- Implementation of the 4-bit arithmetic unit and code.
- Implementation of the 4-bit logic unit and code.
- Updated tables and answers to all the questions.

## Testing

In the files provided, we use Veryl testbenches.

### Examples

The `full_adder.veryl` has no dependencies, thus you can test it by:

```shell
veryl test src/full_adder.veryl
```

The `arith_veryl` depends on the `full_adder`, thus you need to provide both:

```shell
veryl test src/full_adder.veryl src/arith.veryl
```

You can also run all tests by just:

```shell
veryl test
```
