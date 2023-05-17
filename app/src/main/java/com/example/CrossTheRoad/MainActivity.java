package com.example.CrossTheRoad;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {

    private Button playButton;
    private GameView gameView;

    private Bundle extras;
    private MediaPlayer music;

    protected static boolean replay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window applicationWindow = this.getWindow();
        applicationWindow.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        extras = getIntent().getExtras();
        if (extras != null) {
            replay = extras.getBoolean("replay");
        }
        if (!replay) {
            setContentView(R.layout.start_screen);

            playButton = findViewById(R.id.playButton);
            playButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    configScreen();
                }
            });
        } else {
            configScreen();
        }
        music = MediaPlayer.create(this, R.raw.gamesong);
        music.start();
    }

    private void configScreen() {
        setContentView(R.layout.config_screen);

        createSpinner();

        EditText nameInputTextField = findViewById(R.id.nameInput);
        nameInputTextField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String currentString = nameInputTextField.getText().toString();
                checkNameInput(currentString, nameInputTextField);

                if (s.subSequence(start, start + 1).toString()
                        .equalsIgnoreCase("\n")) {
                    System.out.println("here");
                    // Change text to show without '\n'
                    String sText = start > 0 ? s.subSequence(0, start).toString() : "";
                    sText += start < s.length() ? s.subSequence(start + 1,
                            s.length()).toString() : "";
                    nameInputTextField.setText(sText);
                    nameInputTextField.setSelection(sText.length());

                    closeKeyboard();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Spinner difficultyLevelSpinner = findViewById(R.id.difficultySpinner);
        difficultyLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void closeKeyboard() {

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager manager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private static boolean checkNameInput(String name, EditText nameInput) {
        if (name == null) {
            return false;
        }
        if (name.trim().length() == 0) {
            if (nameInput != null) {
                nameInput.setError("Name can not be empty");
            }
            return false;
        }
        if (name.trim().length() > 20) {
            if (nameInput != null) {
                nameInput.setError("Name must be shorter than 20 characters");
            }
            return false;
        }
        return true;
    }
    public static boolean checkNameTestHelper(String name) {
        return (checkNameInput(name, null));
    }

    public void continueConfig(View view) {
        EditText nameInputTextField = findViewById(R.id.nameInput);
        Spinner difficultyLevelSpinner = findViewById(R.id.difficultySpinner);
        RadioGroupGridLayout spriteButtonGroup = findViewById(R.id.spriteRadioGroup);
        String errorMsg = "";
        String name = "";
        int spriteId = 0;
        int diffLevel = 0;


        boolean canContinue = true;
        String currentName = nameInputTextField.getText().toString();
        if (!checkNameInput(currentName, nameInputTextField)) {
            errorMsg = errorMsg + "Your name cannot be empty or null. ";
            canContinue = false;
        } else {
            name = currentName;
            System.out.println("Name: " + name);
        }
        if (difficultyLevelSpinner.getSelectedItem() == null) {
            errorMsg = errorMsg + "You must select a difficultly level. ";
            canContinue = false;
        } else {
            diffLevel = (int) difficultyLevelSpinner.getSelectedItemId();
            System.out.println(diffLevel);
        }
        if (spriteButtonGroup.getCheckedRadioButtonId() == -1) {
            errorMsg = errorMsg + "You must select a sprite character. ";
            canContinue = false;
        } else {
            spriteId = spriteButtonGroup.getCheckedRadioButtonId();
        }
        if (canContinue) {
            music.stop();
            gameView = new GameView(this,
                    new Player(name.trim(), diffLevel, getPlayerType(spriteId)));
            gameView.setContinueGame(true);
            gameView.setVisibility(View.VISIBLE);
            setContentView(gameView);
        } else {
            Snackbar error = Snackbar.make(view, errorMsg, 1000);
            error.show();
        }
    }

    public void onDifficultyBtnClicked(View view) {
    }

    private void createSpinner() {
        Spinner difficultyLevelSpinner = findViewById(R.id.difficultySpinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.levelDropDown, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        difficultyLevelSpinner.setAdapter(spinnerAdapter);
    }

    protected void onStop() {
        super.onStop();
    }

    private static ObjectType getPlayerType(int playerId) {
        switch (playerId) {
        // 0 = Jester
        case 0: return ObjectType.JESTER;
        // 1 = Knight
        case 1: return ObjectType.KNIGHT;
        // 2 = King
        case 2: return ObjectType.KING;
        // 4 = Wizard
        case 4: return ObjectType.WIZARD;
        // 5 = Executioner
        case 5: return ObjectType.EXECUTIONER;
        // 3 = Princess = default
        default: return ObjectType.PRINCESS;
        }
    }
}