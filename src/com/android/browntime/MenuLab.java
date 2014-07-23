package com.android.browntime;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuLab {
	
	private ArrayList<BrownMenu> mMenus;
	private static MenuLab sBrownLab;
	private Context mAppContext;
	
	private MenuLab(Context appContext) {
		mAppContext = appContext;
		mMenus = new ArrayList<BrownMenu>();


        new HttpRequestTask().execute();


//		mMenus.add(new BrownMenu(R.string.espresso, 3000, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.americano, 3800, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.latte_cafe, 4300, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
////		mMenus.add(new BrownMenu(R.string.latte_cube, 00, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.capucino, 4300, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
////		mMenus.add(new BrownMenu(R.string.caffe_latte, 5000, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.caffe_moka, 5000, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.latte_caramel, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.latte_vanilla, 5000, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.con_panna, 3500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.vienna, 4500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//
//		mMenus.add(new BrownMenu(R.string.shakerato, 4000, 2, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.iced_americano, 4300, 2, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.iced_latte_cafe, 4800, 2, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.iced_cappucino, 4800, 2, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.iced_caffe_moka, 5500, 2, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.iced_latte_caramel, 5500, 2, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.iced_latte_vanilla, 5500, 2, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.dutch_coffe, 5000, 2, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.dutch_latte, 5500, 2, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.affogato, 6000, 2, "this is a description for coffee this is a description for coffee this is a description for coffee"));
//
//		mMenus.add(new BrownMenu(R.string.latte_chocolate, 5000, 3, "this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.latte_yam, 5000, 3, "this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.latte_greentea, 5000, 3, "this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.latte_grain, 5000, 3, "this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.latte_chai, 5000, 3, "this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.tea_milk, 5000, 3, "this is a description for coffee"));
//		mMenus.add(new BrownMenu(R.string.korean_roll, 5000, 5));
//		mMenus.add(new BrownMenu(R.string.korean_roll_2, 5500, 3));
//		mMenus.add(new BrownMenu(R.string.pizza_1, 5500, 3));
//		mMenus.add(new BrownMenu(R.string.pizza_2, 5500, 3));
//		mMenus.add(new BrownMenu(R.string.sandwich_1, 5500, 3));
//		mMenus.add(new BrownMenu(R.string.sandwich_2, 5500, 3));
//		

	}

	public static MenuLab get(Context c) {
		if (sBrownLab == null) {
			sBrownLab = new MenuLab(c.getApplicationContext());
		}
		return sBrownLab;
	}
	
	public ArrayList<BrownMenu> getMenus() {
		return mMenus;
	}
	
	public ArrayList<BrownMenu> getMenus(int type) {
		ArrayList<BrownMenu> menuByType = new ArrayList<BrownMenu>();
		for (BrownMenu menus : mMenus) {
			if (menus.getmCategory() == type) {
				menuByType.add(menus);
			}
		}
		return menuByType;
	}
	
	public BrownMenu getMenu(int id) {
		for (BrownMenu c: mMenus) {
			if (c.getmId()==id)
				return c;
		}
		return null;
	}

    private class HttpRequestTask extends AsyncTask<Void, Void, List<BrownMenu>> {
        @Override
        protected List<BrownMenu> doInBackground(Void... params) {
            try {

                final String url = "http://10.0.2.2:8080/BrownTime/json/getMenus/1";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(new JSONRequest().getMessageConverters());

                BrownMenu[] menus = restTemplate.getForObject(url, BrownMenu[].class);
                return Arrays.asList(menus);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<BrownMenu> menus) {
            mMenus.addAll(menus);
        }
    }
}
