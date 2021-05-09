package com.example.represencas_mobile.estudante_activities.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.represencas_mobile.R;
import com.example.represencas_mobile.all.adapters.MyPagerAdapter;
import com.example.represencas_mobile.all.adapters.ViewPagerSwipe;
import com.example.represencas_mobile.estudante_activities.fragments.EstPresencasArquivoFragment;
import com.example.represencas_mobile.estudante_activities.fragments.EstPresencasEstatisticasFragment;
import com.example.represencas_mobile.estudante_activities.fragments.EstPresencasNotificationsFragment;
import com.google.android.material.tabs.TabLayout;

/**
 * @author Imildo Sitoe
 * @see AppCompatActivity
 *
 * */
public class EstPresenca extends AppCompatActivity {

    public static TabLayout tabLayout;
    private static ViewPagerSwipe viewPager;
    private MyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est_presenca);
        setTitle("Represenças");
        tabLayout = findViewById(R.id.est_pres_tab_layout);
        viewPager = findViewById(R.id.est_pres_view_pager);

        this.addTabs();
    }

    private void addTabs() {
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EstPresencasNotificationsFragment(), "Notificações");
        pagerAdapter.addFragment(new EstPresencasEstatisticasFragment(), "Estatísticas");
        pagerAdapter.addFragment(new EstPresencasArquivoFragment(), "Arquivo");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setSwippingEnabled(true);

        //disableTab(0, false, 1, false);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setElevation(0);
    }

    public static void disableTab(int tabPosition, boolean isEnabled, int currentTab, boolean isSwippable) {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        ViewGroup vgTab = (ViewGroup) vg.getChildAt(tabPosition);
        vgTab.setEnabled(isEnabled);
        viewPager.setCurrentItem(currentTab);
        viewPager.setSwippingEnabled(isSwippable);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_ajuda) {
            return true;
        }
        if (id == R.id.action_sair) {
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
