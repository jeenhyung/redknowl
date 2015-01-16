package kr.co.ibreeze.redknowl.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.co.ibreeze.redknowl.R;
import kr.co.ibreeze.redknowl.models.commentItem;

/**
 * Created by cozmo-air1 on 2015-01-06.
 */
public class CommentAdapter extends ArrayAdapter<commentItem> {
    private Context context;
    private int resId;
    private ArrayList<commentItem> data = null;

    public CommentAdapter(Context context, int resouce, ArrayList<commentItem> objs){
        super(context, resouce, objs);
        this.context = context;
        this.resId= resouce;
        this.data = objs;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        commentItem item = getItem(position);
        ViewHolder holder = null;

        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_comment, null);

            holder.proimg = (de.hdodenhof.circleimageview.CircleImageView)convertView.findViewById(R.id.profileimg);
            holder.name = (TextView)convertView.findViewById(R.id.name);
            holder.comment = (TextView)convertView.findViewById(R.id.comment);

            convertView.setTag(holder);
        } else {

            holder = (ViewHolder)convertView.getTag();

        }
        holder.name.setText(getItem(position).name);
        holder.comment.setText(getItem(position).comment);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,getItem(position).name, Toast.LENGTH_SHORT).show();
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,getItem(position).name + "LongClick", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return convertView;

    }






    private static class ViewHolder{
        de.hdodenhof.circleimageview.CircleImageView proimg;
        TextView name;
        TextView comment;
    }
}
