package com.example.maverikapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.mukesh.OtpView;

import java.util.concurrent.TimeUnit;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpOtpFragment extends Fragment {

    private View view;
    private Button b1,b2;
    private EditText phoneEdit;
    private String phoneNumber,verificationId;
    private OtpView otpView;
    private CardView cardViewS,cardViewF;

    private FirebaseAuth mFirebaseAuth;

    public SignUpOtpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_up_otp, container, false);

        cardViewS = (CardView)view.findViewById(R.id.cardViews);
        cardViewF = (CardView)view.findViewById(R.id.cardViewf);

        phoneNumber = phoneEdit.getText().toString();

        otpView = (OtpView)view.findViewById(R.id.otp_view);


        mFirebaseAuth = FirebaseAuth.getInstance();

        b1 = (Button) view.findViewById(R.id.buttons);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewS.setVisibility(View.GONE);
                cardViewF.setVisibility(View.VISIBLE);
                sendVerificationCode(phoneNumber);

            }
        });
        b2 = (Button) view.findViewById(R.id.buttonf);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCode(otpView.getText().toString().trim());
                Navigation.findNavController(v).navigate(R.id.signUpFormFragment);
            }
        });
        return  view;
    }

    private void verifyCode(String code){

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId,code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Navigation.findNavController(view).navigate(R.id.signUpFormFragment);
                        }else {
                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendVerificationCode(String number){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,
                1,
                TimeUnit.MINUTES,
                TaskExecutors.MAIN_THREAD,
                mCallBack
        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();
            if(code != null){
                verifyCode(code);
            }
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationId = s;
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }
    };
}
