package toulouse.insa.smartcontrol.createRule;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import toulouse.insa.smartcontrol.R;

/**
 * Created by gautierenaud on 14/05/16.
 */
public class TriggerViewHolder extends RecyclerView.ViewHolder {

    protected Spinner triggerSpinner;
    protected Button deleteButton;
    private View mView;

    public TriggerViewHolder(View itemView) {
        super(itemView);

        this.mView = itemView;
        this.triggerSpinner = (Spinner) itemView.findViewById(R.id.trigger_spinner);
        this.deleteButton = (Button) itemView.findViewById(R.id.trigger_delete);
    }
}
