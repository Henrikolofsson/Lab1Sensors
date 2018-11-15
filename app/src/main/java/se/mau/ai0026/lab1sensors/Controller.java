package se.mau.ai0026.lab1sensors;

import android.support.v4.app.Fragment;
import android.util.Log;

import Fragments.DataFragment;
import Fragments.StartFragment;

public class Controller {
    private MainActivity mainActivity;
    private DataFragment dataFragment;
    private StartFragment startFragment;

    public Controller(MainActivity activity){
        this.mainActivity = activity;
        initializeFragments();
        setFragment("StartFragment");
    }

    private void initializeFragments(){
        initializeDataFragment();
        initializeStartFragment();
    }

    private void initializeDataFragment(){
        dataFragment = (DataFragment) mainActivity.getFragment("DataFragment");
        if(dataFragment == null){
            dataFragment = new DataFragment();
            mainActivity.addFragment(dataFragment, "DataFragment");
            dataFragment.setActiveFragment("StartFragment");
        }
        dataFragment.setController(this);
    }

    private void initializeStartFragment(){
        startFragment = (StartFragment) mainActivity.getFragment("StartFragment");
        if(startFragment == null){
            startFragment = new StartFragment();
        }
        startFragment.setController(this);
    }

    public boolean onBackPressed() {
        String activeFragment = dataFragment.getActiveFragment();

        if(activeFragment.equals("StartFragment")){
            return false;
        }

        switch(activeFragment){

        }
        return false;

    }

    private void setFragment(String tag){
        switch(tag){
            case "StartFragment":
                setFragment(startFragment, tag);
                break;
        }
    }

    private void setFragment(Fragment fragment, String tag){
        mainActivity.setFragment(fragment, tag);
        dataFragment.setActiveFragment(tag);
    }

    public void displayValues(long timestamp, int accuracy, float[] values){
        if(dataFragment.getActiveFragment().equals("StartFragment")){
            startFragment.setTexts(timestamp,accuracy,values);
        }
    }

    public void unregister(){
        mainActivity.unregister();
    }



}
