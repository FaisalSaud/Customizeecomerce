package com.example.abu.customizeecomerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendShoppingBill extends AppCompatActivity {

    EditText customerFullNameET;
    EditText customerEmailET;
    EditText customerPhoneNumberET;

    Button okSendEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_shopping_bill);

        customerFullNameET = (EditText) findViewById(R.id.editTextCustomerName);
        customerEmailET = (EditText) findViewById(R.id.editTextCustomerEMail);
        customerPhoneNumberET = (EditText) findViewById(R.id.editTextCustomerPhoneNumber);

        okSendEmailButton = (Button) findViewById(R.id.buttonOkSendEMail);

        okSendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();

                Intent i = new Intent(SendShoppingBill.this, UserMainPage.class);
                startActivity(i);
            }
        });

    }

    //send email to customer and company
    public void sendEmail (){

        String customerName = customerFullNameET.getText().toString().trim();
        String customerEmail = customerEmailET.getText().toString().trim();
        String customerPhoneNumber = customerPhoneNumberET.getText().toString().trim();


        String customerMsg = "hello: " + customerName + ".\n\nHow are you?  I hope you are fine.\n\nYour item is:\n";

        //if customer buy from purchase Basket or from bars
        if (UserMainPage.fromPurchaseB){
            int j = 0;//to find the
            double totalPrice = 0;//to cal...
            try {
                for (int i = 0; i < UserMainPage.purchaseBasketCounter; i = i + 3) {
                    customerMsg += i + 1 + ") Item Name: " + UserMainPage.purchaseBasketItems[i] + "\tPrice : " + UserMainPage.purchaseBasketItems[i + 2];
                    Toast.makeText(this,"11",Toast.LENGTH_SHORT).show();
                    customerMsg += " SR \tNumber of items: " + (UserMainPage.itemPriceWithQuantity[j] / Double.parseDouble(UserMainPage.purchaseBasketItems[i + 2]));
                    Toast.makeText(this,"22",Toast.LENGTH_SHORT).show();
                    customerMsg += " Item\tTotal Price: " + UserMainPage.itemPriceWithQuantity[j] + " SR.\n";
                    Toast.makeText(this,"33",Toast.LENGTH_SHORT).show();
                    j++;
                }
                for (int i = 0; i<UserMainPage.purchaseBasketCounter-1/3;i++){
                    totalPrice = totalPrice + UserMainPage.itemPriceWithQuantity[i];
                }
            }catch (Exception e){
                Toast.makeText(SendShoppingBill.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
            customerMsg = customerMsg + "The total cost: " + totalPrice + "SR.\n\n";

            customerMsg = customerMsg + "Thank You.";       // WE CAN ADD THE COMPANY INFO EMAIL...
        }else {
            try {
                customerMsg += "1) Item Name: " + UserMainPage.itemNameFromBars + "\tPrice: " + UserMainPage.itemPriceFromBars
                        + " SR \tNumber of item: " + (UserMainPage.itemPriceWithQuantityFromBars / Double.parseDouble(UserMainPage.itemPriceFromBars))
                        + " Item\tTotal price: " + UserMainPage.itemPriceWithQuantityFromBars + "SR.\nThe total cost: " + UserMainPage.itemPriceWithQuantityFromBars
                        + "SR. \n\nThank You";      //WE CAN ADD THE COMPANY INFO
            } catch (Exception e) {
                Toast.makeText(SendShoppingBill.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        String companyMsg;
        companyMsg = "Hello ";//maybe put extra word
        companyMsg += "\n\nThere is an order from\n\tName: "+customerName+"\tE-mail: "+customerEmail+"\tPhone Number: "+customerPhoneNumber+".\n\nHis order is:\n";

        if (UserMainPage.fromPurchaseB){
            int j = 0;//to find the
            double totalPrice = 0;//to calculate the total price
            try {
                for (int i = 0; i < UserMainPage.purchaseBasketCounter; i = i + 3) {
                    companyMsg += i + 1 + ") Item Name: " + UserMainPage.purchaseBasketItems[i] + "\tPrice : " + UserMainPage.purchaseBasketItems[i + 2]
                            + " SR \tNumber of items: " + (UserMainPage.itemPriceWithQuantity[j] / Double.parseDouble(UserMainPage.purchaseBasketItems[i + 2]))
                            + " Item\tTotal Price: " + UserMainPage.itemPriceWithQuantity[j] + " SR.\n";
                    j++;
                }
                for (int i = 0; i<UserMainPage.purchaseBasketCounter-1/3;i++){
                    totalPrice = totalPrice + UserMainPage.itemPriceWithQuantity[i];
                }
            }catch (Exception e){
                Toast.makeText(SendShoppingBill.this,e.getMessage(),Toast.LENGTH_LONG).show();
            }
            companyMsg += "The total cost: " + totalPrice + "SR.\n\n\nThank you.";
        }else{
            try {
                companyMsg += "1) Item Name: " + UserMainPage.itemNameFromBars + "\tPrice: " + UserMainPage.itemPriceFromBars
                        + " SR \tNumber of item: " + (UserMainPage.itemPriceWithQuantityFromBars / Double.parseDouble(UserMainPage.itemPriceFromBars))
                        + " Item\tTotal price: " + UserMainPage.itemPriceWithQuantityFromBars + "SR.\nThe total cost: " + UserMainPage.itemPriceWithQuantityFromBars
                        + "SR. \n\nThank You";
            } catch (Exception e) {
                Toast.makeText(SendShoppingBill.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        try {
            SendMail smCustomer = new SendMail(this, customerEmail, "Bill information", customerMsg);
//            SendMail smCompany = new SendMail(this, email , "New Customer Order", companyMsg);

            smCustomer.execute();
//            smCompany.execute();
        }catch (Exception e) {
            Toast.makeText(SendShoppingBill.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        Toast.makeText(SendShoppingBill.this,"Bill Send", Toast.LENGTH_SHORT).show();
    }

    //p
    public void sendEmailToCustomer (){

    }

    public void sendEmailToCompany (){

    }
}
