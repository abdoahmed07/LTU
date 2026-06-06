# D0011E Lab 4 (First MIPS version) Veryl Edition
The goal of this lab is to implement a simplified 32-bit MIPS processor that you have seen in quiz 2. 

We will only implement a subset of MIPS instructions (branch, store, and load instructions will be implemented in lab5). We will also leave out until lab 5 the implementation of data memory.

Before you start, study the files `regfile.veryl` and `instr_mem.veryl` which you can already find in `src`. You will need to understand them to continue with the lab. This will be your register file and the instructions that will be executed to test the designed processor. To check your understanding of the instructions in `instr_mem.veryl`, write a comment next to each instruction with its name, and the affected registers or values, an example is provided in the file.

Moreover, add your ALU files from Lab3b to the `src` folder. They should not need any modifications.

# Preparation

We recommend you have a look at Quiz 2 ahead of/in parallell with this lab, since they overlap.


## Warning
A recent bug in Veryl has been fixed in the latest nightly, make sure you update your toolchain to the latest nightly version by running
```
verylup install nightly
verylup update
```
, and also that you are using it for this project by running
```
verylup override set nightly
```

# Part 1:
You will implement a top-level module `Vips1`. This will be your primary module that all the components of the MIPS-Version presented in the Quiz will be instantiated in. You can find a stub in `vips.veryl`.

![MIPS Overview](mips.png)

It should include the following components: a register file (given to you), an ALU (from lab 3b), a memory with instructions(given to you), with the program counter you designed in lab 3b, a control unit, a sign extend block and the two multiplexers seen in the picture. You need to understand how you can use the two debug signals (```dbg_reg``` and ```dbg_reg_data```) for your unit tests. The debug signals allow you to check the internal state of the registry from the test bench. For example, after three clock cycles, the value in ```register1``` should be set to ```8```, which you can check using ```assert``` together with the debug signals.

You can find stubs for the control unit (`decoder.veryl`) and for the SignExtend module (`extend16_to32.veryl`) under `src/`. The multiplexer interfaces are down to you to design. 


You should support the following R- and **I**-type instructions (see quiz 2):
- **ADD, ADDI, SUB, SLT, SLTI, AND, OR** (from the core instruction set) 

Other requirements:
- Executing unimplemented instructions should have no effects on the stored values.
- The result from all instructions must be available in the register file in the next clock cycle.
- You may not use the clock for anything other than the register file and the program counter.

When implementing the control unit, refer to the lecture slides for its inputs and outputs and refer to tables B.1 and B.2 in the textbook for ```OP``` and ```FUNCT``` codes for each instruction that you need to follow and implement in your decoder.

# Part 2:
- Add a test bench, such that it executes the code stored in the program memory.
- Simulate the test bench and manually verify that the resulting values in the register file are correct.
- Make sure the resulting values are correct, refer to the code in the program memory and quiz 2.
- Write relevant unit tests to allow for automatic testing of your program, make sure to test all instructions of the program and make tests for the ControlUnit (ADD, ADDI, SUB, SLT, SLTI, AND, OR) and SignExtend. Use your knowledge of the registry state after each clock cycle to write the unit tests.

# Part 3 Upload your code and the README file created during the lab, remember:
* Implementation of the vips1 which executes the instructions in the program memory.
* Implementaion of the Decoder1 and Extend16to32.
* Working test bench including unit tests (```assert```). Remember to test all 3 components you have implemented.
* Make sure all the relevant files are in your git.
