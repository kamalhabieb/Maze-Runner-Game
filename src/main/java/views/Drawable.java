package views;


import javafx.scene.image.Image;

public abstract class Drawable {
    private int srcX;
    private int srcY;
    protected double destinationX;
    protected double destinationY;
    private boolean animated = false;
    private int animationWidth;
    private int srcWidth;
    private int srcHeight;
    private int destinationHeight;
    private int animationIndex = 0;
    protected int imageWidth;
    private boolean cyclicAnimation = true;

    public int getSrcWidth() {
        return srcWidth;
    }

    public void setSrcWidth(final int srcWidth) {
        this.srcWidth = srcWidth;
    }

    public int getSrcHeight() {
        return srcHeight;
    }

    public void setSrcHeight(final int srcHeight) {
        this.srcHeight = srcHeight;
    }

    public int getDestinationHeight() {
        return destinationHeight;
    }

    public void setDestinationHeight(final int destinationHeight) {
        this.destinationHeight = destinationHeight;
    }

    public int getDestinationWidth() {
        return destinationWidth;
    }

    public void setDestinationWidth(final int destinationWidth) {
        this.destinationWidth = destinationWidth;
    }

    private int destinationWidth;

    public abstract Image getImage();

    public int getSrcX() {
        return this.srcX;
    }

    public void setSrcX(int srcX) {
        this.srcX = srcX;
    }

    public int getSrcY() {
        return this.srcY;
    }

    public void setSrcY(int srcY) {
        this.srcY = srcY;
    }

    public double getDestinationX() {
        return this.destinationX;
    }

    public void setDestinationX(int destinationX) {
        this.destinationX = destinationX;

    }

    public double getDestinationY() {
        return this.destinationY;
    }

    public void setDestinationY(int destinationY) {
        this.destinationY = destinationY;
    }

    public void setAnimated(boolean state, int width) {
        this.animated = state;
        this.animationWidth = width;
    }

    public boolean isCyclicAnimation() {
        return cyclicAnimation;
    }

    public void setCyclicAnimation(final boolean cyclicAnimation) {
        this.cyclicAnimation = cyclicAnimation;
    }

    public boolean isAnimated() {
        return animated;
    }

    public int getAnimationWidth() {
        if (this.animated) {
            return this.animationWidth;
        } else {
            return 0;
        }
    }

    protected void setCoordinates() {
        animationIndex++;
        if (animationIndex == 15) {
            int newX = (getSrcX() + getAnimationWidth());
            if (newX + getAnimationWidth() >= imageWidth) {
                if(cyclicAnimation) {
                    setSrcX(0);
                }else{
                    finishAnimation();
                }
            } else {
                setSrcX(newX);
            }
            animationIndex = 0;
        }
    }

    public void finishAnimation() {

    }

}
