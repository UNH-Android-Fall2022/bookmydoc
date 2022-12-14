// Generated by data binding compiler. Do not edit!
package com.bookmydoc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.bookmydoc.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityDoctorDashboardBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayoutCompat linLogout;

  @NonNull
  public final LinearLayoutCompat linUserName;

  @NonNull
  public final RelativeLayout rlToolbar;

  @NonNull
  public final RecyclerView rvDoctors;

  @NonNull
  public final AppCompatTextView txtUserName;

  protected ActivityDoctorDashboardBinding(Object _bindingComponent, View _root,
      int _localFieldCount, LinearLayoutCompat linLogout, LinearLayoutCompat linUserName,
      RelativeLayout rlToolbar, RecyclerView rvDoctors, AppCompatTextView txtUserName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.linLogout = linLogout;
    this.linUserName = linUserName;
    this.rlToolbar = rlToolbar;
    this.rvDoctors = rvDoctors;
    this.txtUserName = txtUserName;
  }

  @NonNull
  public static ActivityDoctorDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_doctor_dashboard, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityDoctorDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityDoctorDashboardBinding>inflateInternal(inflater, R.layout.activity_doctor_dashboard, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityDoctorDashboardBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_doctor_dashboard, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityDoctorDashboardBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityDoctorDashboardBinding>inflateInternal(inflater, R.layout.activity_doctor_dashboard, null, false, component);
  }

  public static ActivityDoctorDashboardBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityDoctorDashboardBinding bind(@NonNull View view,
      @Nullable Object component) {
    return (ActivityDoctorDashboardBinding)bind(component, view, R.layout.activity_doctor_dashboard);
  }
}
