// Generated by data binding compiler. Do not edit!
package com.bookmydoc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.bookmydoc.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityDocotrLoginBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatButton btnLogin;

  @NonNull
  public final TextInputLayout ipEmail;

  @NonNull
  public final AppCompatImageView ivProfile;

  @NonNull
  public final TextInputEditText mEdtEmail;

  protected ActivityDocotrLoginBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppCompatButton btnLogin, TextInputLayout ipEmail, AppCompatImageView ivProfile,
      TextInputEditText mEdtEmail) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnLogin = btnLogin;
    this.ipEmail = ipEmail;
    this.ivProfile = ivProfile;
    this.mEdtEmail = mEdtEmail;
  }

  @NonNull
  public static ActivityDocotrLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_docotr_login, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityDocotrLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityDocotrLoginBinding>inflateInternal(inflater, R.layout.activity_docotr_login, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityDocotrLoginBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_docotr_login, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityDocotrLoginBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityDocotrLoginBinding>inflateInternal(inflater, R.layout.activity_docotr_login, null, false, component);
  }

  public static ActivityDocotrLoginBinding bind(@NonNull View view) {
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
  public static ActivityDocotrLoginBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityDocotrLoginBinding)bind(component, view, R.layout.activity_docotr_login);
  }
}
