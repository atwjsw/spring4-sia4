package spittr.web;

/**
 * Created by wenda on 8/11/2017.
 */
public class SpittleNotFoundException extends RuntimeException{
    private long spittleId;
    public SpittleNotFoundException(long spittleId) {
        this.spittleId = spittleId;
    }
    public long getSpittleId() {
        return spittleId;
    }
}
