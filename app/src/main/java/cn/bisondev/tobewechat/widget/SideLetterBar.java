package cn.bisondev.tobewechat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import cn.bisondev.tobewechat.R;

/**
 * 侧边选择栏的自定义View
 * Created by Bison on 2017/5/13.
 */

public class SideLetterBar extends View {

    private static final String[] b = {"↑", "☆", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private int choose = -1;
    private Paint paint = new Paint();
    private boolean showBg = false;
    private OnLetterChangedListener onLetterChangedListener;
    private TextView overlay;

    public SideLetterBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideLetterBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideLetterBar(Context context) {
        super(context);
    }

    /**
     * 设置悬浮的textview
     * @param overlay
     */
    public void setOverlay(TextView overlay){
        this.overlay = overlay;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showBg) {
            canvas.drawColor(getResources().getColor(R.color.side_letter_bar_background));
        }

        //获取自定义View的宽高
        int height = getHeight();
        int width = getWidth();

        //得出每一个字母所占的高度
        int singleHeight = height / b.length;

        for (int i = 0; i < b.length; i++) {
            //设置侧边栏字体的大小
            paint.setTextSize(getResources().getDimension(R.dimen.SideLetterBarTextSize));
            //设置字体的颜色
            paint.setColor(getResources().getColor(R.color.less_black));
            paint.setAntiAlias(true);

            //如果该字母为选中的字母
            if (i == choose) {
                paint.setColor(getResources().getColor(R.color.gray_deep));
                paint.setFakeBoldText(true);  //选中就加粗
            }

            //得出要画的字母的左上角的x点
            float xPos = width / 2 - paint.measureText(b[i]) / 2;   //布局重点偏左二分之一的字母大小
            //得出要画的字母的左上角的y起始点，y点的值会动
            float yPos = singleHeight * i + singleHeight;

            //画字母
            canvas.drawText(b[i], xPos, yPos, paint);
            //重置画笔
            paint.reset();
        }

    }

    /**
     * 调度触摸事件
     * @param event
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //获取Action
        final int action = event.getAction();
        //获取触摸事件的Y点
        final float y = event.getY();
        //先保存旧的选中的字母
        final int oldChoose = choose;
        //把选中字母变化的监听类传进来
        final OnLetterChangedListener listener = onLetterChangedListener;
        //获取当前触摸事件中选定的点的位置
        final int index = (int) (y / getHeight() * b.length);

        //根据不同action分别进行不同的处理
        switch (action) {
            //当前用户开始触摸
            case MotionEvent.ACTION_DOWN:
                //显示背景
                showBg = true;
                //如果旧选中的字母与触摸事件中的字母不相同
                if (oldChoose != index && listener != null) {
                    //index的值在有效范围内
                    if (index >= 0 && index < b.length) {
                        //触发字母变动的监听类，移动选中的字母
                        listener.onLetterChanged(b[index]);
                        //替换旧的选中的值
                        choose = index;
                        invalidate();
                        //触摸事件的过程中，如果隐藏的提示框存在，则提示当前选中的字母
                        if (overlay != null){
                            overlay.setVisibility(VISIBLE);
                            overlay.setText(b[index]);
                        }
                    }
                }
                break;
            //当前用户正在移动
            case MotionEvent.ACTION_MOVE:
                if (oldChoose != index && listener != null) {
                    if (index >= 0 && index < b.length) {
                        listener.onLetterChanged(b[index]);
                        choose = index;
                        invalidate();
                        if (overlay != null){
                            overlay.setVisibility(VISIBLE);
                            overlay.setText(b[index]);
                        }
                    }
                }
                break;
            //当前用户抬起了手指
            case MotionEvent.ACTION_UP:
                showBg = false;
                choose = -1;
                invalidate();
                if (overlay != null){
                    overlay.setVisibility(GONE);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnLetterChangedListener(OnLetterChangedListener onLetterChangedListener) {
        this.onLetterChangedListener = onLetterChangedListener;
    }

    public interface OnLetterChangedListener {
        void onLetterChanged(String letter);
    }

}
