package ua.nure.hrunko.android.laba1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Notes extends ListActivity {
    public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            Note[] values = new Note[] {new Note("name", "description", Importance.CRITICAL), new Note("name2", "description2", Importance.MINOR)};

            MySimpleArreyAdapter adapter = new MySimpleArreyAdapter(this, values);
            setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }
}
