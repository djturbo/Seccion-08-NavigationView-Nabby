package navity.fjarquellada.es.navity.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import navity.fjarquellada.es.navity.R;
import navity.fjarquellada.es.navity.fragment.AlertFragment;
import navity.fjarquellada.es.navity.fragment.EmailFragment;
import navity.fjarquellada.es.navity.fragment.InfoFragment;

public class MainActivity extends AppCompatActivity implements DrawerLayout.DrawerListener,
                                                               NavigationView.OnNavigationItemSelectedListener,
                                                               CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    Switch switchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        configureActionBar();

        this.drawerLayout.addDrawerListener(this);


        this.navigationView.setNavigationItemSelectedListener(this);

        setFragmentByDefault();

        switchBtn = (Switch) navigationView.getMenu().findItem(R.id.switch_in_nav_options).getActionView();
        switchBtn.setOnCheckedChangeListener(this);
    }

    private void configureActionBar() {
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setFragmentByDefault(){
        MenuItem item = this.navigationView.getMenu().getItem(0);
        changeFragment(new EmailFragment(), item);
    }

    private void changeFragment(Fragment fragment, MenuItem item){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        item.setChecked(true);
        getSupportActionBar().setTitle(item.getTitle());
        drawerLayout.closeDrawers();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        boolean res = true;

        switch(item.getItemId()){
            case android.R.id.home:
                this.drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                res = super.onOptionsItemSelected(item);

        }
        return res;

    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        if (checked) {
            Toast.makeText(MainActivity.this, "The option is checked", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "The option is unchecked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        boolean fragmentTransaction = false;
        Fragment fragment = null;

        switch(item.getItemId()){
            case R.id.item_mail:
                fragment = new EmailFragment();
                fragmentTransaction = true;
                break;
            case R.id.item_alert:
                fragment = new AlertFragment();
                fragmentTransaction = true;
                break;
            case R.id.item_info:
                fragment = new InfoFragment();
                fragmentTransaction = true;
                break;
            case R.id.item_opcion_01:
                Toast.makeText(MainActivity.this, "Pulsada la opción 01", Toast.LENGTH_LONG).show();
                break;
            case R.id.item_opcion_02:
                Toast.makeText(MainActivity.this, "Pulsada la opción 02", Toast.LENGTH_LONG).show();
                break;
        }

        if(fragmentTransaction){
            changeFragment(fragment, item);
        }

        return true;
    }


    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        Toast.makeText(MainActivity.this, "onDrawerOpened", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        Toast.makeText(MainActivity.this, "onDrawerClosed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
