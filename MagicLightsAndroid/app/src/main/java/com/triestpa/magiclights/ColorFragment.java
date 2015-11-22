package com.triestpa.magiclights;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ColorFragment extends Fragment {
    private OnColorChangeListener mListener;

    public ColorFragment() {
        // Required empty public constructor
    }

    public static ColorFragment newInstance() {
        ColorFragment fragment = new ColorFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_color, container, false);

        fragView.findViewById(R.id.button_color_red).setOnClickListener(colorClickListener);
        fragView.findViewById(R.id.button_color_green).setOnClickListener(colorClickListener);
        fragView.findViewById(R.id.button_color_blue).setOnClickListener(colorClickListener);

        return fragView;
    }

    public View.OnClickListener colorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ColorDrawable background = (ColorDrawable) v.getBackground();
            onColorPressed(""+background.getColor());
        }
    };

    public void onColorPressed(String color) {
        if (mListener != null) {
            mListener.onColorChange(color);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnColorChangeListener) {
            mListener = (OnColorChangeListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnColorChangeListener {
        void onColorChange(String color);
    }
}
