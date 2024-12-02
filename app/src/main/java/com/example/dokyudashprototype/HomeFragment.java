package com.example.dokyudashprototype;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Get the TextView by ID

        TextView emailTextView = view.findViewById(R.id.email);
        TextView greetingTextView = view.findViewById(R.id.greet);
        // Get the current user from FirebaseAuth
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Get the email of the current user
        String dynamicEmail = user.getEmail();

        // Dynamically set the email text to the TextView
        emailTextView.setText(dynamicEmail);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String userId = user.getUid();
        DocumentReference docRef = db.collection("users").document(userId);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // Get the first name from Firestore
                        String firstName = document.getString("firstName");

                        // Update the greeting TextView
                        if (firstName != null) {
                            greetingTextView.setText("Hi, " + firstName);
                        } else {
                            greetingTextView.setText("Hi, User");
                        }
                    }
                }
            }
        });
        Button agencyButton = view.findViewById(R.id.agency_button);
        agencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AgencyActivity.class);
                startActivity(intent);
            }
        });

        // Profile part clickable
        ConstraintLayout profileSection = view.findViewById(R.id.ConstraintLayout);
        profileSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace the fragment with ProfileFragment when profile section is clicked
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, new ProfileFragment())
                        .addToBackStack(null)  // Add to back stack so you can navigate back
                        .commit();
            }
        });



        // Main Features
        Button transactionButton = view.findViewById(R.id.transaction_button);
        transactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TransactionActivity.class);
                startActivity(intent);
            }
        });

        Button documentsButton = view.findViewById(R.id.documents_button);
        documentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DocumentsActivity.class);
                startActivity(intent);
            }
        });


        // END: Code to set up button click listeners

        return view;
    }
}
