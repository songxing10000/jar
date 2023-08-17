package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private GridView gridView;

    private ArrayList<String> lang_strings = new ArrayList<>();
    private ArrayList<String> lang2_strings = new ArrayList<>();
    private ArrayList<String> lang31_strings = new ArrayList<>();
    private ArrayList<String> lang32_strings = new ArrayList<>();
    private ArrayList<String> lang33_strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv1);

        int leftPadding = 20; // 左内边距值（以像素为单位）
        int topPadding = 0; // 上内边距值（以像素为单位）
        int rightPadding = 20; // 右内边距值（以像素为单位）
        int bottomPadding = 20; // 下内边距值（以像素为单位）

        tv.setPadding(leftPadding, topPadding, rightPadding, bottomPadding); // 设置内边距

        Button button = findViewById(R.id.button1);
        button.setOnClickListener(v -> loadLangFile());
        Button button9 = findViewById(R.id.button9);
        button9.setOnClickListener(v -> loadLang2File());

        Button button31 = findViewById(R.id.button31);
        button31.setOnClickListener(v -> loadLang31File());
        Button button32 = findViewById(R.id.button32);
        button32.setOnClickListener(v -> loadLang32File());
        Button button33 = findViewById(R.id.button33);
        button33.setOnClickListener(v -> loadLang33File());
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> loadPakFile());
        imageBytess = new ArrayList<>();
        gridView = findViewById(R.id.gridView1);

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> loadImgs());

    }

    private ArrayList<String> imageNames = new ArrayList<>();
    private List<byte[]> imageBytess;

    private void loadImgs() {
        tv.setVisibility(View.GONE);
        tv.setText("");
        gridView.setVisibility(View.VISIBLE);
        if (gridView.getAdapter() != null) {

            return;
        }

        Bitmap[] bitmapArray = new Bitmap[imageBytess.size()];

        for (int i = 0; i < imageBytess.size(); i++) {
            byte[] imageBytes = imageBytess.get(i);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            bitmapArray[i] = bitmap;
            String imgName = imageNames.get(i);
            if (!imgName.endsWith(".png")) {
                continue;
            }

            File imageFile = new File(getFilesDir(), imgName);
            try {
                OutputStream outputStream = new FileOutputStream(imageFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.close();
            } catch (FileNotFoundException e) {
                System.out.println(e.getLocalizedMessage());

            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }

        }


        gridView.setAdapter(new ImageAdapter(this, bitmapArray, imageNames));

    }

    private void loadPakFile() {
        tv.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.GONE);
        if (imageNames != null && imageNames.size() > 0) {
            tv.setText(arrayToStringOnNewLines(imageNames, 166));
            return;
        }
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open("cat1.pak");
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            short short1 = dataInputStream.readShort();
            final int[] array = new int[short1];
            for (int i = 0; i < short1; ++i) {
                try {
                    imageNames.add(dataInputStream.readUTF());
                    array[i] = dataInputStream.readShort();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            byte[][] image_bytess = new byte[imageNames.size()][];

            for (short n2 = 0; n2 < short1; ++n2) {

                byte[] image_bytes = new byte[array[n2]];

                dataInputStream.readFully(image_bytess[n2] = image_bytes);

                imageBytess.add(image_bytes);
            }
            try {
                dataInputStream.close();
                tv.setText(arrayToStringOnNewLines(imageNames, 166));
                Button button2 = (Button) findViewById(R.id.button2);
                button2.setText(button2.getText() + String.valueOf(imageNames.size()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadLangFile(Button button, String fileName, ArrayList<String> savedArray, int subValue) {
        tv.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.GONE);
        if (savedArray != null && savedArray.size() > 0) {
            tv.setText(arrayToStringOnNewLines(savedArray, subValue));
            return;
        }
        AssetManager assetManager = getAssets();
        try {
            InputStream inputStream = assetManager.open(fileName);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int a = dataInputStream.readInt();

            for (int i = 0; i < a; ++i) {
                try {
                    savedArray.add(dataInputStream.readUTF());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                dataInputStream.close();
                tv.setText(arrayToStringOnNewLines(savedArray, subValue));
                button.setText(button.getText() + String.valueOf(savedArray.size()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadLang31File() {

        loadLangFile(findViewById(R.id.button31), "t.bin", lang31_strings, 272);

    }

    private void loadLang32File() {

        loadLangFile(findViewById(R.id.button32), "t2.bin", lang32_strings, 276);
    }

    private void loadLang33File() {
        loadLangFile(findViewById(R.id.button33), "t3.bin", lang33_strings, 308);
    }

    private void loadLang2File() {
        loadLangFile(findViewById(R.id.button9), "lang2.dat", lang2_strings, 211);
    }

    private void loadLangFile() {
        loadLangFile(findViewById(R.id.button1), "lang.dat", lang_strings, 166);
    }

    public String arrayToStringOnNewLines(ArrayList<String> array, int subValue) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.size(); i++) {
            if (i > subValue) {
                int spValue = i - subValue;
                sb.append(i).append("：").append(array.get(i)).append("  (10进制：").append(spValue).append(") (16进制：").append(Integer.toHexString(spValue)).append(")").append(System.lineSeparator());
            } else {
                sb.append(i).append("：").append(array.get(i)).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}