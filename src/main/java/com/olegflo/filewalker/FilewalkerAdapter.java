package com.olegflo.filewalker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.File;

/**
 * @author Oleg Soroka
 * @date 29.10.13
 * @time 23:09
 * <p/>
 */
public class FilewalkerAdapter extends BaseAdapter {

    private Context context;

    private FileItem[] items;

    FilewalkerAdapter(Context context, String rootPath) {
        this.context = context;
        System.out.println("Open folder: " + rootPath);

        File root = new File(rootPath);

        File[] fileList = root.listFiles();

        if (fileList == null) {
            fileList = new File[0];
        }

        int itemsCount = fileList.length;

        boolean hasUpItem = !rootPath.equals("/");

        if (hasUpItem) {
            itemsCount++; // Add extra line for '..' item
        }

        items = new FileItem[itemsCount];

        int offset = hasUpItem ? 1 : 0;

        if (hasUpItem) {
            File up = root.getParentFile();
            items[0] = new FileItem(up, "..");
        }

        for (int i = 0; i < fileList.length; i++) {
            items[i + offset] = new FileItem(fileList[i]);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
        }

        FileItem f = getItem(position);

        TextView title = (TextView) v.findViewById(android.R.id.text1);
        String fileTitle = f.getTitle();

        if (f.getFile().isDirectory() && !f.getTitle().equals("..")) {
            fileTitle = "/" + fileTitle;
        }

        title.setText(fileTitle);

        v.setTag(f);
        return v;
    }

    //

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public FileItem getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}