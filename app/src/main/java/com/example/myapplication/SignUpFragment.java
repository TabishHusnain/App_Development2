package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {

    private FragmentSignUpBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null && ((MainActivity) getActivity()).getSupportActionBar() != null) {
            ((MainActivity) getActivity()).getSupportActionBar().hide();
        }

        // Navigate back to Login page
        binding.tvLoginLink.setOnClickListener(v ->
                NavHostFragment.findNavController(SignUpFragment.this)
                        .navigate(R.id.action_SignUpFragment_to_LoginFragment)
        );

        // Handle Sign Up button click
        binding.btnSignUp.setOnClickListener(v -> {
            String fullName = binding.etFullName.getText() != null ? binding.etFullName.getText().toString().trim() : "";
            String email = binding.etSignUpEmail.getText() != null ? binding.etSignUpEmail.getText().toString().trim() : "";
            String password = binding.etSignUpPassword.getText() != null ? binding.etSignUpPassword.getText().toString().trim() : "";
            String confirmPassword = binding.etConfirmPassword.getText() != null ? binding.etConfirmPassword.getText().toString().trim() : "";

            if (TextUtils.isEmpty(fullName)) {
                binding.tilFullName.setError("Please enter your full name");
                return;
            } else {
                binding.tilFullName.setError(null);
            }

            if (TextUtils.isEmpty(email)) {
                binding.tilSignUpEmail.setError("Please enter your email");
                return;
            } else {
                binding.tilSignUpEmail.setError(null);
            }

            if (TextUtils.isEmpty(password) || password.length() < 6) {
                binding.tilSignUpPassword.setError("Password must be at least 6 characters");
                return;
            } else {
                binding.tilSignUpPassword.setError(null);
            }

            if (!password.equals(confirmPassword)) {
                binding.tilConfirmPassword.setError("Passwords do not match");
                return;
            } else {
                binding.tilConfirmPassword.setError(null);
            }

            Toast.makeText(requireContext(), "Account Created Successfully!", Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(SignUpFragment.this)
                    .navigate(R.id.action_SignUpFragment_to_HomeFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
