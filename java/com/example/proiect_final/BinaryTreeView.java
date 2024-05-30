package com.example.proiect_final;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class BinaryTreeView extends View {

    public TreeNode root;
    private Paint nodePaint;
    private Paint textPaint;
    private StringBuilder traversalsOutput;

    public BinaryTreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        nodePaint = new Paint();
        nodePaint.setColor(Color.BLACK);
        nodePaint.setStyle(Paint.Style.FILL);
        nodePaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(40);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);

        traversalsOutput = new StringBuilder();
    }

    public void insertNode(int value) {
        root = insertNodeRecursive(root, value);
        nodePaint.setColor(Color.BLUE);
        invalidate(); // Redraw the view when the root changes
    }

    private TreeNode insertNodeRecursive(TreeNode root, int value) {
        if (root == null) {
            return new TreeNode(value);
        }

        if (value < root.val) {
            root.left = insertNodeRecursive(root.left, value);
        } else if (value > root.val) {
            root.right = insertNodeRecursive(root.right, value);
        }

        return root;
    }

    public void buildTree(int[] values) {
        for (int value : values) {
            insertNode(value);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 5000; // Set your desired width here
        int desiredHeight = 5000; // Set your desired height here

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        // Measure Width
        if (widthMode == MeasureSpec.EXACTLY) {
            // Must be this size
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            // Can't be bigger than...
            width = Math.min(desiredWidth, widthSize);
        } else {
            // Be whatever you want
            width = desiredWidth;
        }

        // Measure Height
        if (heightMode == MeasureSpec.EXACTLY) {
            // Must be this size
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            // Can't be bigger than...
            height = Math.min(desiredHeight, heightSize);
        } else {
            // Be whatever you want
            height = desiredHeight;
        }

        // Must call this setMeasuredDimension to set the measured width and height
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (root != null) {
            int startX = getWidth() / 2;
            int startY = 100;
            int offsetX = getWidth() / 4;
            traversalsOutput.setLength(0);

            drawTree(canvas, startX, startY, root, offsetX);

            // Display traversals
            canvas.drawText("Preorder: " + traversePreorder(root), startX, getHeight() - 200, textPaint);
            canvas.drawText("Inorder: " + traverseInorder(root), startX, getHeight() - 150, textPaint);
            canvas.drawText("Postorder: " + traversePostorder(root), startX, getHeight() - 100, textPaint);
        }
    }

    private void drawTree(Canvas canvas, int x, int y, TreeNode node, int offsetX) {
        if (node != null) {
            canvas.drawCircle(x, y, 40, nodePaint); // Draw node
            canvas.drawText(String.valueOf(node.val), x, y + getTextHeight(textPaint) / 2, textPaint); // Draw node value

            if (node.left != null) {
                int childX = x - offsetX;
                int childY = y + 200;
                canvas.drawLine(x, y + 40, childX, childY - 40, nodePaint); // Draw left edge
                drawTree(canvas, childX, childY, node.left, offsetX / 2); // Draw left subtree
            }

            if (node.right != null) {
                int childX = x + offsetX;
                int childY = y + 200;
                canvas.drawLine(x, y + 40, childX, childY - 40, nodePaint); // Draw right edge
                drawTree(canvas, childX, childY, node.right, offsetX / 2); // Draw right subtree
            }
        }
    }

    public String traversePreorder(TreeNode root) {
        traversalsOutput.setLength(0);
        traversePreorderRecursive(root);
        return traversalsOutput.toString();
    }

    public void traversePreorderRecursive(TreeNode root) {
        if (root != null) {
            traversalsOutput.append(root.val).append(" ");
            traversePreorderRecursive(root.left);
            traversePreorderRecursive(root.right);
        }
    }

    public String traverseInorder(TreeNode root) {
        traversalsOutput.setLength(0);
        traverseInorderRecursive(root);
        return traversalsOutput.toString();
    }

    public void traverseInorderRecursive(TreeNode root) {
        if (root != null) {
            traverseInorderRecursive(root.left);
            traversalsOutput.append(root.val).append(" ");
            traverseInorderRecursive(root.right);
        }
    }

    public String traversePostorder(TreeNode root) {
        traversalsOutput.setLength(0);
        traversePostorderRecursive(root);
        return traversalsOutput.toString();
    }

    public void traversePostorderRecursive(TreeNode root) {
        if (root != null) {
            traversePostorderRecursive(root.left);
            traversePostorderRecursive(root.right);
            traversalsOutput.append(root.val).append(" ");
        }
    }

    private int getTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
    }
}


