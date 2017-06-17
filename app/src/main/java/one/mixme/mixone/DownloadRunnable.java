package one.mixme.mixone;

/**
 * Created by laptop on 17.06.2017.
 */

public class DownloadRunnable implements Runnable {
    private long status;

    public void setStatus(long status) {
        this.status = status;
    }

    public long getStatus() {
        return status;
    }

    @Override
    public void run() {
        ;
    }
}
