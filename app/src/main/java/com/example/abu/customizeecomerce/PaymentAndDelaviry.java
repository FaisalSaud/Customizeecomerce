package com.example.abu.customizeecomerce;

import android.hardware.usb.UsbRequest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PaymentAndDelaviry extends AppCompatActivity {

    String[] ParraySpinner;
    String[] DarraySpinner;

    CardView cardView1;
    CardView cardView2;
    CardView cardView3;

    //item 1
    TextView itemName1;
    TextView itemDescription1;
    TextView itemPrice1;
    EditText itemQuantity1;
    EditText itemImage1;
    Button itembutton1;

    //item 2
    TextView itemName2;
    TextView itemDescription2;
    TextView itemPrice2;
    EditText itemQuantity2;
    EditText itemImage2;
    Button itembutton2;

    //item 3
    TextView itemName3;
    TextView itemDescription3;
    TextView itemPrice3;
    EditText itemQuantity3;
    EditText itemImage3;
    Button itembutton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_and_delaviry);
        getSupportActionBar().setBackgroundDrawable(UserMainPage.background);

        cardView1 = (CardView) findViewById(R.id.cardView1PAD);
        cardView2 = (CardView) findViewById(R.id.cardView2PAD);
        cardView3 = (CardView) findViewById(R.id.cardView3PAD);

        itemName1 = (TextView) findViewById(R.id.itemName1);
        itemDescription1 = (TextView) findViewById(R.id.itemDescription1);
        itemPrice1 = (TextView) findViewById(R.id.itemPrice1);
        itemQuantity1 = (EditText) findViewById(R.id.numberOfItem1);
        itemImage1 = (EditText) findViewById(R.id.itemImage1);
        itembutton1 = (Button) findViewById(R.id.button101);

        itemName2 = (TextView) findViewById(R.id.itemName2);
        itemDescription2 = (TextView) findViewById(R.id.itemDescription2);
        itemPrice2 = (TextView) findViewById(R.id.itemPrice2);
        itemQuantity2 = (EditText) findViewById(R.id.numberOfItem2);
        itemImage2 = (EditText) findViewById(R.id.itemImage2);
        itembutton2 = (Button) findViewById(R.id.button102);

        itemName3 = (TextView) findViewById(R.id.itemName3);
        itemDescription3 = (TextView) findViewById(R.id.itemDescription3);
        itemPrice3 = (TextView) findViewById(R.id.itemPrice3);
        itemQuantity3 = (EditText) findViewById(R.id.numberOfItem3);
        itemImage3 = (EditText) findViewById(R.id.itemImage3);
        itembutton3 = (Button) findViewById(R.id.button103);



        try {
            Spinner Payment = (Spinner) findViewById(R.id.PaymentSpinner);
            Spinner Delivery = (Spinner) findViewById(R.id.DeliverySpinner);
//          Payment.setPrompt("Select your payment methode needed");5
//          delivery.setPrompt("Select your payment methode needed");
            this.ParraySpinner = new String[3];
            ParraySpinner[0] = "Please Select";
            Toast.makeText(PaymentAndDelaviry.this, "1", Toast.LENGTH_SHORT).show();
            if (UserMainPage.Payment == 0) {
                ParraySpinner[1] = "Mada";
                ParraySpinner[2] = "";
            } else if (UserMainPage.Payment == 1) {
                ParraySpinner[1] = "Cash";
                ParraySpinner[2] = "";
            } else if (UserMainPage.Payment == 2) {
                ParraySpinner[1] = "Mada";
                ParraySpinner[2] = "Cash";
            }
            Toast.makeText(PaymentAndDelaviry.this, "2", Toast.LENGTH_SHORT).show();

            ArrayAdapter<String> Padapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, ParraySpinner);
            Toast.makeText(PaymentAndDelaviry.this, "2.1", Toast.LENGTH_SHORT).show();

            Payment.setAdapter(Padapter);
            Toast.makeText(PaymentAndDelaviry.this, "2.2", Toast.LENGTH_SHORT).show();

            this.DarraySpinner = new String[3];
            Toast.makeText(PaymentAndDelaviry.this, "2.3", Toast.LENGTH_SHORT).show();

            DarraySpinner[0] = "Please Select";

            Toast.makeText(PaymentAndDelaviry.this, "3", Toast.LENGTH_SHORT).show();

            if (UserMainPage.Delivery == 0) {
                DarraySpinner[1] = "By hand";
                DarraySpinner[2] = " ";
            } else if (UserMainPage.Delivery == 1) {
                DarraySpinner[1] = "In store";
                DarraySpinner[2] = " ";
            } else if (UserMainPage.Delivery == 2) {
                DarraySpinner[1] = "By Hand";
                DarraySpinner[2] = "In Store";
            }
            Toast.makeText(PaymentAndDelaviry.this, "4", Toast.LENGTH_SHORT).show();

            ArrayAdapter<String> Dadapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, DarraySpinner);
            Delivery.setAdapter(Dadapter);
        } catch (Exception e) {
            Toast.makeText(PaymentAndDelaviry.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        makeSpin();//make 2 and 3 spinner

        try {
            createThisPage();
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
        // change the price when the quantity change
        try {
            itemQuantity1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }//no need

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }//no need

                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        double allItemPrice = 0;
                        double oneItemPrice = Double.parseDouble(itemQuantity1.getText().toString());
                        double itemPrice = Double.parseDouble(UserMainPage.purchaseBasketItems[2].toString());
                        try {
                            allItemPrice = oneItemPrice * itemPrice;
                        } catch (Exception e) {
                            Toast.makeText(PaymentAndDelaviry.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        String i = new Double(allItemPrice).toString()+" SR";
                        itemPrice1.setText(i);
                    }catch (Exception e){}
                }
            });


        itemQuantity2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }//no need

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }//no need

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    double allItemPrice = 0;
                    double oneItemPrice = Double.parseDouble(itemQuantity2.getText().toString());
                    double itemPrice = Double.parseDouble(UserMainPage.purchaseBasketItems[5].toString());
                    try {
                        allItemPrice = oneItemPrice * itemPrice;
                    } catch (Exception e) {
                        Toast.makeText(PaymentAndDelaviry.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    String i = new Double(allItemPrice).toString()+" SR";
                    itemPrice2.setText(i);
                }catch (Exception e){}
            }
        });
        itemQuantity3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }//no need

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }//no need

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    double allItemPrice = 0;
                    double oneItemPrice = Double.parseDouble(itemQuantity3.getText().toString());
                    double itemPrice = Double.parseDouble(UserMainPage.purchaseBasketItems[8].toString());
                    try {
                        allItemPrice = oneItemPrice * itemPrice;
                    } catch (Exception e) {
                        Toast.makeText(PaymentAndDelaviry.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    String i = new Double(allItemPrice).toString()+" SR";
                    itemPrice3.setText(i);
                }catch (Exception e){}
            }
        });
        }catch (Exception e){}

    }

    public void createThisPage(){

        if(!UserMainPage.fromPurchaseB){
            itemName1.setText(UserMainPage.itemNameFromBars);
            itemDescription1.setText(UserMainPage.itemDescriptionFromBars);
            itemPrice1.setText(UserMainPage.itemPriceFromBars+" SR");//+"SR" if we want to but it in the screen
            itemImage1.setBackground(UserMainPage.itemImageFromBars);
            itembutton1.setText("Buy");

            cardView1.setVisibility(View.VISIBLE);
            cardView2.setVisibility(View.GONE);
            cardView3.setVisibility(View.GONE);

            itembutton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(PaymentAndDelaviry.this, "should send message her", Toast.LENGTH_SHORT).show();
                }
            });
            itemQuantity1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }//no need

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }//no need

                @Override
                public void afterTextChanged(Editable s) {
                    try{
                        double allItemPrice = 0;
                        double oneItemPrice = Double.parseDouble(itemQuantity1.getText().toString());
                        double itemPrice = Double.parseDouble(UserMainPage.itemPriceFromBars.toString());
                        try {
                            allItemPrice = oneItemPrice * itemPrice;
                        } catch (Exception e) {
                            Toast.makeText(PaymentAndDelaviry.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                        String i = new Double(allItemPrice).toString()+" SR";
                        itemPrice1.setText(i);
                    }catch (Exception e){}
                }
            });

        }else {
            switch (UserMainPage.purchaseBasketCounter) {
                case 0:
                    Toast.makeText(this, "impossible to display this 'PaymentAndDelivery", Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    itemName1.setText(UserMainPage.purchaseBasketItems[0]);
                    itemDescription1.setText(UserMainPage.purchaseBasketItems[1]);
                    itemPrice1.setText(UserMainPage.purchaseBasketItems[2]+" SR");
                    itemImage1.setBackground(UserMainPage.purchaseBasketItemsImages[0]);
                    itembutton1.setText("Buy");

                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.GONE);
                    cardView3.setVisibility(View.GONE);

                    itembutton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(PaymentAndDelaviry.this, "should send message her", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case 6:
                    itemName1.setText(UserMainPage.purchaseBasketItems[0]);
                    itemDescription1.setText(UserMainPage.purchaseBasketItems[1]);
                    itemPrice1.setText(UserMainPage.purchaseBasketItems[2]+" SR");
                    itemImage1.setBackground(UserMainPage.purchaseBasketItemsImages[0]);
                    itembutton1.setText("Next");

                    itemName2.setText(UserMainPage.purchaseBasketItems[3]);
                    itemDescription2.setText(UserMainPage.purchaseBasketItems[4]);
                    itemPrice2.setText(UserMainPage.purchaseBasketItems[5]+" SR");
                    itemImage2.setBackground(UserMainPage.purchaseBasketItemsImages[1]);
                    itembutton2.setText("Buy");

                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.GONE);
                    cardView3.setVisibility(View.GONE);

                    itembutton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cardView1.setVisibility(View.GONE);
                            cardView2.setVisibility(View.VISIBLE);
                            cardView3.setVisibility(View.GONE);
                        }
                    });
                    itembutton2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(PaymentAndDelaviry.this, "should send message her", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
                case 9:
                    itemName1.setText(UserMainPage.purchaseBasketItems[0]);
                    itemDescription1.setText(UserMainPage.purchaseBasketItems[1]);
                    itemPrice1.setText(UserMainPage.purchaseBasketItems[2]+" SR");
                    itemImage1.setBackground(UserMainPage.purchaseBasketItemsImages[0]);
                    itembutton1.setText("Next");

                    itemName2.setText(UserMainPage.purchaseBasketItems[3]);
                    itemDescription2.setText(UserMainPage.purchaseBasketItems[4]);
                    itemPrice2.setText(UserMainPage.purchaseBasketItems[5]+" SR");
                    itemImage2.setBackground(UserMainPage.purchaseBasketItemsImages[1]);
                    itembutton2.setText("Next");

                    itemName3.setText(UserMainPage.purchaseBasketItems[6]);
                    itemDescription3.setText(UserMainPage.purchaseBasketItems[7]);
                    itemPrice3.setText(UserMainPage.purchaseBasketItems[8]+" SR");
                    itemImage3.setBackground(UserMainPage.purchaseBasketItemsImages[2]);
                    itembutton3.setText("Buy");

                    cardView1.setVisibility(View.VISIBLE);
                    cardView2.setVisibility(View.GONE);
                    cardView3.setVisibility(View.GONE);

                    itembutton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cardView1.setVisibility(View.GONE);
                            cardView2.setVisibility(View.VISIBLE);
                            cardView3.setVisibility(View.GONE);
                        }
                    });
                    itembutton2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cardView1.setVisibility(View.GONE);
                            cardView2.setVisibility(View.GONE);
                            cardView3.setVisibility(View.VISIBLE);
                        }
                    });
                    itembutton3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(PaymentAndDelaviry.this, "should send message her", Toast.LENGTH_SHORT).show();
                        }
                    });
                    break;
            }
        }
    }

    //make 2nd item and 3rd item spinners
    public void makeSpin(){
        try {
            Spinner Payment2 = (Spinner) findViewById(R.id.PaymentSpinner2);
            Spinner Delivery2 = (Spinner) findViewById(R.id.DeliverySpinner2);
//          Payment.setPrompt("Select your payment methode needed");5
//          delivery.setPrompt("Select your payment methode needed");
            this.ParraySpinner = new String[3];
            ParraySpinner[0] = "Please Select";
            Toast.makeText(PaymentAndDelaviry.this, "1", Toast.LENGTH_SHORT).show();
            if (UserMainPage.Payment == 0) {
                ParraySpinner[1] = "Mada";
                ParraySpinner[2] = "";
            } else if (UserMainPage.Payment == 1) {
                ParraySpinner[1] = "Cash";
                ParraySpinner[2] = "";
            } else if (UserMainPage.Payment == 2) {
                ParraySpinner[1] = "Mada";
                ParraySpinner[2] = "Cash";
            }
            Toast.makeText(PaymentAndDelaviry.this, "2", Toast.LENGTH_SHORT).show();

            ArrayAdapter<String> Padapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, ParraySpinner);
            Toast.makeText(PaymentAndDelaviry.this, "2.1", Toast.LENGTH_SHORT).show();

            Payment2.setAdapter(Padapter);
            Toast.makeText(PaymentAndDelaviry.this, "2.2", Toast.LENGTH_SHORT).show();

            this.DarraySpinner = new String[3];
            Toast.makeText(PaymentAndDelaviry.this, "2.3", Toast.LENGTH_SHORT).show();

            DarraySpinner[0] = "Please Select";

            Toast.makeText(PaymentAndDelaviry.this, "3", Toast.LENGTH_SHORT).show();

            if (UserMainPage.Delivery == 0) {
                DarraySpinner[1] = "By hand";
                DarraySpinner[2] = " ";
            } else if (UserMainPage.Delivery == 1) {
                DarraySpinner[1] = "In store";
                DarraySpinner[2] = " ";
            } else if (UserMainPage.Delivery == 2) {
                DarraySpinner[1] = "By Hand";
                DarraySpinner[2] = "In Store";
            }
            Toast.makeText(PaymentAndDelaviry.this, "4", Toast.LENGTH_SHORT).show();

            ArrayAdapter<String> Dadapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, DarraySpinner);
            Delivery2.setAdapter(Dadapter);
        } catch (Exception e) {
            Toast.makeText(PaymentAndDelaviry.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        try {
            Spinner Payment3 = (Spinner) findViewById(R.id.PaymentSpinner3);
            Spinner Delivery3 = (Spinner) findViewById(R.id.DeliverySpinner3);
//          Payment.setPrompt("Select your payment methode needed");5
//          delivery.setPrompt("Select your payment methode needed");
            this.ParraySpinner = new String[3];
            ParraySpinner[0] = "Please Select";
            Toast.makeText(PaymentAndDelaviry.this, "1", Toast.LENGTH_SHORT).show();
            if (UserMainPage.Payment == 0) {
                ParraySpinner[1] = "Mada";
                ParraySpinner[2] = "";
            } else if (UserMainPage.Payment == 1) {
                ParraySpinner[1] = "Cash";
                ParraySpinner[2] = "";
            } else if (UserMainPage.Payment == 2) {
                ParraySpinner[1] = "Mada";
                ParraySpinner[2] = "Cash";
            }
            Toast.makeText(PaymentAndDelaviry.this, "2", Toast.LENGTH_SHORT).show();

            ArrayAdapter<String> Padapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, ParraySpinner);
            Toast.makeText(PaymentAndDelaviry.this, "2.1", Toast.LENGTH_SHORT).show();

            Payment3.setAdapter(Padapter);
            Toast.makeText(PaymentAndDelaviry.this, "2.2", Toast.LENGTH_SHORT).show();

            this.DarraySpinner = new String[3];
            Toast.makeText(PaymentAndDelaviry.this, "2.3", Toast.LENGTH_SHORT).show();

            DarraySpinner[0] = "Please Select";

            Toast.makeText(PaymentAndDelaviry.this, "3", Toast.LENGTH_SHORT).show();

            if (UserMainPage.Delivery == 0) {
                DarraySpinner[1] = "By hand";
                DarraySpinner[2] = " ";
            } else if (UserMainPage.Delivery == 1) {
                DarraySpinner[1] = "In store";
                DarraySpinner[2] = " ";
            } else if (UserMainPage.Delivery == 2) {
                DarraySpinner[1] = "By Hand";
                DarraySpinner[2] = "In Store";
            }
            Toast.makeText(PaymentAndDelaviry.this, "4", Toast.LENGTH_SHORT).show();

            ArrayAdapter<String> Dadapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, DarraySpinner);
            Delivery3.setAdapter(Dadapter);
        } catch (Exception e) {
            Toast.makeText(PaymentAndDelaviry.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
