// Generated by data binding compiler. Do not edit!
package com.bookmydoc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.bookmydoc.R;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivitySuccessBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatButton btnHome;

  @NonNull
  public final AppCompatTextView txtDrName;

  protected ActivitySuccessBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppCompatButton btnHome, AppCompatTextView txtDrName) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnHome = btnHome;
    this.txtDrName = txtDrName;
  }

  @NonNull
  public static ActivitySuccessBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_success, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySuccessBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivitySuccessBinding>inflateInternal(inflater, R.layout.activity_success, root, attachToRoot, component);
  }

  @NonNull
  public static ActivitySuccessBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_success, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivitySuccessBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivitySuccessBinding>inflateInternal(inflater, R.layout.activity_success, null, false, component);
  }

  public static ActivitySuccessBinding bind(@NonNull View view) {
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
  public static ActivitySuccessBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivitySuccessBinding)bind(component, view, R.layout.activity_success);
  }
}
