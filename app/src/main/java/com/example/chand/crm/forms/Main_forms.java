package com.example.chand.crm.forms;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chand.crm.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.srodrigo.androidhintspinner.HintAdapter;
import me.srodrigo.androidhintspinner.HintSpinner;

public class Main_forms extends AppCompatActivity implements View.OnClickListener {
    String TAG = "MainActivity.java";
    String popUpContents[];
    String appltype ="", marystatus ="", empltype ="";
    PopupWindow popupservices;
    Button buttonShowDropDown;
    Button buttonsave;
    ImageButton  buttonback;
    EditText name,surname,city,adharnum,dob;
    RadioGroup gender,outofindia;
    RadioButton male,female,other,yes,no;
    Spinner Appli_type_Spinner,Marital_status_Spinner,Emp_type_Spinner;
    DatePickerDialog datePickerDialog;
    ArrayList<String> application_typelist = new ArrayList<>();
    ArrayList<String> maritalstatus_list = new ArrayList<>();
    ArrayList<String> emploment_list = new ArrayList<>();
    List<String> services = new ArrayList<>();
    LinearLayout step1layout;
    private View view;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forms);
        Toolbar toolbar = findViewById(R.id.toolbaraction);
        toolbar.setOverflowIcon(ContextCompat.getDrawable(this,R.drawable.optionmenu36));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

//        final PlaceAutocompleteFragment placeAutocompleteFragment = (PlaceAutocompleteFragment)
//              getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                Log.i(TAG, "Place: " + place.getName());
//            }
//            @Override
//            public void onError(Status status) {
//                Log.i(TAG, "An error occurred: " + status);
//
//            }
//        });
//        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
//                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
//                .build();
//        placeAutocompleteFragment.setFilter(typeFilter);
        services.add("Fresh Passport::Passport Services Selected");
        services.add("Re-Issue Passport::Renew Services Selected");
        services.add("Visa::Visa Services Selected");
        services.add("Travels Booking::Booking Services Selected");
//        onRadioButtonClicked();
        findViewByIds();
        initSpinnersData();
        initSpinners();


        // convert to simple array
        popUpContents = new String[services.size()];
        services.toArray(popUpContents);
        // initialize pop up window
        popupservices = popupWindow();
        // button on click listener
        View.OnClickListener handler = new View.OnClickListener() {
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonShowDropDown:
                        // show the list view as dropdown
                        popupservices.showAsDropDown(v, -5, 0);
                        break;
                }
//                if (v.getId() == R.id.dobirth) {
//                    getDate(dob);
                }

        };

        // our buttons
        buttonShowDropDown = findViewById(R.id.buttonShowDropDown);
        buttonShowDropDown.setOnClickListener(handler);
//        optionmenubutton = findViewById(R.id.optionmenu);
//        optionmenubutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openOptionsMenu();
//            }
//        });
    }
    private void findViewByIds(){
        name = findViewById(R.id.userName);
        surname = findViewById(R.id.surnam);
        city = findViewById(R.id.village_town);
        adharnum = findViewById(R.id.adhar);
        gender = findViewById(R.id.rb);gender.clearCheck();
        outofindia = findViewById(R.id.rb2);outofindia.clearCheck();
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);
        other = findViewById(R.id.other);
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        Appli_type_Spinner = findViewById(R.id.appli_Type);
        Marital_status_Spinner =findViewById(R.id.statusmarried);
        Emp_type_Spinner = findViewById(R.id.employtype);
        dob = findViewById(R.id.dobirth);dob.setOnClickListener(this);
        step1layout = findViewById(R.id.step2);
        buttonback = findViewById(R.id.backarrow);buttonback.setOnClickListener(this);
        buttonsave = findViewById(R.id.save);buttonsave.setOnClickListener(this);

    }


         private void initSpinnersData(){
             application_typelist.add("Normal");
             application_typelist.add("Tatkaal");
             application_typelist.add("P-type");

             maritalstatus_list.add("Single");
             maritalstatus_list.add("Married");
             maritalstatus_list.add("Divorced");
             maritalstatus_list.add("Widow/widower");
             maritalstatus_list.add("Separate");

             emploment_list.add("Government");
             emploment_list.add("Homemaker");
             emploment_list.add("Not employed");
             emploment_list.add("Others");
             emploment_list.add("Owners, Partners Directors of companies which are members of CII,FICCI and ASSOCHAM");
             emploment_list.add("PSU");
             emploment_list.add("Retired Government Servant");
             emploment_list.add("Retired-Private Service");
         }
    public void initSpinners() {
        HintSpinner<String> applicationtypehintSpinner = new HintSpinner<>(
                Appli_type_Spinner,
                // Default layout - You don't need to pass in any layout id, just your hint text and
                // your list data
                new HintAdapter<String>(this, "Appliation Type", application_typelist),
                new HintSpinner.Callback<String>() {
                    @Override
                    public void onItemSelected(int position, String itemAtPosition) {
                        appltype = itemAtPosition;
                    }
                });
        applicationtypehintSpinner.init();

        HintSpinner<String> maritaltypehintSpinner = new HintSpinner<>(
                Marital_status_Spinner,
                // Default layout - You don't need to pass in any layout id, just your hint text and
                // your list data
        new HintAdapter<String>(this, "Marital Status", maritalstatus_list),
                new HintSpinner.Callback<String>() {
                    @Override
                    public void onItemSelected(int position, String itemAtPosition) {
                        marystatus = itemAtPosition;

                    }
                });
                maritaltypehintSpinner.init();

        HintSpinner<String> EmploymenttypehintSpinner = new HintSpinner<>(
                Marital_status_Spinner,
                // Default layout - You don't need to pass in any layout id, just your hint text and
                // your list data
                new HintAdapter<String>(this, "Employment Type", emploment_list),
                new HintSpinner.Callback<String>() {
                    @Override
                    public void onItemSelected(int position, String itemAtPosition) {
                        empltype = itemAtPosition;

                    }
                });
        EmploymenttypehintSpinner.init();
    }

public void onRadioButtonClicked(View view) {

    boolean checked = ((RadioButton) view).isChecked();
    // Check which radio button was clicked
    switch(view.getId()) {
        case R.id.male:
            if (checked)
                Toast.makeText(getApplicationContext(),"male is selected",Toast.LENGTH_SHORT).show();
                break;
        case R.id.female:
            if (checked)
                Toast.makeText(getApplicationContext(),"female is selected",Toast.LENGTH_SHORT).show();
                break;
        case R.id.other:
            if (checked)
                Toast.makeText(getApplicationContext(),"Other is selected",Toast.LENGTH_SHORT).show();
                break;
    }
    switch(view.getId()) {
        case R.id.yes:
            if (checked)
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                break;
        case R.id.no:
            if (checked)
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
                break;

    }

}
    public PopupWindow popupWindow() {

        // initialize a pop up window type
        PopupWindow popupWindow = new PopupWindow(this);

        // the drop down list is a list view
        ListView listViewservices = new ListView(this);

        // set our adapter and pass our pop up window contents
        listViewservices.setAdapter(serviceAdapter(popUpContents));

        // set the item click listener
        listViewservices.setOnItemClickListener(new DropdownOnItemClickListener());

        // some other visual settings
        popupWindow.setFocusable(true);
        popupWindow.setWidth(370);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the list view as pop up window content
        popupWindow.setContentView(listViewservices);

        return popupWindow;
    }
    /*
         * adapter where the list values will be set
         */
    private ArrayAdapter<String> serviceAdapter(String serviceArray[]) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, serviceArray) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                // setting the ID and text for every items in the list
                String item = getItem(position);
                String[] itemArr = item.split("::");
                String text = itemArr[0];
                String id = itemArr[1];

                // visual settings for the list item
                TextView listItem = new TextView(Main_forms.this);

                listItem.setText(text);
                listItem.setTag(id);
                listItem.setTextSize(22);
                listItem.setPadding(10, 10, 10, 10);
                listItem.setTextColor(Color.WHITE);
                return listItem;
            }
        };

        return adapter;

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dobirth){
            // calender class's instance and get current date , month and year from calender
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            // date picker dialog
            // android.R.style.Theme_Holo_Light_Dialog
            datePickerDialog = new DatePickerDialog(Main_forms.this,DatePickerDialog.THEME_HOLO_LIGHT,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            // set day of month , month and year value in the edit text
                            dob.setText(dayOfMonth+"-"+(monthOfYear + 1)+"-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.setTitle("Select Date Of Birth");
            datePickerDialog.show();
        }
    }

    private class DropdownOnItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Context mContext = v.getContext();
            Main_forms mainActivity = ((Main_forms) mContext);

            // add some animation when a list item was clicked
            Animation fadeInAnimation = AnimationUtils.loadAnimation(v.getContext(), android.R.anim.fade_in);
            fadeInAnimation.setDuration(12);
            v.startAnimation(fadeInAnimation);

            // dismiss the pop up
            mainActivity.popupservices.dismiss();

            // get the text and set it as the button text
            String selectedItemText = ((TextView) v).getText().toString();
            mainActivity.buttonShowDropDown.setText(selectedItemText);

            // get the id
            String selectedItemTag = ((TextView) v).getTag().toString();
            Toast.makeText(mContext, "ThankYou: " + selectedItemTag, Toast.LENGTH_SHORT).show();
        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.form_optionmenu,menu);
        return true;
    }


}
