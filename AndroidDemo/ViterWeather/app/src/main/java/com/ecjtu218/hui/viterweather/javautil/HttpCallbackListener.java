package com.ecjtu218.hui.viterweather.javautil;

public interface HttpCallbackListener {

	void onFinish(String response);

	void onError(Exception e);

}
