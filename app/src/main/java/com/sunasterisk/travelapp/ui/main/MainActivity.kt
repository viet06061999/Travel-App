package com.sunasterisk.travelapp.ui.main

import android.content.Context
import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.ui.MyBooking.MyBookingFragment
import com.sunasterisk.travelapp.ui.base.BaseActivity
import com.sunasterisk.travelapp.ui.home.HomeFragment
import com.sunasterisk.travelapp.ui.myreservation.ReservationFragment
import com.sunasterisk.travelapp.ui.savetrips.SavedTripFragment
import com.sunasterisk.travelapp.utils.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_home_view.*

class MainActivity :
    BaseActivity(),
    HomeFragment.OnTabsSetupListener,
    NavigationView.OnNavigationItemSelectedListener {

    override val layoutResource get() = R.layout.activity_main

    override fun onAttachFragment(fragment: Fragment) {
        if (fragment is HomeFragment) {
            fragment.setTabsCallbackListener(this)
        }
    }

    override fun initComponents() {
        replaceFragmentInActivity(HomeFragment(), R.id.frameMain)
        initToolbar()
        initNavigationMenu()
        navTop.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemHome -> {
                startActivity(getIntent(this))
            }
            R.id.itemSaveTrip -> {
                toolbar.removeView(tabsHome)
                title = SavedTripFragment.TITLE
                replaceFragmentInActivity(SavedTripFragment(), R.id.frameMain)
            }
            R.id.itemReservation -> {
                toolbar.removeView(tabsHome)
                title = ReservationFragment.TITLE
                replaceFragmentInActivity(ReservationFragment(), R.id.frameMain)
            }
            R.id.itemBooking -> {
                toolbar.removeView(tabsHome)
                title = MyBookingFragment.TITLE
                replaceFragmentInActivity(MyBookingFragment(), R.id.frameMain)
            }
        }
        drawerMain.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSetup(viewPager: ViewPager) {
        tabsHome.setupWithViewPager(viewPager)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    private fun initNavigationMenu() {
        ActionBarDrawerToggle(
            this,
            drawerMain,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ).apply {
            syncState()
        }
        drawerMain.closeDrawer(GravityCompat.START)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
}
