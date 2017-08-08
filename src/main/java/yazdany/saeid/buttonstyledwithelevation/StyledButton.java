package yazdany.saeid.buttonstyledwithelevation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class StyledButton extends android.support.v7.widget.AppCompatTextView {

  private Paint paint;
  private float DEFAULT_ELEVATION = 2f;
  private float ON_TOUCHED_ELEVATION = DEFAULT_ELEVATION * 4;
  private float SHADOW_BOUNDS = ON_TOUCHED_ELEVATION * 2;
  private float ON_TOUCH_DOWN_VALUE;
  private float ON_TOUCH_UP_VALUE;
  private int WIDTH_WRAP_CONTENT = (int) (176 + SHADOW_BOUNDS);
  private int HEIGHT_WRAP_CONTENT = (int) (96 + SHADOW_BOUNDS);
  private float DEFAULT_BUTTON_RADIUS = 5;
  private CharSequence DEFAULT_BUTTON_TEXT = "Button";
  private String DEFAULT_ELEVATION_COLOR = "#44000000";
  private float DEFAULT_BUTTON_STROKE_WIDTH = 0;
  private int DEFAULT_BUTTON_TEXT_COLOR = Color.BLACK;
  private float DEFAULT_BUTTON_TEXT_SIZE = 13;

  public StyledButton(Context context) {
    super(context);
  }

  public StyledButton(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public StyledButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int width;
    if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT) {
      width = WIDTH_WRAP_CONTENT;
    } else if ((getLayoutParams().width == ViewGroup.LayoutParams.MATCH_PARENT) || (getLayoutParams().width == ViewGroup.LayoutParams.MATCH_PARENT)) {
      width = MeasureSpec.getSize(widthMeasureSpec);
    } else
      width = getLayoutParams().width;

    int height;
    if (getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
      height = HEIGHT_WRAP_CONTENT;
    } else if ((getLayoutParams().height == ViewGroup.LayoutParams.MATCH_PARENT) || (getLayoutParams().height == ViewGroup.LayoutParams.MATCH_PARENT)) {
      height = MeasureSpec.getSize(heightMeasureSpec);
    } else
      height = getLayoutParams().height;


    setMeasuredDimension(width | MeasureSpec.EXACTLY, height | MeasureSpec.EXACTLY);
  }

  private void init(Context context, AttributeSet attributeSet) {

    TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.StyledButton);
    String buttonText = typedArray.getString(R.styleable.StyledButton_buttonText);
    String DEFAULT_BUTTON_COLOR = "#d6d7d7";
    int color = typedArray.getColor(R.styleable.StyledButton_buttonColor, Color.parseColor(DEFAULT_BUTTON_COLOR));
    DEFAULT_BUTTON_RADIUS = typedArray.getFloat(R.styleable.StyledButton_buttonRadius, DEFAULT_BUTTON_RADIUS);
    int strokeColor = typedArray.getColor(R.styleable.StyledButton_buttonStrokeColor, Color.parseColor(DEFAULT_BUTTON_COLOR));
    int buttonStyle = typedArray.getInt(R.styleable.StyledButton_buttonStyleType, 0);
    int buttonTextGravity = typedArray.getInt(R.styleable.StyledButton_buttonTextGravity, 4);
    int elevationColor = typedArray.getColor(R.styleable.StyledButton_buttonElevationColor, Color.parseColor(DEFAULT_ELEVATION_COLOR));
    DEFAULT_BUTTON_TEXT_COLOR = typedArray.getColor(R.styleable.StyledButton_buttonTextColor, DEFAULT_BUTTON_TEXT_COLOR);
    DEFAULT_BUTTON_STROKE_WIDTH = typedArray.getDimension(R.styleable.StyledButton_buttonStrokeWidth, DEFAULT_BUTTON_STROKE_WIDTH);
    DEFAULT_ELEVATION = typedArray.getFloat(R.styleable.StyledButton_buttonElevation, DEFAULT_ELEVATION);
    ON_TOUCHED_ELEVATION = DEFAULT_ELEVATION * 2;
    SHADOW_BOUNDS = ON_TOUCHED_ELEVATION * 2;
    DEFAULT_BUTTON_TEXT_SIZE = typedArray.getFloat(R.styleable.StyledButton_buttonTextSize, DEFAULT_BUTTON_TEXT_SIZE);
    typedArray.recycle();
    if (buttonText != null) {
      this.setText(buttonText);
    } else {
      this.setText(DEFAULT_BUTTON_TEXT.toString().toUpperCase());
    }
    switch (buttonTextGravity) {
      case 0:
        this.setGravity(Gravity.START);
        break;
      case 1:
        this.setGravity(Gravity.END);
        break;
      case 2:
        this.setGravity(Gravity.BOTTOM);
        break;
      case 3:
        this.setGravity(Gravity.TOP);
        break;
      case 4:
        this.setGravity(Gravity.CENTER);
        break;
    }
    this.setTextColor(DEFAULT_BUTTON_TEXT_COLOR);
    this.setTextSize(DEFAULT_BUTTON_TEXT_SIZE);
    paint = new Paint();
    paint.setAntiAlias(true);
    switch (buttonStyle) {
      case 0:
        paint.setStyle(Paint.Style.FILL);
        if (color != 0) {
          paint.setColor(color);
        } else {
          paint.setColor(Color.parseColor(DEFAULT_BUTTON_COLOR));
        }
        break;
      case 1:
        paint.setColor(strokeColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DEFAULT_BUTTON_STROKE_WIDTH);
        break;
    }
    if (elevationColor != 0) {
      paint.setShadowLayer(DEFAULT_ELEVATION, 0, DEFAULT_ELEVATION, elevationColor);
    } else {
      paint.setShadowLayer(DEFAULT_ELEVATION, 0, DEFAULT_ELEVATION, Color.parseColor(DEFAULT_ELEVATION_COLOR));
    }
    setLayerType(LAYER_TYPE_SOFTWARE, paint);

  }

  private RectF getRectF() {
    RectF rectF = new RectF();
    rectF.top = SHADOW_BOUNDS + DEFAULT_BUTTON_STROKE_WIDTH;
    rectF.left = SHADOW_BOUNDS + DEFAULT_BUTTON_STROKE_WIDTH;
    rectF.right = (getWidth() - SHADOW_BOUNDS - DEFAULT_BUTTON_STROKE_WIDTH);
    rectF.bottom = (getHeight() - SHADOW_BOUNDS - DEFAULT_BUTTON_STROKE_WIDTH);
    return rectF;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    canvas.drawRoundRect(getRectF(), DEFAULT_BUTTON_RADIUS, DEFAULT_BUTTON_RADIUS, paint);
    super.onDraw(canvas);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    super.onTouchEvent(event);
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN: {
        ValueAnimator animator = ValueAnimator.ofFloat(DEFAULT_ELEVATION, ON_TOUCHED_ELEVATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          @Override
          public void onAnimationUpdate(ValueAnimator animation) {
            ON_TOUCH_DOWN_VALUE = (float) animation.getAnimatedValue();
            paint.setShadowLayer(ON_TOUCH_DOWN_VALUE, 0, ON_TOUCH_DOWN_VALUE, Color.parseColor(DEFAULT_ELEVATION_COLOR));
            setLayerType(LAYER_TYPE_SOFTWARE, paint);
            postInvalidate();
          }
        });
        animator.setDuration(100);
        animator.start();
        break;
      }
      case MotionEvent.ACTION_UP: {
        postInvalidate();
        ValueAnimator animator = ValueAnimator.ofFloat(ON_TOUCHED_ELEVATION, DEFAULT_ELEVATION);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          @Override
          public void onAnimationUpdate(ValueAnimator animation) {
            ON_TOUCH_UP_VALUE = (float) animation.getAnimatedValue();
            paint.setShadowLayer(ON_TOUCH_UP_VALUE, 0, ON_TOUCH_UP_VALUE, Color.parseColor(DEFAULT_ELEVATION_COLOR));
            setLayerType(LAYER_TYPE_SOFTWARE, paint);
            postInvalidate();
          }
        });
        animator.setDuration(100);
        animator.start();
        break;
      }
    }
    return true;
  }
}
