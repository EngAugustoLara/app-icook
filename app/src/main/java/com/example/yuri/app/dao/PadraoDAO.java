package com.example.yuri.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by yuri on 06/09/17.
 */

public class PadraoDAO {

    protected String mTable;
    protected Context mContext;

    public PadraoDAO(Context mContext, String mTable) {
        this.mContext = mContext;
        this.mTable = mTable;
    }


    /**
     * Efetua uma inserção de registro na tabela, do banco de dados, indicada pela variável global dessa classe nomeada de 'mTable' e fecha o banco passado por parâmetro.
     *
     * @param pContentValues Objeto com os valores que serão inseridos e suas respectivas colunas. Essa objeto deve vir preenchidos com os valores que se deseja inserir.
     * @param pDataBase      Banco de dados utilizado para efetuar a operação.	Não pode ser nulo.
     * @return TRUE - A inserção foi concluída com sucesso. <br /> FALSE - Os dados não foram inclusos, devido algum problema.
     * @author Douglas Scalizze
     */
    protected Boolean insert(ContentValues pContentValues, SQLiteDatabase pDataBase) {
        return insert(pContentValues, pDataBase, true);
    }


    /**
     * Efetua uma inserção de registro na tabela, do banco de dados, indicada pela variável global dessa classe nomeada de 'mTable' e fecha o banco passado por parâmetro.
     *
     * @param pContentValues Objeto com os valores que serão inseridos e suas respectivas colunas. Essa objeto deve vir preenchidos com os valores que se deseja inserir.
     * @param pDatabase      Banco de dados utilizado para efetuar a operação.	Não pode ser nulo.
     * @return TRUE - A inserção foi concluída com sucesso. <br /> FALSE - Os dados não foram inclusos, devido algum problema.
     * @author Eduardo Gardin
     */
    protected Boolean insertOrUpdate(ContentValues pContentValues, String pClausulasWhere, String[] pArgumentosWhere, SQLiteDatabase pDatabase, Boolean pForcaFechamentoBanco) {

        if (updateInt(pContentValues, pClausulasWhere, pArgumentosWhere, pDatabase, false) == 0) {
            return insert(pContentValues, pDatabase, false);
        }
        return true;
    }


    /**
     * Efetua uma inserção de registro na tabela, do banco de dados, indicada pela variável global dessa classe nomeada de 'mTable'.
     *
     * @param pContentValues        Objeto com os valores que serão inseridos e suas respectivas colunas. Essa objeto deve vir preenchidos com os valores que se deseja inserir.
     * @param pDataBase             Banco de dados utilizado para efetuar a operação.	Não pode ser nulo.
     * @param pForcaFechamentoBanco TRUE - Fecha o banco que foi passado por parâmetro<br />FALSE - Não fecha o banco que foi passado por parâmetro.
     * @return TRUE - A inserção foi concluída com sucesso. <br /> FALSE - Os dados não foram inclusos, devido algum problema.
     * @author Douglas Scalizze
     */
    protected Boolean insert(ContentValues pContentValues, SQLiteDatabase pDataBase, boolean pForcaFechamentoBanco) {
        boolean retorno = false;

        if (pDataBase == null) {
            return false;
        }
        try {
            retorno = pDataBase.insert(mTable, null, pContentValues) > 0;
        } catch (Exception e) {
            Log.e("PadraoDao", "Insert " + e.getMessage());
        }
        closeDataBase(pDataBase, pForcaFechamentoBanco);
        return retorno;
    }


    /**
     * Exclui um determinado registro da tabela, do banco de dados, indicada pela variável global dessa classe nomeada de 'mTable' e fecha o banco passado por parâmetro.
     *
     * @param pWhereClausulas  Texto com o(s) nome(s) da(s) coluna(s), da tabela, que será(ão) usada(s) como referencia para esfetuar a operação, seguida(s)
     *                         de um sinal de igual e um ponto de interrogação (ex.: IdCliente = ?). Caso exista mais de uma coluna deve existir um "AND" entre elas (ex.: IdCliente = ? AND IdPedido = ?).
     * @param pWhereArgumentos Valor(es) correspondente(s) da(s) coluna(s) que está(ão) no parâmetro 'pWhereClausulas'.
     *                         Todos os registro da tabela que conter esse(s) valor(es) serão apagados. A sequêcia dos valores do vetor é a mesma das colunas do atributo 'pWhereClausulas'.
     * @param pDataBase        Banco de dados utilizado para efetuar a operação.	Não pode ser nulo.
     * @return TRUE - A exclusão foi concluída com sucesso. <br /> FALSE - Os dados não foram excluidos, devido algum problema.
     * @author Douglas Scalizze
     */
    protected Boolean delete(String pWhereClausulas, String[] pWhereArgumentos, SQLiteDatabase pDataBase) {
        return delete(pWhereClausulas, pWhereArgumentos, pDataBase, true);
    }

    /**
     * Exclui um determinado registro da tabela, do banco de dados, indicada pela variável global dessa classe nomeada de 'mTable'.
     *
     * @param pWhereClausulas       Texto com o(s) nome(s) da(s) coluna(s), da tabela, que será(ão) usada(s) como referencia para esfetuar a operação, seguida(s)
     *                              de um sinal de igual e um ponto de interrogação (ex.: IdCliente = ?). Caso exista mais de uma coluna deve existir um "AND" entre elas (ex.: IdCliente = ? AND IdPedido = ?).
     * @param pWhereArgumentos      Valor(es) correspondente(s) da(s) coluna(s) que está(ão) no parâmetro 'pWhereClausulas'.
     *                              Todos os registro da tabela que conter esse(s) valor(es) serão apagados. A sequêcia dos valores do vetor é a mesma das colunas do atributo 'pWhereClausulas'.
     * @param pDataBase             Banco de dados utilizado para efetuar a operação.	Não pode ser nulo.
     * @param pForcaFechamentoBanco TRUE - Fecha o banco que foi passado por parâmetro<br />FALSE - Não fecha o banco que foi passado por parâmetro.
     * @return TRUE - A exclusão foi concluída com sucesso. <br /> FALSE - Os dados não foram excluidos, devido algum problema.
     */
    protected Boolean delete(String pWhereClausulas, String[] pWhereArgumentos, SQLiteDatabase pDataBase, boolean pForcaFechamentoBanco) {
        boolean retorno = false;

        if (pDataBase == null) {
            return false;
        }
        try {
            retorno = pDataBase.delete(mTable, pWhereClausulas, pWhereArgumentos) > 0;
        } catch (Exception e) {
            Log.e("PadraoDao", "Delete " + e.getMessage());
        }
        closeDataBase(pDataBase, pForcaFechamentoBanco);
        return retorno;
    }

    /**
     * Exclui todos os registros da tabela, do banco de dados, indicada pela variável global dessa classe nomeada de 'mTable' e fecha o banco passado por parâmetro.
     *
     * @param pDataBase Banco de dados utilizado para efetuar a operação.	Não pode ser nulo.
     * @return TRUE - A exclusão foi concluída com sucesso. <br /> FALSE - Os dados não foram excluidos, devido algum problema.
     */
    protected Boolean deleteAll(SQLiteDatabase pDataBase) {
        return deleteAll(pDataBase, true);
    }

    /**
     * Exclui todos os registros da tabela, do banco de dados, indicada pela variável global dessa classe nomeada de 'mTable'.
     *
     * @param pDataBase             Banco de dados utilizado para efetuar a operação.	Não pode ser nulo.
     * @param pForcaFechamentoBanco TRUE - Fecha o banco que foi passado por parâmetro<br />FALSE - Não fecha o banco que foi passado por parâmetro.
     * @return TRUE - A exclusão foi concluída com sucesso. <br /> FALSE - Os dados não foram excluidos, devido algum problema.
     */
    protected Boolean deleteAll(SQLiteDatabase pDataBase, boolean pForcaFechamentoBanco) {
        boolean retorno = false;

        if (pDataBase == null) {
            return false;
        }
        try {
            retorno = pDataBase.delete(mTable, null, null) > 0;
        } catch (Exception e) {
            Log.e("PadraoDao", "Delete " + e.getMessage());
        }
        closeDataBase(pDataBase, pForcaFechamentoBanco);
        return retorno;
    }


    /**
     * Atualiza um determinado registro da tabela, do banco de dados, indicada pela variável global dessa classe nomeada de 'mTable' e fecha o banco passado por parâmetro.
     *
     * @param pContentValues   Objeto com os novos valores que serão inseridos no registro já existente e suas respectivas colunas.
     *                         Essa objeto deve vir preenchidos com os valores que se deseja inserir.
     * @param pClausulasWhere  Texto com o(s) nome(s) da(s) coluna(s), da tabela, que será(ão) usada(s) como referencia para esfetuar a operação, seguida(s)
     *                         de um sinal de igual e um ponto de interrogação (ex.: IdCliente = ?). Caso exista mais de uma coluna deve existir um "AND" entre elas (ex.: IdCliente = ? AND IdPedido = ?).
     * @param pArgumentosWhere Valor(es) correspondente(s) da(s) coluna(s) que está(ão) no parâmetro 'pClausulasWhere'.
     *                         Todos os registro da tabela que conter esse(s) valor(es) serão atualizados. A sequêcia dos valores do vetor é a mesma das colunas do atributo 'pClausulasWhere'.
     * @param pDataBase        Banco de dados utilizado para efetuar a operação.	Não pode ser nulo.
     * @return TRUE - A atualização foi concluída com sucesso. <br /> FALSE - Os dados não foram atualizados, devido algum problema.
     */
    protected Boolean update(ContentValues pContentValues, String pClausulasWhere, String[] pArgumentosWhere, SQLiteDatabase pDataBase) {
        return update(pContentValues, pClausulasWhere, pArgumentosWhere, pDataBase, true);
    }

    protected Integer updateInt(ContentValues pContentValues, String pClausulasWhere, String[] pArgumentosWhere, SQLiteDatabase pDataBase, boolean pForcaFechamentoBanco) {
        Integer retorno = 0;

        if (pDataBase == null) {
            return 0;
        }
        try {
            retorno = pDataBase.update(mTable, pContentValues, pClausulasWhere, pArgumentosWhere);
        } catch (Exception e) {
            Log.e("PadraoDao", "update " + e.getMessage());
        }
        closeDataBase(pDataBase, pForcaFechamentoBanco);
        return retorno;
    }

    /**
     * Atualiza um determinado registro da tabela, do banco de dados, indicada pela variável global dessa classe nomeada de 'mTable'.
     *
     * @param pContentValues        Objeto com os novos valores que serão inseridos no registro já existente e suas respectivas colunas.
     *                              Essa objeto deve vir preenchidos com os valores que se deseja inserir.
     * @param pClausulasWhere       Texto com o(s) nome(s) da(s) coluna(s), da tabela, que será(ão) usada(s) como referencia para esfetuar a operação, seguida(s)
     *                              de um sinal de igual e um ponto de interrogação (ex.: IdCliente = ?). Caso exista mais de uma coluna deve existir um "AND" entre elas (ex.: IdCliente = ? AND IdPedido = ?).
     * @param pArgumentosWhere      Valor(es) correspondente(s) da(s) coluna(s) que está(ão) no parâmetro 'pClausulasWhere'.
     *                              Todos os registro da tabela que conter esse(s) valor(es) serão atualizados. A sequêcia dos valores do vetor é a mesma das colunas do atributo 'pClausulasWhere'.
     * @param pDataBase             Banco de dados utilizado para efetuar a operação.	Não pode ser nulo.
     * @param pForcaFechamentoBanco TRUE - Fecha o banco que foi passado por parâmetro<br />FALSE - Não fecha o banco que foi passado por parâmetro.
     * @return TRUE - A atualização foi concluída com sucesso. <br /> FALSE - Os dados não foram atualizados, devido algum problema.
     */
    protected Boolean update(ContentValues pContentValues, String pClausulasWhere, String[] pArgumentosWhere, SQLiteDatabase pDataBase, boolean pForcaFechamentoBanco) {
        return updateInt(pContentValues, pClausulasWhere, pArgumentosWhere, pDataBase, pForcaFechamentoBanco) > 0;
    }

    /**
     * Verifica a existencia de registro na tabela, do banco de dados, indicada pela variável global dessa classe nomeada de 'mTable' e fecha o banco passado por parâmetro.
     *
     * @param pWhereClauses  Cada item do vetor deve conter o nome de uma coluna, da tabela. Essa(s) coluna(s) será(ão) usada(s) como referencia para esfetuar a operação.
     * @param pSelectionArgs Valor(es) correspondente(s) da(s) coluna(s) que está(ão) no parâmetro 'pWhereClauses'.
     *                       Será verificado se existe algum registro com esse(s) valor(es). A sequêcia dos valores desse vetor é a mesma do atributo 'pWhereClauses'.
     * @param pDataBase      Banco de dados utilizado para efetuar a operação.	Não pode ser nulo.
     * @return TRUE - Existe um registro com os valores indicados. <br /> FALSE - Não existe um registro com os valores indicados.
     * @author Douglas Scalizze
     */
    protected boolean exists(String[] pWhereClauses, String[] pSelectionArgs, SQLiteDatabase pDataBase) {
        return exists(pWhereClauses, pSelectionArgs, pDataBase, true);
    }

    /**
     * Verifica a existencia de registro na tabela, do banco de dados, indicada pela variável global dessa classe nomeada de 'mTable'.
     *
     * @param pWhereClauses         Cada item do vetor deve conter o nome de uma coluna, da tabela. Essa(s) coluna(s) será(ão) usada(s) como referencia para esfetuar a operação.
     * @param pSelectionArgs        Valor(es) correspondente(s) da(s) coluna(s) que está(ão) no parâmetro 'pWhereClauses'.
     *                              Será verificado se existe algum registro com esse(s) valor(es). A sequêcia dos valores desse vetor é a mesma do atributo 'pWhereClauses'.
     * @param pDataBase             Banco de dados utilizado para efetuar a operação.	Não pode ser nulo.
     * @param pForcaFechamentoBanco TRUE - Fecha o banco que foi passado por parâmetro<br />FALSE - Não fecha o banco que foi passado por parâmetro.
     * @return TRUE - Existe um registro com os valores indicados. <br /> FALSE - Não existe um registro com os valores indicados.
     * @author Douglas Scalizze
     */
    protected boolean exists(String[] pWhereClauses, String[] pSelectionArgs, SQLiteDatabase pDataBase, boolean pForcaFechamentoBanco) {
        boolean retorno = false;
        Cursor c = null;

        if (pDataBase == null) {
            return false;
        }
        try {
            String sql = "SELECT COUNT(*) FROM " + mTable;
            if (pWhereClauses != null && pSelectionArgs != null) {
                c = pDataBase.rawQuery(String.format("%s WHERE %s", sql, getStringClauses(pWhereClauses)), pSelectionArgs);
            } else {
                c = pDataBase.rawQuery(sql, null);
            }
            if (c.moveToFirst()) {
                retorno = c.getInt(0) > 0;
            }
        } catch (Exception e) {
            Log.e("PadraoDAO", "exists " + mTable + e.getMessage());
        } finally {
            if (c != null) {
                c.close();
            }
            closeDataBase(pDataBase, pForcaFechamentoBanco);
        }
        return retorno;
    }

    /**
     * Gera um texto com o(s) nome(s) da(s) coluna(s) seguida(s) por um sinal de igual e um ponto de interrogação.
     * Caso exista mais de uma coluna no vetor elas serão separadas por um 'AND' no texto final.
     *
     * @param pWhereClauses Vetor com os nomes das colunas que deseja gerar o texto.
     * @return Texto com a(s) coluna(s) que estava(m) no vetor formatoda(s) adequadamente.
     * @author Douglas Scalizze
     */
    private String getStringClauses(String[] pWhereClauses) {
        StringBuilder stringWhere = new StringBuilder();

        for (String clause : pWhereClauses) {
            stringWhere.append(String.format(" %s = ? AND", clause));
        }
        return stringWhere.toString().substring(0, stringWhere.toString().length() - 3);
    }

	/*(Metódo para ser escrito nas DAOs, é muito útil, no lugar do "null" retorne uma nova instancia  do banco do projeto)/**
	 * Verifica se o banco passado por parâmetro está nulo ou não. Caso esteja é criada uma nova instância.
	 * @param pDataBase	 Banco que será verificado se ele está nulo ou não.
	 * @return			 Banco de dados instânciado como 'WritebleDatabase'.
	 * @author Douglas Scalizze

	protected SQLiteDatabase getDataBase(SQLiteDatabase pDataBase) {
		if (pDataBase != null) {
			return pDataBase;
		} else {
			return null;
		}
	}*/

    /**
     * Verifica se banco que está no primeiro parâmetro está nulo e caso esteja fecha o banco local que está no segundo parâmetro.
     *
     * @param pDataBase        Banco de dados que será verificado se está nulo ou não.
     * @param pForcaFechamento TRUE - Fecha o banco independente dos outros parâmetro<br />FALSE - Fecha o banco dependendo da situação do parâmetro "pDataBase".
     * @author Douglas Scalizze
     */
    private void closeDataBase(SQLiteDatabase pDataBase, boolean pForcaFechamento) {
        if (pForcaFechamento) {
            pDataBase.close();
        }
    }


}
