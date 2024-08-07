package dj.kai.magitrongames.minesweeper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import com.caverock.androidsvg.SVG;
import java.io.InputStream;

public class MinesweeperView extends View {
    private Paint paint;
    private int[][] grid; // 0: empty, 1-8: numbers, 9: mine, 10: flagged
    private int cellSize = 100; // Size of each cell in pixels

    public MinesweeperView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        // Initialize a sample grid for demonstration
        grid = new int[][] {
                {0, 1, 9},
                {1, 2, 1},
                {9, 1, 0}
        };
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGrid(canvas);
    }

    private void drawGrid(Canvas canvas) {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                drawCell(canvas, row, col, grid[row][col]);
            }
        }
    }

    private void drawCell(Canvas canvas, int row, int col, int cellType) {
        int left = col * cellSize;
        int top = row * cellSize;
        int right = left + cellSize;
        int bottom = top + cellSize;

        // Draw cell border
        paint.setColor(Color.BLACK);
        canvas.drawRect(left, top, right, bottom, paint);

        // Draw SVG asset based on cell type
        int svgResource = getSvgResource(cellType);
        if (svgResource != 0) {
            try (InputStream inputStream = getResources().openRawResource(svgResource)) {
                SVG svg = SVG.getFromInputStream(inputStream);
                svg.setDocumentWidth(cellSize);
                svg.setDocumentHeight(cellSize);
                svg.renderToCanvas(canvas, left, top);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int getSvgResource(int cellType) {
        switch (cellType) {
            case 0: return R.raw.cell_empty;
            case 1: return R.raw.cell_1;
            case 2: return R.raw.cell_2;
            case 9: return R.raw.cell_mine;
            case 10: return R.raw.cell_flag;
            default: return 0;
        }
    }
}
