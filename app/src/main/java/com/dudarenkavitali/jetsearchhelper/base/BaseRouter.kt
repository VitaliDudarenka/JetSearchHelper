package com.dudarenkavitali.jetsearchhelper.base

import android.widget.Toast
import androidx.fragment.app.FragmentManager

abstract class BaseRouter <A : BaseActivity>(val activity: A) {

    fun goBack() {
        activity.onBackPressed()
    }

    fun showError(e: Throwable) {
        Toast.makeText(activity, "Error " + e.toString(), Toast.LENGTH_SHORT).show()
    }

    fun replaceFragment(fragmentManager: FragmentManager, fragment: BaseFragment, containerResId: Int, addToBackStack: Boolean = false) {
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(containerResId, fragment, fragment::class.java.canonicalName)
        if (addToBackStack) {
            fragmentTransition.addToBackStack(null)
        }
        fragmentTransition.commit()
    }
}