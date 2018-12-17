package com.rairmmd.citypicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.rairmmd.citypicker.adapter.CityAdapter;
import com.rairmmd.citypicker.bean.CityInfoBean;
import com.rairmmd.citypicker.bean.SortModel;
import com.rairmmd.citypicker.utils.CharacterParser;
import com.rairmmd.citypicker.utils.PinyinComparator;
import com.rairmmd.citypicker.widget.SideBar;

import java.util.ArrayList;
import java.util.Collections;

public class CitySelectActivity extends AppCompatActivity {

    private EditText mEtCitySearch;
    private ListView mLvCityList;
    private TextView mTvTips;
    private SideBar mSidebar;
    public CityAdapter adapter;
    private CharacterParser characterParser;
    private ArrayList<SortModel> sourceDateList;
    private PinyinComparator pinyinComparator;
    private ArrayList<CityInfoBean> cityListInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);
        setToolbar();
        mEtCitySearch = findViewById(R.id.et_city_search);
        mLvCityList = findViewById(R.id.lv_city_list);
        mTvTips = findViewById(R.id.tv_tips);
        mSidebar = findViewById(R.id.sidebar);
        initCityList();
        initData();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initCityList() {
        sourceDateList = new ArrayList<>();
        adapter = new CityAdapter(this, sourceDateList);
        mLvCityList.setAdapter(adapter);
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        mSidebar.setTextView(mTvTips);
        mSidebar.setOnLetterChangedListener(new SideBar.OnLetterChangedListener() {

            @Override
            public void onLetterChanged(String s) {
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    mLvCityList.setSelection(position);
                }
            }
        });

        mLvCityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityName = ((SortModel) adapter.getItem(position)).getName();
                CityInfoBean cityInfoBean = CityInfoBean.findCity(cityListInfo, cityName);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.CITY_INFO, cityInfoBean);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        mEtCitySearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterData(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void initData() {
        cityListInfo = new ArrayList<>();
        cityListInfo = CityPicker.getInstance().getCityListData();
        if (cityListInfo == null) {
            return;
        }
        int count = cityListInfo.size();
        String[] list = new String[count];
        for (int i = 0; i < count; i++) {
            list[i] = cityListInfo.get(i).getName();
        }
        sourceDateList.addAll(fillListData(cityListInfo));
        // 根据a-z进行排序源数据
        Collections.sort(sourceDateList, pinyinComparator);
        adapter.notifyDataSetChanged();
    }

    /**
     * 为ListView填充数据
     */
    private ArrayList<SortModel> fillListData(ArrayList<CityInfoBean> cityList) {
        ArrayList<SortModel> mSortList = new ArrayList<>();
        for (int i = 0; i < cityList.size(); i++) {
            CityInfoBean result = cityList.get(i);
            if (result != null) {
                SortModel sortModel = new SortModel();
                String cityName = result.getName();
                // 汉字转换成拼音
                String pinyin = result.getPinYin();
                if (!TextUtils.isEmpty(cityName) && !TextUtils.isEmpty(pinyin)) {
                    sortModel.setName(cityName);
                    String sortString = pinyin.substring(0, 1).toUpperCase();
                    // 正则表达式，判断首字母是否是英文字母
                    if (sortString.matches("[A-Z]")) {
                        sortModel.setSortLetters(sortString.toUpperCase());
                    } else {
                        sortModel.setSortLetters("#");
                    }
                    mSortList.add(sortModel);
                }
            }
        }
        return mSortList;
    }

    /**
     * 根据输入框中的值来过滤数据
     *
     * @param filterStr 过滤的值
     */
    private void filterData(String filterStr) {
        ArrayList<SortModel> filterDateList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = sourceDateList;
        } else {
            filterDateList.clear();
            for (SortModel sortModel : sourceDateList) {
                String name = sortModel.getName();
                if (name.contains(filterStr) || characterParser.getSelling(name).startsWith(filterStr)) {
                    filterDateList.add(sortModel);
                }
            }
        }
        // 排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }
}
