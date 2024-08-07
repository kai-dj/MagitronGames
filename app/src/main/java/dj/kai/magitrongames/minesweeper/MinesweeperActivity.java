package dj.kai.magitrongames.minesweeper;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MinesweeperActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MinesweeperView(this));
    }
}
