package com.example.yuri.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yuri.app.database.AtributosDatabase;
import com.example.yuri.app.database.Database;
import com.example.yuri.app.model.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuri on 29/08/17.
 */

public class UsuarioDao{

    private Context mContext;

    public UsuarioDao(Context context) {
        this.mContext = context;
    }

    public void adicionarUsuario(Usuario usuario){
        SQLiteDatabase db = new Database(mContext).getWritableDatabase();
        ContentValues usuarioValues = new ContentValues();

        usuarioValues.put(AtributosDatabase.Usuario.COLUNA_NOME, usuario.getNome());
        usuarioValues.put(AtributosDatabase.Usuario.COLUNA_EMAIL, usuario.getEmail());
        usuarioValues.put(AtributosDatabase.Usuario.COLUNA_TELEFONE_DDI, usuario.getDdi());
        usuarioValues.put(AtributosDatabase.Usuario.COLUNA_TELEFONE_DDD, usuario.getDdd());
        usuarioValues.put(AtributosDatabase.Usuario.COLUNA_TELEFONE_NUMERO_CELULAR, usuario.getNumero_celular());

        db.insert(AtributosDatabase.Usuario.NOME_TABELA, null, usuarioValues);
        db.close();
    }

    public void removerUsuario(Usuario usuario){
        SQLiteDatabase db = new Database(mContext).getWritableDatabase();

        db.delete(AtributosDatabase.Usuario.NOME_TABELA,
                AtributosDatabase.Usuario.COLUNA_ID
                + " = ?", new String[] {
                        String.valueOf(usuario.getId())
        });
        db.close();
    }

    public Usuario selecionarUsuario(int codigo){
        SQLiteDatabase db = new Database(mContext).getReadableDatabase();
        Log.i("UsuarioDao"," 56");

        Cursor cursor = db.query(AtributosDatabase.Usuario.NOME_TABELA,
                new String[]{
                        AtributosDatabase.Usuario.COLUNA_ID,
                        AtributosDatabase.Usuario.COLUNA_NOME,
                        AtributosDatabase.Usuario.COLUNA_EMAIL,
                        AtributosDatabase.Usuario.COLUNA_TELEFONE_DDI,
                        AtributosDatabase.Usuario.COLUNA_TELEFONE_DDD,
                        AtributosDatabase.Usuario.COLUNA_TELEFONE_NUMERO_CELULAR
                },
                AtributosDatabase.Usuario.COLUNA_ID + " = ?",
                new String[] {
                        String.valueOf(codigo)
                },
                null, null, null, null
        );
        Log.i("UsuarioDao"," 73");

        if (cursor == null){
            cursor.moveToFirst();
            Log.i("UsuarioDao"," 77");
        }
        Usuario usuario = new Usuario(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getInt(4),
                cursor.getInt(5)
        );
        return usuario;
    }

    void atualizaUsuario(Usuario usuario){
        SQLiteDatabase db = new Database(mContext).getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(AtributosDatabase.Usuario.COLUNA_NOME, usuario.getNome());
        values.put(AtributosDatabase.Usuario.COLUNA_EMAIL, usuario.getEmail());
        values.put(AtributosDatabase.Usuario.COLUNA_TELEFONE_DDI, usuario.getDdi());
        values.put(AtributosDatabase.Usuario.COLUNA_TELEFONE_DDD, usuario.getDdd());
        values.put(AtributosDatabase.Usuario.COLUNA_TELEFONE_NUMERO_CELULAR, usuario.getNumero_celular());

        db.update(AtributosDatabase.Usuario.NOME_TABELA, values, AtributosDatabase.Usuario.COLUNA_ID + " = ?",
                new String[] {
                        String.valueOf(usuario.getId())
                });
    }

    public List<Usuario> listaTodosUsuarios(){
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        String query = "SELECT * FROM " + AtributosDatabase.Usuario.NOME_TABELA;

        SQLiteDatabase db = new Database(mContext).getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(c.getString(0)));
                usuario.setNome(c.getString(1));
                usuario.setEmail(c.getString(2));
                usuario.setDdi(c.getInt(3));
                usuario.setDdd(c.getInt(4));
                usuario.setNumero_celular(c.getInt(5));

                listaUsuarios.add(usuario);
            }while (c.moveToNext());
        }
        return listaUsuarios;
    }

}
