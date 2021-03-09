package maa.covid_wear.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.blankj.utilcode.util.ConvertUtils;

import maa.covid_wear.R;


public class ImageTextButton extends LinearLayout {

    private Context context;

    private ImageView imageView;
    private TextView textView;

    private boolean showImageView;
    private boolean showTextView;
    private boolean selected;

    private int textSize;
    private int image_padding;

    private Drawable normalBackground;
    private Drawable normalImage;
    private String normalText;
    protected int normalTextColor;

    private Drawable selectedBackground;
    private Drawable selectedImage;
    private String selectedText;
    protected int selectedTextColor;

    public ImageTextButton(Context context) {
        super(context);
        init(context);
    }

    public ImageTextButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initWith(context, attrs);
    }

    public ImageTextButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initWith(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ImageTextButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        initWith(context, attrs);
    }


    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.button_image_text, this, true);
        imageView = findViewById(R.id.iv_image_itb);
        textView = findViewById(R.id.tv_text_itb);
    }

    private void initWith(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ImageTextButton, 0, 0);
        showImageView = typedArray.getBoolean(R.styleable.ImageTextButton_show_image_view, true);
        showTextView = typedArray.getBoolean(R.styleable.ImageTextButton_show_text_view, true);
        selected = typedArray.getBoolean(R.styleable.ImageTextButton_is_selected, false);
        int defaultTextSize = ConvertUtils.sp2px((float) 12);//12sp
        textSize = typedArray.getDimensionPixelSize(R.styleable.ImageTextButton_text_size, defaultTextSize);
        int defaultPadding = ConvertUtils.dp2px( 3);
        image_padding = typedArray.getDimensionPixelSize(R.styleable.ImageTextButton_image_padding, defaultPadding);
        normalBackground = typedArray.getDrawable(R.styleable.ImageTextButton_normal_background);
        normalImage = typedArray.getDrawable(R.styleable.ImageTextButton_normal_image);
        normalText = typedArray.getString(R.styleable.ImageTextButton_normal_text);
        normalTextColor = typedArray.getColor(R.styleable.ImageTextButton_normal_text_color, Color.parseColor("#555555"));
        selectedBackground = typedArray.getDrawable(R.styleable.ImageTextButton_selected_background);
        selectedImage = typedArray.getDrawable(R.styleable.ImageTextButton_selected_image);
        selectedText = typedArray.getString(R.styleable.ImageTextButton_selected_text);
        selectedTextColor = typedArray.getColor(R.styleable.ImageTextButton_selected_text_color, Color.parseColor("#FFFFFF"));
        typedArray.recycle();
        int sp = ConvertUtils.px2sp(textSize);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        imageView.setPadding(image_padding, image_padding, image_padding, image_padding);
        setShowTextView(showTextView);
        setShowImageView(showImageView);
        setSelected(selected);

        post(new Runnable() {
            @Override
            public void run() {
                adjustTvTextSize(textView, textView.getWidth(), normalText);
            }
        });

    }

    public void setShowImageView(boolean showImageView) {
        this.showImageView = showImageView;
        if (showImageView) {
            imageView.setVisibility(VISIBLE);
        } else {
            imageView.setVisibility(GONE);
        }
    }


    public boolean isShowImageView() {
        return showImageView;
    }

    public void setShowTextView(boolean showTextView) {
        this.showTextView = showTextView;
        if (showTextView) {
            textView.setVisibility(VISIBLE);
        } else {
            textView.setVisibility(GONE);
        }
    }

    public boolean isShowTextView() {
        return showTextView;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if (selected) {
            this.setBackground(selectedBackground);
            imageView.setImageDrawable(selectedImage);
            textView.setText(selectedText);
            textView.setTextColor(selectedTextColor);
            textView.setTypeface(null, Typeface.BOLD);
            adjustTvTextSize(textView, textView.getWidth(), selectedText);
        } else {
            this.setBackground(normalBackground);
            imageView.setImageDrawable(normalImage);
            textView.setText(normalText);
            textView.setTextColor(normalTextColor);
            textView.setTypeface(null, Typeface.NORMAL);
            adjustTvTextSize(textView, textView.getWidth(), normalText);
        }
        //Typeface typeface = Typeface.createFromAsset(this.getContext().getAssets(), "fonts/LEMONMILK-RegularItalic.otf");
       // textView.setTypeface(typeface);
    }


    public boolean isSelected() {
        return selected;
    }

    private void adjustTvTextSize(TextView tv, int maxWidth, String text) {

    }

    public void setText(String str) {
        textView.setText(str);
    }
}