package com.info.kisileruygulamasi.DatabaseHelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.info.kisileruygulamasi.Objects.Kisiler;

import java.util.ArrayList;

public class KisilerDao {

    public ArrayList<Kisiler> tumKisiler(Veritabani vt) {
        ArrayList<Kisiler> kisilers = new ArrayList<>();

        SQLiteDatabase database = vt.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM kisiler", null);

        while (c.moveToNext()) {
            kisilers.add(new Kisiler(c.getInt(c.getColumnIndex("kisi_id")),
                    c.getString(c.getColumnIndex("kisi_ad")),
                    c.getString(c.getColumnIndex("kisi_tel"))));
        }

        database.close();
        return kisilers;
    }

    public ArrayList<Kisiler> kisiAra(Veritabani vt, String isim) {
        ArrayList<Kisiler> kisilers = new ArrayList<>();

        SQLiteDatabase database = vt.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM kisiler WHERE kisi_ad like '%" + isim + "%'", null);

        while (c.moveToNext()) {
            kisilers.add(new Kisiler(c.getInt(c.getColumnIndex("kisi_id")),
                    c.getString(c.getColumnIndex("kisi_ad")),
                    c.getString(c.getColumnIndex("kisi_tel"))));
        }
        database.close();
        return kisilers;
    }

    public void kisiSil(Veritabani vt, int id){

        SQLiteDatabase database = vt.getWritableDatabase();
        database.delete("kisiler","kisi_id=?", new String[]{String.valueOf(id)});
        database.close();
    }

    public void kisiEkle(Veritabani vt, String ad, String tel) {

        SQLiteDatabase database = vt.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kisi_ad", ad);
        values.put("kisi_tel", tel);

        database.insertOrThrow("kisiler",null, values);
        database.close();
    }

    public void kisiGuncelle(Veritabani vt, int id, String ad, String tel) {

        SQLiteDatabase database = vt.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kisi_ad", ad);
        values.put("kisi_tel", tel);

        database.update("kisiler", values,"kisi_id=?", new String[]{String.valueOf(id)});
        database.close();
    }
}
