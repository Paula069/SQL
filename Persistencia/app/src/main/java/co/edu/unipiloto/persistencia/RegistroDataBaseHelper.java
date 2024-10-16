package co.edu.unipiloto.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RegistroDataBaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="REGISTROS";
    public static final int DB_VERSION=1;

    public RegistroDataBaseHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDataBase(db, 0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDataBase(db, oldVersion, newVersion);

    }

    private void updateMyDataBase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion<1){
            db.execSQL("CREATE TABLE REGISTROS("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "DIRECCION_O TEXT," +
                    "TIPO_CARGA TEXT," +
                    "ALTO TEXT," +
                    "ANCHO TEXT," +
                    "PESO TEXT," +
                    "HORA TEXT," +
                    "FECHA TEXT);");
        }
        if(oldVersion<2){
            //db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }

    }

}
