package android.view;

import android.graphics.drawable.Drawable;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityEventSource;

import androidx.annotation.NonNull;

public class View implements Drawable.Callback, KeyEvent.Callback, AccessibilityEventSource {

    protected final InputEventConsistencyVerifier mInputEventConsistencyVerifier = null;
    ListenerInfo mListenerInfo;
    int mViewFlags;
    AttachInfo mAttachInfo;

    public boolean dispatchKeyEvent(KeyEvent event) {
        if ((event.getKeyCode() == KeyEvent.KEYCODE_PAGE_UP ||   // LEFT
                event.getKeyCode() == KeyEvent.KEYCODE_PAGE_DOWN)  // RIGHT
                && event.getRepeatCount() > 0) {
            return true;
        }

        if (this.mInputEventConsistencyVerifier != null) {
            this.mInputEventConsistencyVerifier.onKeyEvent(event, 0);
        }
        ListenerInfo li = this.mListenerInfo;
        if (li != null && li.mOnKeyListener != null && (this.mViewFlags & 32) == 0 && li.mOnKeyListener.onKey(this, event.getKeyCode(), event)) {
            return true;
        }
        if (event.dispatch(this, this.mAttachInfo != null ? this.mAttachInfo.mKeyDispatchState : null, this)) {
            return true;
        }
        if (this.mInputEventConsistencyVerifier != null) {
            this.mInputEventConsistencyVerifier.onUnhandledEvent(event, 0);
        }
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        ListenerInfo li;
        if (this.mInputEventConsistencyVerifier != null) {
            this.mInputEventConsistencyVerifier.onTouchEvent(event, 0);
        }
        if (onFilterTouchEventForSecurity(event) && (((li = this.mListenerInfo) != null && li.mOnTouchListener != null && (this.mViewFlags & 32) == 0 && li.mOnTouchListener.onTouch(this, event)) || onTouchEvent(event))) {
            return true;
        }
        if (this.mInputEventConsistencyVerifier != null) {
            this.mInputEventConsistencyVerifier.onUnhandledEvent(event, 0);
        }
        return false;
    }

    public boolean onFilterTouchEventForSecurity(MotionEvent event) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public static class ListenerInfo {
        private OnTouchListener mOnTouchListener;
        private OnKeyListener mOnKeyListener;

        ListenerInfo() {
        }
    }

    /* access modifiers changed from: package-private */
    public static class AttachInfo {
        final KeyEvent.DispatcherState mKeyDispatchState = new KeyEvent.DispatcherState();
    }

    public interface OnTouchListener {
        boolean onTouch(View view, MotionEvent motionEvent);
    }

    public interface OnKeyListener {
        boolean onKey(View view, int i, KeyEvent keyEvent);
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public interface OnCreateContextMenuListener {
        void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo);
    }

    public KeyEvent.DispatcherState getKeyDispatcherState() {
        return null;
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable drawable) {

    }

    @Override
    public void scheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable, long l) {

    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable drawable, @NonNull Runnable runnable) {

    }

    @Override
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean onKeyMultiple(int i, int i1, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public void sendAccessibilityEvent(int i) {

    }

    @Override
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {

    }

}
