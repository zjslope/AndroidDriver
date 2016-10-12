package com.example.slope.androiddriver;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.slope.androiddriver.adapter.SuggestAdapter;
import com.example.slope.androiddriver.basicclass.Msg;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

public class SuggestActivity extends AppCompatActivity  implements SwipeBackActivityBase {


    ListView suggestListview;

    EditText inputSuggest;
    private SwipeBackActivityHelper swipeBackActivityHelper;
    Button suggestSend;
    SuggestAdapter adapter;
    List<Msg>msgList=new ArrayList<Msg>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);
        swipeBackActivityHelper = new SwipeBackActivityHelper(this);
        swipeBackActivityHelper.onActivityCreate();
        suggestListview= (ListView) findViewById(R.id.suggest_listview);
        inputSuggest= (EditText) findViewById(R.id.input_suggest);
        suggestSend= (Button) findViewById(R.id.suggest_send);
        initMsges();
        adapter=new SuggestAdapter(this,R.layout.suggest_item,msgList);
        suggestListview.setAdapter(adapter);
        suggestSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputSuggest.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    suggestListview.setSelection(msgList.size());
                    inputSuggest.setText("");
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        swipeBackActivityHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && swipeBackActivityHelper != null)
            return swipeBackActivityHelper.findViewById(id);
        return v;
    }

    private void initMsges(){
        Msg msg1=new Msg("您有什么建议可以发给我",Msg.TYPE_RECEIVED);
        msgList.add(msg1);

    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackActivityHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        me.imid.swipebacklayout.lib.Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
}
