package gurug.student.activity;

/**
 * Created by moin on 12/10/16.
 */

import android.graphics.drawable.GradientDrawable;

public class CustomizeDrawable extends GradientDrawable {

    public CustomizeDrawable(int pStartColor, int pCenterColor, int pEndColor, int pStrokeWidth, int pStrokeColor, float cornerRadius) {
        super(Orientation.BOTTOM_TOP,new int[]{pStartColor,pCenterColor,pEndColor});
        setStroke(pStrokeWidth,pStrokeColor);
        setShape(GradientDrawable.RECTANGLE);
        setCornerRadius(cornerRadius);
    }
}
