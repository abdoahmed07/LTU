# D0011E Lab 5 (The improved MIPS version) Veryl Edition

The goal of this lab is to implement a data memory for the simplified 32-bit MIPS processor from Lab 4 and add support for the following instructions:

- `lw` and `sw`
- `beq` and `Jump` to target

This lab is based on Lab 4. Start by importing the Veryl code from lab 4 into this project. You do not have to import `regfile` as one is already included here.

## Warning

Make sure you are on the latest Veryl nightly:

```
verylup install nightly
verylup update
verylup override set nightly
```

## Preparation

**Q1.** Explain how the `lw` and `sw` instructions will work and show the active paths during each instruction.

lw rt, offset(rs): The ALU adds rs and the sign-extended offset to get the memory address. The data memory is read at that address and the result goes into rt. Control signals: alu_source=1, mem_to_reg=1, write_enable=1.

sw rt, offset(rs): Same address calculation. The value of rt is written to memory at that address. No register is written. Control signals: alu_source=1, mem_write=1, write_enable=0.

**Q2.** Similarly explain how the `beq` instruction will work and show the active paths during its execution.

The ALU subtracts rt from rs. If the result is zero the branch is taken. The branch target is PC+4 plus the sign-extended offset shifted left by 2. No register or memory is written. Control signals: alu_source=0, alu_sub=1, branch=1, write_enable=0. The PC takes the branch target when branch & zero = 1, otherwise PC+4.

**Q3.** Why should the main ALU not be used for the `beq` branch target computation?

The ALU is used to subtract rs - rt to check if they are equal. Since it is already doing that calculation you cant also use it for the branch address in the same cycle. So the branch target (PC+4 + offset<<2) is calculated separately with its own adder outside the ALU.

---

**Q4.** Explain how the `J` instruction will work and show the active paths during its execution.

The 26-bit target field is shifted left by 2 and the top 4 bits of PC+4 are placed in front: {PC+4[31:28], instr[25:0], 00}. The PC takes this value next cycle. Nothing is written to registers or memory. Control signals: jump=1, write_enable=0.

**Q5.** How far is it possible to jump within a program using `J` and `beq` respectively?

J can reach any address within the same 256 MB region (2^28 bytes, or 2^26 word addresses). beq has a 16-bit signed offset so it can jump about ±128 KB relative to PC+4.

---

Instruction decoder table including the new signals for `lw`, `sw`, `beq` and `J`:

| Instruction | Opcode | funct | WE | ALUControl | RegDestination | ALUSource | mem_write | mem_to_reg | branch | jump |
|:-----------:|:------:|:-----:|:--:|:----------:|:--------------:|:---------:|:---------:|:----------:|:------:|:---:|
| ADD         | 0      | 32    | 1  | 10 / sub=0 | 1 (rd)         | 0         | 0 | 0 | 0 | 0 |
| SUB         | 0      | 34    | 1  | 10 / sub=1 | 1 (rd)         | 0         | 0 | 0 | 0 | 0 |
| AND         | 0      | 36    | 1  | 00 / sub=0 | 1 (rd)         | 0         | 0 | 0 | 0 | 0 |
| OR          | 0      | 37    | 1  | 01 / sub=0 | 1 (rd)         | 0         | 0 | 0 | 0 | 0 |
| SLT         | 0      | 42    | 1  | 11 / sub=1 | 1 (rd)         | 0         | 0 | 0 | 0 | 0 |
| ADDI        | 8      | —     | 1  | 10 / sub=0 | 0 (rt)         | 1         | 0 | 0 | 0 | 0 |
| SLTI        | 10     | —     | 1  | 11 / sub=1 | 0 (rt)         | 1         | 0 | 0 | 0 | 0 |
| LW          | 35     | —     | 1  | 10 / sub=0 | 0 (rt)         | 1         | 0 | 1 | 0 | 0 |
| SW          | 43     | —     | 0  | 10 / sub=0 | X              | 1         | 1 | 0 | 0 | 0 |
| BEQ         | 4      | —     | 0  | 10 / sub=1 | X              | 0         | 0 | 0 | 1 | 0 |
| J           | 2      | —     | 0  | X          | X              | X         | 0 | 0 | 0 | 1 |

---

Division program using repeated subtraction (`N / D`, storing `Q` and `R`):

**Q6.** MIPS assembly:

```mips
addi r12, r0, 0       # Q = 0
        addi r13, r0, N       # R = N
        addi r11, r0, D       # D = divisor
loop:   slt  r14, r13, r11    # r14 = (R < D)
        beq  r14, r0, body    # if R >= D go to body
        j    done             # R < D, exit
body:   addi r12, r12, 1      # Q++
        sub  r13, r13, r11    # R -= D
        j    loop
done:   sw   r12, Q_addr(r0)  # store Q
        sw   r13, R_addr(r0)  # store R
```

**Q7.** Machine code:

```mips
0x48: 0x200C0000  # addi r12, r0, 0      Q = 0
0x4C: 0x200D000C  # addi r13, r0, 12     R = N = 12
0x50: 0x200B0005  # addi r11, r0, 5      D = 5
0x54: 0x01AB702A  # slt  r14, r13, r11
0x58: 0x11C00001  # beq  r14, r0, +1
0x5C: 0x0800001B  # j    0x6C
0x60: 0x218C0001  # addi r12, r12, 1
0x64: 0x01AB6822  # sub  r13, r13, r11
0x68: 0x08000015  # j    0x54
0x6C: 0x200F007C  # addi r15, r0, 124
0x70: 0xADEC0000  # sw   r12, 0(r15)
0x74: 0x200F0078  # addi r15, r0, 120
0x78: 0xADED0000  # sw   r13, 0(r15)
(divisions 2 and 3 follow same pattern at 0x7C and 0xB0)
```

**Q8.** How many cycles are needed to reach the final result?

Around 65 cycles from reset for all three divisions. The lab 4 program runs first (12 cycles), then the SW/LW part (4 cycles), then the beq loop (4 cycles). Each division loop iteration takes 5 cycles (slt, beq, addi, sub, j) so it depends on how big the quotient is. Division 1 needs 2 iterations so around 20 cycles, division 2 needs 1 so around 15, and division 3 exits straight away since 5 < 12 so around 10. More iterations = more cycles.

**Q9.** How can you leverage the debug statements to test internal signals of different components?

The dbg_reg input lets you pick any register to read on dbg_reg_data without affecting execution. Same for dbg_dm_addr and the data memory. In the test bench you change the selector before a clk.next() and assert the value after. This way you can check any register or memory word at any point without needing extra output ports.

## Part 1

Based on `regfile.veryl`, implement a data memory where data can be stored and fetched.

The memory interface:

```
module DataMem #(
    param Words: u32 = 32,
) (
    i_clk     : input  clock               ,
    i_reset   : input  reset_async_high    ,
    i_we      : input  logic               ,
    i_addr    : input  logic           <32>, // byte address, but only word operations supported
    i_data    : input  logic           <32>,
    i_dbg_addr: input  logic           <32>,
    o_data    : output logic           <32>,
    o_dbg_data: output logic           <32>,
) 
```

- Data writing is synchronous — written at the rising edge of the clock when `i_we` is high.
- Data reading is asynchronous — data at the given address is output whenever `i_we` is low.
- Make sure the address is in an accessible range.
- Only 32 words of memory space are needed. All accesses can be assumed word-aligned.

**Q10:** Describe how you made sure that the address is in an accessible range.

I check if i_addr is below 128 (which is 32 words times 4 bytes each). If the address is too high, writes dont happen and reads just return 0. The word index comes from bits [6:2] of the address which gives a 5-bit value, so it can only ever be 0 to 31 and wont go out of bounds.

## Part 2

![Part 2 simulation](image/simulation_part2.png)

The waveform shows register values stepping through r1 to r9 and data memory at addresses 8 and 4 after the SW/LW instructions. r8 reads back 2 from address 8 and r9 reads back -6 from address 4.

Implement support for `lw` and `sw` instructions. Copy `vips1.veryl` to `vips.veryl` and update the module definition:

```
module Vips (
    i_clk         : input  clock               ,
    i_reset       : input  reset_async_high    ,
    i_dbg_reg     : input  logic           <5> ,
    i_dbg_dm_addr : input  logic           <32>,
    o_dbg_reg_data: output logic           <32>,
    o_dbg_dm_data : output logic           <32>,
)
```

Add to `instr_mem.veryl` after the lab 4 program:

```mips
sw r2, 0(r7)  
lw r8, 0(r7)  
sw r3, 4(r9)  
lw r9, 4(r10)  
```

## Part 3

Modify the MIPS to support `beq` and `Jump to target`.

Test the full control unit (supporting `sw`, `lw`, `beq`, `J`, `add`, `addi`, `sub`, `and`, `or`, `slt`, `slti`) in the test bench with assert statements.

Add the following two instructions to the end of the program from Part 2:

```mips
sub r2, r2, r4
beq r2, r4, -2
```

## Part 4

Test the division program from the preparation using the MIPS. Add all 3 divisions to the program memory containing the Part 2 program.

The three divisions to test:

1. N = 12, D = 5 → Q stored in data memory position 31, R in position 30
2. N = 12, D = 12 → Q stored in data memory position 28, R in position 27
3. N = 5, D = 12 → Q stored in data memory position 26, R in position 25

(Memory position n corresponds to byte address n×4.)

**Congratulations. You have made a general-purpose processor that can be configured on a Field-programmable gate array (FPGA).**
