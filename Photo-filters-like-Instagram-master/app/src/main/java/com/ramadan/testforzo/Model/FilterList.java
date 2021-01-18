package com.ramadan.testforzo.Model;

import com.ramadan.testforzo.Controller.ApplyFilterController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahmoud Ramadan on 8/11/16.
 */
public class FilterList {

    public List<String> names = new ArrayList<String>();
    public List<ApplyFilterController.FilterType> types = new ArrayList<ApplyFilterController.FilterType>();

    public void addFilter(String name, ApplyFilterController.FilterType type) {
        names.add(name);
        types.add(type);
    }

}
