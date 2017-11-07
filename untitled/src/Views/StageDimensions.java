package Views;

public enum StageDimensions {
    WIDTH(1000), HEIGHT(600);

    private int size;

    StageDimensions(int size){
        this.size = size;
    }

    public int size(){
        return size;
    }
}
