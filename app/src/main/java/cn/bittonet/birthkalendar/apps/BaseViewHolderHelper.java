package cn.bittonet.birthkalendar.apps;

import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.bittonet.birthkalendar.utils.ViewUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseViewHolder;


public class BaseViewHolderHelper extends BaseViewHolder {

    public BaseViewHolderHelper(View view) {
        super(view);
    }

    public BaseViewHolderHelper setHtmlText(@IdRes int viewId, @StringRes int strId, String value) {
        TextView view = getView(viewId);
        String htmlValue = String.format(view.getContext().getResources().getString(strId),
                                         value);
        view.setText(Html.fromHtml(htmlValue));
        return this;
    }

    /**
     * 给TextView添加删除线
     *
     * @param viewId
     * @return
     */
    public BaseViewHolderHelper setTextDeleteLine(@IdRes int viewId) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }

    /**
     * 给TextView添加下划线
     *
     * @param viewId
     * @return
     */
    public BaseViewHolderHelper setTextUnderLine(@IdRes int viewId) {
        TextView view = getView(viewId);
        view.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        return this;
    }

    /**
     * Set a view visibility to VISIBLE or GONE or INVISIBLE.
     *
     * @param viewId  The view id.
     * @param visible VISIBLE, GONE, or INVISIBLE.
     * @return The BaseViewHolder for chaining.
     */
    public BaseViewHolder setVisible(@IdRes int viewId, int visible) {
        View view = getView(viewId);
        view.setVisibility(visible);
        return this;
    }

    public BaseViewHolderHelper setImageUrl(@IdRes int viewId, String imageUrl) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().centerCrop()
                                                     .priority(Priority.HIGH)
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    public BaseViewHolderHelper setImageUrl(@IdRes int viewId, String imageUrl,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().centerCrop()
                                                     .placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    public BaseViewHolderHelper setImageUrl(@IdRes int viewId, String imageUrl, boolean isGif,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().centerCrop()
                                                     .placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        if (isGif) {
            Glide.with(imageview.getContext())
                 .asGif()
                 .load(imageUrl)
                 .apply(options)
                 .into(imageview);
        } else {
            Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        }
        return this;
    }

    public BaseViewHolderHelper setCircleImageUrl(@IdRes int viewId, String imageUrl,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .dontAnimate()
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    public BaseViewHolderHelper setAvatar(@IdRes int viewId, String imageUrl,
            @DrawableRes int defaultDrawable) {
        ImageView imageview = getView(viewId);
        RequestOptions options = new RequestOptions().placeholder(defaultDrawable)
                                                     .error(defaultDrawable)
                                                     .priority(Priority.HIGH)
                                                     .dontAnimate()
                                                     .diskCacheStrategy(DiskCacheStrategy.NONE);

        Glide.with(imageview.getContext()).load(imageUrl).apply(options).into(imageview);
        return this;
    }

    public BaseViewHolderHelper setColorFilter(@IdRes int viewId, @ColorInt int colorARGB) {
        ImageView view = getView(viewId);
        view.setColorFilter(colorARGB);
        return this;
    }

    public BaseViewHolderHelper setColorFilter(@IdRes int viewId, int r, int g, int b) {
        ImageView view = getView(viewId);
        view.setColorFilter(Color.argb(255, r, g, b));
        return this;
    }

    public BaseViewHolderHelper setColorFilter(@IdRes int viewId, int a, int r, int g, int b) {
        ImageView view = getView(viewId);
        view.setColorFilter(Color.argb(a, r, g, b));
        return this;
    }

    /**
     * 设置控件的高度
     *
     * @param viewId The view id.
     */
    public BaseViewHolderHelper setViewHeight(@IdRes int viewId, float type) {
        View view = getView(viewId);
        ViewUtils.setViewHeight(view.getContext(), view, type);
        return this;
    }

    /**
     * 设置控件的高度
     *
     * @param viewId The view id.
     */
    public BaseViewHolderHelper setViewHeight(@IdRes int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewHeight(view.getContext(), view, type, screen);
        return this;
    }

    /**
     * 设置控件的宽度
     *
     * @param viewId The view id.
     */
    public BaseViewHolderHelper setViewWidth(@IdRes int viewId, float type) {
        View view = getView(viewId);
        ViewUtils.setViewWidth(view.getContext(), view, type);
        return this;
    }

    /**
     * 设置控件的宽度
     *
     * @param viewId The view id.
     */
    public BaseViewHolderHelper setViewWidth(@IdRes int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewWidth(view.getContext(), view, type, screen);
        return this;
    }

    /**
     * 设置控件的大小
     *
     * @param viewId The view id.
     */
    public BaseViewHolderHelper setViewSize(@IdRes int viewId, float type) {
        View view = getView(viewId);
        ViewUtils.setViewSize(view.getContext(), view, type);
        return this;
    }

    /**
     * 设置控件的大小
     *
     * @param viewId The view id.
     */
    public BaseViewHolderHelper setViewSize(@IdRes int viewId, float type, float screen) {
        View view = getView(viewId);
        ViewUtils.setViewSize(view.getContext(), view, type, screen);
        return this;
    }

    /**
     * 设置控件的尺寸
     *
     * @param viewId The view id.
     * @param width  The view width.
     * @param height The view height.
     */
    public BaseViewHolderHelper setViewSize(@IdRes int viewId, int width, int height) {
        View view = getView(viewId);
        ViewUtils.setViewSize(view.getContext(), view, width, height);
        return this;
    }

    public BaseViewHolderHelper setEnabled(@IdRes int viewId, boolean enable) {
        View view = getView(viewId);
        view.setEnabled(enable);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsLeft(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(view.getContext(), drawableRes), null, null, null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsTop(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null,
                                                     ContextCompat.getDrawable(view.getContext(),
                                                                               drawableRes), null,
                                                     null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsRight(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null, null,
                                                     ContextCompat.getDrawable(view.getContext(),
                                                                               drawableRes), null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBoundsBottom(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null, null, null,
                                                     ContextCompat.getDrawable(view.getContext(),
                                                                               drawableRes));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBounds(@IdRes int viewId,
            @DrawableRes int drawableResLeft, @DrawableRes int drawableResTop,
            @DrawableRes int drawableResRight, @DrawableRes int drawableResBottom) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(view.getContext(), drawableResLeft),
                ContextCompat.getDrawable(view.getContext(), drawableResTop),
                ContextCompat.getDrawable(view.getContext(), drawableResRight),
                ContextCompat.getDrawable(view.getContext(), drawableResBottom));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesWithIntrinsicBounds(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(
                ContextCompat.getDrawable(view.getContext(), drawableRes),
                ContextCompat.getDrawable(view.getContext(), drawableRes),
                ContextCompat.getDrawable(view.getContext(), drawableRes),
                ContextCompat.getDrawable(view.getContext(), drawableRes));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesLeft(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(ContextCompat.getDrawable(view.getContext(), drawableRes), null,
                                  null, null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesTop(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(null, ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  null, null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesRight(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(null, null,
                                  ContextCompat.getDrawable(view.getContext(), drawableRes), null);
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawablesBottom(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(null, null, null,
                                  ContextCompat.getDrawable(view.getContext(), drawableRes));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawables(@IdRes int viewId,
            @DrawableRes int drawableResLeft, @DrawableRes int drawableResTop,
            @DrawableRes int drawableResRight, @DrawableRes int drawableResBottom) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(ContextCompat.getDrawable(view.getContext(), drawableResLeft),
                                  ContextCompat.getDrawable(view.getContext(), drawableResTop),
                                  ContextCompat.getDrawable(view.getContext(), drawableResRight),
                                  ContextCompat.getDrawable(view.getContext(), drawableResBottom));
        return this;
    }

    public BaseViewHolderHelper setCompoundDrawables(@IdRes int viewId,
            @DrawableRes int drawableRes) {
        TextView view = getView(viewId);
        view.setCompoundDrawables(ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  ContextCompat.getDrawable(view.getContext(), drawableRes),
                                  ContextCompat.getDrawable(view.getContext(), drawableRes));
        return this;
    }

    public BaseViewHolderHelper setLayoutManager(@IdRes int viewId, LinearLayoutManager manager) {
        RecyclerView recyclerview = getView(viewId);
        recyclerview.setLayoutManager(manager);
        return this;
    }

    public BaseViewHolderHelper setLayoutManager(@IdRes int viewId, GridLayoutManager manager) {
        RecyclerView recyclerview = getView(viewId);
        recyclerview.setLayoutManager(manager);
        return this;
    }
}
