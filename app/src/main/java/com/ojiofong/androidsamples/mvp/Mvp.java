package com.ojiofong.androidsamples.mvp;

import com.ojiofong.androidsamples.R;

/**
 * Created by ojiofong on 4/29/17.
 */

public class Mvp {

    /**
     * MVP Interfaces/classes below
     */

    // Model .......................................
    public interface IModel {
        int getFirstColor();

        int getSecondColor();
    }

    public static class ColorModel implements IModel {
        int firstColor = R.color.colorAccent;
        int secondColor = R.color.colorPrimary;

        public ColorModel() {
        }

        @Override
        public int getFirstColor() {
            return firstColor;
        }

        @Override
        public int getSecondColor() {
            return secondColor;
        }
    }

    // View .......................................
    public interface IView {
        void onChangeColor(int color);
    }

    // Presenter .......................................
    public interface IPresenter {
        void doChangeColor();
    }

    public static class PresenterImpl implements IPresenter {
        boolean flag = false;
        IView iView;
        ColorModel colorModel;

        public PresenterImpl(IView iView) {
            this.iView = iView;
            colorModel = new ColorModel();
        }

        @Override
        public void doChangeColor() {
            int color = flag ? colorModel.getFirstColor() : colorModel.getSecondColor();
//            view.setBackgroundColor(ContextCompat.getColor(this, color));
            flag = !flag;
            iView.onChangeColor(color);
        }
    }

}
