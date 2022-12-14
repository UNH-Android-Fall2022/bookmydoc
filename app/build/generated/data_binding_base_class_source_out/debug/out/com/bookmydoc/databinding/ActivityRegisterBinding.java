// Generated by data binding compiler. Do not edit!
package com.bookmydoc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.bookmydoc.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityRegisterBinding extends ViewDataBinding {
  @NonNull
  public final AppCompatButton btnSignup;

  @NonNull
  public final AppCompatCheckBox cbTermsAndCondition;

  @NonNull
  public final CountryCodePicker ccp1;

  @NonNull
  public final TextInputLayout ipConfirmPassword;

  @NonNull
  public final TextInputLayout ipEmail;

  @NonNull
  public final TextInputLayout ipFullName;

  @NonNull
  public final TextInputLayout ipMobile;

  @NonNull
  public final TextInputLayout ipPassword;

  @NonNull
  public final AppCompatImageView ivProfile;

  @NonNull
  public final TextInputEditText mEdtConfirmPassword;

  @NonNull
  public final TextInputEditText mEdtEmail;

  @NonNull
  public final TextInputEditText mEdtFullName;

  @NonNull
  public final TextInputEditText mEdtMobile;

  @NonNull
  public final TextInputEditText mEdtPassword;

  @NonNull
  public final AppCompatTextView txtLogin;

  protected ActivityRegisterBinding(Object _bindingComponent, View _root, int _localFieldCount,
      AppCompatButton btnSignup, AppCompatCheckBox cbTermsAndCondition, CountryCodePicker ccp1,
      TextInputLayout ipConfirmPassword, TextInputLayout ipEmail, TextInputLayout ipFullName,
      TextInputLayout ipMobile, TextInputLayout ipPassword, AppCompatImageView ivProfile,
      TextInputEditText mEdtConfirmPassword, TextInputEditText mEdtEmail,
      TextInputEditText mEdtFullName, TextInputEditText mEdtMobile, TextInputEditText mEdtPassword,
      AppCompatTextView txtLogin) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnSignup = btnSignup;
    this.cbTermsAndCondition = cbTermsAndCondition;
    this.ccp1 = ccp1;
    this.ipConfirmPassword = ipConfirmPassword;
    this.ipEmail = ipEmail;
    this.ipFullName = ipFullName;
    this.ipMobile = ipMobile;
    this.ipPassword = ipPassword;
    this.ivProfile = ivProfile;
    this.mEdtConfirmPassword = mEdtConfirmPassword;
    this.mEdtEmail = mEdtEmail;
    this.mEdtFullName = mEdtFullName;
    this.mEdtMobile = mEdtMobile;
    this.mEdtPassword = mEdtPassword;
    this.txtLogin = txtLogin;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_register, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityRegisterBinding>inflateInternal(inflater, R.layout.activity_register, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_register, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityRegisterBinding>inflateInternal(inflater, R.layout.activity_register, null, false, component);
  }

  public static ActivityRegisterBinding bind(@NonNull View view) {
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
  public static ActivityRegisterBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityRegisterBinding)bind(component, view, R.layout.activity_register);
  }
}
