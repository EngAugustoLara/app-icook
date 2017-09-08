package com.example.yuri.app.database;

/**
 * Created by yuri on 29/08/17.
 */

public class AtributosDatabase {

    public static class BancoDados{
        public static final String NOME_BANCO = "BANCOGERAL.DB";
        public static final Integer VERSAO_BANCO = 1;
    }

    public static class Usuario{
        public static final String NOME_TABELA = "TABELA_USUARIO";
        public static final String COLUNA_ID = "ID";
        public static final String COLUNA_NOME = "NOME";
        public static final String COLUNA_EMAIL = "EMAIL";
        public static final String COLUNA_TELEFONE_DDI = "DDI";
        public static final String COLUNA_TELEFONE_DDD = "DDD";
        public static final String COLUNA_TELEFONE_NUMERO_CELULAR = "NUMERO_CELULAR";
    }

    public static class Receita{
        public static final String NOME_TABELA = "TABELA_RECEITAS";
        public static final String COLUNA_ID = "ID";
        public static final String COLUNA_NOME = "NOME";
    }

    public static class Ingrediente{
        public static final String NOME_TABELA = "TABELA_INGREDIENTES";
        public static final String COLUNA_ID = "ID";
        public static final String COLUNA_ID_RECEITA = "ID_RECEITA";
        public static final String COLUNA_NOME = "NOME";
        public static final String COLUNA_QUANTIDADE = "QUANTIDADE";
        public static final String COLUNA_UNIDADE_MEDIDA = "UNIDADE_MEDIDA";
    }
}
