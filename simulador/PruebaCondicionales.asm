ldi r0,3
ldi r1,1

jmp pruebaFlagCarry
add r2,r1,r0

pruebaFlagCarry:
 cmp r1,r0
 jpc pruba

pruba:
 add r3,r1,r0


