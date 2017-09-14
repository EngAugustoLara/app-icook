package com.example.yuri.app.util;

import android.content.Context;

/**
 * Created by yuri on 29/08/17.
 */

public class ManipulacaoString {

    public static String getStringResource(Context pContext, int pId) {
        return pContext.getResources().getString(pId);
    }

    /**
     * Converte o texto especificado no par�metro 'pTexto' para mai�sculo.
     *
     * @param pTexto Texto que ser� convertido.
     * @return Texto com caracteres mai�sculos.
     */
    public static String converterParaMaiusculo(String pTexto) {
        return pTexto != null ? pTexto.toUpperCase().trim() : "";
    }

    /**
     * Converte o texto especificado no par�metro 'pTexto' para minusculo.
     *
     * @param pTexto Texto que ser� convertido.
     * @return Texto com caracteres mai�sculos.
     */
    public static String converterParaMinuscula(String pTexto) {
        return pTexto != null ? pTexto.toLowerCase().trim() : "";
    }


    /**
     * Tira todos os espaços de uma determinada string.
     *
     * @param pTexto Texto que terá os espaços retirados.
     * @return String com o mesmo valor do parâmetro 'pTexto', porém sem espaços.
     */
    public static String tirarTodosEspacos(String pTexto) {
        return pTexto.replace(" ", "");
    }

    public static String tirarZerosEsquerda(String stringComZeros) {
        int inicio = 0;

        if (stringComZeros != null && !stringComZeros.equals("")) {
            char[] array = stringComZeros.toCharArray();
            while (array[inicio] == '0' && inicio < (stringComZeros.length() - 1))
                inicio++;
        }

        return stringComZeros.substring(inicio);
    }
    public static String tirarAcentos(String textoComAcentos) {
        if (textoComAcentos != null) {
            StringBuilder textor = new StringBuilder(textoComAcentos.length());

            char[] texto = textoComAcentos.toCharArray();

            for (int i = 0; i < textoComAcentos.length(); i++) {

                if (texto[i] == 'ã')
                    textor.append('a');
                else if (texto[i] == 'á')
                    textor.append('a');
                else if (texto[i] == 'à')
                    textor.append('a');
                else if (texto[i] == 'â')
                    textor.append('a');
                else if (texto[i] == 'ä')
                    textor.append('a');
                else if (texto[i] == 'é')
                    textor.append('e');
                else if (texto[i] == 'è')
                    textor.append('e');
                else if (texto[i] == 'ê')
                    textor.append('e');
                else if (texto[i] == 'ë')
                    textor.append('e');
                else if (texto[i] == 'í')
                    textor.append('i');
                else if (texto[i] == 'ì')
                    textor.append('i');
                else if (texto[i] == 'ï')
                    textor.append('i');
                else if (texto[i] == 'ô')
                    textor.append('i');
                else if (texto[i] == 'õ')
                    textor.append('o');
                else if (texto[i] == 'ó')
                    textor.append('o');
                else if (texto[i] == 'ò')
                    textor.append('o');
                else if (texto[i] == 'ö')
                    textor.append('o');
                else if (texto[i] == 'ú')
                    textor.append('u');
                else if (texto[i] == 'ù')
                    textor.append('u');
                else if (texto[i] == 'ü')
                    textor.append('u');
                else if (texto[i] == 'ç')
                    textor.append('c');
                else if (texto[i] == 'Ã')
                    textor.append('A');
                else if (texto[i] == 'Á')
                    textor.append('A');
                else if (texto[i] == 'À')
                    textor.append('A');
                else if (texto[i] == 'Â')
                    textor.append('A');
                else if (texto[i] == 'Ä')
                    textor.append('A');
                else if (texto[i] == 'É')
                    textor.append('E');
                else if (texto[i] == 'È')
                    textor.append('E');
                else if (texto[i] == 'Ê')
                    textor.append('E');
                else if (texto[i] == 'Ë')
                    textor.append('E');
                else if (texto[i] == 'Í')
                    textor.append('I');
                else if (texto[i] == 'Ì')
                    textor.append('I');
                else if (texto[i] == 'Ï')
                    textor.append('I');
                else if (texto[i] == 'Ô')
                    textor.append('O');
                else if (texto[i] == 'Õ')
                    textor.append('O');
                else if (texto[i] == 'Ó')
                    textor.append('O');
                else if (texto[i] == 'Ò')
                    textor.append('O');
                else if (texto[i] == 'Ö')
                    textor.append('O');
                else if (texto[i] == 'Ú')
                    textor.append('U');
                else if (texto[i] == 'Ù')
                    textor.append('U');
                else if (texto[i] == 'Ü')
                    textor.append('U');
                else if (texto[i] == 'Ç')
                    textor.append('C');
                else if (texto[i] == '\'')
                    textor.append("");
                else
                    textor.append(texto[i]);
            }
            return textor.toString();
        } else {
            return "";
        }
    }

}
