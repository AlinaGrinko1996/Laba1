package ua.nure.hrunko.android.laba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Notes extends ListActivity {
    List<Note> values;
    TextView text;
    View header;

    DBHelper dbHelper;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        dbHelper = new DBHelper(this);
        values = new ArrayList<>();
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null &&  !extras.isEmpty()) {
            Log.d("name----------->",extras.getString("name"));
            Note currentNote = new Note(extras.getString("name"), extras.getString("descr"), extras.getString("importance"), extras.getString("timestamp"));
            if (extras.containsKey("image")) {
                currentNote.image = intent.getParcelableExtra("image");
            }
          //  if (Storage.field != null ) {
            //    values = Storage.field;
                values = Storage.GetAll(dbHelper, getContentResolver());
          //  }
        //    values.add(currentNote);
        }
        else
        {
//            values = new ArrayList<Note>();
//            Note note = new Note("name1", "description1", Importance.CRITICAL.toString());
//            values.add(note);
//            Storage.Insert(note, dbHelper);

//            values.add(new Note("name2", "description2", Importance.MINOR.toString()));
//            values.add(new Note("name3", "description3", Importance.AVARAGE.toString()));
//            values.add(new Note("name4", "description4", Importance.MINOR.toString()));
         //   Storage.field=values;
            values = Storage.GetAll(dbHelper, getContentResolver());
        }
//        else {
//            values = Storage.GetAll(dbHelper);
//        }

        header = getLayoutInflater().inflate(R.layout.header, null);
        ListView listView = getListView();
        listView.addHeaderView(header);

        MySimpleArreyAdapter adapter = new MySimpleArreyAdapter(this, values);
        setListAdapter(adapter);
        ListView list = getListView();
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                Intent calcActivity = new Intent(getApplicationContext(), Edit.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", values.get(position-1).name);
                bundle.putString("descr", values.get(position-1).description);
                bundle.putParcelable("image", values.get(position-1).image);
                bundle.putString("timestamp", values.get(position-1).time);
                bundle.putString("importance", values.get(position-1).importance.toString());
                calcActivity.putExtras(bundle);
                Note note = values.get(position-1);
                values.remove(position-1);
                Storage.Delete(note, dbHelper);
              //  Storage.field = values;
                startActivity(calcActivity);
                return true;
            }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
    }

    public void onAddNote(View view) {
        Intent rgbActivity = new Intent(getApplicationContext(), Edit.class);
        startActivity(rgbActivity);
    }

    public void onSearch(View view) {
        text = (TextView)header.findViewById(R.id.text_input);
        CharSequence cs=text.getText();

        List<Note> bufvalues = new ArrayList<Note>();
        for (Note e: values) {
            if (e.description.contains(cs)) {
                bufvalues.add(e);
            }
        }
        MySimpleArreyAdapter adapter = new MySimpleArreyAdapter(this, bufvalues);
        setListAdapter(adapter);
    }

    public void onFilterCritical(View view) {
        List<Note> bufvalues = new ArrayList<Note>();
        for (Note e: values) {
            if (e.importance == Importance.CRITICAL) {
                bufvalues.add(e);
            }
        }
        MySimpleArreyAdapter adapter = new MySimpleArreyAdapter(this, bufvalues);
        setListAdapter(adapter);
    }

    public void onFilterAverage(View view) {
        List<Note> bufvalues = new ArrayList<Note>();
        for (Note e: values) {
            if (e.importance == Importance.AVARAGE) {
                bufvalues.add(e);
            }
        }
        MySimpleArreyAdapter adapter = new MySimpleArreyAdapter(this, bufvalues);
        setListAdapter(adapter);
    }

    public void onFilterLow(View view) {
        List<Note> bufvalues = new ArrayList<Note>();
        for (Note e: values) {
            if (e.importance == Importance.MINOR) {
                bufvalues.add(e);
            }
        }
        MySimpleArreyAdapter adapter = new MySimpleArreyAdapter(this, bufvalues);
        setListAdapter(adapter);
    }
}
