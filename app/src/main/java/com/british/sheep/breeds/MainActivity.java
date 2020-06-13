package com.british.sheep.breeds;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity<override, val> extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    RecyclerViewAdapter adapter;
    String[] url;
    String[] name;
    String[] type;
    String[] established;
    String[] handle;
    String[] colour;
    String[] fleece;
    String[] staple;
    String[] micron;
    String[] gallery;
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mTypes = new ArrayList<>();
    private ArrayList<String> mEstablisheds = new ArrayList<>();
    private ArrayList<String> mHandles = new ArrayList<>();
    private ArrayList<String> mColours = new ArrayList<>();
    private ArrayList<String> mFleeces = new ArrayList<>();
    private ArrayList<String> mStaples = new ArrayList<>();
    private ArrayList<String> mMicrons = new ArrayList<>();
    private ArrayList<String> mGalleryUrls = new ArrayList<>();

    SearchView searchView;
    ArrayList<Images> imagesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();

        //search view
        searchView = findViewById(R.id.search_view);
        url = res.getStringArray(R.array.url);
        name = res.getStringArray(R.array.name);
        type = res.getStringArray(R.array.type);
        established = res.getStringArray(R.array.established);
        handle = res.getStringArray(R.array.handle);
        colour = res.getStringArray(R.array.colour);
        fleece = res.getStringArray(R.array.fleece);
        staple = res.getStringArray(R.array.staple);
        micron = res.getStringArray(R.array.micron);
        gallery = res.getStringArray(R.array.gallery);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Log.d(TAG, "onCreate: started.");
        initImageBitmaps();
    }

    private void initImageBitmaps() {

        //TODO redo this section so that the data matches up and doesnt pull from a string. Hard coded not dynamic but try with a couple entries.

        imagesArrayList = new ArrayList<>();
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
        for (int i = 0; i < url.length; i++) {
            mImageUrls.add(url[i]);
            mNames.add(name[i]);
            mTypes.add(type[i]);
            mEstablisheds.add(established[i]);
            mHandles.add(handle[i]);
            mColours.add(colour[i]);
            mFleeces.add(fleece[i]);
            mStaples.add(staple[i]);
            mMicrons.add(micron[i]);
            mGalleryUrls.add(gallery[i]);
            imagesArrayList.add(new Images(name[i], url[i], type[i], established[i], handle[i], colour[i], fleece[i], staple[i], micron[i], gallery[i]));
        }

        initRecyclerView();
    }

    private void initRecyclerView() {

        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        adapter = new RecyclerViewAdapter(this, mNames, mImageUrls, mTypes, mEstablisheds, mHandles, mColours, mFleeces, mStaples, mMicrons, mGalleryUrls);
        adapter = new RecyclerViewAdapter(this, imagesArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.sort);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                //when click on sort button this method will show alert dialog
                showAlertDialog();
                return false;
            }
        });
//        SearchView searchView = (SearchView) item.getActionView();
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
        return true;
    }

    private void showAlertDialog() {
        //creating custom alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //inflating layout for custom dialog
        View view = getLayoutInflater().inflate(R.layout.dialog_layout, null);
        builder.setView(view);
//        creating dialog
        final AlertDialog alertDialog = builder.create();

        //dialg positive button "RESET" which resets the recycler view to its original position
        // filter by name
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "RESET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sortArrayListByName();
                alertDialog.dismiss();
                Toast.makeText(MainActivity.this, "Reset", Toast.LENGTH_SHORT).show();
            }
        });
        //when alert dialog appears this calls
        // and here we change color of background and text
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.white));

            }
        });

        //all buttons in dialog box
        Button buttonName = view.findViewById(R.id.btn_name);
        Button buttonColor = view.findViewById(R.id.btn_color);
        Button buttonHandle = view.findViewById(R.id.btn_handle);
        Button buttonfleece = view.findViewById(R.id.btn_fleece);
        Button buttonStaple = view.findViewById(R.id.btn_staple);
        Button buttonEstablished = view.findViewById(R.id.btn_established);

        //sort recycler view by name
        buttonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortArrayListByName();
                alertDialog.dismiss();
                Toast.makeText(MainActivity.this, "Sorted By Name", Toast.LENGTH_SHORT).show();

            }


        });
//sort recycler view by color
        buttonColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortArrayListByColor();
                alertDialog.dismiss();
                Toast.makeText(MainActivity.this, "Sorted By Color", Toast.LENGTH_SHORT).show();
            }
        });
//sort recycler view by handle
        buttonHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortArrayListByHandle();
                alertDialog.dismiss();
                Toast.makeText(MainActivity.this, "Sorted By handle", Toast.LENGTH_SHORT).show();
            }
        });
//sort recycler view by fleece
        buttonfleece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortArrayListByFleece();
                alertDialog.dismiss();
                Toast.makeText(MainActivity.this, "Sorted By fleece", Toast.LENGTH_SHORT).show();
            }
        });
//sort recycler view by established
        buttonEstablished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortArrayListByEstablished();
                alertDialog.dismiss();
                Toast.makeText(MainActivity.this, "Sorted by established", Toast.LENGTH_SHORT).show();
            }
        });
//sort recycler view by staple
        buttonStaple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortArrayListByStaple();
                alertDialog.dismiss();
                Toast.makeText(MainActivity.this, "Sorted By staples", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();




    }

    private void sortArrayListByStaple() {
        //Collection class which provides some methods
        // to sort all List implementations such as LinkedList and ArrayList.
        Collections.sort(imagesArrayList, new Comparator<Images>() {
            @Override
            public int compare(Images images, Images t1) {
                return images.getmImageStaples().compareTo(t1.getmImageStaples());
            }
        });

        mNames.clear();
        mImageUrls.clear();
        mTypes.clear();
        mEstablisheds.clear();
        mHandles.clear();
        mColours.clear();
        mFleeces.clear();
        mStaples.clear();
        mMicrons.clear();
        mGalleryUrls.clear();
        for (int i = 0; i < imagesArrayList.size(); i++) {
            mNames.add(imagesArrayList.get(i).getmImageNames());
            mImageUrls.add(imagesArrayList.get(i).getmImages());
            mTypes.add(imagesArrayList.get(i).getmImageTypes());
            mEstablisheds.add(imagesArrayList.get(i).getmImageEstablisheds());
            mHandles.add(imagesArrayList.get(i).getmImageHandles());
            mColours.add(imagesArrayList.get(i).getmImageColours());
            mFleeces.add(imagesArrayList.get(i).getmImageFleeces());
            mStaples.add(imagesArrayList.get(i).getmImageStaples());
            mMicrons.add(imagesArrayList.get(i).getmImageMicrons());
            mGalleryUrls.add(imagesArrayList.get(i).getmImageGallerys());
        }
        initRecyclerView();
//Notifies the attached observers that the underlying data has been changed
        // and any View reflecting the data set should refresh itself

        adapter.notifyDataSetChanged();
    }

    private void sortArrayListByEstablished() {
        //Collection class which provides some methods
        // to sort all List implementations such as LinkedList and ArrayList.

        Collections.sort(imagesArrayList, new Comparator<Images>() {
            @Override
            public int compare(Images images, Images t1) {
                return images.getmImageEstablisheds().compareTo(t1.getmImageEstablisheds());
            }
        });

        mNames.clear();
        mImageUrls.clear();
        mTypes.clear();
        mEstablisheds.clear();
        mHandles.clear();
        mColours.clear();
        mFleeces.clear();
        mStaples.clear();
        mMicrons.clear();
        mGalleryUrls.clear();
        for (int i = 0; i < imagesArrayList.size(); i++) {
            mNames.add(imagesArrayList.get(i).getmImageNames());
            mImageUrls.add(imagesArrayList.get(i).getmImages());
            mTypes.add(imagesArrayList.get(i).getmImageTypes());
            mEstablisheds.add(imagesArrayList.get(i).getmImageEstablisheds());
            mHandles.add(imagesArrayList.get(i).getmImageHandles());
            mColours.add(imagesArrayList.get(i).getmImageColours());
            mFleeces.add(imagesArrayList.get(i).getmImageFleeces());
            mStaples.add(imagesArrayList.get(i).getmImageStaples());
            mMicrons.add(imagesArrayList.get(i).getmImageMicrons());
            mGalleryUrls.add(imagesArrayList.get(i).getmImageGallerys());
        }
        initRecyclerView();
//Notifies the attached observers that the underlying data has been changed
        // and any View reflecting the data set should refresh itself

        adapter.notifyDataSetChanged();
    }

    private void sortArrayListByFleece() {
        //Collection class which provides some methods
        // to sort all List implementations such as LinkedList and ArrayList.

        Collections.sort(imagesArrayList, new Comparator<Images>() {
            @Override
            public int compare(Images images, Images t1) {
                return images.getmImageFleeces().compareTo(t1.getmImageFleeces());
            }
        });

        mNames.clear();
        mImageUrls.clear();
        mTypes.clear();
        mEstablisheds.clear();
        mHandles.clear();
        mColours.clear();
        mFleeces.clear();
        mStaples.clear();
        mMicrons.clear();
        mGalleryUrls.clear();
        for (int i = 0; i < imagesArrayList.size(); i++) {
            mNames.add(imagesArrayList.get(i).getmImageNames());
            mImageUrls.add(imagesArrayList.get(i).getmImages());
            mTypes.add(imagesArrayList.get(i).getmImageTypes());
            mEstablisheds.add(imagesArrayList.get(i).getmImageEstablisheds());
            mHandles.add(imagesArrayList.get(i).getmImageHandles());
            mColours.add(imagesArrayList.get(i).getmImageColours());
            mFleeces.add(imagesArrayList.get(i).getmImageFleeces());
            mStaples.add(imagesArrayList.get(i).getmImageStaples());
            mMicrons.add(imagesArrayList.get(i).getmImageMicrons());
            mGalleryUrls.add(imagesArrayList.get(i).getmImageGallerys());
        }
        initRecyclerView();
//Notifies the attached observers that the underlying data has been changed
        // and any View reflecting the data set should refresh itself

        adapter.notifyDataSetChanged();
    }

    private void sortArrayListByHandle() {
        //Collection class which provides some methods
        // to sort all List implementations such as LinkedList and ArrayList.

        Collections.sort(imagesArrayList, new Comparator<Images>() {
            @Override
            public int compare(Images images, Images t1) {
                return images.getmImageHandles().compareTo(t1.getmImageHandles());
            }
        });

        mNames.clear();
        mImageUrls.clear();
        mTypes.clear();
        mEstablisheds.clear();
        mHandles.clear();
        mColours.clear();
        mFleeces.clear();
        mStaples.clear();
        mMicrons.clear();
        mGalleryUrls.clear();
        for (int i = 0; i < imagesArrayList.size(); i++) {
            mNames.add(imagesArrayList.get(i).getmImageNames());
            mImageUrls.add(imagesArrayList.get(i).getmImages());
            mTypes.add(imagesArrayList.get(i).getmImageTypes());
            mEstablisheds.add(imagesArrayList.get(i).getmImageEstablisheds());
            mHandles.add(imagesArrayList.get(i).getmImageHandles());
            mColours.add(imagesArrayList.get(i).getmImageColours());
            mFleeces.add(imagesArrayList.get(i).getmImageFleeces());
            mStaples.add(imagesArrayList.get(i).getmImageStaples());
            mMicrons.add(imagesArrayList.get(i).getmImageMicrons());
            mGalleryUrls.add(imagesArrayList.get(i).getmImageGallerys());
        }
        initRecyclerView();
//Notifies the attached observers that the underlying data has been changed
        // and any View reflecting the data set should refresh itself

        adapter.notifyDataSetChanged();

    }

    private void sortArrayListByName() {
        //Collection class which provides some methods
        // to sort all List implementations such as LinkedList and ArrayList.

        Collections.sort(imagesArrayList, new Comparator<Images>() {
            @Override
            public int compare(Images images, Images t1) {
                return images.getmImageNames().compareTo(t1.getmImageNames());
            }
        });

        mNames.clear();
        mImageUrls.clear();
        mTypes.clear();
        mEstablisheds.clear();
        mHandles.clear();
        mColours.clear();
        mFleeces.clear();
        mStaples.clear();
        mMicrons.clear();
        mGalleryUrls.clear();
        for (int i = 0; i < imagesArrayList.size(); i++) {
            mNames.add(imagesArrayList.get(i).getmImageNames());
            mImageUrls.add(imagesArrayList.get(i).getmImages());
            mTypes.add(imagesArrayList.get(i).getmImageTypes());
            mEstablisheds.add(imagesArrayList.get(i).getmImageEstablisheds());
            mHandles.add(imagesArrayList.get(i).getmImageHandles());
            mColours.add(imagesArrayList.get(i).getmImageColours());
            mFleeces.add(imagesArrayList.get(i).getmImageFleeces());
            mStaples.add(imagesArrayList.get(i).getmImageStaples());
            mMicrons.add(imagesArrayList.get(i).getmImageMicrons());
            mGalleryUrls.add(imagesArrayList.get(i).getmImageGallerys());
        }
        initRecyclerView();
        //Notifies the attached observers that the underlying data has been changed
        // and any View reflecting the data set should refresh itself

        adapter.notifyDataSetChanged();

    }

    private void sortArrayListByColor() {
        //Collection class which provides some methods
        // to sort all List implementations such as LinkedList and ArrayList.

        Collections.sort(imagesArrayList, new Comparator<Images>() {
            @Override
            public int compare(Images images, Images t1) {
                return images.getmImageColours().compareTo(t1.getmImageColours());
            }
        });
        mNames.clear();
        mImageUrls.clear();
        mTypes.clear();
        mEstablisheds.clear();
        mHandles.clear();
        mColours.clear();
        mFleeces.clear();
        mStaples.clear();
        mMicrons.clear();
        mGalleryUrls.clear();
        for (int i = 0; i < imagesArrayList.size(); i++) {
            mNames.add(imagesArrayList.get(i).getmImageNames());
            mImageUrls.add(imagesArrayList.get(i).getmImages());
            mTypes.add(imagesArrayList.get(i).getmImageTypes());
            mEstablisheds.add(imagesArrayList.get(i).getmImageEstablisheds());
            mHandles.add(imagesArrayList.get(i).getmImageHandles());
            mColours.add(imagesArrayList.get(i).getmImageColours());
            mFleeces.add(imagesArrayList.get(i).getmImageFleeces());
            mStaples.add(imagesArrayList.get(i).getmImageStaples());
            mMicrons.add(imagesArrayList.get(i).getmImageMicrons());
            mGalleryUrls.add(imagesArrayList.get(i).getmImageGallerys());
        }
        initRecyclerView();
        //Notifies the attached observers that the underlying data has been changed
        // and any View reflecting the data set should refresh itself
        adapter.notifyDataSetChanged();

    }

}
