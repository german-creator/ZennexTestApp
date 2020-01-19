package com.ivanilov.zennex.View.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.ivanilov.zennex.R;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


public class ScalingFragment extends Fragment {

    View view;
    private static final int PICK_IMAGE = 1;
    private static final int PICK_IMAGE_CAMERA = 2;

    Uri imageUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scaling, container, false);


    }

    @Override
    public void onResume() {
        super.onResume();

        view = getView();

        Button buttonGallery = view.findViewById(R.id.Fragment_List_Button_Gallery);

        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
            }
        });
        Button buttonCam = view.findViewById(R.id.Fragment_List_Button_Cam);

        buttonCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, PICK_IMAGE_CAMERA);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                ImageFragment imageFragment = new ImageFragment();

                Bundle bundle = new Bundle();
                bundle.putParcelable("Image", bitmap);
                imageFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.container, imageFragment);
                fragmentTransaction.commit();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == PICK_IMAGE_CAMERA && resultCode == RESULT_OK) {
            imageUri = data.getData();
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();

            ImageFragment imageFragment = new ImageFragment();

            Bundle bundle = new Bundle();
            bundle.putParcelable("Image", bitmap);
            imageFragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.container, imageFragment);
            fragmentTransaction.commit();

        }

    }


    public interface OnFragmentInteractionListener {
    }


}