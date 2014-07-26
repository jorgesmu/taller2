// PROGRAMA DE PRUEBA 1

//Leo un numero y lo guardo en r1
ldm r1, 0xFD

//Leo otro numero y lo guardo en r2
ldm r2, 0xFD

//multiplico ambos numeros ingresado y lo guardo en r3
multfp r3,r1,r2

//Se almacena en salida el resultado
stm r3, 0xFF

