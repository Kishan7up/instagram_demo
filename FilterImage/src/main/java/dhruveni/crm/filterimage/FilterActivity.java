package dhruveni.crm.filterimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import dhruveni.crm.instagram_demo.MainActivity;

import static dhruveni.crm.instagram_demo.MainActivity.retrunBitmap7up;

public class FilterActivity extends AppCompatActivity {

    Bitmap bitmap ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        GenerateFilterImageActivity();

        generateBitmap();

    }

    private Bitmap generateBitmap() {
        if(retrunBitmap7up!=null)
        {
            bitmap=retrunBitmap7up;
        }
        return bitmap;
    }

    private void GenerateFilterImageActivity() {
        Intent i = new Intent(FilterActivity.this, MainActivity.class);
        startActivity(i);
    }
}