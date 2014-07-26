// PROGRAMA DE PRUEBA 2

// se lee el primer numero del dispositivo de entrada
ldm r0, 0xFD  // parte alta
ldm r1, 0xFD  // parte baja

// se lee el segundo numero del dispositivo de entrada
ldm r2, 0xFD  // parte alta
ldm r3, 0xFD  // parte baja


// SUMATORIA DE PARTE BAJA
// copio los dos bits mas bajos de cada numero
ldi r4, 3
and r5, r4, r1
and r6, r4, r3

// los sumo y guardo el primer carry
add r7, r5, r6
ldi r4, 4
and r8, r4, r7

// shifteo las partes bajas y el primer carry
// 2 bits a la derecha rellenando con ceros
sr0 r1
sr0 r1
sr0 r3
sr0 r3
sr0 r8
sr0 r8

// sumo (operandos y primer carry) y guardo
// el carry de toda la parte baja
add r8, r1, r8
add r8, r3, r8
ldi r4, 64       // bit 7
and r9, r4, r8   // r9 lo tengo que sumar con partes altas

// acomodo el carry de la parte baja
sr0 r9
sr0 r9
sr0 r9
sr0 r9
sr0 r9
sr0 r9

// shifteo la suma y agrego los bits menos significativos
sl0 r8
sl0 r8
ldi r4, 3
and r7, r4, r7 // saco el posible primer carry
add r8, r8, r7 // r8 tiene la suma definitiva de la parte baja


// SUMATORIA DE PARTE ALTA
add r9, r0, r9
add r9, r2, r9 // r9 tiene la suma definitiva de la parte alta


// El enunciado pida que se "imprima por pantalla".
// Se lo manda al puerto de salida (se van a pisar).

stm r8, 0xFF
stm r9, 0xFF
