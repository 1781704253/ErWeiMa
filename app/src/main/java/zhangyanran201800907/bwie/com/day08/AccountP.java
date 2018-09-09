package zhangyanran201800907.bwie.com.day08;

/**
 * Created by 匹诺曹 on 2018/9/7.
 */

public class AccountP {
    private AccountView view;
    private AccountM model;

    public AccountP(AccountView view) {
        this.view = view;
        model = new AccountM();
    }

    public void reg(String mName, String mPwd) {
        model.rag(mName,mPwd,new AccountM.AccountCallack(){

            @Override
            public void onSuccess(String msg) {
                view.onSuccess(msg);
            }

            @Override
            public void onError(String errorMsg) {
                view.onError("注册失败");
            }
        });
    }

    public void login(String mName, String mPwd) {
        model.login(mName,mPwd,new AccountM.AccountCallack(){

            @Override
            public void onSuccess(String msg) {
                view.onSuccess(msg);
            }

            @Override
            public void onError(String errorMsg) {
                view.onError("登录失败");
            }
        });
    }
}
