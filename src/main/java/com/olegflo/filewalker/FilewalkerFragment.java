package com.olegflo.filewalker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Oleg Soroka
 * @date 29.10.13
 * @time 23:01
 * <p/>
 */
public class FilewalkerFragment extends Fragment {

    private ListView lvFilewalker;
    private TextView tvCurrentPath;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String root = "/";

        tvCurrentPath = (TextView) getActivity().findViewById(R.id.tv_current_path);
        tvCurrentPath.setText(root);

        lvFilewalker = (ListView) getActivity().findViewById(R.id.lv_filewalker);
        lvFilewalker.setAdapter(new FilewalkerAdapter(getActivity(), root));
        lvFilewalker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FileItem f = (FileItem) view.getTag();
                if (f.getFile().isDirectory()) {
                    tvCurrentPath.setText(f.getFile().getAbsolutePath());
                    lvFilewalker.setAdapter(new FilewalkerAdapter(getActivity(), f.getFile().getAbsolutePath()));
                }
            }
        });
    }

    //

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.filewalker_layout, container, false);
        return view;
    }
}