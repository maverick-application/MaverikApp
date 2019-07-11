package com.example.maverikapp.ui.authentication.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maverikapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpOtpFragment extends Fragment {

    private View view;
    private EditText editText;
    private OtpView otpView;
    private CardView cv1,cv2;
    private String mVerificationId;
    private FirebaseAuth mFirebaseAuth;
    EditText phoneEditText;


    public SignUpOtpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up_otp, container, false);

        mFirebaseAuth = FirebaseAuth.getInstance();

        phoneEditText = (EditText) view.findViewById(R.id.phoneEditText);

        cv1 = (CardView)view.findViewById(R.id.cardViews);
        cv2 = (CardView)view.findViewById(R.id.cardViewf);

        cv1.findViewById(R.id.buttons).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = phoneEditText.getText().toString();

                if(phoneNumber.isEmpty() || phoneNumber.length() < 10 || phoneNumber.length() > 10){
                    Toast.makeText(getContext(), "please enter the 10 digit phone number", Toast.LENGTH_SHORT).show();
                    phoneEditText.requestFocus();
                    return;
                }else {
                    sendVerificationCode(phoneNumber);
                    cv1.setVisibility(View.GONE);
                    cv2.setVisibility(View.VISIBLE);
                }

                otpView = (OtpView)view.findViewById(R.id.otp_view);
                otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
                    @Override
                    public void onOtpCompleted(String otp) {
                        verifyCode(otp);
                    }
                });


            }
        });

        return  view;
    }

    // This method is used for sending OTP to the mobile
    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+phoneNumber,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks // This callback is called when to check weather the received OTP is valid or not
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the six digit code from the sms
            String code = phoneAuthCredential.getSmsCode();
            /*
            Sometimes the sms may not be detected automatically in this case it will be null so user has to enter the code manually
             */
            if(code != null){
                otpView.setText(code);
                verifyCode(code);
            }
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //Storing the verification id that is sent to user
            mVerificationId = s;

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getContext(),"Verification Failed"+e,Toast.LENGTH_LONG).show();
        }
    };

    private void verifyCode(String code) {
        //Creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,code);

        //Signing the user
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Navigation.findNavController(view).navigate(R.id.signUpFormFragment);
                        }else{
                          //  Toast.makeText(getContext(),"Problem in Sign In",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
