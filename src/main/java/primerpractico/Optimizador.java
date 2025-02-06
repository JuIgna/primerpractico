package primerpractico;

import java.util.*;

public class Optimizador {
    private final String codigoOriginal;
    private final StringBuilder codigoOptimizado;
    private final Map<String, Integer> constantes;
    private final Set<String> variablesUtilizadas;

    public Optimizador(String codigoOriginal) {
        this.codigoOriginal = codigoOriginal;
        this.codigoOptimizado = new StringBuilder();
        this.constantes = new HashMap<>();
        this.variablesUtilizadas = new HashSet<>();
    }

    public String optimizar() {
        analizarVariablesUtilizadas();
        eliminarOperacionesRedundantes();
        return codigoOptimizado.toString();
    }

    private void analizarVariablesUtilizadas() {
        String[] lineas = codigoOriginal.split("\n");
        for (String linea : lineas) {
            String[] partes = linea.split("=");
            if (partes.length > 1) {
                String derecha = partes[1].trim();
                for (String token : derecha.split("[ +\\-*/<>&|(),]+")) {
                    token = token.trim();
                    if (!token.isEmpty() && !Character.isDigit(token.charAt(0))) {
                        variablesUtilizadas.add(token);
                    }
                }
            }
        }
    }

    private void eliminarOperacionesRedundantes() {
        String[] lineas = codigoOriginal.split("\n");
        for (String linea : lineas) {
            linea = linea.trim();
            if (esAsignacion(linea)) {
                String[] partes = linea.split("=");
                String izquierda = partes[0].trim();
                String derecha = partes[1].trim().replace(";", "");

                // Si la expresión ya es constante, se puede optimizar directamente
                if (variablesUtilizadas.contains(izquierda)) {
                    Integer valorConstante = evaluarExpresion(derecha);
                    if (valorConstante != null) {
                        constantes.put(izquierda, valorConstante);
                        codigoOptimizado.append(izquierda).append(" = ").append(valorConstante).append(";\n");
                    } else {
                        String expresionOptimizada = reemplazarConstantes(derecha);
                        codigoOptimizado.append(izquierda).append(" = ").append(expresionOptimizada).append(";\n");
                    }
                } else {
                    codigoOptimizado.append(linea).append("\n");
                }
            } else {
                codigoOptimizado.append(linea).append("\n");
            }
        }
    }

    private Integer evaluarExpresion(String expresion) {
        try {
            return Integer.parseInt(expresion);
        } catch (NumberFormatException e) {
            String[] tokens = expresion.split(" ");
            if (tokens.length == 3) {
                Integer a = obtenerValor(tokens[0].trim());
                Integer b = obtenerValor(tokens[2].trim());
                String operador = tokens[1];
                if (a != null && b != null) {
                    return switch (operador) {
                        case "+" -> a + b;
                        case "-" -> a - b;
                        case "*" -> a * b;
                        case "/" -> (b != 0) ? a / b : null;
                        default -> null;
                    };
                }
            }
        }
        return null;
    }

    private Integer obtenerValor(String token) {
        if (constantes.containsKey(token)) {
            return constantes.get(token);
        }
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException ignored) {}
        return null;
    }

    private String reemplazarConstantes(String expresion) {
        for (Map.Entry<String, Integer> entry : constantes.entrySet()) {
            expresion = expresion.replace(entry.getKey(), entry.getValue().toString());
        }
        return expresion;
    }

    private boolean esAsignacion(String linea) {
        return linea.contains("=") && !linea.matches(".*(if|goto|call|return).*");
    }
}
