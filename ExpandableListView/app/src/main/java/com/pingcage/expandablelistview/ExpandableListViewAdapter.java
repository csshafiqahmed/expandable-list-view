package com.pingcage.expandablelistview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    Context context;
    List<String> titles;
    HashMap<String, List<String>> listHashMap;

    public ExpandableListViewAdapter(Context context, List<String> titles, HashMap<String, List<String>> listHashMap) {
        this.context = context;
        this.titles = titles;
        this.listHashMap = listHashMap;
    }

//this returns how many parent items we have
    @Override
    public int getGroupCount() {
        return titles.size();
    }
//this returns the size of the children list of parent item with index groupPosition
    @Override
    public int getChildrenCount(int groupPosition) {
        return listHashMap.get(titles.get(groupPosition)).size();
    }
//this returns the parent object with index groupPosition
    @Override
    public Object getGroup(int groupPosition) {
        return titles.get(groupPosition);
    }
//this returns a specific sub item
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //this is the parent item with index groupPosition
        String parentListItem = titles.get(groupPosition);

        //this is the list with all child items of parentListItem
        List<String> childrenList = listHashMap.get(parentListItem);

        //this is the specific sub item
        return childrenList.get(childPosition);
    }
//this returns the index of our parent item
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //this returns the index of our child item
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_list_item, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.tvParentListItem);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expandable_list_item, null);
        }
        TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.tvExpandableListItem);
        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
