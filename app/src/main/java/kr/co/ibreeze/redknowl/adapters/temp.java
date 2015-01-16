package kr.co.ibreeze.redknowl.adapters;

/**
 * Created by cozmo-air1 on 2015-01-05.
 */
public class temp {
}
/**

public class mainCategoryAdapter extends ArrayAdapter<categoryItem> {

    private static class ViewHolder
    {
        TextView textCategory;
        TextView textCount;
    }

    private Context context;
    private int resId;
    private ArrayList<categoryItem> data = null;

    public mainCategoryAdapter(Context context, int resource, ArrayList<categoryItem> objects) {
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
            convertView = inflater.inflate(R.layout.category_list_item, parent, false);

            holder.textCategory = (TextView)convertView.findViewById(R.id.categoryText);
            holder.textCount = (TextView)convertView.findViewById(R.id.countText);

            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }


        holder.textCategory.setText(item.category);
        holder.textCount.setText(item.count);

        return convertView;
    }



}


public class categoryItem {
    public String category;
    public String count;

    public categoryItem(){}

    public categoryItem(String category, String count)
    {
    this.category = category;
    this.count = count;
    }

    }
 */