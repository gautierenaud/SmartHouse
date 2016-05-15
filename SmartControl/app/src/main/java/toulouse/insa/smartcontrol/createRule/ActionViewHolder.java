package toulouse.insa.smartcontrol.createRule;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import toulouse.insa.smartcontrol.R;

/**
 * Created by gautierenaud on 14/05/16.
 */
public class ActionViewHolder extends RecyclerView.ViewHolder {

    protected Spinner actionSpinner;
    protected Button deleteButton;
    private View mView;

    public ActionViewHolder(View itemView) {
        super(itemView);

        this.mView = itemView;
        this.actionSpinner = (Spinner) itemView.findViewById(R.id.action_spinner);
        this.deleteButton = (Button) itemView.findViewById(R.id.action_delete);
    }
}
