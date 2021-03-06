package kr.co.ibreeze.redknowl.utils;

/**
 * Created by cozmo-air1 on 2015-01-05.
 */
import android.util.SparseArray;
import android.view.View;

public class ViewHolderHelper {

    public static <T extends View> T get(View convertView, int id) {

        SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();

        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            convertView.setTag(viewHolder);
        }

        View childView = viewHolder.get(id);

        if (childView == null) {
            childView = convertView.findViewById(id);
            viewHolder.put(id, childView);
        }

        return (T) childView;
    }
}