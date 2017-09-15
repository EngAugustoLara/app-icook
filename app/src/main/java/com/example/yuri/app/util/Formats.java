package com.example.yuri.app.util;

import android.annotation.SuppressLint;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yuri on 14/09/17.
 */


@SuppressLint("SimpleDateFormat")
public class Formats {
    public static final SimpleDateFormat DATA_HORA_MILISEGUNDO_DB = new SimpleDateFormat(Constantes.FORMATO_DATA_HORA_MILISEGUNDO_DB);
    public static final SimpleDateFormat DATA_HORA_DB = new SimpleDateFormat(Constantes.FORMATO_DATA_HORA_DB);
    public static final SimpleDateFormat DATA_DB = new SimpleDateFormat(Constantes.FORMATO_DATA_DB);
    public static final SimpleDateFormat DATA_MINUTO_DB = new SimpleDateFormat(Constantes.FORMATO_DATA_HORA_MINUTO_DB);
    public static final SimpleDateFormat DATA_HORA_BR = new SimpleDateFormat(Constantes.FORMATO_DATA_HORA_BR);
    public static final SimpleDateFormat DIA_MES_HORA_BR = new SimpleDateFormat(Constantes.FORMATO_DIA_MES_HORA_BR);
    public static final SimpleDateFormat DATA_HORA_MINUTO_BR = new SimpleDateFormat(Constantes.FORMATO_DATA_HORA_MINUTO_BR);
    public static final SimpleDateFormat DATA_BR = new SimpleDateFormat(Constantes.FORMATO_DATA_BR);
    public static final SimpleDateFormat DATA_BR_TRACOS = new SimpleDateFormat(Constantes.FORMATO_DATA_BR_TRACOS);
    public static final SimpleDateFormat DATA_HORA_IMPORTACAO = new SimpleDateFormat(Constantes.FORMATO_DATA_HORA_IMPORTACAO);
    public static final SimpleDateFormat DATA_IMPORTACAO = new SimpleDateFormat(Constantes.FORMATO_DATA_IMPORTACAO);
    public static final SimpleDateFormat DATA_IMPORTACAO_DOMUSERP = new SimpleDateFormat(Constantes.FORMATO_DATA_IMPORTACAO_DOMUSERP);
    public static final SimpleDateFormat DATA_EXPORTACAO = new SimpleDateFormat(Constantes.FORMATO_DATA_EXPORTACAO);
    public static final SimpleDateFormat HORA = new SimpleDateFormat(Constantes.FORMATO_HORA);
    public static final SimpleDateFormat HORA_EXPORTACAO = new SimpleDateFormat(Constantes.FORMATO_HORA_EXPORTACAO);
    public static final SimpleDateFormat DIA_HORA_MINUTO_SEGUNDO_SEM_FORMATACAO = new SimpleDateFormat(Constantes.FORMATO_DIA_HORA_MINUTO_SEGUNDO_SEM_FORMATACAO);
    public static final SimpleDateFormat DIA_MES_ANO_SEM_FORMATACAO = new SimpleDateFormat(Constantes.FORMATO_DIA_MES_ANO_SEM_FORMATACAO);
    public static final SimpleDateFormat DATA_HORA_MINUTO_EXPORTACAO = new SimpleDateFormat(Constantes.FORMATO_DATA_HORA_MINUTO_EXPORTACAO);
    public static final SimpleDateFormat DATA_HORA_MINUTO_SEGUNDO_EXPORTACAO = new SimpleDateFormat(Constantes.FORMATO_DATA_HORA_MINUTO_SEGUNDO_EXPORTACAO);
    public static final SimpleDateFormat DATA_HORA_MINUTO_IMPORTACAO = new SimpleDateFormat(Constantes.FORMATO_DATA_HORA_MINUTO_IMPORTACAO);

    public static final NumberFormat MOEDA = NumberFormat.getCurrencyInstance();

    public static final NumberFormat PERCENTUAL = NumberFormat.getPercentInstance(new Locale("pt", "BR"));

    public static final NumberFormat df = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));


    /**
     * Gera um SimpleDataFormat com um padrao "data no formato de banco de dados acompanhado da hora".
     * @param pHora  Hora que o "pattern" do SimpleDataFormat deve possuir. ex.: "12:30:00"
     * @return       SimpleDataFormat com uma data no padrao de banco de dados e hora fixa.
     */
    public static SimpleDateFormat getSdfComHoraEspecifica(String pHora) {
        return new SimpleDateFormat(String.format("%s %s", Constantes.FORMATO_DATA_DB, pHora));
    }

    /**
     * Converte uma string para uma data, com o formato de banco de dados (yyyy-MM-dd).
     * @param pDataString  Texto com uma data. Obrigatoriamente ele deve estar no formato 'yyyy-MM-dd'. (ex.: "2013-10-26")
     * @return  		   Data que o parametro 'pDataString' possuia. *Caso ocorra algum erro de conversao, a data retornada sera a atual.
     */
    public static Date converterStringParaData(String pDataString) {
        return converterStringParaData(Constantes.FORMATO_DATA_DB, pDataString);
    }

    /**
     * Converte uma string para uma data.
     * @param pDataString  Texto com uma data. Este texto com a data deve respetar o parametro 'pFormato'.
     * @param pFormato     Formato que o parametro 'pDataString' esta. (ex.:"yyyy-MM-dd", "dd/MM/yyyy", etc)
     * @return  		   Data que o parametro 'pDataString' possuia. *Caso ocorra algum erro de conversao, a data retornada sera a atual.
     */
    @SuppressLint("SimpleDateFormat")
    public static Date converterStringParaData(String pFormato, String pDataString) {
        return converterStringParaData(new SimpleDateFormat(pFormato), pDataString);
    }

    /**
     * Converte uma string para uma data.
     * @param pDataString  Texto com uma data. Este texto com a data deve respetar o parametro 'pFormato'.
     * @param pSdf         SimpleDataFormat que sera usado para efetuar a conversao.
     * @return  		   Data que o parametro 'pDataString' possuia. *Caso ocorra algum erro de conversao, a data retornada sera a atual.
     */
    public static Date converterStringParaData(SimpleDateFormat pSdf, String pDataString) {
        try {
            return pSdf.parse(pDataString);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
