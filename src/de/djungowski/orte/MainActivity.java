package de.djungowski.orte;

import java.util.Random;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public Integer getRandomNumber()
	{
		final RandomNumberGenerator random = new RandomNumberGenerator(new Random());
		return random.getNext();
	}
	
	public void generateNewRandomNumber(View view)
	{
		TextView text = (TextView)findViewById(R.id.random_number);
		updateTextWithNewRandomNumber(text); 
	}
	
	public void setRandomNumberOnStartup(View view)
	{
		TextView text = (TextView)view.findViewById(R.id.random_number);
		updateTextWithNewRandomNumber(text);
	}
	
	public void setRandomNumberOnStartup(View view, CharSequence randomNumber)
	{
		final TextView textView = (TextView)view.findViewById(R.id.random_number);
		textView.setText(randomNumber.toString());
	}
	
	public void updateTextWithNewRandomNumber(TextView text)
	{
		text.setText(Integer.toString(getRandomNumber()));
	}
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		View rootView;
		
		public PlaceholderFragment() {
		}
		
		@Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			final TextView text = (TextView)rootView.findViewById(R.id.random_number);
			outState.putCharSequence("foo", text.getText());
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			rootView = inflater.inflate(R.layout.fragment_main, container,
					false);			

			Activity activity = getActivity();

			if (activity instanceof MainActivity) {
				MainActivity mainActivity = (MainActivity) activity;
				
				if (savedInstanceState != null) {
					mainActivity.setRandomNumberOnStartup(rootView, savedInstanceState.getCharSequence("foo"));
				} else {
					mainActivity.setRandomNumberOnStartup(rootView);
				}
			}
			return rootView;
		}		
	}

}
