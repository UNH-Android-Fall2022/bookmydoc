package com.bookmydoc.databinding;
import com.bookmydoc.R;
import com.bookmydoc.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityDashboardBindingImpl extends ActivityDashboardBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.rlToolbar, 1);
        sViewsWithIds.put(R.id.linUserName, 2);
        sViewsWithIds.put(R.id.txtUserName, 3);
        sViewsWithIds.put(R.id.rlProfile, 4);
        sViewsWithIds.put(R.id.imgProfile, 5);
        sViewsWithIds.put(R.id.rlSearch, 6);
        sViewsWithIds.put(R.id.linPopular, 7);
        sViewsWithIds.put(R.id.txtDrName, 8);
        sViewsWithIds.put(R.id.txtCategory, 9);
        sViewsWithIds.put(R.id.linCategory, 10);
        sViewsWithIds.put(R.id.txtViewmore, 11);
        sViewsWithIds.put(R.id.cardDental, 12);
        sViewsWithIds.put(R.id.cardBrain, 13);
        sViewsWithIds.put(R.id.cardEye, 14);
        sViewsWithIds.put(R.id.rvMorningSlot, 15);
        sViewsWithIds.put(R.id.txtDrViewMore, 16);
        sViewsWithIds.put(R.id.rvDoctors, 17);
        sViewsWithIds.put(R.id.cardTopDoctor, 18);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityDashboardBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }
    private ActivityDashboardBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (com.google.android.material.card.MaterialCardView) bindings[13]
            , (com.google.android.material.card.MaterialCardView) bindings[12]
            , (com.google.android.material.card.MaterialCardView) bindings[14]
            , (com.google.android.material.card.MaterialCardView) bindings[18]
            , (de.hdodenhof.circleimageview.CircleImageView) bindings[5]
            , (android.widget.LinearLayout) bindings[10]
            , (androidx.appcompat.widget.LinearLayoutCompat) bindings[7]
            , (androidx.appcompat.widget.LinearLayoutCompat) bindings[2]
            , (android.widget.RelativeLayout) bindings[4]
            , (android.widget.RelativeLayout) bindings[6]
            , (android.widget.RelativeLayout) bindings[1]
            , (androidx.recyclerview.widget.RecyclerView) bindings[17]
            , (androidx.recyclerview.widget.RecyclerView) bindings[15]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[9]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[8]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[16]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[3]
            , (androidx.appcompat.widget.AppCompatTextView) bindings[11]
            );
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}