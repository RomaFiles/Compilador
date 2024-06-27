
/*  NOTAS
Nivel 1: Multiplicación, División, Módulo
Nivel 2: Suma, Resta;
*/
import java.util.HashSet;
import java.util.Stack;
import java.util.ArrayList;

public class CTDOA {// CÓDIGO de TRES DIRECCIONES para OPERACIONES ARITMÉTICAS

    private java.util.ArrayList<String> list;
    private String codigoS = "", codigo = ""; // codigo de 3 direcciones
    private int no_var = 0;
    String nombreVariable = "T"; // genera la variable temporal

    public CTDOA() {
        list = new java.util.ArrayList<String>();
    }

    // version 1
    /*
     * public void add(String s){
     * this.list.add(s);
     * }
     */
    // version 2
    public void add(String s) {
        String s0 = "";
        boolean in = true;
        int l = list.size();
        int i = (l > 0) ? (l - 1) : 0;
        l = 0;
        for (String e : list) {// se obtiene el ultimo dato
            if (l == i)
                s0 = e;
            l++;
        }
        switch (s0) {
            case "1":
                if (s.equals("*")) {
                    // System.out.println("quitar");
                    in = false;
                }
                break;
            case "/":
                if (s.equals("1")) {
                    in = false;
                }
            case "*":
                if (s.equals("1")) {
                    // System.out.println("quitar");
                    in = false;
                }
                break;
        }

        if (in) {
            this.list.add(s);
        } else {
            list.set(i, "r0");
            // list.remove("r0");
        }
    }

    public String optimizarFunciones() {
        // Lista para almacenar las instrucciones optimizadas
        ArrayList<String> listaOptimizada = new ArrayList<>();

        // Variables temporales para almacenar los valores y operadores actuales
        String v1, v2, op;
        v1 = v2 = op = "";

        for (String instruccion : list) {
            String[] tokens = instruccion.split("\\s+");
            String resultado = "";

            // Verificar si es una operación aritmética válida
            if (tokens.length == 4) {
                v1 = tokens[1];
                op = tokens[2];
                v2 = tokens[3];

                // Realizar optimización de funciones para operaciones aritméticas
                if (esConstante(v1) && esConstante(v2)) {
                    double valor1 = Double.parseDouble(v1);
                    double valor2 = Double.parseDouble(v2);
                    double resultadoDouble = evaluarOperacion(valor1, op, valor2);
                    resultado = String.valueOf(resultadoDouble);
                }
            }

            // Si no se optimizó la operación, agregarla tal cual a la lista optimizada
            if (resultado.isEmpty()) {
                listaOptimizada.add(instruccion);
            } else {
                // Agregar el resultado optimizado a la lista de instrucciones optimizadas
                listaOptimizada.add(tokens[0] + "   " + resultado);
            }
        }

        // Actualizar la lista original con las instrucciones optimizadas
        list = listaOptimizada;

        // Generar el código de tres direcciones optimizado
        StringBuilder codigoOptimizado = new StringBuilder();
        for (String instruccion : list) {
            codigoOptimizado.append(instruccion).append("\n");
        }
        return codigoOptimizado.toString();
    }

    public String generarCodigosinoptimizar() {
        Stack<String> operadores = new Stack<>();
        Stack<String> operandos = new Stack<>();
        for (String token : list) {
            if (token.matches("[A-Z]+|[0-9]+")) {
                operandos.push(token);
            } else if (token.matches("[+\\-*/%]")) {
                while (!operadores.isEmpty() && tienePrecedencia(token, operadores.peek())) {
                    String operador = operadores.pop();
                    String op2 = operandos.pop();
                    String op1 = operandos.pop();
                    String var = generarVariable();
                    operandos.push(var);
                    codigoS += operador + "    " + op1 + "    " + op2 + "    " + var + "\n";

                }
                operadores.push(token);
            }
        }
        while (!operadores.isEmpty()) {
            String operador = operadores.pop();
            String op2 = operandos.pop();
            String op1 = operandos.pop();
            String var = generarVariable();
            operandos.push(var);
            codigoS += operador + "    " + op1 + "    " + op2 + "    " + var + "\n";
        }

        return this.codigo;
    }// // Elimina este bloque de código existente
     // while (!listaRecorrida) {
     // nivel1(defecto);
     // if (prioridad) {
     // generarVariable();
     // // Modificación para escribir correctamente las operaciones aritméticas
     // if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") ||
     // op.equals("%")) {
     // codigo += "OP " + v1 + " " + v2 + " " + nombreVariable + "\n";
     // } else {
     // codigo += op + " " + v1 + " " + v2 + " " + nombreVariable + "\n";
     // }
     // // Fin de la modificación
     // list.set(r1, "r");
     // list.set(r2, "r1");
     // list.set(r3, nombreVariable);
     // list.remove("r");
     // list.remove("r1");

    // prioridad = false;
    // }
    // return this.codigo;
    // }

    // if (list.size() >= 3)
    // listaRecorrida = false;
    // else
    // listaRecorrida = true;

    // // Elimina este bloque de código existente
    // while (!listaRecorrida) {
    // nivel2();
    // generarVariable();
    // codigo += op + " " + v1 + " " + v2 + " " + nombreVariable + "\n";
    // list.set(0, "r");
    // list.set(1, "r1");
    // list.set(2, nombreVariable);
    // list.remove("r");
    // list.remove("r1");
    // }
    // // Fin de la eliminación

    // return this.codigo;
    // }

    public String generarCodigo() {
        Stack<String> operadores = new Stack<>();
        Stack<String> operandos = new Stack<>();

        HashSet<String> variablesUtilizadas = new HashSet<>(); // Almacena variables temporales utilizadas

        for (String token : list) {
            if (token.matches("[A-Z]+|[0-9]+")) {
                operandos.push(token);
                if (token.startsWith("T")) { // Si es una variable temporal, agregarla al conjunto
                    variablesUtilizadas.add(token);
                }
            } else if (token.matches("[+\\-*/%]")) {
                while (!operadores.isEmpty() && tienePrecedencia(token, operadores.peek())) {
                    String operador = operadores.pop();
                    String op2 = operandos.pop();
                    String op1 = operandos.pop();
                    String var = generarVariable();
                    operandos.push(var);
                    codigo += operador + "    " + op1 + "    " + op2 + "    " + var + "\n";

                    if (op1.startsWith("T")) { // Si es una variable temporal, agregarla al conjunto
                        variablesUtilizadas.add(op1);
                    }
                    if (op2.startsWith("T")) { // Si es una variable temporal, agregarla al conjunto
                        variablesUtilizadas.add(op2);
                    }
                }
                operadores.push(token);
            }
        }
        while (!operadores.isEmpty()) {
            String operador = operadores.pop();
            String op2 = operandos.pop();
            String op1 = operandos.pop();
            String var = generarVariable();
            operandos.push(var);
            codigo += operador + "    " + op1 + "    " + op2 + "    " + var + "\n";

            if (op1.startsWith("T")) { // Si es una variable temporal, agregarla al conjunto
                variablesUtilizadas.add(op1);
            }
            if (op2.startsWith("T")) { // Si es una variable temporal, agregarla al conjunto
                variablesUtilizadas.add(op2);
            }
        }

        // Eliminar las variables temporales no utilizadas del conjunto de variables
        // utilizadas
        HashSet<String> variablesTemporales = new HashSet<>();
        for (String token : list) {
            if (token.matches("[A-Z]+")) {
                variablesTemporales.add(token);
            }
        }
        variablesUtilizadas.retainAll(variablesTemporales);

        // Revisar las variables temporales que no se utilizaron y eliminarlas del
        // código
        StringBuilder codigoOptimizado = new StringBuilder();
        for (String line : codigo.split("\n")) {
            String[] tokens = line.split("\\s+");
            if (tokens.length == 4 && tokens[3].startsWith("T") && !variablesUtilizadas.contains(tokens[3])) {
                continue;
            }
            codigoOptimizado.append(line).append("\n");
        }

        return codigoOptimizado.toString();
    }

    private int obtenerPrecedencia(String operador) {
        switch (operador) {
            case "+":
            case "-":
                return 2;
            case "*":
            case "/":
            case "%":
                return 1;
            default:
                return -1;
        }
    }

    private boolean tienePrecedencia(String op1, String op2) {
        int precedenciaOp1 = obtenerPrecedencia(op1);
        int precedenciaOp2 = obtenerPrecedencia(op2);
        if (precedenciaOp1 == precedenciaOp2) {
            return true;
        }
        return precedenciaOp1 > precedenciaOp2;

    }
    // public String generarCodigo() {
    // // Elimina este bloque de código existente
    // while (!listaRecorrida) {
    // nivel1(defecto);
    // if (prioridad) {
    // eliminarCodigoMuerto();
    // generarVariable();
    // // Modificación para escribir correctamente las operaciones aritméticas
    // if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") ||
    // op.equals("%")) {
    // codigo += "OP " + v1 + " " + v2 + " " + nombreVariable + "\n";
    // } else {
    // codigo += op + " " + v1 + " " + v2 + " " + nombreVariable + "\n";
    // }
    // // Fin de la modificación
    // list.set(r1, "r");
    // list.set(r2, "r1");
    // list.set(r3, nombreVariable);
    // list.remove("r");
    // list.remove("r1");

    // prioridad = false;
    // }
    // return this.codigo;
    // }

    // if (list.size() >= 3)
    // listaRecorrida = false;
    // else
    // listaRecorrida = true;

    // // Elimina este bloque de código existente
    // while (!listaRecorrida) {
    // nivel2();
    // generarVariable();
    // codigo += op + " " + v1 + " " + v2 + " " + nombreVariable + "\n";
    // list.set(0, "r");
    // list.set(1, "r1");
    // list.set(2, nombreVariable);
    // list.remove("r");
    // list.remove("r1");
    // }
    // // Fin de la eliminación

    // return this.codigo;
    // }

    // private void nivel1(int i) {// para depurar de operaciones nivel 1(*,/,%)
    // int j = 1;
    // int k = 1;
    // boolean t = false;
    // System.out.println(list.size());
    // for (String e : list) {
    // if (j >= i) {
    // if (k == 1)
    // v1 = e;
    // else if (k == 2) {
    // if (!e.equals("+") && !e.equals("-")) {
    // op = e;
    // r3 = j;
    // r2 = j - 1;
    // r1 = r2 - 1;
    // prioridad = true;
    // } else
    // defecto = j + 1;
    // } else if (k == 3) {
    // if (prioridad)
    // v2 = e;
    // t = true;
    // }
    // k++;
    // }
    // if (t)
    // break;
    // j++;
    // }
    // if (j == list.size())
    // listaRecorrida = true;
    // }

    // private void nivel2() {
    // int k = 1;
    // for (String e : list) {
    // if (k == 1)
    // v1 = e;
    // if (k == 2)
    // op = e;
    // if (k == 3) {
    // v2 = e;
    // break;
    // }
    // k++;
    // }
    // if (k == list.size())
    // listaRecorrida = true;
    // }

    // Método para eliminar variables que no se utilizan en el código
    private void eliminarCodigoMuerto() {
        HashSet<String> variablesUtilizadas = new HashSet<>();
        for (String instruccion : list) {
            String[] partes = instruccion.split("\\s+");
            for (String parte : partes) {
                if (parte.startsWith("T")) { // Identificamos variables temporales
                    variablesUtilizadas.add(parte);
                }
            }
        }

        ArrayList<String> variablesNoUtilizadas = new ArrayList<>();
        for (String variable : list) {
            if (!variablesUtilizadas.contains(variable)) {
                variablesNoUtilizadas.add(variable);
            }
        }

        list.removeAll(variablesNoUtilizadas);
    }

    // Método para evaluar operaciones aritméticas
    private double evaluarOperacion(double valor1, String operador, double valor2) {
        switch (operador) {
            case "+":
                return valor1 + valor2;
            case "-":
                return valor1 - valor2;
            case "*":
                return valor1 * valor2;
            case "/":
                return valor1 / valor2;
            case "%":
                return valor1 % valor2;
            default:
                throw new IllegalArgumentException("Operador desconocido: " + operador);
        }
    }

    private String generarVariable() {
        nombreVariable = "T" + (no_var++);
        return nombreVariable;
    }

    public int longitud() {
        return list.size();
    }

    // Método para verificar si un valor es una constante numérica
    private boolean esConstante(String valor) {
        try {
            Double.parseDouble(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
