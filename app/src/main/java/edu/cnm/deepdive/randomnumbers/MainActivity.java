package edu.cnm.deepdive.randomnumbers;

import static java.lang.Integer.parseInt;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import androidx.appcompat.app.AppCompatActivity;
import edu.cnm.deepdive.randomnumbers.databinding.ActivityMainBinding;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  ActivityMainBinding binding;
  private boolean updateInProgress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    binding.bar.setProgress(10);
    setContentView(binding.getRoot());
    binding.limit.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
        if (!updateInProgress) {
          updateInProgress = true;
          String cleaned = editable.toString().trim();
          int value = parseInt(cleaned);
          Random rng = new Random();
          StringBuilder nums = new StringBuilder();
          for (int i = 0; i < 7; i++) {
            int rngVal = rng.nextInt(value) + 1;
            nums.append(rngVal).append("\n");
          }
          binding.randomNumbers.setText(nums);
          updateInProgress = false;
        }
      }
    });
  }

}