package ua.nure.hrunko.android.laba1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Notes extends ListActivity {
    public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            Note[] values = new Note[] {
                    new Note("name", "description", Importance.CRITICAL),
                    new Note("name2", "description2", Importance.MINOR),
                    new Note("name", "description", Importance.AVARAGE),
                    new Note("name2", "description2", Importance.MINOR)};


        View header = getLayoutInflater().inflate(R.layout.header, null);
        ListView listView = getListView();
        listView.addHeaderView(header);
//        setListAdapter(new ArrayAdapter<Note>(this,
//                android.R.layout.row,
//                android.R.id.text1, values));

        MySimpleArreyAdapter adapter = new MySimpleArreyAdapter(this, values);
        setListAdapter(adapter);
        ListView list = getListView();
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Toast.makeText(Notes.this,
                        "Item in position " + position + " clicked",
                        Toast.LENGTH_LONG).show();
                // Возвращает "истину", чтобы завершить событие клика, чтобы
                // onListItemClick больше не вызывался
                return true;
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }
}
