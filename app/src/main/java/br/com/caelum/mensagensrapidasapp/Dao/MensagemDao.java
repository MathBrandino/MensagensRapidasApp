package br.com.caelum.mensagensrapidasapp.Dao;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.mensagensrapidasapp.Modelo.Mensagem;

/**
 * Created by matheus on 13/07/15.
 */
@TargetApi(Build.VERSION_CODES.KITKAT)
public class MensagemDao extends SQLiteOpenHelper implements AutoCloseable {

    private final static String BANCO = "MensagensRapidasApp";
    private final static int VERSAO = 1;
    private final static String TABELAMensagens = "Mensagens";


    public MensagemDao(Context context) {
        super(context, BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql =
                "Create Table "+ TABELAMensagens +
                        " (id INTEGER PRIMARY KEY, " +
                        " nome TEXT NOT NULL," +
                        " corpo TEXT NOT NULL );";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Mensagem> buscaLista(){

        String sql = "Select * from "+ TABELAMensagens + "  ;";

        List<Mensagem> mensagens = new ArrayList<>();

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        while (cursor.moveToNext()){
            Mensagem mensagem = new Mensagem();

            mensagem.setId(cursor.getLong(cursor.getColumnIndex("id")));
            mensagem.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            mensagem.setCorpo(cursor.getString(cursor.getColumnIndex("corpo")));

            mensagens.add(mensagem);
        }
        cursor.close();

        return  mensagens;


    }

    public void salva(Mensagem mensagem){

        ContentValues values = new ContentValues();

        values.put("nome", mensagem.getNome());
        values.put("corpo", mensagem.getCorpo());

        getWritableDatabase().insert(TABELAMensagens, null, values);

    }

    public void deleta(Mensagem mensagem){

        String [] args = {mensagem.getId().toString()};

        getWritableDatabase().delete(TABELAMensagens, "id=?", args);

    }

    public void altera(Mensagem mensagem){

        ContentValues values =  new ContentValues();

        values.put("nome", mensagem.getNome());
        values.put("corpo", mensagem.getCorpo());

        String [] args = {mensagem.getId().toString()};

        Log.i("ALTERA", "ALTERANDO");
        getWritableDatabase().update(TABELAMensagens, values, "id=?", args);
    }
}
