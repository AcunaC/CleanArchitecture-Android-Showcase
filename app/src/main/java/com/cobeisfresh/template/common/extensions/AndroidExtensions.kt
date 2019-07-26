package com.cobeisfresh.template.common.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

inline fun <T> LiveData<T>.subscribe(owner: LifecycleOwner, crossinline onDataReceived: (T) -> Unit) =
    observe(owner, Observer { onDataReceived(it) })

fun snackbar(@StringRes message: Int, rootView: View) = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()

fun snackbar(message: String, rootView: View) = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()

fun View.visible() {
  visibility = View.VISIBLE
}

fun View.gone() {
  visibility = View.GONE
}

inline fun View.onClick(crossinline onClick: () -> Unit) {
  setOnClickListener { onClick() }
}

fun FragmentActivity.showFragment(fragment: Fragment, @IdRes container: Int, addToBackStack: Boolean = false) {
  supportFragmentManager.beginTransaction()
      .apply {
        if (addToBackStack) {
          addToBackStack(fragment.tag)
        }
      }
      .replace(container, fragment)
      .commitAllowingStateLoss()
}