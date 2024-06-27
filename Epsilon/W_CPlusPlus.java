public class W_CPlusPlus {
    private java.io.File f;
    
    public W_CPlusPlus(String ruta){
        f = new java.io.File(ruta);
        CleanCPP();
        Write_to_CPlus(
            "#include <iostream>\n"+
            "#include <string>\n"+
            "#include <typeinfo>\n"+
            "#include <cstring>\n"+
            "#include<locale.h>\n"+
            "using namespace std;\n"+
            "int main(){\n"
        );

    }

    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                W_CPlusPlus pe = new W_CPlusPlus("C:/Epsilon/Implementacion.cpp");
                pe.Write_to_CPlus("cout << \"prueba transcrita\";\n");
                pe.end_to_CPlus();
            }
        });        
    }
    
    public void Write_to_CPlus(String s){//Transcribe el codigo
//Escritura
        try {

            java.io.FileWriter w = new java.io.FileWriter(f , true);

            java.io.BufferedWriter bw = new java.io.BufferedWriter(w);

            java.io.PrintWriter wr = new java.io.PrintWriter(bw);
            wr.write(s);//escribimos en el archivo

            //wr.append("\n"); //concatenamos en el archivo sin borrar lo existente

            //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
            //de no hacerlo no se escribirá nada en el archivo
            wr.close();
            bw.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.out.println("no se pudo escribir");
        }
    }

    private void CleanCPP(){//Se escribe

        try {

            java.io.FileWriter w = new java.io.FileWriter(f , false);

            java.io.BufferedWriter bw = new java.io.BufferedWriter(w);

            java.io.PrintWriter wr = new java.io.PrintWriter(bw);
            
            wr.write("");//Escribimos en el archivo.

            //wr.append("\n"); //Concatenamos en el archivo sin borrar lo existente.

            wr.close();
            bw.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.out.println("No se puedo limpiar");
        }
    }

    public void end_to_CPlus(){
        Write_to_CPlus("cout << endl << \"Ejecuci\u00f3n Finalizada\"<< endl;\n");
        Write_to_CPlus("system(\"pause\");\nreturn 0;\n}");
    }
}


