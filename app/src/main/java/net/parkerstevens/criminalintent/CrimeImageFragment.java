package net.parkerstevens.criminalintent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.util.UUID;


public class CrimeImageFragment extends DialogFragment {

    private static final String CRIME_ID = "crime_id";
    private String mCrimeId;
    private Crime mCrime;
    private File mPhotoFile;
    private ImageView mImageView;
    
    public CrimeImageFragment() {
        // Required empty public constructor
    }

    public static CrimeImageFragment newInstance(UUID crimeId) {
        CrimeImageFragment fragment = new CrimeImageFragment();
        Bundle args = new Bundle();
        args.putString(CRIME_ID, crimeId.toString());
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCrimeId = getArguments().getString(CRIME_ID);
            mCrime = CrimeLab.get(getActivity()).getCrime(UUID.fromString(mCrimeId));
            mPhotoFile = CrimeLab.get(getActivity()).getPhotoFile(mCrime);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_crime_image, container, false);

        mImageView = (ImageView) v.findViewById(R.id.crime_image_dialog);

        if (mPhotoFile == null || !mPhotoFile.exists()){
            mImageView.setImageDrawable(null);
        }else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            mImageView.setImageBitmap(bitmap);

        }

        return v;
    }

}
