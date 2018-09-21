package as.bwei.com.zhoukao3ayingyuan.presenter;

import as.bwei.com.zhoukao3ayingyuan.model.ShowModel;
import as.bwei.com.zhoukao3ayingyuan.view.ShowView;

/**
 * Created by HP on 2018/9/21.
 */

public class ShowPresenter {
    private ShowView showView;
    private ShowModel showModel;

    public ShowPresenter(ShowView showView) {
        showModel = new ShowModel();
        this.showView = showView;
    }

    public void show(){
        showModel.show(new ShowModel.ShowCallBack() {
            @Override
            public void failure(String msg) {
                showView.failure(msg);
            }

            @Override
            public void success(String msg) {
                showView.success(msg);
            }
        });
    }
}
