package com.smartschool.tenversion;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DBTestActivity extends ListActivity implements View.OnClickListener {
	private static final String TAG = "DBTestActivity";
	String mode = "SAFE";
	
	EditText carNameEdit;
    EditText selectIdEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_db_layout);
        
        carNameEdit = (EditText) findViewById(R.id.list_data);
        selectIdEdit = (EditText) findViewById(R.id.select_id);
                
        ((Button) findViewById(R.id.insert)).setOnClickListener(this);
        ((Button) findViewById(R.id.selectone)).setOnClickListener(this);
        ((Button) findViewById(R.id.selectall)).setOnClickListener(this);
    }

    public void onClick(View v) {
        DBHandler dbhandler = DBHandler.open(this);
        
        if (v.getId() == R.id.insert) {
        	
            String listData = carNameEdit.getText().toString();
            
            long cnt = dbhandler.insert(mode,listData);
            
            if (cnt == -1) {
                Toast.makeText(this, listData + "가 테이블에 추가되지 않았습니다.", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, listData + "가 테이블에 추가되었습니다.", Toast.LENGTH_LONG).show();                
            }            
        } else if (v.getId() == R.id.selectone) {            
            String selectIdStr = selectIdEdit.getText().toString();
            int selectId = Integer.parseInt(selectIdStr);
            
            Cursor cursor = dbhandler.select(selectId);
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "데이터가 없습니다.", Toast.LENGTH_LONG).show();
            } else {            
                String name = cursor.getString(cursor.getColumnIndex( DBHandler.KEY_LIST_DATA));
                Toast.makeText(this, "Data" + name, Toast.LENGTH_LONG).show();
            }
            cursor.close(); 
        } else if (v.getId() == R.id.selectall) {
            Cursor cursor = dbhandler.selectAll();
            startManagingCursor(cursor);
            
            SimpleCursorAdapter cursorAdapter = 
                new SimpleCursorAdapter(this, R.layout.test_db_list_row, cursor, 
                        new String[] {DBHandler.KEY_MODE, DBHandler.KEY_LIST_DATA}, new int[] {R.id.text1,R.id.text2}  );
            setListAdapter(cursorAdapter);
        }
        
        dbhandler.close();
    }
}