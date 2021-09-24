package com.example.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

public class MyGlobal extends Application {
    public static int level=0;
    public static String reports;
    public static Set<String> reportContentList = new HashSet<>();
    public static List<Boolean> yesClicked = new ArrayList<>();
}
