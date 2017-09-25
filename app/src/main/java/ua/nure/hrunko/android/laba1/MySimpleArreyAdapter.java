package ua.nure.hrunko.android.laba1;

/**
 * Created by Alina on 25.09.2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MySimpleArreyAdapter extends ArrayAdapter<Note> {
    private final Context context;
    private final Note[] values;

    public MySimpleArreyAdapter(Context context, Note[] values) {
        super(context, R.layout.rowlayout, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        TextView timeView = (TextView) rowView.findViewById(R.id.timestamp);
        ImageView iconView = (ImageView) rowView.findViewById(R.id.icon);

        Note current = values[position];
        textView.setText(current.name);

        if (current.importance == Importance.CRITICAL) {
            iconView.setImageResource(R.drawable.critical);
        }
        else if (current.importance == Importance.AVARAGE) {
            iconView.setImageResource(R.drawable.high);
        }
        else {
            iconView.setImageResource(R.drawable.low);
        }
        timeView.setText(current.time.toString());

        return rowView;
    }
}
