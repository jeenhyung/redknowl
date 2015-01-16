package kr.co.ibreeze.redknowl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.co.ibreeze.redknowl.R;
import kr.co.ibreeze.redknowl.models.categoryItem;

/**
 * Created by cozmo-air1 on 2015-01-05.
 */
public class CategoryAdapter extends ArrayAdapter<categoryItem> {
    private static class ViewHolder
    {
        TextView textCategory;

    }

    private Context context;
    private int resId;
    private ArrayList<categoryItem> data = null;

    public CategoryAdapter(Context context, int resource, ArrayList<categoryItem> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
        this.context= context;
        this.resId = resource;
        this.data = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        categoryItem item = getItem(position);

        ViewHolder holder = null;

        if(convertView == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(resId, parent, false);

            holder.textCategory = (TextView)convertView.findViewById(R.id.nameofitem);

            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }


        holder.textCategory.setText(item.category);

        return convertView;
    }
}
