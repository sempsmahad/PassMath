package com.kh69.passmath.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Environment;
import android.util.Log;

import com.kh69.passmath.Question;
import com.kh69.passmath.Tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private Context        context;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "pass_math";

    // Main Table Name
    private static final String TABLE_QUESTION = "questions";

    // Table Columns names TABLE_QUESTION
    private static final String KEY_QUESTION_ID    = "question_id";
    private static final String KEY_TEXT           = "text";
    private static final String KEY_YEAR           = "year";
    private static final String KEY_PAPER          = "paper";
    private static final String KEY_SECTION        = "section";
    private static final String KEY_TOPIC          = "topic";
    private static final String KEY_ANSWER         = "answer";
    private static final String KEY_KATEX_QUESTION = "katex_question";
    private static final String KEY_KATEX_ANSWER   = "katex_answer";
    private static final String KEY_EDITED         = "edited";


    private int        cat_id[]; // category id
    private String     cat_name[]; // category name
    private TypedArray cat_icon; // category name

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.db      = getWritableDatabase();

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase d) {
        createTableQuestion(d);

    }

    private void createTableQuestion(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_QUESTION + " ("
                + KEY_QUESTION_ID + " INTEGER PRIMARY KEY, "
                + KEY_TEXT + " TEXT, "
                + KEY_YEAR + " NUMERIC, "
                + KEY_PAPER + " NUMERIC, "
                + KEY_SECTION + " TEXT, "
                + KEY_TOPIC + " TEXT, "
                + KEY_ANSWER + " TEXT, "
                + KEY_KATEX_QUESTION + " TEXT, "
                + KEY_KATEX_ANSWER + " TEXT, "
                + KEY_EDITED + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DB ", "onUpgrade " + oldVersion + " to " + newVersion);
        if (oldVersion < newVersion) {
            // Drop older table if existed
            truncateDB(db);
        }
    }

    public void truncateDB(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);

        // Create tables again
        onCreate(db);
    }

    // refresh table place and place_category
    public void refreshTableQuestion() {
        db.execSQL("DELETE FROM " + TABLE_QUESTION);
        db.execSQL("VACUUM");
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Insert List place
    public void insertListQuestion(List<Question> modelList) {
        for (Question question : modelList) {
            ContentValues values = getQuestionValue(question);
            // Inserting or Update Row
            db.insertWithOnConflict(TABLE_QUESTION, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    // Insert List place with asynchronous scheme
    public void insertListQuestionAsync(List<Question> modelList) {
        String sql = "INSERT OR REPLACE INTO " + TABLE_QUESTION + " ";
        sql = sql + "(" + KEY_QUESTION_ID + ", " + KEY_TEXT + "," + KEY_YEAR + ", " + KEY_PAPER + ", " + KEY_SECTION + ", "
                + KEY_TOPIC + ", " + KEY_ANSWER + ", " + KEY_KATEX_QUESTION + ", " + KEY_KATEX_ANSWER + ", " + KEY_EDITED + ") ";
        sql = sql + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);
        for (Question q : modelList) {
            stmt.bindString(1, q.getId());
            stmt.bindString(2, q.getText());
            stmt.bindString(3, q.getYear() + "");
            stmt.bindString(4, q.getPaper() + "");
            stmt.bindString(5, q.getSection());
            stmt.bindString(6, q.getTopic());
            stmt.bindString(7, q.getAnswer());
            stmt.bindString(8, q.getKatex_question());
            stmt.bindString(9, q.getKatex_answer());
            stmt.bindString(10, String.valueOf(q.getEdited()));
            stmt.execute();
            stmt.clearBindings();
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }


    // Update one Question
    public Question updateQuestion(Question question) {
        List<Question> objcs = new ArrayList<>();
        objcs.add(Question);
        insertListQuestion(objcs);
        if (isQuestionExist(question.getId())) {
            return getQuestion(question.getId());
        }
        return null;
    }

    private ContentValues getQuestionValue(Question model) {
        ContentValues values = new ContentValues();
        values.put(KEY_QUESTION_ID, model.getId());
        values.put(KEY_TEXT, model.getText());
        values.put(KEY_YEAR, model.getYear());
        values.put(KEY_PAPER, model.getPaper());
        values.put(KEY_SECTION, model.getSection());
        values.put(KEY_TOPIC, model.getTopic());
        values.put(KEY_ANSWER, model.getAnswer());
        values.put(KEY_KATEX_QUESTION, model.getKatex_question());
        values.put(KEY_KATEX_ANSWER, model.getKatex_answer());
        values.put(KEY_EDITED, model.getEdited());
        return values;
    }


    // Adding new location by Category
    public List<Question> searchAllQuestion(String keyword) {
        List<Question> locList = new ArrayList<>();
        Cursor         cur;
        if (keyword.equals("")) {
            cur = db.rawQuery("SELECT p.* FROM " + TABLE_QUESTION + " p ORDER BY " + KEY_YEAR + " DESC", null);
        } else {
            keyword = keyword.toLowerCase();
            cur     = db.rawQuery("SELECT * FROM " + TABLE_QUESTION + " WHERE LOWER(" + KEY_NAME + ") LIKE ? OR LOWER(" + KEY_ADDRESS + ") LIKE ? OR LOWER(" + KEY_DESCRIPTION + ") LIKE ? ",
                    new String[]{"%" + keyword + "%", "%" + keyword + "%", "%" + keyword + "%"});
        }
        locList = getListQuestionByCursor(cur);
        return locList;
    }

    public List<Question> getAllQuestion() {
        return getAllPlaceByCategory(-1);
    }
    public List<Place> getAllPlace() {
        return getAllPlaceByCategory(-1);
    }

    public List<Place> getPlacesByPage(int c_id, int limit, int offset) {
        List<Place>   locList = new ArrayList<>();
        StringBuilder sb      = new StringBuilder();
        sb.append(" SELECT DISTINCT p.* FROM " + TABLE_PLACE + " p ");
        if (c_id == -2) {
            sb.append(", " + TABLE_FAVORITES + " f ");
            sb.append(" WHERE p." + KEY_PLACE_ID + " = f." + KEY_PLACE_ID + " ");
        } else if (c_id != -1) {
            sb.append(", " + TABLE_PLACE_CATEGORY + " pc ");
            sb.append(" WHERE pc." + KEY_RELATION_PLACE_ID + " = p." + KEY_PLACE_ID + " AND pc." + KEY_RELATION_CAT_ID + "=" + c_id + " ");
        }
        sb.append(" ORDER BY p." + KEY_DISTANCE + " ASC, p." + KEY_LAST_UPDATE + " DESC ");
        sb.append(" LIMIT " + limit + " OFFSET " + offset + " ");
        Cursor cursor = db.rawQuery(sb.toString(), null);
        if (cursor.moveToFirst()) {
            locList = getListPlaceByCursor(cursor);
        }
        return locList;
    }

    public List<Place> getAllPlaceByCategory(int c_id) {
        List<Place>   locList = new ArrayList<>();
        StringBuilder sb      = new StringBuilder();
        sb.append(" SELECT DISTINCT p.* FROM " + TABLE_PLACE + " p ");
        if (c_id == -2) {
            sb.append(", " + TABLE_FAVORITES + " f ");
            sb.append(" WHERE p." + KEY_PLACE_ID + " = f." + KEY_PLACE_ID + " ");
        } else if (c_id != -1) {
            sb.append(", " + TABLE_PLACE_CATEGORY + " pc ");
            sb.append(" WHERE pc." + KEY_RELATION_PLACE_ID + " = p." + KEY_PLACE_ID + " AND pc." + KEY_RELATION_CAT_ID + "=" + c_id + " ");
        }
        sb.append(" ORDER BY p." + KEY_LAST_UPDATE + " DESC ");
        Cursor cursor = db.rawQuery(sb.toString(), null);
        if (cursor.moveToFirst()) {
            locList = getListPlaceByCursor(cursor);
        }
        return locList;
    }

    public Question getQuestion(String question_id) {
        Question p      = new Question();
        String   query  = "SELECT * FROM " + TABLE_QUESTION + " p WHERE p." + KEY_QUESTION_ID + " = ?";
        Cursor   cursor = db.rawQuery(query, new String[]{question_id + ""});
        p.getId() = question_id;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            p = getPlaceByCursor(cursor);
        }
        return p;
    }

    public Question getQuestion(String question_id) {
        Question p      = new Question();
        String   query  = "SELECT * FROM " + TABLE_QUESTION + " p WHERE p." + KEY_QUESTION_ID + " = ?";
        Cursor   cursor = db.rawQuery(query, new String[]{question_id + ""});
        p.getId() = question_id;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            p = getPlaceByCursor(cursor);
        }
        return p;
    }

    public Place getPlace(int place_id) {
        Place  p      = new Place();
        String query  = "SELECT * FROM " + TABLE_PLACE + " p WHERE p." + KEY_PLACE_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{place_id + ""});
        p.place_id = place_id;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            p = getPlaceByCursor(cursor);
        }
        return p;
    }

    private List<Question> getListQuestionByCursor(Cursor cur) {
        List<Question> locList = new ArrayList<>();
        // looping through all rows and adding to list
        if (cur.moveToFirst()) {
            do {
                // Adding place to list
                locList.add(getQuestionByCursor(cur));
            } while (cur.moveToNext());
        }
        return locList;
    }

    private Question getQuestionByCursor(Cursor cur) {
        Question p = new Question();
        p.setId(cur.getString(cur.getColumnIndex(KEY_QUESTION_ID)));
        p.setText(cur.getString(cur.getColumnIndex(KEY_TEXT)));
        p.setYear(cur.getString(cur.getColumnIndex(KEY_YEAR)));
        p.setPaper(cur.getString(cur.getColumnIndex(KEY_PAPER)));
        p.setSection(cur.getString(cur.getColumnIndex(KEY_SECTION)));
        p.setTopic(cur.getString(cur.getColumnIndex(KEY_TOPIC)));
        p.setAnswer(cur.getString(cur.getColumnIndex(KEY_ANSWER)));
        p.setKatex_question(cur.getString(cur.getColumnIndex(KEY_KATEX_QUESTION)));
        p.setKatex_answer(cur.getString(cur.getColumnIndex(KEY_KATEX_ANSWER)));
        p.setEdited(cur.getString(cur.getColumnIndex(KEY_EDITED)));
        return p;
    }


    private boolean isQuestionExist(String id) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_QUESTION + " WHERE " + KEY_QUESTION_ID + " = ?", new String[]{id + ""});
        int    count  = cursor.getCount();
        cursor.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getQuestionsSize() {
        int count = (int) DatabaseUtils.queryNumEntries(db, TABLE_QUESTION);
        return count;
    }



    public int getQuestionsSize(int c_id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(DISTINCT p." + KEY_QUESTION_ID + ") FROM " + TABLE_QUESTION + " p ");

        Cursor cursor = db.rawQuery(sb.toString(), null);
        cursor.moveToFirst();
        int size = cursor.getInt(0);
        cursor.close();
        return size;
    }

    // to export database file
    // for debugging only
    private void exportDatabase() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            if (sd.canWrite()) {
                String currentDBPath = "/data/data/" + context.getPackageName() + "/databases/" + DATABASE_NAME;
                String backupDBPath  = "backup_" + DATABASE_NAME + ".db";
                File   currentDB     = new File(currentDBPath);
                File   backupDB      = new File(sd, backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
