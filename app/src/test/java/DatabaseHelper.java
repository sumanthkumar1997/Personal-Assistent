import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP on 18-07-2018.

 */

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String TAG="DatabaseHelper";
    private static final String TABLE_NAME="reminder";
    private static final String Col1="id";
    private static final String Col2="subject";
    private static final String Col3="date";
    private static final String Col4="descr";

    public DatabaseHelper(Context context){
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable="CREATE TABLE "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+Col2+"TEXT,"+Col3+"DATETIME,"
                +Col4+"TEXT)";
        db.execSQL(createTable);


    }
}
