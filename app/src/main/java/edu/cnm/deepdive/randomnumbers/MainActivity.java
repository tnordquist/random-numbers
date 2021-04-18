package edu.cnm.deepdive.randomnumbers;

import static java.lang.Integer.parseInt;

import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import edu.cnm.deepdive.randomnumbers.databinding.ActivityMainBinding;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  ActivityMainBinding binding;
  private boolean updateInProgress;
  private int seekvalue = 5;

  @RequiresApi(api = VERSION_CODES.O)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());
    binding.bar.setMin(1);
    binding.bar.setProgress(5);
    binding.bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int limit, boolean b) {
        seekvalue = limit;
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {
      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
    binding.numberDisplay.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        seekvalue = binding.bar.getProgress();
      }
    });
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
          if (value > Integer.MAX_VALUE) {
            Toast.makeText(MainActivity.this, "Enter a smaller number", Toast.LENGTH_LONG).show();
          } else {
            Random rng = new Random();
            StringBuilder nums = new StringBuilder();
            for (int i = 0; i < seekvalue; i++) {
              int rngVal = rng.nextInt(value) + 1;
              nums.append(rngVal).append("\n");
            }
            binding.randomNumbers.setText(nums);
            updateInProgress = false;
          }
        }
      }
    });
  }

}