package at.liftme;

import android.content.ClipData;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class NewTrainingActivity extends AppCompatActivity {

    List<String> exercisesList = new ArrayList<String>();

    class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
        //Drawable enterShape = getResources().getDrawable(R.drawable.roundedcorner_training);
        //Drawable normalShape = getResources().getDrawable(R.drawable.roundedcorner_textview);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            boolean isInTraining = false;
            View view = (View) event.getLocalState();

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setBackgroundDrawable(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setBackgroundDrawable(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup

                    ViewGroup owner = (ViewGroup) view.getParent();
                    owner.removeView(view);
                    FrameLayout container = (FrameLayout) v;
                    container.addView(view);
                    view.setVisibility(View.VISIBLE);
                    isInTraining = true;
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackgroundDrawable(normalShape);
                    if(!isInTraining) {
                        view.setVisibility(View.VISIBLE);
                    }
                default:
                    break;
            }



            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_training);

        initTextViews(R.id.textView_exercise1);
        initTextViews(R.id.textView_exercise2);
        initTextViews(R.id.textView_exercise3);

        initTextViews(R.id.textView_weight1);
        initTextViews(R.id.textView_weight2);
        initTextViews(R.id.textView_weight3);
        initTextViews(R.id.textView_weight4);
        initTextViews(R.id.textView_weight5);
        initTextViews(R.id.textView_weight6);

        initTextViews(R.id.textView_repetition5);
        initTextViews(R.id.textView_repetition7);
        initTextViews(R.id.textView_repetition10);
        initTextViews(R.id.textView_repetition12);
        initTextViews(R.id.textView_repetition15);
        initTextViews(R.id.textView_repetition20);

        initTextViews(R.id.textView_pause1);
        initTextViews(R.id.textView_pause2);
        initTextViews(R.id.textView_pause3);

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayout_training);
        frameLayout.setOnDragListener(new MyDragListener());

        Button finishButton = (Button) findViewById(R.id.finishButton);
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initTextViews(int resId) {
        TextView tv = (TextView) findViewById(resId);
        tv.setOnTouchListener(new MyTouchListener());
    }
}
