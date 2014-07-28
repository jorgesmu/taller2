
//Cargo el valor inmediato, 15 en decimal en el Registro 0;

ldi r0,15

//Cargo el valor inmediato, 10 en hexa en el Registro 1;

ldi r1,0xA

//Almaceno en memoria el contenido del registro 1 en la posicion 05
stm r1,05

//Almaceno en memoria el contenido del registro 0 en la posicion 56
stm r0,0x56

//Cargo en el r3 el contenido de memoria en la posicion 56

ldm r2,0x56