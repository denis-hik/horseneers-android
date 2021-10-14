package denishik.horsemeers.ru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Switch;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CompoundButton;
import android.graphics.Typeface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity {
	
	private String error = "";
	private  String urlGame = "https://projects.denishik.ru/horseneers";
	
	private LinearLayout linear5;
	private LinearLayout linear6;
	private TextView textview1;
	private ImageView imageview5;
	private ImageView imageview4;
	private Switch switch1;
	private Switch switch3;
	
	private RequestNetwork inet;
	private RequestNetwork.RequestListener _inet_request_listener;
	private Intent i = new Intent();
	private SharedPreferences file;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear5 = findViewById(R.id.linear5);
		linear6 = findViewById(R.id.linear6);
		textview1 = findViewById(R.id.textview1);
		imageview5 = findViewById(R.id.imageview5);
		imageview4 = findViewById(R.id.imageview4);
		switch1 = findViewById(R.id.switch1);
		switch3 = findViewById(R.id.switch3);
		inet = new RequestNetwork(this);
		file = getSharedPreferences("filr", Activity.MODE_PRIVATE);
		
		imageview5.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				
				return true;
			}
		});
		
		imageview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_onErrorView(error);
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setAction(Intent.ACTION_VIEW);
				i.putExtra("url", urlGame);
				i.putExtra("opt", file.getString("opt", ""));
				i.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(i);
			}
		});
		
		switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					file.edit().putString("opt", "true").commit();
				}
				else {
					file.edit().remove("opt").commit();
				}
			}
		});
		
		_inet_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				imageview4.setVisibility(View.VISIBLE);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				imageview5.setVisibility(View.VISIBLE);
				error = _message;
			}
		};
	}
	
	private void initializeLogic() {
		imageview4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFFFFE0B2));
		imageview5.setColorFilter(0xFFF44336, PorterDuff.Mode.MULTIPLY);
		inet.startRequestNetwork(RequestNetworkController.GET, urlGame, "A", _inet_request_listener);
		switch1.setChecked(file.contains("opt"));
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/stick.ttf"), 1);
	}
	
	public void _RadiusGradient4(final View _view, final String _color1, final String _color2, final double _lt, final double _rt, final double _rb, final double _lb, final double _border, final String _color3) {
		int[] colors = { Color.parseColor(_color1), Color.parseColor(_color2) }; android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colors);
		gd.setCornerRadii(new float[]{(int)_lt,(int)_lt,(int)_rt,(int)_rt,(int)_rb,(int)_rb,(int)_lb,(int)_lb});
		gd.setStroke((int) _border, Color.parseColor(_color3));
		_view.setBackground(gd);
	}
	
	
	public void _onErrorView(final String _text) {
		final com.google.android.material.bottomsheet.BottomSheetDialog dialog = new com.google.android.material.bottomsheet.BottomSheetDialog(MainActivity.this); View lay = getLayoutInflater().inflate(R.layout.error_view, null); dialog.setContentView(lay);
		final LinearLayout linear1 = (LinearLayout)lay.findViewById(R.id.linear1); 
		
		final LinearLayout linear3 = (LinearLayout)lay.findViewById(R.id.linear3); 
		
		final TextView textView = lay.findViewById(R.id.textview1); 
		dialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
		dialog.show();
		android.graphics.drawable.GradientDrawable wd = new android.graphics.drawable.GradientDrawable(); wd.setColor(Color.TRANSPARENT); wd.setCornerRadius((int)10f);
		linear1.setBackground(wd);
		textView.setText(_text);
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}