package com.example.gorup16project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;

import static android.content.ContentValues.TAG;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class PayPal extends AppCompatActivity {

    ActivityResultLauncher activityResultLauncher;

    private static PayPalConfiguration config;
    Button btnPayNow;
    EditText edtAmount;
    TextView paymentTV;

    String amount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paypal);
        config = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(Config.PAYPAL_CLIENT_ID);

        edtAmount = findViewById(R.id.edtAmount);
        btnPayNow = findViewById(R.id.btnPayNow);
        // creating a variable for button, edit text and status tv.
        paymentTV = findViewById(R.id.idTVStatus);
        // initiallizing Activity Launcher
        initializeActivityLauncher();

        btnPayNow.setOnClickListener(v -> processPayment());
    }

    private void initializeActivityLauncher() {
        // Initialize result launcher
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                assert result.getData() != null;
                PaymentConfirmation confirmation = result.getData().getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                    try {
                        // Getting the payment details
                        String paymentDetails = confirmation.toJSONObject().toString(4);
                        // on below line we are extracting json response and displaying it in a text view.
                        JSONObject payObj = new JSONObject(paymentDetails);
                        String payID = payObj.getJSONObject("response").getString("id");
                        String state = payObj.getJSONObject("response").getString("state");
                        paymentTV.setText("Payment " + state + "\n with payment id is " + payID);
                    } catch (JSONException e) {
                        // handling json exception on below line
                        Log.e("Error", "an extremely unlikely failure occurred: ", e);
                    }
                }

            } else if (result.getResultCode() == PaymentActivity.RESULT_EXTRAS_INVALID){
                Log.d(TAG,"Launcher Result Invalid");
            } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                Log.d(TAG, "Launcher Result Cancelled");
            }
        });
    }

    private void processPayment() {
        // Getting amount from editText
        amount = edtAmount.getText().toString();
        // Creating Paypal payment
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(amount),"CAD","Purchase Goods",PayPalPayment.PAYMENT_INTENT_SALE);
        // Creating Paypal Payment activity intent
        Intent intent = new Intent(this, PaymentActivity.class);
        // Adding paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        // Adding paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        // Starting Activity Request launcher
        activityResultLauncher.launch(intent);
    }
}