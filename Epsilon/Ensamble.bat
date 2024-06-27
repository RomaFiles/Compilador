echo off
cd C:/Epsilon
g++ -o Ejecutable Implementacion.cpp
gcc -S Implementacion.cpp
ren Implementacion.S Ensamblador.S
title Ejecutando el Programa
cls
Ejecutable.exe
@echo off
::exit
exit
