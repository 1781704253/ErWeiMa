package zhangyanran201800907.bwie.com.day08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MainActivity extends AppCompatActivity implements AccountView {

    private AccountP accountP;
    @ViewInject(R.id.edit_name)
    private EditText edit_name;
    @ViewInject(R.id.edit_pwd)
    private EditText edit_pwd;
    private String mName,mPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建P层
        accountP = new AccountP(this);
        x.view().inject(this);
    }
    @Event({R.id.but_login,R.id.but_reg})
    private void butClick(View view){
        readNameAndPassword();
        switch (view.getId()){
            case R.id.but_reg:
                accountP.reg(mName,mPwd);
                break;
            case R.id.but_login:
                accountP.login(mName,mPwd);
                break;
        }
    }

    private void readNameAndPassword() {
        mName = edit_name.getText().toString();
        mPwd = edit_pwd.getText().toString();
    }

    @Override
    public void onSuccess(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onError(String errorMsg) {
        Toast.makeText(MainActivity.this,errorMsg,Toast.LENGTH_SHORT).show();
    }
}
