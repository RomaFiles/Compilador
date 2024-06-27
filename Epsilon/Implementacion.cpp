#include <iostream>
#include <string>
#include <typeinfo>
#include <cstring>
#include<locale.h>
using namespace std;
int main(){
setlocale(LC_CTYPE,"spanish");
int numeroEntero;
string input; 
string s= "f";
int n;
float A ;
float B ;
float C ;
float D ;
float E ;
float F ;
float G ;
G= 10 ;
A= 5 ;
B= 8 ;
A= A + B ;
float H ;
if (A > B  ){
H= 15 ;
}
cout << "Ingrese el primer valor numerico" << endl;
numeroEntero = s.compare(typeid(A).name()); 
if (numeroEntero == 0) {cin >> input;
try {
A = stoi(input); 
 }catch (const invalid_argument& e){
cout << "Error fatal: No ingresaste un numero. \n"; 
return 0; }
 } 
else{
cin >> A;
 }cout << "Ingrese el segundo valor numerico" << endl;
numeroEntero = s.compare(typeid(B).name()); 
if (numeroEntero == 0) {cin >> input;
try {
B = stoi(input); 
 }catch (const invalid_argument& e){
cout << "Error fatal: No ingresaste un numero. \n"; 
return 0; }
 } 
else{
cin >> B;
 }C= A + B ;
if (A < B  ){
cout << "El numero menor es " << A << endl;
cout << "El numero mayor es " << B << endl;
}
if (A > B  ){
cout << "El numero menor es " << B << endl;
cout << "El numero mayor es " << A << endl;
}
cout << "La suma es " << C << endl;
cout << endl << "Ejecución Finalizada"<< endl;
system("pause");
return 0;
}