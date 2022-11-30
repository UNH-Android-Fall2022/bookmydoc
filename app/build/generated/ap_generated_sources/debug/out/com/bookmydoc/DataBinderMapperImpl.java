package com.bookmydoc;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.bookmydoc.databinding.ActivityCategoryBindingImpl;
import com.bookmydoc.databinding.ActivityDashboardBindingImpl;
import com.bookmydoc.databinding.ActivityDocotrDetailBindingImpl;
import com.bookmydoc.databinding.ActivityDocotrLoginBindingImpl;
import com.bookmydoc.databinding.ActivityDoctorDashboardBindingImpl;
import com.bookmydoc.databinding.ActivityDoctorListBindingImpl;
import com.bookmydoc.databinding.ActivityLoginBindingImpl;
import com.bookmydoc.databinding.ActivityMyBookingBindingImpl;
import com.bookmydoc.databinding.ActivityRegisterBindingImpl;
import com.bookmydoc.databinding.ActivitySettingsBindingImpl;
import com.bookmydoc.databinding.ActivitySplashBindingImpl;
import com.bookmydoc.databinding.ActivitySuccessBindingImpl;
import com.bookmydoc.databinding.ActivityUserProfileBindingImpl;
import com.bookmydoc.databinding.ActivityWelcomeBindingImpl;
import com.bookmydoc.databinding.HcItemCalendarBindingImpl;
import com.bookmydoc.databinding.ViewBookingTimeBindingImpl;
import com.bookmydoc.databinding.ViewCategoryBindingImpl;
import com.bookmydoc.databinding.ViewDoctorBindingImpl;
import com.bookmydoc.databinding.ViewMyBookingBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYCATEGORY = 1;

  private static final int LAYOUT_ACTIVITYDASHBOARD = 2;

  private static final int LAYOUT_ACTIVITYDOCOTRDETAIL = 3;

  private static final int LAYOUT_ACTIVITYDOCOTRLOGIN = 4;

  private static final int LAYOUT_ACTIVITYDOCTORDASHBOARD = 5;

  private static final int LAYOUT_ACTIVITYDOCTORLIST = 6;

  private static final int LAYOUT_ACTIVITYLOGIN = 7;

  private static final int LAYOUT_ACTIVITYMYBOOKING = 8;

  private static final int LAYOUT_ACTIVITYREGISTER = 9;

  private static final int LAYOUT_ACTIVITYSETTINGS = 10;

  private static final int LAYOUT_ACTIVITYSPLASH = 11;

  private static final int LAYOUT_ACTIVITYSUCCESS = 12;

  private static final int LAYOUT_ACTIVITYUSERPROFILE = 13;

  private static final int LAYOUT_ACTIVITYWELCOME = 14;

  private static final int LAYOUT_HCITEMCALENDAR = 15;

  private static final int LAYOUT_VIEWBOOKINGTIME = 16;

  private static final int LAYOUT_VIEWCATEGORY = 17;

  private static final int LAYOUT_VIEWDOCTOR = 18;

  private static final int LAYOUT_VIEWMYBOOKING = 19;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(19);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_category, LAYOUT_ACTIVITYCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_dashboard, LAYOUT_ACTIVITYDASHBOARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_docotr_detail, LAYOUT_ACTIVITYDOCOTRDETAIL);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_docotr_login, LAYOUT_ACTIVITYDOCOTRLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_doctor_dashboard, LAYOUT_ACTIVITYDOCTORDASHBOARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_doctor_list, LAYOUT_ACTIVITYDOCTORLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_login, LAYOUT_ACTIVITYLOGIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_my_booking, LAYOUT_ACTIVITYMYBOOKING);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_register, LAYOUT_ACTIVITYREGISTER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_settings, LAYOUT_ACTIVITYSETTINGS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_splash, LAYOUT_ACTIVITYSPLASH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_success, LAYOUT_ACTIVITYSUCCESS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_user_profile, LAYOUT_ACTIVITYUSERPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.activity_welcome, LAYOUT_ACTIVITYWELCOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.hc_item_calendar, LAYOUT_HCITEMCALENDAR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.view_booking_time, LAYOUT_VIEWBOOKINGTIME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.view_category, LAYOUT_VIEWCATEGORY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.view_doctor, LAYOUT_VIEWDOCTOR);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.bookmydoc.R.layout.view_my_booking, LAYOUT_VIEWMYBOOKING);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYCATEGORY: {
          if ("layout/activity_category_0".equals(tag)) {
            return new ActivityCategoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_category is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYDASHBOARD: {
          if ("layout/activity_dashboard_0".equals(tag)) {
            return new ActivityDashboardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_dashboard is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYDOCOTRDETAIL: {
          if ("layout/activity_docotr_detail_0".equals(tag)) {
            return new ActivityDocotrDetailBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_docotr_detail is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYDOCOTRLOGIN: {
          if ("layout/activity_docotr_login_0".equals(tag)) {
            return new ActivityDocotrLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_docotr_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYDOCTORDASHBOARD: {
          if ("layout/activity_doctor_dashboard_0".equals(tag)) {
            return new ActivityDoctorDashboardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_doctor_dashboard is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYDOCTORLIST: {
          if ("layout/activity_doctor_list_0".equals(tag)) {
            return new ActivityDoctorListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_doctor_list is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYLOGIN: {
          if ("layout/activity_login_0".equals(tag)) {
            return new ActivityLoginBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_login is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMYBOOKING: {
          if ("layout/activity_my_booking_0".equals(tag)) {
            return new ActivityMyBookingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_my_booking is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYREGISTER: {
          if ("layout/activity_register_0".equals(tag)) {
            return new ActivityRegisterBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_register is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSETTINGS: {
          if ("layout/activity_settings_0".equals(tag)) {
            return new ActivitySettingsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_settings is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSPLASH: {
          if ("layout/activity_splash_0".equals(tag)) {
            return new ActivitySplashBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_splash is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYSUCCESS: {
          if ("layout/activity_success_0".equals(tag)) {
            return new ActivitySuccessBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_success is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYUSERPROFILE: {
          if ("layout/activity_user_profile_0".equals(tag)) {
            return new ActivityUserProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_user_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYWELCOME: {
          if ("layout/activity_welcome_0".equals(tag)) {
            return new ActivityWelcomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_welcome is invalid. Received: " + tag);
        }
        case  LAYOUT_HCITEMCALENDAR: {
          if ("layout/hc_item_calendar_0".equals(tag)) {
            return new HcItemCalendarBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for hc_item_calendar is invalid. Received: " + tag);
        }
        case  LAYOUT_VIEWBOOKINGTIME: {
          if ("layout/view_booking_time_0".equals(tag)) {
            return new ViewBookingTimeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for view_booking_time is invalid. Received: " + tag);
        }
        case  LAYOUT_VIEWCATEGORY: {
          if ("layout/view_category_0".equals(tag)) {
            return new ViewCategoryBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for view_category is invalid. Received: " + tag);
        }
        case  LAYOUT_VIEWDOCTOR: {
          if ("layout/view_doctor_0".equals(tag)) {
            return new ViewDoctorBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for view_doctor is invalid. Received: " + tag);
        }
        case  LAYOUT_VIEWMYBOOKING: {
          if ("layout/view_my_booking_0".equals(tag)) {
            return new ViewMyBookingBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for view_my_booking is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(19);

    static {
      sKeys.put("layout/activity_category_0", com.bookmydoc.R.layout.activity_category);
      sKeys.put("layout/activity_dashboard_0", com.bookmydoc.R.layout.activity_dashboard);
      sKeys.put("layout/activity_docotr_detail_0", com.bookmydoc.R.layout.activity_docotr_detail);
      sKeys.put("layout/activity_docotr_login_0", com.bookmydoc.R.layout.activity_docotr_login);
      sKeys.put("layout/activity_doctor_dashboard_0", com.bookmydoc.R.layout.activity_doctor_dashboard);
      sKeys.put("layout/activity_doctor_list_0", com.bookmydoc.R.layout.activity_doctor_list);
      sKeys.put("layout/activity_login_0", com.bookmydoc.R.layout.activity_login);
      sKeys.put("layout/activity_my_booking_0", com.bookmydoc.R.layout.activity_my_booking);
      sKeys.put("layout/activity_register_0", com.bookmydoc.R.layout.activity_register);
      sKeys.put("layout/activity_settings_0", com.bookmydoc.R.layout.activity_settings);
      sKeys.put("layout/activity_splash_0", com.bookmydoc.R.layout.activity_splash);
      sKeys.put("layout/activity_success_0", com.bookmydoc.R.layout.activity_success);
      sKeys.put("layout/activity_user_profile_0", com.bookmydoc.R.layout.activity_user_profile);
      sKeys.put("layout/activity_welcome_0", com.bookmydoc.R.layout.activity_welcome);
      sKeys.put("layout/hc_item_calendar_0", com.bookmydoc.R.layout.hc_item_calendar);
      sKeys.put("layout/view_booking_time_0", com.bookmydoc.R.layout.view_booking_time);
      sKeys.put("layout/view_category_0", com.bookmydoc.R.layout.view_category);
      sKeys.put("layout/view_doctor_0", com.bookmydoc.R.layout.view_doctor);
      sKeys.put("layout/view_my_booking_0", com.bookmydoc.R.layout.view_my_booking);
    }
  }
}
