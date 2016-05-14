package toulouse.insa.smartcontrol.viewRules;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import toulouse.insa.smartcontrol.R;

/**
 * Created by gautierenaud on 27/04/16.
 */
public class CustomViewHolder extends RecyclerView.ViewHolder {

    protected TextView categoryView;
    protected TextView titleView;

    private View mView;

    public CustomViewHolder(View itemView) {
        super(itemView);
        mView = itemView;

        mView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                CharSequence text = titleView.getText();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        this.categoryView = (TextView) itemView.findViewById(R.id.card_category);
        this.titleView = (TextView) itemView.findViewById(R.id.card_title);
    }
}
