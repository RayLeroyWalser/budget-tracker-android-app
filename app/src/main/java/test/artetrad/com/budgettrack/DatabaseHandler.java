package test.artetrad.com.budgettrack;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "budget tracker";
    // Budgets table name
    private static final String TABLE_BUDGETS = "budgets";
    private static final String TABLE_OTHER_EXPENSES = "other expenses";
    private static final String TABLE_BUDGETS_AND_OTHER_EXPENSES = "budgets and other expenses";

    // Common column names
    private static final String KEY_ID = "id";

    // Budgets Table Columns names
    private static final String KEY_DATE_BEGIN = "date_begin";
    private static final String KEY_DATE_END = "date_end";
    private static final String KEY_INCOME = "income";
    private static final String KEY_SUPER_MARKET = "super_market";
    private static final String KEY_CIGARETTES = "cigarettes";
    private static final String KEY_FUN= "fun";
    private static final String KEY_BUFFER = "buffer";
    private static final String KEY_NEW_SAVINGS = "new_savings";
    private static final String KEY_PAST_SAVINGS = "past_savings";

    // Other Expenses Table Columns names
    private static final String KEY_NAME = "other_expense's_name";
    private static final String KEY_VALUE = "other_expense's_value";

    // Other Expenses Table Columns names
    private static final String KEY_BUDGET_ID = "budget_id";
    private static final String KEY_OTHER_EXPENSE_ID = "other_expense's_id";

    private static final String CREATE_TABLE_BUDGETS = "CREATE TABLE " + TABLE_BUDGETS
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_DATE_BEGIN + " TEXT,"
            + KEY_DATE_END + " TEXT,"
            + KEY_INCOME + " REAL,"
            + KEY_SUPER_MARKET + " REAL,"
            + KEY_CIGARETTES + " REAL,"
            + KEY_FUN + " REAL,"
            + KEY_BUFFER + " REAL,"
            + KEY_NEW_SAVINGS + " REAL,"
            + KEY_PAST_SAVINGS + " REAL" + ")";

    private static final String CREATE_TABLE_OTHER_EXPENSES = "CREATE TABLE " + TABLE_OTHER_EXPENSES
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAME + " TEXT,"
            + KEY_VALUE + " REAL" + ")";

    private static final String CREATE_TABLE_BUDGETS_AND_OTHER_EXPENSES = "CREATE TABLE " + TABLE_BUDGETS_AND_OTHER_EXPENSES
            + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_BUDGET_ID + " INTEGER,"
            + KEY_OTHER_EXPENSE_ID + " INTEGER" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_BUDGETS);
        db.execSQL(CREATE_TABLE_OTHER_EXPENSES);
        db.execSQL(CREATE_TABLE_BUDGETS_AND_OTHER_EXPENSES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGETS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OTHER_EXPENSES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BUDGETS_AND_OTHER_EXPENSES);

        // Create tables again
        onCreate(db);
    }

    // Creating a  new budget entry
    public void createBudget(Budget budget, long[] other_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE_BEGIN, budget.getDateBegin());
        values.put(KEY_DATE_END, budget.getDateEnd());
        values.put(KEY_INCOME, budget.getIncome());
        values.put(KEY_SUPER_MARKET, budget.getSuperMarket());
        values.put(KEY_CIGARETTES, budget.getCigarettes());
        values.put(KEY_FUN, budget.getFun());
        values.put(KEY_BUFFER, budget.getBuffer());
        values.put(KEY_NEW_SAVINGS, budget.getNewSavings());
        values.put(KEY_PAST_SAVINGS, budget.getPastSavings());

        // Inserting Row
        long budget_id = db.insert(TABLE_BUDGETS, null, values);

        // Assigning other expenses to this budget
        for (long other_id : other_ids){
            createBudgetAndOtherExpenses(budget_id, other_id);
        }

        db.close(); // Closing database connection
    }

    // Getting a single Budget entry by ID
    public Budget getBudgetById(long budget_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_BUDGETS + " WHERE "
                + KEY_ID + " = " + budget_id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        Budget budget = new Budget();

        if (cursor != null){
            try {
                cursor.moveToFirst();
                budget.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                budget.setDateBegin(cursor.getString(cursor.getColumnIndex(KEY_DATE_BEGIN)));
                budget.setDateEnd(cursor.getString(cursor.getColumnIndex(KEY_DATE_END)));
                budget.setIncome(cursor.getFloat(cursor.getColumnIndex(KEY_INCOME)));
                budget.setSuperMarket(cursor.getFloat(cursor.getColumnIndex(KEY_SUPER_MARKET)));
                budget.setCigarettes(cursor.getFloat(cursor.getColumnIndex(KEY_CIGARETTES)));
                budget.setFun(cursor.getFloat(cursor.getColumnIndex(KEY_FUN)));
                budget.setBuffer(cursor.getFloat(cursor.getColumnIndex(KEY_BUFFER)));
                budget.setNewSavings(cursor.getFloat(cursor.getColumnIndex(KEY_NEW_SAVINGS)));
                budget.setPastSavings(cursor.getFloat(cursor.getColumnIndex(KEY_PAST_SAVINGS)));
            }finally {
                cursor.close();
            }
        }

        return budget;
    }

    // TODO: Getting a single Budget entry
    public Budget getBudgetByDate(long budget_id){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_BUDGETS + " WHERE "
                + KEY_ID + " = " + budget_id;

        Cursor cursor = db.rawQuery(selectQuery, null);

        Budget budget = new Budget();

        if (cursor != null){
            try {
                cursor.moveToFirst();
                budget.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                budget.setDateBegin(cursor.getString(cursor.getColumnIndex(KEY_DATE_BEGIN)));
                budget.setDateEnd(cursor.getString(cursor.getColumnIndex(KEY_DATE_END)));
                budget.setIncome(cursor.getFloat(cursor.getColumnIndex(KEY_INCOME)));
                budget.setSuperMarket(cursor.getFloat(cursor.getColumnIndex(KEY_SUPER_MARKET)));
                budget.setCigarettes(cursor.getFloat(cursor.getColumnIndex(KEY_CIGARETTES)));
                budget.setFun(cursor.getFloat(cursor.getColumnIndex(KEY_FUN)));
                budget.setBuffer(cursor.getFloat(cursor.getColumnIndex(KEY_BUFFER)));
                budget.setNewSavings(cursor.getFloat(cursor.getColumnIndex(KEY_NEW_SAVINGS)));
                budget.setPastSavings(cursor.getFloat(cursor.getColumnIndex(KEY_PAST_SAVINGS)));
            }finally {
                cursor.close();
            }
        }

        return budget;
    }

    // Getting all Budget entries
    public List<Budget> getAllBudgets(){
        List<Budget> budgets = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_BUDGETS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        try {
            if (cursor.moveToFirst()) {
                do {
                    Budget budget = new Budget();
                    budget.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                    budget.setDateBegin(cursor.getString(cursor.getColumnIndex(KEY_DATE_BEGIN)));
                    budget.setDateEnd(cursor.getString(cursor.getColumnIndex(KEY_DATE_END)));
                    budget.setIncome(cursor.getFloat(cursor.getColumnIndex(KEY_INCOME)));
                    budget.setSuperMarket(cursor.getFloat(cursor.getColumnIndex(KEY_SUPER_MARKET)));
                    budget.setCigarettes(cursor.getFloat(cursor.getColumnIndex(KEY_CIGARETTES)));
                    budget.setFun(cursor.getFloat(cursor.getColumnIndex(KEY_FUN)));
                    budget.setBuffer(cursor.getFloat(cursor.getColumnIndex(KEY_BUFFER)));
                    budget.setNewSavings(cursor.getFloat(cursor.getColumnIndex(KEY_NEW_SAVINGS)));
                    budget.setPastSavings(cursor.getFloat(cursor.getColumnIndex(KEY_PAST_SAVINGS)));

                    // adding to budgets list
                    budgets.add(budget);
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
        }

        return budgets;
    }

    // Get Budget entries count
    public int getToDoCount() {
        String countQuery = "SELECT  * FROM " + TABLE_BUDGETS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    // Updating a Budget entry
    public int updateBudget(Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE_BEGIN, budget.getDateBegin());
        values.put(KEY_DATE_END, budget.getDateEnd());
        values.put(KEY_INCOME, budget.getIncome());
        values.put(KEY_SUPER_MARKET, budget.getSuperMarket());
        values.put(KEY_CIGARETTES, budget.getCigarettes());
        values.put(KEY_FUN, budget.getFun());
        values.put(KEY_BUFFER, budget.getBuffer());
        values.put(KEY_NEW_SAVINGS, budget.getNewSavings());
        values.put(KEY_PAST_SAVINGS, budget.getPastSavings());

        // updating row
        return db.update(TABLE_BUDGETS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(budget.getId()) });
    }

    // Deleting a Budget entry
    public void deleteBudget(long budget_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BUDGETS, KEY_ID + " = ?",
                new String[] { String.valueOf(budget_id) });
    }

    // Creating an Other Expenses entry
    public long createTag(OtherExpenses other) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, other.getName());
        values.put(KEY_VALUE, other.getValue());

        // insert row

        return db.insert(TABLE_OTHER_EXPENSES, null, values);
    }

    // Updating an Other Expenses entry
    public int updateTag(OtherExpenses other) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, other.getName());
        values.put(KEY_VALUE, other.getValue());

        // updating row
        return db.update(TABLE_OTHER_EXPENSES, values, KEY_ID + " = ?",
                new String[] { String.valueOf(other.getId()) });
    }

    // Deleting an Other Expenses entry
    public void deleteTag(OtherExpenses other) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_OTHER_EXPENSES, KEY_ID + " = ?",
                new String[] { String.valueOf(other.getId()) });
    }

    // Assigning an Other Expenses entry to a Budget entry
    // Or Creating an Budgets And Other Expenses entry
    public long createBudgetAndOtherExpenses(long budget_id, long other_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BUDGET_ID, budget_id);
        values.put(KEY_OTHER_EXPENSE_ID, other_id);

        return db.insert(TABLE_BUDGETS_AND_OTHER_EXPENSES, null, values);
    }

    // Updating an Other Expenses-Budget entry
    public int updateBudgetAndOtherExpenses(long id, long other_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_OTHER_EXPENSE_ID, other_id);

        // updating row
        return db.update(TABLE_BUDGETS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    // Deleting an Other Expenses-Budget entry
    public void deleteBudgetAndOtherExpenses(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BUDGETS, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
    }

    // TODO: closing database connection
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}

