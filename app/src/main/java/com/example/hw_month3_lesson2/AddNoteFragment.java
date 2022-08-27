package com.example.hw_month3_lesson2;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.util.Date;
import java.util.Locale;

public class AddNoteFragment extends Fragment {

    private EditText etTitle, etDescription;
    private Button btnSave;
    private int position;
    String date = new SimpleDateFormat("dd.MM.yyyy   HH:mm", Locale.getDefault()).format(new Date());


    public AddNoteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_note, container, false);
        etTitle = view.findViewById(R.id.et_title);
        etDescription = view.findViewById(R.id.et_description);
        btnSave = view.findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String description = etDescription.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("description", description);
                bundle.putString("date", date);
                bundle.putInt("position", position);
                getActivity().getSupportFragmentManager().setFragmentResult("newNote", bundle);
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        checkIsEdit();
        return view;
    }

    private void checkIsEdit() {
        if (getArguments() != null){
            if (!requireArguments().getString("title").isEmpty() && !requireArguments().
                    getString("description").isEmpty()){
                etTitle.setText(requireArguments().getString("title"));
                etDescription.setText(requireArguments().getString("description"));
                position = requireArguments().getInt("position");
                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String title = etTitle.getText().toString();
                        String description = etDescription.getText().toString();

                        Bundle bundle = new Bundle();
                        bundle.putString("title", title);
                        bundle.putString("description", description);
                        bundle.putString("date", date);
                        bundle.putInt("position", position);
                        getActivity().getSupportFragmentManager().
                                setFragmentResult("getNote", bundle);
                        getActivity().getSupportFragmentManager().popBackStack();
                    }
                });
            }
        }
    }
}