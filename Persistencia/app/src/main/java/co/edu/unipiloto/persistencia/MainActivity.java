package co.edu.unipiloto.persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private SQLiteDatabase db2;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteOpenHelper RegistroDataBaseHelper = new RegistroDataBaseHelper(MainActivity.this);
        db = RegistroDataBaseHelper.getWritableDatabase();
        db2 = RegistroDataBaseHelper.getReadableDatabase();
    }

    public static void insertCarga(SQLiteDatabase db, String DIR, String TIPO, String ALTO, String ANCHO, String PESO, String HORA, String FECHA) {

        ContentValues drinkValues = new ContentValues();
        drinkValues.put("DIRECCION_O", DIR);
        drinkValues.put("TIPO_CARGA", TIPO);
        drinkValues.put("ALTO", ALTO);
        drinkValues.put("ANCHO", ANCHO);
        drinkValues.put("PESO", PESO);
        drinkValues.put("HORA", HORA);
        drinkValues.put("FECHA", FECHA);
        db.insert("REGISTROS", null, drinkValues);

    }

    public void BotonCarga(View view) {
        EditText dir = (EditText) findViewById(R.id.direccion);
        EditText tipo = (EditText) findViewById(R.id.tipo);
        EditText alto = (EditText) findViewById(R.id.alto);
        EditText ancho = (EditText) findViewById(R.id.ancho);
        EditText peso = (EditText) findViewById(R.id.peso);
        EditText hora = (EditText) findViewById(R.id.hora);
        EditText fecha = (EditText) findViewById(R.id.fecha);

        String direc = dir.getText().toString();
        String tipos = tipo.getText().toString();
        String alt = alto.getText().toString();
        String anch = ancho.getText().toString();
        String pesoo = peso.getText().toString();
        String hour = hora.getText().toString();
        String date = fecha.getText().toString();

        insertCarga(db, direc, tipos, alt, anch, pesoo, hour, date);

    }

    public void solicitar(View view) {
        ListView datos = (ListView) findViewById(R.id.daticos);
        try {
            cursor = db2.query("REGISTROS",
                    new String[]{"_id, DIRECCION_O"}, null, null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"DIRECCION_O"},
                    new int[]{android.R.id.text1},
                    0);
            datos.setAdapter(listAdapter);

        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "epa, como que no funciono mani...", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }


}