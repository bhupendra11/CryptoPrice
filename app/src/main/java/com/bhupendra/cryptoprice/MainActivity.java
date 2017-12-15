package com.bhupendra.cryptoprice;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bhupendra.cryptoprice.model.ZebPayAPIModel;
import com.bhupendra.cryptoprice.rest.ZebPayAPIClient;
import com.bhupendra.cryptoprice.rest.ZebPayAPIInterface;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.marketTextView)
     TextView marketTextView;

    @BindView(R.id.buyTextView)
     TextView buyTextView;

    @BindView(R.id.sellTextView)
     TextView sellTextView;

    @BindView(R.id.currencyTextView)
     TextView currencyTextView;

    @BindView(R.id.volumeTextView)
     TextView volumeTextView;

    @BindView(R.id.swipe_refresh_layout)
     SwipeRefreshLayout swipeRefreshLayout;

    private static ZebPayAPIModel mZebPayAPIModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        loadZebpayData(this);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadZebpayData(getApplicationContext());
               // swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private  void loadZebpayData(final Context context){
        ZebPayAPIInterface zebPayAPIservice = ZebPayAPIClient.getClient().create(ZebPayAPIInterface.class);

        Call<ZebPayAPIModel> call = zebPayAPIservice.getZebPayTicker();
        call.enqueue(new Callback<ZebPayAPIModel>() {
            @Override
            public void onResponse(Call<ZebPayAPIModel> call, Response<ZebPayAPIModel> response) {
                int statusCode = response.code();
                ZebPayAPIModel zebPayAPIModel = response.body();
                if(zebPayAPIModel!= null) {
                    Log.d("TAG",zebPayAPIModel.getBuy() + zebPayAPIModel.getMarket()+"");
                  //  mZebPayAPIModel = zebPayAPIModel;
                    marketTextView.setText(zebPayAPIModel.getMarket() + "");
                    buyTextView.setText(zebPayAPIModel.getBuy() + "");
                    sellTextView.setText(zebPayAPIModel.getSell() + "");
                    currencyTextView.setText(zebPayAPIModel.getCurrency());
                    volumeTextView.setText(zebPayAPIModel.getVolume() + "");
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ZebPayAPIModel> call, Throwable t) {
                Toast.makeText(context, "API Call failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
