package com.example.assignment_1_emi_calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.assignment_1_emi_calculator.databinding.FragmentFirstBinding;

import java.text.DecimalFormat;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private static final DecimalFormat round = new DecimalFormat("0.00");

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.calculateEMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve the loan amount from ET1 and convert it to an integer
                int amount = Integer.valueOf(binding.ET1.getText().toString());

                // Retrieve the annual interest rate from ET2, divide by 12 to get monthly interest, and convert it to a float
                float interest = Float.valueOf(binding.ET2.getText().toString()) / 12;

                // Retrieve the number of months for the loan from ET3 and convert it to an integer
                int months = Integer.valueOf(binding.ET3.getText().toString());

                // Calculate the monthly payment using the formula for EMI (Equated Monthly Installment)
                double pow = Math.pow(1 + (interest / 100), months);
                double rateCalc = pow / (pow - 1);
                double payment = amount * (interest / 100) * rateCalc;

                // Display the calculated monthly payment in TV5, formatted as a currency string rounded to 2 decimals.
                binding.TV5.setText("$" + round.format(payment));
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}