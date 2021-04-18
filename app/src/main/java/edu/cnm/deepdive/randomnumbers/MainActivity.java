package edu.cnm.deepdive.randomnumbers;

import static java.lang.Integer.parseInt;

import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import edu.cnm.deepdive.randomnumbers.databinding.ActivityMainBinding;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

  ActivityMainBinding binding;
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
    binding.numberDisplay.setOnClickListener((view) -> {
      seekvalue = binding.bar.getProgress();
      @SuppressWarnings("ConstantConditions") String cleaned = binding.limit.getText().toString().trim();
      int value = parseInt(cleaned);
      Random rng = new Random();
      StringBuilder nums = new StringBuilder();
      for (int i = 0; i < seekvalue; i++) {
        int rngVal = rng.nextInt(value) + 1;
        nums.append(rngVal).append("\n");
      }
      binding.randomNumbers.setText(nums);
    });
    setContentView(binding.getRoot());
  }

}