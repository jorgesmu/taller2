ldi r0,0xA
ldi r1,5

//Se suma los contenidos en complemento a 2 de los registros r0 y r1 y se guarda en r2
add r2,r1,r0

ldi r3,48
//Se suma los contenidos en punto flotante de los registros r3 y r3 y lo guardo en r4
addfp r4,r3,r3

//Se restan los contenidos en complemento a 2 de los registros r0 y r1 y se guarda en r5
sub r5,r0,r1

//Se multiplica el contenido de r3 y r3 y lo guardo en r6
multfp r6,r3,r3

//Se suma un inmediato y se guardo en el registro r7
adi r7,r0,0xF