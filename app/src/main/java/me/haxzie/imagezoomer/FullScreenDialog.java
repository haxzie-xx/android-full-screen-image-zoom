package me.haxzie.imagezoomer;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

@SuppressLint("ValidFragment")
public class FullScreenDialog extends DialogFragment {

    public static final String TAG = "FullScreenDialog";
    private Context mContext;

    private String url;

    @SuppressLint("ValidFragment")
    FullScreenDialog(String url) {
        this.url = url;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
        mContext = getActivity();
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
        super.onCreateView(inflater, parent, state);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_layout, parent, false);
        PhotoView photoView = view.findViewById(R.id.photo_view);
        Glide.with(mContext)
                .load(url)
                .into(photoView);
        return view;
    }
}
