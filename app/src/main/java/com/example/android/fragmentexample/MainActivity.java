/*
 * Copyright (C) 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.fragmentexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private static final String STATE_FRAGMENT = "State_of_fragment";

    private Button button;
    private boolean fragmentDisplayed = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!=null) {
            fragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);

            if (fragmentDisplayed) {
                button.setText(R.string.close);
            }
        }

        button = findViewById(R.id.open_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!fragmentDisplayed) {
                    displayFragment();;
                } else {
                    closeFragment();
                }
            }
        });



    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(STATE_FRAGMENT, fragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }


    public void displayFragment() {
        SimpleFragment simpleFragment = SimpleFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, simpleFragment).addToBackStack(null);

        button.setText(R.string.close);

        fragmentDisplayed = true;

    }

    public void closeFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment simpleFragment =  fragmentManager.findFragmentById(R.id.fragment_container);

        if (simpleFragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.remove(simpleFragment).commit();

            button.setText(R.string.open);

            fragmentDisplayed = false;

        }


    }

}
