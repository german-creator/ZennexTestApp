package com.ivanilov.zennex.View.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.ivanilov.zennex.R;
import com.ortiz.touchview.TouchImageView;


public class ImageFragment extends Fragment {

    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image, container, false);


    }

    @Override
    public void onResume() {
        super.onResume();

        view = getView();

        TouchImageView touchImageView = view.findViewById(R.id.fragment_image__image);

        Bitmap bitmap = getArguments().getParcelable("Image");

        touchImageView.setImageBitmap(bitmap);


    }


    public interface OnFragmentInteractionListener {
    }


}