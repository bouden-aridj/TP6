package com.example.tp6;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    private EditText lastNameTxt, firstNameTxt, emailTxt, phoneTxt, ageTxt;
    private RadioGroup sexGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        lastNameTxt = findViewById(R.id.lastNameTxt);
        firstNameTxt = findViewById(R.id.firstNameTxt);
        emailTxt = findViewById(R.id.emailTxt);
        phoneTxt = findViewById(R.id.phoneTxt);
        ageTxt = findViewById(R.id.ageTxt);
        sexGroup = findViewById(R.id.sexGroup);
        Button saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(v -> save());
    }

    private void save() {
        String lastName = lastNameTxt.getText().toString().trim();
        String firstName = firstNameTxt.getText().toString().trim();
        String email = emailTxt.getText().toString().trim();
        String phone = phoneTxt.getText().toString().trim();
        String ageStr = ageTxt.getText().toString().trim();

        if (lastName.isEmpty()) {
            lastNameTxt.setError(getString(R.string.error_required));
            lastNameTxt.requestFocus();
            return;
        }
        if (firstName.isEmpty()) {
            firstNameTxt.setError(getString(R.string.error_required));
            firstNameTxt.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            emailTxt.setError(getString(R.string.error_required));
            emailTxt.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailTxt.setError(getString(R.string.error_email));
            emailTxt.requestFocus();
            return;
        }
        if (phone.isEmpty()) {
            phoneTxt.setError(getString(R.string.error_required));
            phoneTxt.requestFocus();
            return;
        }
        if (!phone.matches("\\d+")) {
            phoneTxt.setError(getString(R.string.error_phone));
            phoneTxt.requestFocus();
            return;
        }
        if (ageStr.isEmpty()) {
            ageTxt.setError(getString(R.string.error_required));
            ageTxt.requestFocus();
            return;
        }

        int age;
        try {
            age = Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            ageTxt.setError(getString(R.string.error_age));
            ageTxt.requestFocus();
            return;
        }
        if (age < 0 || age > 120) {
            ageTxt.setError(getString(R.string.error_age));
            ageTxt.requestFocus();
            return;
        }

        int checkedId = sexGroup.getCheckedRadioButtonId();
        if (checkedId == -1) {
            Toast.makeText(this, R.string.error_sex, Toast.LENGTH_SHORT).show();
            return;
        }
        String sex = (checkedId == R.id.maleRadio) ? "Homme" : "Femme";

        AppData.appointments.add(
                new Patient(lastName, firstName, email, phone, age, sex));
        Toast.makeText(this, R.string.success_added, Toast.LENGTH_SHORT).show();
        finish();
    }
}