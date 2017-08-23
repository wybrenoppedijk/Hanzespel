package wybren_erik.hanzespel.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import wybren_erik.hanzespel.City;
import wybren_erik.hanzespel.R;
import wybren_erik.hanzespel.dialog.ConfirmDialog;
import wybren_erik.hanzespel.interfaces.BoatListener;
import wybren_erik.hanzespel.interfaces.OnDestinationClickHandler;
import wybren_erik.hanzespel.model.Boat;

public class MapsCustomView extends LinearLayout implements View.OnClickListener {
    private static OnDestinationClickHandler onDestinationClickHandler;
    Paint locationText;
    Paint travelTimeText;
    Paint border;
    City travelDestination;
    int travelTime;
    Boat boat;
    boolean enabled = true;

    public MapsCustomView(Context context) {
        super(context);
        init();
    }

    public MapsCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MapsCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public static void addListener(OnDestinationClickHandler listener) {
        onDestinationClickHandler = listener;
    }

    public void init() {
        LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.view_custom_maps, this);
        border = new Paint();
        locationText = new Paint();
        travelTimeText = new Paint();
        setOnClickListener(this);

        border.setColor(Color.BLACK);
        border.setStyle(Paint.Style.STROKE);
        border.setStrokeWidth(150);

        this.setBackgroundColor(Color.WHITE);
        locationText.setColor(Color.BLACK);
        locationText.setTextSize(22);
        locationText.setTypeface(Typeface.create("cursive", Typeface.BOLD));
        locationText.setTextAlign(Paint.Align.CENTER);

        travelTimeText.setColor(Color.GRAY);
        travelTimeText.setTextSize(18);
        travelTimeText.setTypeface(Typeface.create("cursive", Typeface.BOLD));
        travelTimeText.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        boat = Boat.getInstance();

        enabled = Boat.isInDock() && !boat.getLocation().toString().equals(travelDestination.toString());
        if (enabled) {
            setBackgroundResource(R.drawable.rounded_rectangle);
        } else {
            setBackgroundResource(R.drawable.rounded_rectangle_disabled);
        }

        canvas.drawText(travelDestination.toString(), canvas.getWidth() / 2, canvas.getHeight() / 4, locationText);
        canvas.drawText(travelTime + "s", canvas.getWidth() / 2, canvas.getHeight() / 4 + 50, travelTimeText);

    }

    public void setDestination(City location) {
        this.travelDestination = location;
        invalidate();
    }

    public void setTravelTime(int travelTime) {
        this.travelTime = travelTime;
        invalidate();
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        invalidate();
    }

    @Override
    public void onClick(View v) {
        if (enabled) {
            if (Boat.isInDock()) {
                if (!boat.getLocation().equals(travelDestination)) {
                    onDestinationClickHandler.onClickListener(travelDestination);
                    invalidate();
                }
            }
        }

    }
}
