//package com.example.www.mm;
//
//import android.widget.Filter;
//
//import java.util.ArrayList;
//
//public class searchFilter extends Filter {
//
//    profilsAdapter Adapter;
//    ArrayList<String[]> FilteredList;
//
//    public searchFilter(profilsAdapter adapter, ArrayList<String[]> data) {
//        Adapter = adapter;
//        FilteredList = data;
//    }
//
//    @Override
//    protected FilterResults performFiltering(CharSequence constraint) {
//        FilterResults filterResults = new FilterResults();
//        if (constraint != null && constraint.length() > 0){
//            constraint = constraint.toString().toUpperCase();
//            ArrayList<String[]> FilteredData = new ArrayList<>();
//            for (int i = 0; i < FilteredList.size(); i++) {
//                if (FilteredList.get(i).getName().toUppercase())
//            }
//        }
//        return null;
//    }
//
//    @Override
//    protected void publishResults(CharSequence constraint, FilterResults results) {
//
//    }
//}
