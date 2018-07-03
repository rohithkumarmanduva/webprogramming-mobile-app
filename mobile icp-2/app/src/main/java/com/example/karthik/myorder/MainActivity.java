package com.example.karthik.myorder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_TAG = "MainActivity";
    final int PIZZA_PRICE = 5;
    final int ONIONS = 1;
    final int CHICKEN = 3;
    final int GREENPEPPERS = 2;
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

       /* Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:47.6,-122.3"));
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }*/

//        get user input
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
//
        CheckBox onions = (CheckBox) findViewById(R.id.onions_id);
        boolean hasOnions = onions.isChecked();

        CheckBox chicken = (CheckBox) findViewById(R.id.chicken_id);
        boolean hasChicken = chicken.isChecked();

        CheckBox green_peppers = (CheckBox) findViewById(R.id.green_peppers_id);
        boolean hasGreenPeppers = green_peppers.isChecked();

        float totalPrice = calculatePrice(hasOnions, hasChicken, hasGreenPeppers);
//        create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasOnions, hasChicken, hasGreenPeppers, totalPrice);
// Write the relevant code for making the buttons work(i.e impelement the implicit and explicit intents
        Intent i = new Intent(MainActivity.this, SecondActivity.class);

        i.putExtra("STRING_I_NEED", orderSummaryMessage);
        startActivity(i);


    }
    private String boolToString(boolean bool){
        return bool?(getString(R.string.yes)):(getString(R.string.no));

    }

    private String createOrderSummary(String userInputName, boolean hasWhippedCream, boolean hasChicken, boolean hasGreenPeppers, float price) {
        String orderSummaryMessage = getString(R.string.order_summary_name,userInputName) +"\n"+
                getString(R.string.order_summary_onions,boolToString(hasWhippedCream))+"\n"+
                getString(R.string.order_summary_chicken,boolToString(hasChicken)) +"\n"+
                getString(R.string.order_summary_greenpeppers,boolToString(hasChicken)) +"\n"+
                getString(R.string.order_summary_quantity,quantity)+"\n"+
                getString(R.string.order_summary_total_price,price) +"\n"+
                getString(R.string.thank_you);
        return orderSummaryMessage;

    }

    public void emailOrder(View v) {
        Log.i("Send email", "");
        String[] TO = {""};
        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        EditText userInputNameView = (EditText) findViewById(R.id.user_input);
        String userInputName = userInputNameView.getText().toString();
//
        CheckBox onions = (CheckBox) findViewById(R.id.onions_id);
        boolean hasOnions = onions.isChecked();

        CheckBox chicken = (CheckBox) findViewById(R.id.chicken_id);
        boolean hasChicken = chicken.isChecked();

        CheckBox green_peppers = (CheckBox) findViewById(R.id.green_peppers_id);
        boolean hasGreenPeppers = green_peppers.isChecked();

        float totalPrice = calculatePrice(hasOnions, hasChicken, hasGreenPeppers);
//        create and store the order summary
        String orderSummaryMessage = createOrderSummary(userInputName, hasOnions, hasChicken, hasGreenPeppers, totalPrice);
        emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummaryMessage);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Method to calculate the total price
     *
     * @return total Price
     */
    private float calculatePrice(boolean hasOnions, boolean hasChicken , boolean hasGreenpeppers) {
        int basePrice = PIZZA_PRICE;
        if (hasOnions) {
            basePrice += ONIONS;
        }
        if (hasChicken) {
            basePrice += CHICKEN;
        }
        if (hasGreenpeppers) {
            basePrice += GREENPEPPERS;
        }

        return quantity * basePrice;

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);

    }


    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {

            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;

        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {

            Log.i("MainActivity", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_coffee);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;


        }
    }
}
