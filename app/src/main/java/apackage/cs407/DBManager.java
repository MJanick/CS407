package apackage.cs407;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "leaderboard.db";
    public static final String TABLE_SCORES = "scores";
    public static final String COLUMN_ID = "Attempt";
    public static final String COLUMN_NAME = "User";
    public static final String COLUMN_SCORE = "Score";

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_SCORES + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_SCORE + " INTEGER " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABLE_SCORES);
        onCreate(db);
    }

    public void addScore(Scores newScore) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newScore.get_name());
        values.put(COLUMN_SCORE, newScore.get_score());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SCORES, null, values);
        db.close();
    }

    public ArrayList<Scores> databaseScores() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_SCORES + " WHERE 1";

        ArrayList<Scores> allScores = new ArrayList<>();

        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        while(!c.isAfterLast()) {
            String currName = "";
            int currID = 0;
            int currScore = 0;
            if(c.getString(c.getColumnIndex(COLUMN_NAME)) != null) {
                currName = c.getString(c.getColumnIndex(COLUMN_NAME));
            }
            currID = c.getInt(c.getColumnIndex(COLUMN_ID));
            currScore = c.getInt(c.getColumnIndex(COLUMN_SCORE));
            Scores curr = new Scores(currID, currScore, currName);
            allScores.add(curr);
            c.moveToNext();
        }
        db.close();
        return allScores;
    }
}
