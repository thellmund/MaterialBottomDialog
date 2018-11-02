package com.hellmund.materialbottomdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.MenuItem;
import android.widget.Toast;
import com.hellmund.library.MaterialBottomDialog;
import com.hellmund.library.actions.Action;
import com.hellmund.library.actions.DisabledAction;
import com.hellmund.library.actions.EnabledAction;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class DarkModeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dark_mode);

        AppCompatButton showWithActionButton = findViewById(R.id.showDialogWithActionsButton);
        showWithActionButton.setOnClickListener(view -> showDialogWithActions());

        AppCompatButton showWithActionableButton = findViewById(R.id.showDialogWithActionableButton);
        showWithActionableButton.setOnClickListener(view -> showDialogWithActionable());
    }

    private void showDialogWithActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new EnabledAction("Lorem ipsum", null));
        actions.add(new DisabledAction("Disabled option", null));

        MaterialBottomDialog.make(this, null)
                .with(actions)
                .onSelected(index -> {
                    Toast.makeText(this, "Selected item at index " + index, LENGTH_SHORT).show();
                })
                .onDismiss(() -> Toast.makeText(this, "Dismissed bottom dialog", LENGTH_SHORT).show())
                .show();
    }

    private void showDialogWithActionable() {
        Contact contact = new Contact(123, "Till", "Hellmund");

        MaterialBottomDialog.make(this, null)
                .with(contact)
                .onSelected(index -> {
                    Toast.makeText(this, "Selected item at index index " + index, LENGTH_SHORT).show();
                })
                .onDismiss(() -> Toast.makeText(this, "Dismissed bottom dialog", LENGTH_SHORT).show())
                .show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
