// PROGRAMA DE PRUEBA 3

// lectura de numeros
ldi r0, 0
ldi r2, 127 // minimo
ldi r3, 0 // maximo

// numero 1
leerNum:
ldm r1, 0xFD
cmp r1,r0
jpz fin // ingreso cero, voy a fin
cmp r2,r1
jpc noEsMin
or r2, r0, r1 // actualizo el minimo

noEsMin:
cmp r1,r3
jpc noEsMax
or r3, r0, r1 // actualizo el maximo

noEsMax:
jmp leerNum // leo otro numero

fin:
ldm r1, 0xFD // pido si quiere maximo o minimo
or r0,r1,r0
jpz quiereMin
stm r3, 0xFF // imprimo por pantalla
jmp terminado
quiereMin: stm r2, 0xFF
terminado: nop
