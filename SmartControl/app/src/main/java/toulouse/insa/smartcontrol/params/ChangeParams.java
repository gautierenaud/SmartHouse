package toulouse.insa.smartcontrol.params;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import order.Order;
import order.OrderSender;
import toulouse.insa.smartcontrol.R;
import toulouse.insa.smartcontrol.params.Parameters;

public class ChangeParams extends AppCompatActivity {

    private EditText frameselfIP;
    private OrderSender orderSender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_params);

        frameselfIP = (EditText) findViewById(R.id.param_frameself_ip_value);
        frameselfIP.setText(Parameters.getFrameselfAddress());

        setTitle("Parameters");

        frameselfIP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = frameselfIP.getText().toString();
                if (validIP(text))
                    frameselfIP.setBackgroundColor(Color.GREEN);
                else
                    frameselfIP.setBackgroundColor(Color.RED);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // create and start the order sender
        this.orderSender = new OrderSender();
    }

    public void SetNewFrameselfIP(View view){
        String newIP = frameselfIP.getText().toString();
        if (validIP(newIP))
            Parameters.setFrameselfAddress(newIP);
        else {
            Context context = getApplicationContext();
            CharSequence text = "wrong IP Address";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        }
    }

    public void sendStart(View view){
        this.orderSender.orderQueue.add(new Order(Order.OrderType.START));
    }

    public void sendStop(View view){
        this.orderSender.orderQueue.add(new Order(Order.OrderType.STOP));
    }

    // check if string is a valid ip
    public static boolean validIP (String ip) {
        try {
            if ( ip == null || ip.isEmpty() ) {
                return false;
            }

            String[] parts = ip.split( "\\." );
            if ( parts.length != 4 ) {
                return false;
            }

            for ( String s : parts ) {
                int i = Integer.parseInt( s );
                if ( (i < 0) || (i > 255) ) {
                    return false;
                }
            }
            if ( ip.endsWith(".") ) {
                return false;
            }

            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
